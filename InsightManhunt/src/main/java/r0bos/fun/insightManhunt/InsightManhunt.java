package r0bos.fun.insightManhunt;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.*;

public final class InsightManhunt extends JavaPlugin implements Listener {

    HashSet<UUID> runners = new HashSet<>();

    List<UUID> hunters = new ArrayList<>();

    private Map<UUID, Integer> cooldowns = new HashMap<>();





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
    public static final int DEFAULT_COOLDOWN = 60;

    public void setCooldown(UUID player, int time){
        if(time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }
    public int getCooldown(UUID player){
        return cooldowns.getOrDefault(player, 0);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (e.getItem() != null && this.hunters.contains(e.getPlayer().getUniqueId()) && e.getItem().getType().equals(Material.COMPASS)) {
            CompassMeta compassMeta2 = (CompassMeta) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
            if (e.getItem().getType().equals(Material.COMPASS) || Objects.requireNonNull(compassMeta2).hasLodestone()) {
                Player player = e.getPlayer();
                Player result = null;
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                    double lastDistance = Double.MAX_VALUE;
                    for (Player p : player.getWorld().getPlayers()) {
                        if (player == p || this.hunters.contains(p.getUniqueId())) {
                            continue;
                        }

                        double distance = player.getLocation().distance(p.getLocation());
                        if (distance < lastDistance) {
                            lastDistance = distance;
                            result = p;
                        }
                    }
                }

                if (result != null) {
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

        Player player = e.getPlayer();

        // Must be the speedrunner
        if (!runners.contains(player.getUniqueId())) return;

        // Must be right click with item
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != Material.SPYGLASS) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName() || !meta.getDisplayName().equals(ChatColor.GOLD + "Spectate Switch")) return;

        // ADD COOLDOWN CHECK HERE
        if (getCooldown(player.getUniqueId()) > 0) {
            player.sendMessage(ChatColor.RED + "You must wait " + getCooldown(player.getUniqueId()) + " seconds before using the Spectate Switch again!");
            return;
        }

        if (hunters.isEmpty()) {
            player.sendMessage(ChatColor.RED + "No hunters online to spectate.");
            return;
        }

        Player target = Bukkit.getPlayer(hunters.get(new Random().nextInt(hunters.size())));
        setPlayerToSpectatorForDuration(player, 10, target);

        player.sendMessage(ChatColor.GREEN + "Now spectating " + target.getName());

        // START COOLDOWN
        setCooldown(player.getUniqueId(), DEFAULT_COOLDOWN);
        new BukkitRunnable() {
            @Override
            public void run() {
                int timeLeft = getCooldown(player.getUniqueId());
                if (timeLeft > 0) {
                    setCooldown(player.getUniqueId(), timeLeft - 1);
                }
            }
        }.runTaskTimer(this, 20L, 20L); // Runs every second (20 ticks)
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
    public void setPlayerToSpectatorForDuration(Player player, int durationSeconds,Player target) {
        // Store the player's current game mode
        GameMode previousGameMode = player.getGameMode();

        Location previouslocation = player.getLocation();

        // Set the player to spectator mode
        player.setGameMode(GameMode.SPECTATOR);

        player.setSpectatorTarget(target);

        // Schedule a task to revert the player's game mode after the specified duration
        new BukkitRunnable() {
            @Override
            public void run() {
                // Check if the player is still online
                if (player.isOnline()) {
                    // Revert to the previous game mode
                    player.setSpectatorTarget(null);
                    player.setGameMode(previousGameMode);
                    player.teleport(previouslocation);
                    player.sendMessage("You have returned to your previous game mode.");



                }
            }
        }.runTaskLater(this, durationSeconds * 20L); // Convert seconds to ticks (20 ticks = 1 second)
    }

    



}
