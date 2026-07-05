package fun.bedwars;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Score;

import java.util.Map;


public final class Bedwars extends JavaPlugin implements Listener {


    public ItemStack leatherchestplate;
    public ItemStack leatherleggings;
    public ItemStack leatherboots;
    public ItemStack chainmailchestplate;
    public ItemStack chainmailleggings;
    public ItemStack chainmailboots;
    public ItemStack ironchestplate;
    public ItemStack ironleggings;

    public ItemStack ironboots;
    public ItemStack diamondboots;
    public ItemStack diamondchestplate;

    @Override
    public void onEnable() {

        leatherchestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        leatherleggings = new ItemStack(Material.LEATHER_LEGGINGS);
        leatherboots = new ItemStack(Material.LEATHER_BOOTS);
        chainmailchestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        chainmailleggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        chainmailboots = new ItemStack(Material.CHAINMAIL_BOOTS);
        ironchestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ironleggings = new ItemStack(Material.IRON_LEGGINGS);
        ironboots = new ItemStack(Material.IRON_BOOTS);
        diamondboots = new ItemStack(Material.DIAMOND_BOOTS);
        diamondchestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);

        LeatherArmorMeta leatherchestplateItemMeta = (LeatherArmorMeta) leatherchestplate.getItemMeta();
        LeatherArmorMeta leatherleggingsItemMeta = (LeatherArmorMeta) leatherleggings.getItemMeta();
        LeatherArmorMeta leatherbootsItemMeta = (LeatherArmorMeta) leatherboots.getItemMeta();
        ArmorMeta chainmailchestplateItemMeta = (ArmorMeta) chainmailchestplate.getItemMeta();
        ArmorMeta chainmailleggingsItemMeta = (ArmorMeta) chainmailleggings.getItemMeta();
        ArmorMeta chainmailbootsItemMeta = (ArmorMeta) chainmailboots.getItemMeta();
        ArmorMeta ironchestplateItemMeta = (ArmorMeta) ironchestplate.getItemMeta();
        ArmorMeta ironleggingsItemMeta = (ArmorMeta) ironleggings.getItemMeta();
        ArmorMeta ironbootsItemMeta = (ArmorMeta) ironboots.getItemMeta();
        ArmorMeta diamondbootsItemMeta = (ArmorMeta) diamondboots.getItemMeta();
        ArmorMeta diamondchestplateItemMeta = (ArmorMeta) diamondchestplate.getItemMeta();

        leatherchestplateItemMeta.setUnbreakable(true);
        leatherleggingsItemMeta.setUnbreakable(true);
        leatherbootsItemMeta.setUnbreakable(true);
        chainmailchestplateItemMeta.setUnbreakable(true);
        chainmailleggingsItemMeta.setUnbreakable(true);
        chainmailbootsItemMeta.setUnbreakable(true);
        ironchestplateItemMeta.setUnbreakable(true);
        ironleggingsItemMeta.setUnbreakable(true);
        ironbootsItemMeta.setUnbreakable(true);
        diamondbootsItemMeta.setUnbreakable(true);
        diamondchestplateItemMeta.setUnbreakable(true);

        leatherchestplate.setItemMeta(leatherchestplateItemMeta);
        leatherleggings.setItemMeta(leatherleggingsItemMeta);
        leatherboots.setItemMeta(leatherbootsItemMeta);
        chainmailchestplate.setItemMeta(chainmailchestplateItemMeta);
        chainmailleggings.setItemMeta(chainmailleggingsItemMeta);
        chainmailboots.setItemMeta(chainmailbootsItemMeta);
        ironchestplate.setItemMeta(ironchestplateItemMeta);
        ironleggings.setItemMeta(ironleggingsItemMeta);
        ironboots.setItemMeta(ironbootsItemMeta);
        diamondboots.setItemMeta(diamondbootsItemMeta);
        diamondchestplate.setItemMeta(diamondchestplateItemMeta);

        GenCommand command = new GenCommand(this);

        getServer().getPluginManager().registerEvents(new PlayerPickUp(command),this);
        getCommand("gen").setExecutor(command);
        getServer().getPluginManager().registerEvents(new Shop(),this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(),this);
        getServer().getPluginManager().registerEvents(new TntBow(),this);
        getServer().getPluginManager().registerEvents(new BlockPlace(this),this);
        getServer().getPluginManager().registerEvents(this, this);


      //  getServer().getPluginManager().registerEvents(new Invispotion(), this);

    }

    public BukkitTask task;


    @EventHandler
    public void Craft(CraftItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void FoodChange(FoodLevelChangeEvent e){
        e.getEntity().setFoodLevel(20);
        e.setCancelled(true);
    }



    public void EggLoop(Egg egg, Player player) {
        task = Bukkit.getScheduler().runTaskTimer(this, () -> {
            if (!egg.isDead()) {
                Block block = egg.getLocation().subtract(0, 1, 0).getBlock();
                Block block2 = egg.getLocation().subtract(0, 1, 1).getBlock();
                Block block3 = egg.getLocation().subtract(1, 1, 0).getBlock();


                block.setType(Material.WHITE_WOOL);
                block2.setType(Material.WHITE_WOOL);
                block3.setType(Material.WHITE_WOOL);

                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(egg.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
                }

            }

            if (egg.isDead()) {

                return;

            }

        }, 3, 1);
    }



    @EventHandler
    public void Projectile(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            if (e.getEntityType() == EntityType.EGG) {
                EggLoop((Egg) e.getEntity(), (Player) e.getEntity().getShooter());

            }

        }
    }
    @EventHandler
    public void PlayerDamaga(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            if (e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) || e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)){
                e.setDamage(4);
            }
        }

    }



    @EventHandler
    public void consume(PlayerItemConsumeEvent e) {
        if (e.getItem().getType().equals(Material.POTION)) {
            PotionMeta potionMeta = (PotionMeta) e.getItem().getItemMeta();
            Bukkit.getScheduler().runTaskLater(this, () -> {
                if (potionMeta.getBasePotionData().getType().equals(PotionType.INVISIBILITY)) {
                    e.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 0));
                    e.getPlayer().getInventory().remove(Material.GLASS_BOTTLE);
                    ItemStack invischestplate = new ItemStack(Material.WARPED_BUTTON);


                    if (e.getPlayer().getInventory().getChestplate().getType().equals(Material.LEATHER_CHESTPLATE)) {
                        invischestplate = new ItemStack(Material.OAK_BUTTON);
                    } else if (e.getPlayer().getInventory().getChestplate().getType().equals(Material.CHAINMAIL_CHESTPLATE)) {
                        invischestplate = new ItemStack(Material.BIRCH_BUTTON);
                    } else if (e.getPlayer().getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                        invischestplate = new ItemStack(Material.STONE_BUTTON);
                    }




                    ItemMeta invischestplatemeta = (ItemMeta) invischestplate.getItemMeta();
                    double playerarmor = e.getPlayer().getAttribute(Attribute.GENERIC_ARMOR).getValue();
                    AttributeModifier attributeModifier = (new AttributeModifier("generic.armor", playerarmor, AttributeModifier.Operation.ADD_NUMBER));
                    invischestplatemeta.addAttributeModifier(Attribute.GENERIC_ARMOR, attributeModifier);
                    invischestplatemeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                    invischestplate.setItemMeta(invischestplatemeta);


                    e.getPlayer().getInventory().setBoots(null);
                    e.getPlayer().getInventory().setLeggings(null);

                    e.getPlayer().getInventory().setChestplate(invischestplate);
                    invistimer(e.getPlayer());

                }

                if (potionMeta.getBasePotionData().getType().equals(PotionType.JUMP)){
                    e.getPlayer().removePotionEffect(PotionEffectType.JUMP);
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP,900,4));
                    e.getPlayer().getInventory().remove(Material.GLASS_BOTTLE);
                }
                if (potionMeta.getBasePotionData().getType().equals(PotionType.SPEED)){
                    e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,900,1));
                    e.getPlayer().getInventory().remove(Material.GLASS_BOTTLE);
                }


            }, 1);

        }


    }

    public BukkitTask invistimer;

    public void invis(Player p) {
        if (p.getInventory().getChestplate().getType().equals(Material.OAK_BUTTON)) {
            p.getInventory().setChestplate(leatherchestplate);
            p.getInventory().setLeggings(leatherleggings);
            p.getInventory().setBoots(leatherboots);
        } else if (p.getInventory().getChestplate().getType().equals(Material.BIRCH_BUTTON)) {
            p.getInventory().setChestplate(chainmailchestplate);
            p.getInventory().setLeggings(chainmailleggings);
            p.getInventory().setBoots(chainmailboots);

        } else if (p.getInventory().getChestplate().getType().equals(Material.STONE_BUTTON)) {
            p.getInventory().setChestplate(ironchestplate);
            p.getInventory().setLeggings(ironleggings);
            p.getInventory().setBoots(ironboots);

        } else if (p.getInventory().getChestplate().getType().equals(Material.WARPED_BUTTON)) {
            p.getInventory().setChestplate(diamondchestplate);
            p.getInventory().setLeggings(ironleggings);
            p.getInventory().setBoots(diamondboots);

        }
        invistimer.cancel();
    }


    public void invistimer(Player p) {
        invistimer = Bukkit.getScheduler().runTaskLater(this, () -> {
            if (p.getInventory().getChestplate().getType().equals(Material.OAK_BUTTON)) {
                p.getInventory().setChestplate(leatherchestplate);
                p.getInventory().setLeggings(leatherleggings);
                p.getInventory().setBoots(leatherboots);
            } else if (p.getInventory().getChestplate().getType().equals(Material.BIRCH_BUTTON)) {
                p.getInventory().setChestplate(chainmailchestplate);
                p.getInventory().setLeggings(chainmailleggings);
                p.getInventory().setBoots(chainmailboots);

            } else if (p.getInventory().getChestplate().getType().equals(Material.STONE_BUTTON)) {
                p.getInventory().setChestplate(ironchestplate);
                p.getInventory().setLeggings(ironleggings);
                p.getInventory().setBoots(ironboots);

            } else if (p.getInventory().getChestplate().getType().equals(Material.WARPED_BUTTON)) {
                p.getInventory().setChestplate(diamondchestplate);
                p.getInventory().setLeggings(ironleggings);
                p.getInventory().setBoots(diamondboots);

            }


        }, 600);


    }

    @EventHandler
    public void playerDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            for (PotionEffect p : ((Player) e.getEntity()).getActivePotionEffects()) {
                if (p.getType().equals(PotionEffectType.INVISIBILITY)) {
                    ((Player) e.getEntity()).getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
                    Player invisPlayer = ((Player) e.getEntity()).getPlayer();
                    invis(invisPlayer);
                }
            }
        }
    }
    @EventHandler
    public void waterbucket(PlayerBucketEmptyEvent e){
        if (e.getBucket().equals(Material.WATER_BUCKET)){
            Bukkit.getScheduler().runTaskLater(this, () -> {
                e.getPlayer().getInventory().remove(Material.BUCKET);

            },1);
        }
    }
    @EventHandler
    public void PlayerDie(PlayerDeathEvent e){
        for (ItemStack itemStack : e.getEntity().getInventory().getContents()){
            if (itemStack != null){
                if (itemStack.getType().equals(Material.EMERALD) || itemStack.getType().equals(Material.IRON_INGOT)
                        || itemStack.getType().equals(Material.GOLD_INGOT) || itemStack.getType().equals(Material.LAPIS_LAZULI)){
                    if (e.getEntity().getKiller() instanceof Player){
                        e.getEntity().getKiller().getInventory().addItem(itemStack);
                    }


                }
            }

            e.getEntity().getInventory().clear();

        }
        e.getDrops().removeAll(e.getDrops());





        Bukkit.getScheduler().runTaskLater(this, () -> {
            e.getEntity().getInventory().setBoots(leatherboots);
            e.getEntity().getInventory().setLeggings(leatherleggings);
            e.getEntity().getInventory().setChestplate(leatherchestplate);
            ItemStack woodensword = new ItemStack(Material.WOODEN_SWORD);
            ItemMeta woodenswordmeta = woodensword.getItemMeta();
            woodenswordmeta.setUnbreakable(true);
            woodensword.setItemMeta(woodenswordmeta);
            e.getEntity().getInventory().addItem(woodensword);


        },5*20);
    }


}





