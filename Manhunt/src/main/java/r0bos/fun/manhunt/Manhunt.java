package r0bos.fun.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.Speed;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

public final class Manhunt extends JavaPlugin implements Listener {

    HashSet<UUID> runners = new HashSet<>();

    HashSet<UUID> hunters = new HashSet<>();




    @Override
    public void onEnable() {


        getServer().getPluginManager().registerEvents(this,this);

        getCommand("runner").setExecutor(new SpeedrunnerCommand(this));

        getCommand("hunter").setExecutor(new HunterCommand(this));

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (e.getItem() != null && this.hunters.contains(e.getPlayer().getUniqueId())){
            CompassMeta compassMeta2 = (CompassMeta) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
            if (e.getItem().getType().equals(Material.COMPASS) || Objects.requireNonNull(compassMeta2).hasLodestone()){
                Player player = e.getPlayer();
                Player result = null;
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
                    double lastDistance = Double.MAX_VALUE;
                    for(Player p : player.getWorld().getPlayers()) {
                        if(player == p || this.hunters.contains(p.getUniqueId())) {
                            continue;
                        }

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
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        if (this.hunters.contains(event.getEntity().getUniqueId())) {
            event.getDrops().removeIf((next) -> next.getType() == Material.COMPASS);
        }

    }

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        if (this.hunters.contains(event.getPlayer().getUniqueId()) && event.getItemDrop().getItemStack().getType() == Material.COMPASS) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (this.hunters.contains(player.getUniqueId())) {
            player.getInventory().addItem(new ItemStack(Material.COMPASS));
        }


    }





}
