package fun.fun;

import org.bukkit.plugin.java.JavaPlugin;

public final class Fun extends JavaPlugin {




    @Override
    public void onEnable() {
        EventListener eventListener = new EventListener(this);

        
        getServer().getPluginManager().registerEvents(eventListener,this);
        getCommand("kit").setExecutor(eventListener);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
