package fun.tntpattern;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.TNTPrimeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.A;

import java.util.AbstractList;

public final class TntPattern extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void tntblow(EntityExplodeEvent e){
        for (Player p :Bukkit.getOnlinePlayers()){
            p.sendMessage("Works before");
        }
        TNTPrimed tnt1 =(TNTPrimed) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.PRIMED_TNT);
        TNTPrimed tnt2 =(TNTPrimed) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.PRIMED_TNT);
        TNTPrimed tnt3 =(TNTPrimed) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.PRIMED_TNT);
        TNTPrimed tnt4 =(TNTPrimed) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.PRIMED_TNT);
        tnt1.setFuseTicks(80);
        tnt2.setFuseTicks(80);
        tnt3.setFuseTicks(80);
        tnt4.setFuseTicks(80);
        tnt1.setVelocity(new Vector(1,1,0));
        tnt2.setVelocity(new Vector(0,1,1));
        tnt3.setVelocity(new Vector(-1,1,0));
        tnt4.setVelocity(new Vector(0,1,-1));
        for (Player p :Bukkit.getOnlinePlayers()){
            p.sendMessage("Works after");
        }


    }
}
