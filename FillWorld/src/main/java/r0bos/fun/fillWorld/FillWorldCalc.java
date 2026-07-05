package r0bos.fun.fillWorld;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class FillWorldCalc extends BukkitRunnable {

    Material type;
    int chance = 75000;
    Random random = new Random();

    public FillWorldCalc(Material type){
        this.type = type;
    }

    final int radius = 64;
    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()){
            for (int x = -radius; x<radius; x++){
                for (int y = -radius; y<radius; y++){
                    for (int z = -radius; z<radius; z++){
                        Block b = p.getLocation().add(x,y,z).getBlock();

                        if (b.getType().equals(Material.AIR)){
                            if (random.nextInt(chance) == 0){


                                if (type.equals(Material.TNT)){
                                    b.getWorld().spawn(b.getLocation(),TNTPrimed.class);
                                } else {
                                    b.setType(type);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
