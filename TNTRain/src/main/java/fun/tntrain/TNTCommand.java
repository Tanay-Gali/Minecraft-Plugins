package fun.tntrain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class TNTCommand implements CommandExecutor, Listener {



    static boolean status = false;

    Plugin plugin;

    BukkitTask task;
    BukkitTask task1;
    BukkitTask task2;

    BukkitTask task3;

    int radius = 10;
    Random random = new Random();
    TNTPrimed tnt;

    int averageheight = 0;

    int countdown;

    int tntrepeatingtimer = 14;


    public TNTCommand(Plugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!status) {
            status = true;
            Bukkit.broadcastMessage(ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + "Tnt Rain is Enabled");


        } else {
            status = false;
            Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Tnt Rain is Disabled");
            task.cancel();
            task1.cancel();
            task2.cancel();
            task3.cancel();
        }
        if (status) {
            countdown = 5;
            task1 = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                task2 = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                    if (countdown > 0) {
                        Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Tnt Rain starting in " + countdown + " seconds");
                        countdown--;
                    } else {
                        countdown = 5;
                        task2.cancel();
                    }
                }, 0, 20);
            }, 1100, 1200);
            task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {

                task3 = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                    if (tntrepeatingtimer > 0) {
                        for (Player p : Bukkit.getOnlinePlayers()){
                            TNTLoop(p,Bukkit.getOnlinePlayers());
                        }
                        tntrepeatingtimer--;
                    } else {
                        tntrepeatingtimer = 14;
                        task3.cancel();
                    }

                }, 0,15);


            },1200,1200);


        }
        return false;


    }
    public void TNTLoop(Player p, Collection<? extends Player> onlinePlayers){

        


        Collection<Block> blocks = new HashSet<>();
        int playerX = p.getLocation().getBlockX();
        int playerZ = p.getLocation().getBlockZ();
        for(int x = playerX - radius; x <= playerX + radius; x++) {
                for(int z = playerZ - radius; z <= playerZ + radius; z++) {
                    blocks.add(p.getLocation().getWorld().getBlockAt(x,p.getLocation().getBlockY() + 20,z));
                }

        }
        for (Block b: blocks){
            if (random.nextInt(50) == 1){
                TNTPrimed tnt = p.getWorld().spawn(b.getLocation(), TNTPrimed.class);
                tnt.setYield(2);
            }
        }










    }
    @EventHandler
    public void tntexplode(EntityExplodeEvent e){
        if (e.getEntity() instanceof TNTPrimed){
            TNTPrimed tnt = (TNTPrimed) e.getEntity();
            tnt.setYield(2);
        }
    }


}

