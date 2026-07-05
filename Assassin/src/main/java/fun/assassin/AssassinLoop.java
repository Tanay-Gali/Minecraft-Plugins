package fun.assassin;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.Objects;


public class AssassinLoop extends BukkitRunnable {
    Assassin assassin;

    public AssassinLoop(Assassin assassin){
        this.assassin = assassin;
    }
    @Override
    public void run() {
        Player onlinePlayer = null;

        for (Player p: Bukkit.getOnlinePlayers()){
            if (!(assassin.assassinCollection.contains(p.getUniqueId()))){
                onlinePlayer = p.getPlayer();
            }
        }
        label:
        for (int i = 0; i < 100; ++i) {
            assert onlinePlayer != null;
            Location location = onlinePlayer.getEyeLocation().add(onlinePlayer.getEyeLocation().getDirection().multiply(i + 1));
            if (location.getBlock().getType().isOccluding()) {
                break;
            }
            Iterator<Entity> var5 = onlinePlayer.getWorld().getEntitiesByClasses(new Class[]{Player.class}).iterator();
                Player player;
                do {
                    do {
                        if (!var5.hasNext()) {
                            continue label;
                        }


                        player = (Player) var5.next();
                    } while (!assassin.assassinCollection.contains(player.getUniqueId()));
                } while (!(location.distance(player.getLocation()) <= 1) && !(location.distance(player.getEyeLocation()) <= 1));






                assassin.noMove.put(player.getUniqueId(), System.currentTimeMillis() + 500L);

                for (int j = 1; j < i; ++j) {
                    Location particleLocation = onlinePlayer.getEyeLocation().add(onlinePlayer.getEyeLocation().getDirection().multiply(j + 1));
                    Objects.requireNonNull(particleLocation.getWorld()).spawnParticle(Particle.REDSTONE, particleLocation, 1, new Particle.DustOptions(Color.RED, 0.4F));
                }

    }

    }
}
