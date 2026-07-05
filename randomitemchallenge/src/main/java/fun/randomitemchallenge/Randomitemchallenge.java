package fun.randomitemchallenge;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Randomitemchallenge extends JavaPlugin implements Listener {
    RandomItemCommand command = new RandomItemCommand(this);

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();


        getCommand("randomitemchallenge").setExecutor(command);
        getServer().getPluginManager().registerEvents(command,this);


    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
