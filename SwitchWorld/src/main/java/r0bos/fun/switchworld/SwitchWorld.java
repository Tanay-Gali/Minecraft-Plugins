package r0bos.fun.switchworld;



import com.onarandombox.MultiverseCore.MultiverseCore;


import org.bukkit.Bukkit;
import org.bukkit.PortalType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class SwitchWorld extends JavaPlugin implements CommandExecutor {
    SwitchWorldCommand switchWorldCommand = new SwitchWorldCommand(this);

    @Override
    public void onEnable() {



        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");

        // Plugin startup logic
        getCommand("switchworld").setExecutor(switchWorldCommand);
        getServer().getPluginManager().registerEvents(switchWorldCommand,this);



    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}