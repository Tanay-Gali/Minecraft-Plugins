package r0bos.fun.shareHealth;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShareHealth extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);

        getCommand("sharedamage").setExecutor(new HealthCommand());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean isHandlingDamage = false;

    @EventHandler
    public void damageChange(EntityDamageEvent e) {
        if (HealthCommand.isSharing && !isHandlingDamage && e.getEntity() instanceof Player && !e.isCancelled()) {
            isHandlingDamage = true;

            try {
                double damage = e.getFinalDamage(); // use the final damage value
                Player damagedPlayer = (Player) e.getEntity();

                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.equals(damagedPlayer)) {
                        p.damage(damage);
                    }
                }
            } finally {
                isHandlingDamage = false;
            }
        }
    }


}