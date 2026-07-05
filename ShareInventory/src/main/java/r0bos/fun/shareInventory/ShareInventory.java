package r0bos.fun.shareInventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShareInventory extends JavaPlugin implements Listener {




    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        getCommand("shareinventory").setExecutor(new Command());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    
    private ItemStack[] sharedInventory = new ItemStack[0];



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (Command.isEnabled && sharedInventory.length > 0) {
            e.getPlayer().getInventory().setContents(sharedInventory);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getPlayer() instanceof Player player) {
            updateInventory(player);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        updateInventory(e.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (Command.isEnabled) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.getInventory().clear();
            }
        }
    }

    public void updateInventory(Player p) {
        if (Command.isEnabled) {
            ItemStack[] newContents = p.getInventory().getContents();
            sharedInventory = newContents;
            for (Player a : Bukkit.getOnlinePlayers()) {
                a.getInventory().setContents(newContents);
            }
        }
    }


}
