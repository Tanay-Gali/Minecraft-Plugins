package fun.freezearyan;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class FreezeAryan extends JavaPlugin {





    @Override
    public void onEnable() {
        getCommand("freezearyan").setExecutor(new FreezeAryanCommand());
        getServer().getPluginManager().registerEvents(new FreezeAryanCommand(), this);

        // Plugin startup logic


    }



}
