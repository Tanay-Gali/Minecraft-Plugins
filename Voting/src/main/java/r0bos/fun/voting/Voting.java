package r0bos.fun.voting;

import org.bukkit.*;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;


public final class Voting extends JavaPlugin implements Listener {

    Random random = new Random();

    static boolean meetingstatus;

    static ItemStack book;
    static HashMap<Player, ItemStack[]> inventories = new HashMap<>();


    static HashMap<Player, Integer> votes = new HashMap<>();

    static Player pressed;


    static List<Player> voterList = new ArrayList<>();






    @Override
    public void onEnable() {


        book = new ItemStack(Material.BOOK,1);
        ItemMeta meta = book.getItemMeta();
        assert meta != null;
        meta.setItemName(ChatColor.AQUA + "Voting Book");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "Be careful who you vote for...");
        meta.setLore(lore);


        book.setItemMeta(meta);


        ButtonCommand buttonCommand = new ButtonCommand();

        getServer().getPluginManager().registerEvents(this,this);
        getServer().getPluginManager().registerEvents(buttonCommand,this);


        getCommand("button").setExecutor(buttonCommand);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent e){
        if (meetingstatus){
            e.setCancelled(true);
        }
    }





    public static void startMeeting(){

        meetingstatus = true;
        votes.clear();

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"tick freeze");






        for (Player p : Bukkit.getOnlinePlayers()){
            inventories.put(p,p.getInventory().getContents());
            if (p.getGameMode().equals(GameMode.SURVIVAL)){
                voterList.add(p);
            }
            p.getInventory().clear();

            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, PotionEffect.INFINITE_DURATION, 10));
            p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, PotionEffect.INFINITE_DURATION, 255));


            p.getInventory().addItem(book);
        }
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent e){
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if (e.getPlayer().getInventory().getItemInMainHand().equals(book)){
                Inventory inventory = Bukkit.createInventory(e.getPlayer(),9,ChatColor.RED +ChatColor.BOLD.toString() + "Voting");

                for (int i = 0; i<voterList.size(); i++){

                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD);


                    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

                    skullMeta.setOwnerProfile((voterList.get(i)).getPlayerProfile());

                    skull.setItemMeta(skullMeta);
                    inventory.setItem(i,skull);

                }
                e.getPlayer().openInventory(inventory);


            }
        }
    }
    @EventHandler
    public void guiInteract(InventoryClickEvent e){

        if (e.getView().getTitle().equals(ChatColor.RED +ChatColor.BOLD.toString() + "Voting")){
            if (e.getInventory().getItem(e.getRawSlot()) != null){
                e.setCancelled(true);
                ItemStack skull = e.getInventory().getItem(e.getRawSlot());
                SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                votes.put(Bukkit.getPlayerExact(skullMeta.getOwnerProfile().getName()), votes.getOrDefault(Bukkit.getPlayerExact(skullMeta.getOwnerProfile().getName()),0)+1);
                ((Player) e.getWhoClicked()).getInventory().clear();
                e.getWhoClicked().closeInventory();
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,SoundCategory.BLOCKS,1,1f);

                voterList.remove((Player) e.getWhoClicked());
                if (voterList.isEmpty()){
                    meetingEnd();
                }
            }
        }

    }

    public void meetingEnd(){
        for (Player p : Bukkit.getOnlinePlayers()){
            p.getInventory().setContents(inventories.get(p));
            
            p.removePotionEffect(PotionEffectType.BLINDNESS);
            p.removePotionEffect(PotionEffectType.RESISTANCE);
        }

        meetingstatus = false;
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"tick unfreeze");




        ArrayList<Player> ties = new ArrayList<>();
        Player votedPlayer = null;
        int maxvotes = 0;
        for (Map.Entry<Player, Integer> entry : votes.entrySet()) {
            if (maxvotes<entry.getValue()){
                maxvotes = entry.getValue();
                votedPlayer = entry.getKey();
                ties.clear();
            } else if (maxvotes == entry.getValue()) {
                ties.add(votedPlayer);
                ties.add(entry.getKey());
            }
        }
        if (!ties.isEmpty()){
            votedPlayer = ties.get(random.nextInt(ties.size()));
        }

        if (pressed.equals(votedPlayer)){
            pressed.getAttribute(Attribute.MAX_HEALTH).setBaseValue(pressed.getAttribute(Attribute.MAX_HEALTH).getBaseValue()-8);

            if (pressed.getAttribute(Attribute.MAX_HEALTH).getBaseValue()<=0){
                pressed.setGameMode(GameMode.SPECTATOR);
            }

        } else {
            votedPlayer.getAttribute(Attribute.MAX_HEALTH).setBaseValue(votedPlayer.getAttribute(Attribute.MAX_HEALTH).getBaseValue()-4);
            PotionEffectType potionEffectType = PotionEffectType.values()[random.nextInt(PotionEffectType.values().length)];

            if (votedPlayer.getAttribute(Attribute.MAX_HEALTH).getBaseValue()<=0){
                votedPlayer.setGameMode(GameMode.SPECTATOR);
            }

            pressed.getAttribute(Attribute.MAX_HEALTH).setBaseValue(pressed.getAttribute(Attribute.MAX_HEALTH).getBaseValue()+2);
            votedPlayer.addPotionEffect(new PotionEffect(potionEffectType,PotionEffect.INFINITE_DURATION,1));
        }

        ButtonCommand.button.getBlock().setType(Material.STONE_BUTTON);

    }
    @EventHandler
    public void death(PlayerDeathEvent e){
        e.getEntity().getAttribute(Attribute.MAX_HEALTH).setBaseValue(e.getEntity().getAttribute(Attribute.MAX_HEALTH).getBaseValue()-2);

        if (e.getEntity().getAttribute(Attribute.MAX_HEALTH).getBaseValue()<=0){
            e.getEntity().setGameMode(GameMode.SPECTATOR);
        }
    }
}
