package fun.stop;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public final class Stop extends JavaPlugin implements Listener {
    int amountofbranches = 8;
    int trunkHeight = 1000;
    BukkitTask task;
    BukkitTask task1;

    List<BukkitTask> bukkitTaskList = new ArrayList<>();



    @Override
    public void onEnable() {
       
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void bucketPick(PlayerBucketFillEvent e){
        bukkitloop(e.getBlock().getLocation());

    }

    public void bukkitloop(Location l){



        Objects.requireNonNull(l.getWorld()).getBlockAt(l).setType(Material.AIR);


        Bukkit.getScheduler().runTaskLater(this, () -> {

                if (Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).isLiquid() ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getType().equals(Material.SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getType().equals(Material.KELP) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getType().equals(Material.KELP_PLANT) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getType().equals(Material.TALL_SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getBlockData() instanceof Waterlogged){
                    bukkitloop(l.clone().add(1,0,0));
                }
                if (Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(-1,0,0)).isLiquid() ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(-1,0,0)).getType().equals(Material.SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(-1,0,0)).getType().equals(Material.KELP) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(-1,0,0)).getType().equals(Material.KELP_PLANT) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getType().equals(Material.TALL_SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getBlockData() instanceof Waterlogged){
                    bukkitloop(l.clone().add(-1,0,0));
                }
                if (Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,1,0)).isLiquid()  ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,1,0)).getType().equals(Material.SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,1,0)).getType().equals(Material.KELP) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,1,0)).getType().equals(Material.KELP_PLANT) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getType().equals(Material.TALL_SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getBlockData() instanceof Waterlogged){
                    bukkitloop(l.clone().add(0,1,0));
                }
                if (Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,-1,0)).isLiquid()  ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,-1,0)).getType().equals(Material.SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,-1,0)).getType().equals(Material.KELP) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,-1,0)).getType().equals(Material.KELP_PLANT) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getType().equals(Material.TALL_SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getBlockData() instanceof Waterlogged){
                    bukkitloop(l.clone().add(0,-1,0));
                }
                if (Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,0,1)).isLiquid()  ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,0,1)).getType().equals(Material.SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,0,1)).getType().equals(Material.KELP) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,0,1)).getType().equals(Material.KELP_PLANT)  ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getType().equals(Material.TALL_SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getBlockData() instanceof Waterlogged){
                    bukkitloop(l.clone().add(0,0,1));
                }
                if (Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,0,-1)).isLiquid()  ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,0,-1)).getType().equals(Material.SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,0,-1)).getType().equals(Material.KELP) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(0,0,-1)).getType().equals(Material.KELP_PLANT)  ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getType().equals(Material.TALL_SEAGRASS) ||
                        Objects.requireNonNull(l.getWorld()).getBlockAt(l.clone().add(1,0,0)).getBlockData() instanceof Waterlogged){
                    bukkitloop(l.clone().add(0,0,-1));
                }







        },2);




    }

    @EventHandler

    public void interact(PlayerInteractEvent e){

        if (e.getItem() != null){
            if (e.getItem().getType().equals(Material.BONE_MEAL)){
                if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                    if (e.getClickedBlock().getType().equals(Material.OAK_SAPLING)){
                        trunkHeight = 500;

                        amountofbranches = 5;
                        treeGrow(e.getClickedBlock());
                    }
                }
            }
        }
    }


    public Block returnblockunder(Location location){

        Block block = null;
        int goDown = -1;
        while (block == null){
            if (!location.clone().add(0,goDown,0).getBlock().getType().equals(Material.AIR)){
                block = location.clone().add(0,goDown,0).getBlock();
            } else {
                goDown--;
            }
        }
        return block;
    }


    public void treeGrow(Block clickblock){
        Location location = clickblock.getLocation();


        Random random = new Random();



        Vector direction = new Vector(0,1,0);





            task = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {


                @Override
                public void run() {


                    if (random.nextInt(2) == 0) {
                        location.getBlock().setType(Material.OAK_WOOD);
                        location.clone().add(1, 0, 0).getBlock().setType(Material.OAK_WOOD);
                        location.clone().add(0, 1, 0).getBlock().setType(Material.OAK_WOOD);
                        location.clone().add(0, 0, 1).getBlock().setType(Material.OAK_WOOD);
                        location.clone().add(-1, 0, 0).getBlock().setType(Material.OAK_WOOD);
                        location.clone().add(0, -1, 0).getBlock().setType(Material.OAK_WOOD);
                        location.clone().add(0, 0, -1).getBlock().setType(Material.OAK_WOOD);
                    } else {
                        location.getBlock().setType(Material.OAK_LEAVES);
                        location.clone().add(1,0,0).getBlock().setType(Material.OAK_LEAVES);
                        location.clone().add(0,1,0).getBlock().setType(Material.OAK_LEAVES);
                        location.clone().add(0,0,1).getBlock().setType(Material.OAK_LEAVES);
                        location.clone().add(-1,0,0).getBlock().setType(Material.OAK_LEAVES);
                        location.clone().add(0,-1,0).getBlock().setType(Material.OAK_LEAVES);
                        location.clone().add(0,0,-1).getBlock().setType(Material.OAK_LEAVES);
                    }


                    double dx = (random.nextDouble() - 0.5) * 0.5;
                    double dz = (random.nextDouble() - 0.5) * 0.5;
                    double dy = (random.nextDouble() - 0.5) * 0.5;


                    if (random.nextInt(100) <5 && amountofbranches >0){


                        treeGrow(location.getBlock());

                        amountofbranches--;
                    }

                    direction.add(new Vector(dx, dy, dz)).normalize();


                    location.add(direction);
                    trunkHeight--;

                    if (trunkHeight < 0){


                        cancelAllTasks();



                    }
                }



            },1,1);


            bukkitTaskList.add(task);
    }

    public void cancelAllTasks() {
        for (BukkitTask task : bukkitTaskList) {
            task.cancel();
        }

        bukkitTaskList.clear();
    }

    public void addLeaves(Location location){
        location.getBlock().setType(Material.OAK_WOOD);
        Random random = new Random();

        location.clone().add(1,0,0).getBlock().setType(Material.OAK_LEAVES);
        location.clone().add(0,1,0).getBlock().setType(Material.OAK_LEAVES);
        location.clone().add(0,0,1).getBlock().setType(Material.OAK_LEAVES);
        location.clone().add(-1,0,0).getBlock().setType(Material.OAK_LEAVES);
        location.clone().add(0,-1,0).getBlock().setType(Material.OAK_LEAVES);
        location.clone().add(0,0,-1).getBlock().setType(Material.OAK_LEAVES);
        double dx = (random.nextDouble() - 0.5) * 0.5;
        double dz = (random.nextDouble() - 0.5) * 0.5;
        double dy = (random.nextDouble() - 0.5) * 0.5;
    }















}
