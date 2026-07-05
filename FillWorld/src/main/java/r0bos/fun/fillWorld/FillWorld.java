package r0bos.fun.fillWorld;

import org.bukkit.plugin.java.JavaPlugin;

public final class FillWorld extends JavaPlugin {
    FillWorldCommand fillWorldCommand = new FillWorldCommand(this);
    @Override
    public void onEnable() {

        getCommand("fillWorld").setExecutor(fillWorldCommand);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
