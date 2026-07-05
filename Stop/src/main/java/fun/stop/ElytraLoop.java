package fun.stop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;

public class ElytraLoop extends BukkitRunnable {
    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()){
            if (p.getInventory().getItemInMainHand().equals(new ItemStack(Material.FIREWORK_ROCKET))){
                spawnSphere(p,p.getLocation(),6);
            }
        }

    }
    public void spawnSphere(Player p, Location center, double radius) {
        for (int x = (int) (center.getBlockX()-radius); x<=center.getBlockX()+radius; x++){
            for (int y = (int) (center.getBlockY()-radius); y<=center.getBlockY()+radius; y++){
                for (int z = (int) (center.getBlockZ()-radius); z<=center.getBlockZ()+radius; z++){
                    if (center.distanceSquared(new Location(p.getWorld(),x,y,z)) < radius*radius){
                        p.getWorld().setType(x,y,z,Material.AIR);
                    }
                }

            }
        }
    }



}
