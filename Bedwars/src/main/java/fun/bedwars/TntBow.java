package fun.bedwars;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.Objects;

public class TntBow implements Listener {



    Player target;
    @EventHandler
    public void Projshoot(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            if (e.getEntityType().equals(EntityType.ARROW)) {
                if (Objects.requireNonNull(((Player) e.getEntity().getShooter()).getInventory().getItemInMainHand().getItemMeta()).getDisplayName().equals("Fire Bow")
                        || Objects.requireNonNull(((Player) e.getEntity().getShooter()).getInventory().getItemInOffHand().getItemMeta()).getDisplayName().equals("Fire Bow")) {




                }
            }
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


            }
        }

}


