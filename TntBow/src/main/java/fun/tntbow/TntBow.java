package fun.tntbow;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class TntBow extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("sbow").setExecutor(new SbowCommand());


    }
    public BukkitTask task;


    public void ArrowLoop(Arrow arrow,Player player){
        task = Bukkit.getScheduler().runTaskTimer(this, () -> {

            if (!arrow.isOnGround() || !arrow.isDead()) {
                TNTPrimed tntPrimed = (TNTPrimed) arrow.getWorld().spawnEntity(arrow.getLocation(), EntityType.PRIMED_TNT);
                tntPrimed.setSource(player);
                tntPrimed.setFuseTicks(80);
                tntPrimed.setCustomName("bruh");
                tntPrimed.setCustomNameVisible(false);
            }


            if (arrow.isOnGround() || arrow.isDead()) {
                if (arrow.isOnGround()){
                    arrow.remove();
                }
                return;
            }

        },10,1);
    }


    @EventHandler
    public void Projectile(ProjectileLaunchEvent e){
        if (e.getEntity().getShooter() instanceof Player){
            if (e.getEntityType().equals(EntityType.ARROW)){
                if (((Player) e.getEntity().getShooter()).getInventory().getItemInMainHand().getType().equals(Material.BOW)){
                    ItemMeta bowmeta = ((Player) e.getEntity().getShooter()).getInventory().getItemInMainHand().getItemMeta();
                    assert bowmeta != null;
                    bowmeta.setDisplayName("S Bow");
                    if (((Player) e.getEntity().getShooter()).getInventory().getItemInMainHand().getItemMeta().equals(bowmeta)){
                        ArrowLoop((Arrow) e.getEntity(), (Player) e.getEntity().getShooter());
                    }


                }
                if (((Player) e.getEntity().getShooter()).getInventory().getItemInOffHand().getType().equals(Material.BOW)){
                    ItemMeta bowmeta = ((Player) e.getEntity().getShooter()).getInventory().getItemInOffHand().getItemMeta();
                    assert bowmeta != null;
                    bowmeta.setDisplayName("S Bow");
                    if (((Player) e.getEntity().getShooter()).getInventory().getItemInOffHand().getItemMeta().equals(bowmeta)){
                        ArrowLoop((Arrow) e.getEntity(), (Player) e.getEntity().getShooter());
                    }


                }


                }





            }
        }


    }




