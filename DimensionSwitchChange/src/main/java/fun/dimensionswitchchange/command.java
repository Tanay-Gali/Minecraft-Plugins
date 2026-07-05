package fun.dimensionswitchchange;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class command implements CommandExecutor {
    public boolean status = false;
    Plugin plugin;


    BukkitTask task;

    BukkitTask task1;

    BukkitTask task2;


    int countdown = 10;


    public command(Plugin plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!status){
            status = true;
            Bukkit.broadcastMessage(ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + "Dimension Switching is Enabled");


        } else {
            status = false;
            Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Dimension Switching is Disabled");
            task.cancel();
            task1.cancel();
            task2.cancel();
        }
        if (status){
            countdown = 10;
            task1 = Bukkit.getScheduler().runTaskTimer(plugin, () ->{
                task2 = Bukkit.getScheduler().runTaskTimer(plugin, () ->{
                    if (countdown > 0){
                        Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Switching Dimensions in "+  countdown +  " seconds");
                        countdown--;
                    } else {
                        countdown = 10;
                        task2.cancel();
                    }
                },0,20);
            },3400,3600);
            task = Bukkit.getScheduler().runTaskTimer(plugin, () ->{
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (p.getWorld().getEnvironment().equals(World.Environment.NETHER)){
                        Location location = p.getLocation();
                        for (World world: Bukkit.getServer().getWorlds()){
                            if (world.getEnvironment().equals(World.Environment.NORMAL)){
                                location.setWorld(world);
                                p.teleport(location);
                            }
                        }
                    } else if (p.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                        Location location = p.getLocation();
                        for (World world: Bukkit.getServer().getWorlds()){
                            if (world.getEnvironment().equals(World.Environment.NETHER)){
                                location.setWorld(world);
                                p.teleport(location);
                            }
                        }
                    } else {
                        return;
                    }
                }
            }, 3600,3600);
        }










        return false;
    }
}
