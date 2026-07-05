package fun.tracking;

import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Tracking extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(this,this);
        // Plugin startup logic
        getCommand("compass").setExecutor(new Compass());






    }

    @EventHandler
    public void Interact(PlayerInteractEvent e){
        CompassMeta compassMeta2 = (CompassMeta) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
        if (e.getItem() != null){
            if (e.getItem().getType().equals(Material.COMPASS) || Objects.requireNonNull(compassMeta2).hasLodestone()){
                Player player = e.getPlayer();
                Player result = null;
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
                    double lastDistance = Double.MAX_VALUE;
                    for(Player p : player.getWorld().getPlayers()) {
                        if(player == p || p.getGameMode().equals(GameMode.SPECTATOR))
                            continue;

                        double distance = player.getLocation().distance(p.getLocation());
                        if(distance < lastDistance) {
                            lastDistance = distance;
                            result = p;

                        }

                    }

                }




                if(result != null) {
                    CompassMeta compassMeta = (CompassMeta) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                    assert compassMeta != null;
                    compassMeta.setLodestoneTracked(false);
                    compassMeta.setLodestone(result.getLocation());
                    e.getPlayer().getInventory().getItemInMainHand().setItemMeta(compassMeta);
                    player.sendMessage(ChatColor.GREEN + "You are tracking " + result.getDisplayName());
                } else {
                    player.sendMessage(ChatColor.RED + "There are no players to track");
                }
            }
        }


    }




}

