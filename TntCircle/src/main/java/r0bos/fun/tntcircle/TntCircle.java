package r0bos.fun.tntcircle;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.A;

import java.util.Objects;

public final class TntCircle extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(this,this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    @EventHandler
    public void EntityExplode(EntityExplodeEvent e){

        int amount = 100;
        double radius = 0.5;

        if (!Objects.equals(e.getEntity().getCustomName(), "Tnt")) {

            for (int i = 0; i < amount; i++) {
                double degrees = 360.0/amount*i;

                double x = Math.cos(Math.toRadians(degrees))*radius;
                double y = Math.sin(Math.toRadians(degrees))*radius;

                Entity entity = e.getEntity().getWorld().spawn(e.getLocation(), e.getEntity().getClass());


                entity.setVelocity(new Vector(x, 1, y));


            }
        }
    }
    
}
