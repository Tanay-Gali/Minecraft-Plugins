package fun.compasstime;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class CompassTime extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(this,this);
        
        getCommand("compass").setExecutor(new Compass());

        Player player = Compass.sender;




    }

    @EventHandler
    public void Interact(PlayerInteractEvent e){
        if (e.getItem()== Compass.item){
            Player player = e.getPlayer();
            Player result = null;
            double lastDistance = Double.MAX_VALUE;
            for(Player p : player.getWorld().getPlayers()) {
                if(player == p)
                    continue;

                double distance = player.getLocation().distance(p.getLocation());
                if(distance < lastDistance) {
                    lastDistance = distance;
                    result = p;
                }
            }
            if (result == null){
                player.sendMessage(ChatColor.RED + "There are no players to track");
            }

            if(result != null) {
                player.setCompassTarget(result.getLocation());
                player.sendMessage(ChatColor.GREEN + "You are tracking" + result.getDisplayName());
            } else {

            }


        }

    }




}



