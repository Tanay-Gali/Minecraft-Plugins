package fun.bedwars;


import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.Objects;

public class PlayerInteract implements Listener {

    Scoreboard board;
    Score redbed;
    Score bluebed;
    Score greenbed;
    Score yellowbed;

    Objective obj;
    @EventHandler
    public void PlayerInteraction(PlayerInteractEvent e) {
        if (e.getItem() != null){
            if (e.getItem().getType().equals(Material.FIRE_CHARGE)) {
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (!(e.getPlayer().hasPotionEffect(PotionEffectType.SLOW))) {
                        Fireball fireball = e.getPlayer().getWorld().spawn(e.getPlayer().getEyeLocation(), Fireball.class);
                        fireball.setYield(4);
                        fireball.setVelocity(e.getPlayer().getVelocity());
                        fireball.setShooter(e.getPlayer());
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 0));
                        if (e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.FIRE_CHARGE)) {
                            e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                        } else {
                            e.getPlayer().getInventory().getItemInOffHand().setAmount(e.getPlayer().getInventory().getItemInOffHand().getAmount() - 1);
                        }

                    }


                }

            }
        }

    }
    @EventHandler
    public void onEnable(PluginEnableEvent e){
        char checkmark = '✔';
        board = Bukkit.getScoreboardManager().getNewScoreboard();
        board.registerNewTeam("red");
        board.registerNewTeam("blue");
        board.registerNewTeam("green");
        board.registerNewTeam("yellow");



        obj = board.registerNewObjective("bedwarsboard","dummy");


        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.YELLOW+ "      Bedwars     ");

        Calendar cal = Calendar.getInstance();
        Score date = obj.getScore(ChatColor.GRAY + String.valueOf(cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR)));
        date.setScore(8);

        Score blank = obj.getScore(" ");
        blank.setScore(7);
        Team redbedteam = board.registerNewTeam("redbedteam");
        redbedteam.addEntry(ChatColor.WHITE.toString());
        redbedteam.setPrefix(ChatColor.RED + "Red: ");
        redbedteam.setSuffix(ChatColor.GREEN.toString() + checkmark);

        redbed = obj.getScore(ChatColor.WHITE.toString());
        redbed.setScore(6);


        Team bluebedteam = board.registerNewTeam("bluebedteam");
        bluebedteam.addEntry(ChatColor.RED.toString());
        bluebedteam.setPrefix(ChatColor.BLUE + "Blue: ");
        bluebedteam.setSuffix(ChatColor.GREEN.toString() + checkmark);
        bluebed = obj.getScore(ChatColor.RED.toString());
        bluebed.setScore(5);



        Team greenbedteam = board.registerNewTeam("greenbedteam");
        greenbedteam.addEntry(ChatColor.AQUA.toString());
        greenbedteam.setPrefix(ChatColor.GREEN + "Green: ");
        greenbedteam.setSuffix(ChatColor.GREEN.toString() + checkmark);
        greenbed = obj.getScore(ChatColor.AQUA.toString());
        greenbed.setScore(4);



        Team yellowbedteam = board.registerNewTeam("yellowbedteam");
        yellowbedteam.addEntry(ChatColor.GRAY.toString());
        yellowbedteam.setPrefix(ChatColor.YELLOW + "Yellow: ");
        yellowbedteam.setSuffix(ChatColor.GREEN.toString() + checkmark);
        yellowbed = obj.getScore(ChatColor.GRAY.toString());
        yellowbed.setScore(3);


        Score blank1 = obj.getScore(" ");
        blank1.setScore(2);

        Score ip = obj.getScore(ChatColor.AQUA + "KractiDumb.minehut.gg");
        ip.setScore(1);




    }
    @EventHandler
    public void CommandRun(PlayerCommandPreprocessEvent e){
        String commands = e.getMessage();

        if (commands.equals("/team join red")){
            e.getPlayer().setDisplayName(ChatColor.RED + e.getPlayer().getName());
            e.getPlayer().setPlayerListName(ChatColor.RED + e.getPlayer().getName());
            board.getTeam("red").addEntry(e.getPlayer().getName());
            e.getPlayer().setScoreboard(board);
        } else if (commands.equals("/team join blue")) {
            e.getPlayer().setDisplayName(ChatColor.BLUE + e.getPlayer().getName());
            e.getPlayer().setPlayerListName(ChatColor.BLUE + e.getPlayer().getName());
            board.getTeam("blue").addEntry(e.getPlayer().getName());
            e.getPlayer().setScoreboard(board);
        } else if (commands.equals("/team join green")) {
            e.getPlayer().setDisplayName(ChatColor.GREEN + e.getPlayer().getName());
            e.getPlayer().setPlayerListName(ChatColor.GREEN + e.getPlayer().getName());
            board.getTeam("green").addEntry(e.getPlayer().getName());
            e.getPlayer().setScoreboard(board);
        } else if (commands.equals("/team join yellow")) {
            e.getPlayer().setDisplayName(ChatColor.YELLOW + e.getPlayer().getName());
            e.getPlayer().setPlayerListName(ChatColor.YELLOW + e.getPlayer().getName());
            board.getTeam("yellow").addEntry(e.getPlayer().getName());
            e.getPlayer().setScoreboard(board);
        }
        if (commands.startsWith("/team leave")){
            if (board.getTeam("red").hasEntry(e.getPlayer().getName())){
                board.getTeam("red").removeEntry(e.getPlayer().getName());

            } else if (board.getTeam("blue").hasEntry(e.getPlayer().getName())) {
                board.getTeam("blue").removeEntry(e.getPlayer().getName());

            } else if (board.getTeam("green").hasEntry(e.getPlayer().getName())) {
                board.getTeam("green").removeEntry(e.getPlayer().getName());

            } else if (board.getTeam("yellow").hasEntry(e.getPlayer().getName())) {
                board.getTeam("yellow").removeEntry(e.getPlayer().getName());

            }
            e.getPlayer().getScoreboard().getTeam("redbedteam").setSuffix(ChatColor.GREEN + "✔");
            e.getPlayer().getScoreboard().getTeam("bluebedteam").setSuffix(ChatColor.GREEN + "✔");
            e.getPlayer().getScoreboard().getTeam("greenbedteam").setSuffix(ChatColor.GREEN + "✔");
            e.getPlayer().getScoreboard().getTeam("yellowbedteam").setSuffix(ChatColor.GREEN + "✔");
        }


    }




    @EventHandler
    public void BlockBreak(BlockBreakEvent e){

        if (e.getBlock().getType().equals(Material.RED_BED)){
            for (Player p : Bukkit.getOnlinePlayers()){
                if (Objects.requireNonNull(e.getPlayer().getScoreboard().getTeam("red")).hasEntry(p.getName())){
                    p.sendTitle(ChatColor.RED + "" +ChatColor.BOLD+ "Bed Broken!",ChatColor.RED + "You cant respawn anymore",10,30,10);
                }
                e.getPlayer().getScoreboard().getTeam("redbedteam").setSuffix(ChatColor.RED + "✘");
                p.sendMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Red bed has been broken by" + " " + e.getPlayer().getDisplayName());
            }
            e.setDropItems(false);

        } else if (e.getBlock().getType().equals(Material.BLUE_BED)) {
            for (Player p : Bukkit.getOnlinePlayers()){
                if (Objects.requireNonNull(e.getPlayer().getScoreboard().getTeam("blue")).hasEntry(p.getName())){
                    p.sendTitle(ChatColor.RED + "" +ChatColor.BOLD+ "Bed Broken!",ChatColor.RED + "You cant respawn anymore",10,30,10);
                }
                e.getPlayer().getScoreboard().getTeam("bluebedteam").setSuffix(ChatColor.RED + "✘");
                p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Blue bed has been broken by" + " " + e.getPlayer().getDisplayName());

            }
            e.setDropItems(false);


        } else if (e.getBlock().getType().equals(Material.LIME_BED)) {
            for (Player p : Bukkit.getOnlinePlayers()){
                if (Objects.requireNonNull(e.getPlayer().getScoreboard().getTeam("green")).hasEntry(p.getName())){
                    p.sendTitle(ChatColor.RED + "" +ChatColor.BOLD+ "Bed Broken!",ChatColor.RED + "You cant respawn anymore",10,30,10);
                }
                e.getPlayer().getScoreboard().getTeam("greenbedteam").setSuffix(ChatColor.RED + "✘");
                p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Green bed has been broken by" + " " + e.getPlayer().getDisplayName());
            }
            e.setDropItems(false);


        } else if (e.getBlock().getType().equals(Material.YELLOW_BED)) {
            for (Player p : Bukkit.getOnlinePlayers()){
                if (Objects.requireNonNull(e.getPlayer().getScoreboard().getTeam("yellow")).hasEntry(p.getName())){
                    p.sendTitle(ChatColor.RED + "" +ChatColor.BOLD+ "Bed Broken!",ChatColor.RED + "You cant respawn anymore",10,30,10);
                }
                e.getPlayer().getScoreboard().getTeam("yellowbedteam").setSuffix(ChatColor.RED + "✘");
                p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Yellow bed has been broken by" + " " + e.getPlayer().getDisplayName());
            }
            e.setDropItems(false);

        }
    }












}




