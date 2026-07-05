package fun.dimensionswitchchange;

import org.bukkit.plugin.java.JavaPlugin;

public final class DimensionSwitchChange extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("dimensionswitch").setExecutor(new command(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
