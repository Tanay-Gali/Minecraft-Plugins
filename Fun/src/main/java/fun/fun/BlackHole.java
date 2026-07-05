package fun.fun;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.Random;

public class BlackHole extends BukkitRunnable {

    Random random = new Random();
    Location center;
    public BlackHole(Location center,int maxRadius){
        this.center = center;
        this.maxRadius = maxRadius;
    }
    boolean size =true;
    int currentradius = 1;
    int maxRadius;

    int iteration = 1;

    @Override
    public void run() {
        if (currentradius == 1 && !size){
            this.cancel();
            return;
        }

        for (int x = -currentradius; x<=currentradius; x++){
            for (int y = -currentradius; y<=currentradius; y++){
                for (int z = -currentradius; z<=currentradius; z++){
                    long distance = Math.round(Math.sqrt(x * x + y * y + z * z));
                    if (distance<=currentradius && distance >= currentradius - 1){
                        Location location = center.clone().add(x,y,z);
                        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLACK,1.0f);
                        Objects.requireNonNull(location.getWorld()).spawnParticle(Particle.DUST,location,1,dustOptions);

                    }


                    if (random.nextInt(30)==1 && distance<=currentradius){
                        Location location = center.clone().add(x,y,z);
                        location.getBlock().setType(Material.AIR);
                    }


                }
            }
        }
        pullPlayers();
        if (iteration%5==0) {
            if (currentradius == maxRadius) {
                size = false;
            }
            if (size) {
                currentradius++;
            } else {
                currentradius--;
            }
        }
        iteration++;
    }




    public void pullPlayers(){
        for (Player p : Bukkit.getOnlinePlayers()){


            double distance = p.getLocation().distance(center);
            if (distance>100) return;
            double maxstrength = 0.15;
            double strength = maxstrength/distance;
            Vector pull = center.toVector().subtract(p.getLocation().toVector());

            p.setVelocity(pull.multiply(strength*((double) currentradius /5)));


            if (p.getLocation().distance(center) <1){
                p.damage(5);
            }

        }
    }
}
