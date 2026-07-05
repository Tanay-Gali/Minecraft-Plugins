package r0bos.fun.switchworld;

import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;

import org.bukkit.advancement.AdvancementProgress;



import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import org.bukkit.scheduler.BukkitTask;



import java.util.Iterator;
import java.util.Objects;

public class SwitchWorldCommand implements CommandExecutor,Listener{
    static int loops = 1;

    MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");

    long minutes = 20;
    Plugin plugin;

    public SwitchWorldCommand(Plugin plugin){
        this.plugin = plugin;
    }
    BukkitTask worldtask1;

    static boolean status = false;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {






        if (!status){
            status = true;
            Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "WorldSwitcher has started");

            core.getMVWorldManager().getMVWorld("world_nether").setRespawnToWorld("world");
            core.getMVWorldManager().getMVWorld("world_the_end").setRespawnToWorld("world");



            Player p = (Player) commandSender;
            worldloop(p.getWorld());
        } else {
            if (strings.length>=1){
                if (strings[0] != null) {
                    if (strings[0].equals("skip")) {
                        Bukkit.broadcastMessage(ChatColor.BLUE + String.valueOf(ChatColor.BOLD) + "Skipped!");

                        worldtask1.cancel();
                        loops++;
                        worldloop(((Player) commandSender).getWorld());

                    }
                }
            } else {
                status = false;
                Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "WorldSwitcher has stopped");

                worldtask1.cancel();
            }
        }










        return false;
    }
    public void worldloop(World w){
        if (Bukkit.getWorld("lobby") == null){
            core.getMVWorldManager().addWorld("lobby", World.Environment.NORMAL, "12312",WorldType.FLAT,true,null);
        }


        unloadWorld();
        createWorld(w);
        teleportworld(Objects.requireNonNull(Bukkit.getWorld("world_1")));


        worldtask1 = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            long countdown = minutes * 60;
            @Override
            public void run() {
                if (countdown <= 0) {

                    start(w);
                    countdown = minutes * 60;
                } else {
                    if (countdown % 300 == 0) {
                        Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "World resets in " + countdown/60 + " minutes!");
                    } else {
                        if (countdown<=10){
                            Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "World resets in " + countdown + " seconds!");
                        }

                    }
                    countdown--;
                }


            }
        },20,20);
    }



    public void start(World w){
        Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Switching!");

        core.getMVWorldManager().loadWorld("lobby");
        teleportworld(Objects.requireNonNull(Bukkit.getWorld("lobby")));
        createWorld(w);
        teleportworld(Objects.requireNonNull(Bukkit.getWorld("world_1")));
        unloadWorld();
        loops++;

    }
    public void teleportworld(World w){



        w.setTime(0);
        Location worldspawn = w.getSpawnLocation();
        for (Player p : Bukkit.getOnlinePlayers()){
            p.teleport(worldspawn);


            Iterator<Advancement> iterator = Bukkit.getServer().advancementIterator();

            while (iterator.hasNext()){
                AdvancementProgress progress = p.getAdvancementProgress(iterator.next());
                for (String criteria : progress.getAwardedCriteria()) {
                    progress.revokeCriteria(criteria);
                }
            }

            p.getInventory().clear();
            p.setHealth(20);
            for (PotionEffect a : p.getActivePotionEffects()){
                p.removePotionEffect(a.getType());
            }
            p.setFoodLevel(20);
            p.setExp(0);
        }

    }

    public void createWorld(World world){

        if (Bukkit.getWorld("world_1") != null){
            core.deleteWorld("world_1");
        }
        if (Bukkit.getWorld("world_1_nether") != null) {
            core.deleteWorld("world_1_nether");
        }
        if (Bukkit.getWorld("world_1_the_end") != null){
            core.deleteWorld("world_1_the_end");
        }


        core.getMVWorldManager().addWorld("world_1", World.Environment.NORMAL, String.valueOf(world.getSeed()),WorldType.NORMAL,true,null);
        core.getMVWorldManager().addWorld("world_1_nether", World.Environment.NETHER, String.valueOf(world.getSeed()),WorldType.NORMAL,true,null);
        core.getMVWorldManager().addWorld("world_1_the_end", World.Environment.THE_END, String.valueOf(world.getSeed()),WorldType.NORMAL,true,null);


        core.getMVWorldManager().getMVWorld("world_1_nether").setRespawnToWorld("world_1");
        core.getMVWorldManager().getMVWorld("world_1_the_end").setRespawnToWorld("world_1");



    }


    public void unloadWorld(){
        core.getMVWorldManager().unloadWorld("lobby");
    }
    @EventHandler
    public void achieve(PlayerAdvancementDoneEvent e) {
        if (e.getAdvancement().getDisplay() != null) {
            if (Objects.requireNonNull(e.getAdvancement().getDisplay()).getTitle().equals("Free the End")) {
                Bukkit.broadcastMessage(ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + "YOU DID IT IN " + (loops) + " LOOPS");
                worldtask1.cancel();
            }
        }
    }
    




}