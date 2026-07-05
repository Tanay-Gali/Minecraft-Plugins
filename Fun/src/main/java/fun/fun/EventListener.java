package fun.fun;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.A;

import java.util.Objects;

public class EventListener implements CommandExecutor,Listener {

    BukkitTask task;

    BukkitTask task1;

    Plugin plugin;

    Player target;

    public EventListener(Plugin plugin){
        this.plugin = plugin;
    }


    int radius = 5;






    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        ItemStack stonesword = new ItemStack(Material.STONE_SWORD);
        stonesword.addEnchantment(Enchantment.KNOCKBACK,2);
        ItemStack diamondpickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack shield = new ItemStack(Material.SHIELD);
        ItemStack diaboots= new ItemStack(Material.DIAMOND_BOOTS);
        ItemStack diachestplate= new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack dialeggings= new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack crossbow = new ItemStack(Material.CROSSBOW);
        ItemStack trident = new ItemStack(Material.TRIDENT);
        ItemStack snowball = new ItemStack(Material.SNOWBALL,16);
        ItemStack egg = new ItemStack(Material.EGG,4);
        ItemStack arrow = new ItemStack(Material.ARROW,64);
        ItemStack food = new ItemStack(Material.COOKED_BEEF,16);
        ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL);
        ItemStack elytra = new ItemStack(Material.ELYTRA);
        ItemStack cobblestone = new ItemStack(Material.COBBLESTONE,128);
        ItemStack fireball = new ItemStack(Material.FIRE_CHARGE,3);
        ItemStack fishingrod = new ItemStack(Material.FISHING_ROD,1);
        ItemStack mace = new ItemStack(Material.MACE);
        mace.addEnchantment(Enchantment.WIND_BURST, 2);
        ItemStack waterbucket = new ItemStack(Material.WATER_BUCKET);
        ItemStack windcharge = new ItemStack(Material.WIND_CHARGE, 3);

        ItemStack firework = new ItemStack(Material.FIREWORK_ROCKET, 3);

        ((Player) commandSender).getInventory().addItem(stonesword);
        ((Player) commandSender).getInventory().addItem(diamondpickaxe);
        ((Player) commandSender).getInventory().addItem(shield);
        ((Player) commandSender).getInventory().addItem(diaboots);
        ((Player) commandSender).getInventory().addItem(diachestplate);
        ((Player) commandSender).getInventory().addItem(dialeggings);
        ((Player) commandSender).getInventory().addItem(crossbow);
        ((Player) commandSender).getInventory().addItem(trident);
        ((Player) commandSender).getInventory().addItem(snowball);
        ((Player) commandSender).getInventory().addItem(egg);
        ((Player) commandSender).getInventory().addItem(arrow);
        ((Player) commandSender).getInventory().addItem(food);
        ((Player) commandSender).getInventory().addItem(enderpearl);
        ((Player) commandSender).getInventory().addItem(elytra);
        ((Player) commandSender).getInventory().addItem(cobblestone);
        ((Player) commandSender).getInventory().addItem(fireball);
        ((Player) commandSender).getInventory().addItem(fishingrod);
        ((Player) commandSender).getInventory().addItem(mace);
        ((Player) commandSender).getInventory().addItem(waterbucket);
        ((Player) commandSender).getInventory().addItem(windcharge);
        ((Player) commandSender).getInventory().addItem(firework);


        return false;
    }

    @EventHandler
    public void crossbow(ProjectileLaunchEvent e){
        if (e.getEntity().getShooter() instanceof Player) {
            if (e.getEntityType().equals(EntityType.ARROW)) {
                if (((Player) e.getEntity().getShooter()).getInventory().getItemInMainHand().getType().equals(Material.CROSSBOW)) {
                    ((Player) e.getEntity().getShooter()).playSound(e.getEntity().getLocation(),Sound.ITEM_CROSSBOW_SHOOT,1f,1f);
                    Creeper creeper = (Creeper) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(),EntityType.CREEPER);
                    creeper.setVelocity(e.getEntity().getVelocity());
                    e.getEntity().remove();
                    creeper.setPowered(true);
                    creeper.ignite();
                }
            } else if (e.getEntityType().equals(EntityType.EGG)) {
                EggLoop((Egg) e.getEntity());
            } else if (e.getEntityType().equals(EntityType.ENDER_PEARL)) {
                e.getEntity().addPassenger((Entity) e.getEntity().getShooter());
            }
        }
    }
    public void EggLoop(Egg egg) {
        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
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
    public void damage(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Fireball){
            e.setDamage(3);
        }
    }
    @EventHandler
    public void arrowland(ProjectileHitEvent e) {

        if (e.getEntityType().equals(EntityType.SNOWBALL)) {
            ShulkerBullet shulkerBullet = e.getEntity().getWorld().spawn(e.getEntity().getLocation(), ShulkerBullet.class);
            double lastDistance = Double.MAX_VALUE;
            for (Player p : shulkerBullet.getWorld().getPlayers()) {

                double distance = shulkerBullet.getLocation().distance(p.getLocation());
                if (distance < lastDistance) {
                    lastDistance = distance;
                    target = p;
                }
            }
            shulkerBullet.setTarget(target);


        } else if (e.getEntityType().equals(EntityType.FIREWORK_ROCKET)) {
            e.getEntity().remove();
            blackHole(e.getEntity().getLocation());
        }
    }
    @EventHandler
    public void PlayerInteraction(PlayerInteractEvent e) {
        if (e.getItem() != null){
            if (e.getItem().getType().equals(Material.FIRE_CHARGE)) {
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (!(e.getPlayer().hasPotionEffect(PotionEffectType.SLOWNESS))) {
                        Fireball fireball = e.getPlayer().getWorld().spawn(e.getPlayer().getEyeLocation(), Fireball.class);
                        fireball.setYield(4);
                        fireball.setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(0.3));
                        fireball.setShooter(e.getPlayer());
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20, 0));
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
    public void ElytraEquip(PlayerInteractEvent e){
        if (e.getItem() != null){
            if (e.getItem().getType().equals(Material.ELYTRA)){
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                    e.getPlayer().setVelocity(new Vector(0,30,0));
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        Objects.requireNonNull(e.getPlayer().getInventory().getChestplate()).addEnchantment(Enchantment.BINDING_CURSE,1);
                    },1);
                    elytra(e.getPlayer());
                }
            }
        }

    }
    public void elytra(Player p){
        task1 = Bukkit.getScheduler().runTaskLater(plugin, () -> {
            p.getInventory().setChestplate(null);
        },200);
    }
    @EventHandler
    public void projectileLand(ProjectileHitEvent e){
        if (e.getEntity().getType().equals(EntityType.TRIDENT)){
            for (int i = -1; i<=1;i++){
                for (int a=-1; a<=1; a++){
                    e.getEntity().getWorld().spawn(e.getEntity().getLocation().add(i,20,a), TNTPrimed.class);
                    e.getEntity().remove();
                }
            }
        }

    }
    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {


        if (e.getState().equals(PlayerFishEvent.State.REEL_IN)) {

            Player player = e.getPlayer();
            FishHook hook = e.getHook();

            if (e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.FISHING_ROD)){
                damageItemInHand(e.getPlayer(),3,true);
            }
            else if (e.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.FISHING_ROD)){
                damageItemInHand(e.getPlayer(),3,false);
            }

            Vector direction = hook.getLocation().toVector().subtract(player.getLocation().toVector());
            direction.setY(direction.getY() + 0.5);
            direction.normalize().multiply(3);

            player.setVelocity(direction);



        }
    }


    public void blackHole(Location center){

        BlackHole blackHole = new BlackHole(center,20);
        blackHole.runTaskTimer(plugin,0,4);




    }


    public void damageItemInHand(Player player, int damageAmount,boolean mainhand) {
        ItemStack item = player.getInventory().getItemInMainHand();

        // Ensure the item is not null and is damageable
        if (item != null && item.getType() != Material.AIR && item.getItemMeta() instanceof Damageable) {
            Damageable damageable = (Damageable) item.getItemMeta();
            int currentDamage = damageable.getDamage();
            int newDamage = currentDamage + damageAmount;

            // Check if the item breaks
            if (newDamage >= item.getType().getMaxDurability()) {
                if (mainhand){
                    player.getInventory().setItemInMainHand(null);
                } else {
                    player.getInventory().setItemInOffHand(null);
                }
                 // Remove broken item
                player.sendMessage("Your item broke!");
            } else {
                damageable.setDamage(newDamage);
                item.setItemMeta((org.bukkit.inventory.meta.ItemMeta) damageable);
            }
        }
    }













}
