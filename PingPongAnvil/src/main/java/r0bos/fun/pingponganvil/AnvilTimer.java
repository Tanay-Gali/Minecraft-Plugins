package r0bos.fun.pingponganvil;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.bukkit.NamespacedKey;

public class AnvilTimer extends BukkitRunnable {

    private final FallingBlock anvil;
    private final NamespacedKey key;
    private final JavaPlugin plugin;

    public AnvilTimer(FallingBlock anvil, JavaPlugin plugin) {
        this.anvil = anvil;
        this.plugin = plugin;
        this.key = new NamespacedKey(plugin, "bounced");
    }

    @Override
    public void run() {
        PersistentDataContainer data = anvil.getPersistentDataContainer();

       
        if (!data.has(key, PersistentDataType.BYTE)) {
            Location loc = anvil.getLocation();

            if (!loc.clone().add(0, 0, 1).getBlock().isPassable()) {
                anvil.setVelocity(new Vector(anvil.getVelocity().getX(), 0.7, -1 * anvil.getVelocity().getZ()));
                data.set(key, PersistentDataType.BYTE, (byte) 1);
            } else if (!loc.clone().add(0, 0, -1).getBlock().isPassable()) {
                anvil.setVelocity(new Vector(anvil.getVelocity().getX(), 0.7, -1 * anvil.getVelocity().getZ()));
                data.set(key, PersistentDataType.BYTE, (byte) 1);
            } else if (!loc.clone().add(1, 0, 0).getBlock().isPassable()) {
                anvil.setVelocity(new Vector(-1 * anvil.getVelocity().getX(), 0.7, anvil.getVelocity().getZ()));
                data.set(key, PersistentDataType.BYTE, (byte) 1);
            } else if (!loc.clone().add(-1, 0, 0).getBlock().isPassable()) {
                anvil.setVelocity(new Vector(-1 * anvil.getVelocity().getX(), 0.7, anvil.getVelocity().getZ()));
                data.set(key, PersistentDataType.BYTE, (byte) 1);
            }
        } else {

            data.remove(key);
        }
    }
}
