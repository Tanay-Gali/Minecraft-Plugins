package fun.tntrain;

import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.RayTraceResult;

public final class TNTRain extends JavaPlugin {
    TNTCommand command = new TNTCommand(this);


    @Override
    public void onEnable() {
        getCommand("tntrain").setExecutor(command);
        getServer().getPluginManager().registerEvents(command,this);

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
