package fun.bedwars;

import jdk.javadoc.internal.doclint.HtmlTag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.TNT;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.lang.String;


public class BlockPlace implements Listener {
    BukkitTask task;
    Plugin plugin;

    public BlockPlace(Plugin plugin){
        this.plugin = plugin;

    }








    @EventHandler
    public void PlaceBlock(BlockPlaceEvent e){
        if (e.getBlockPlaced().getType() == Material.TNT) {
            e.getBlockPlaced().setType(Material.AIR);
            TNTPrimed tnt = e.getBlockPlaced().getWorld().spawn(e.getBlockPlaced().getLocation().add(0.5,0.5,0.5), TNTPrimed.class);
            tnt.setSource(e.getPlayer());


        }



    }




    @EventHandler
    public void BlockExplosion(BlockExplodeEvent e){
        e.blockList().removeIf(b -> b.getType().equals(Material.RED_BED));
        e.blockList().removeIf(b -> b.getType().equals(Material.BLUE_BED));
        e.blockList().removeIf(b -> b.getType().equals(Material.LIME_BED));
        e.blockList().removeIf(b -> b.getType().equals(Material.YELLOW_BED));
        e.blockList().removeIf(b -> b.getType().equals(Material.SANDSTONE));
        e.blockList().removeIf(b -> b.getType().equals(Material.SANDSTONE_SLAB));
        e.blockList().removeIf(b -> b.getType().equals(Material.RED_SANDSTONE));
        e.blockList().removeIf(b -> b.getType().equals(Material.RED_SANDSTONE_SLAB));
        e.blockList().removeIf(b -> b.getType().equals(Material.SAND));
        e.blockList().removeIf(b -> b.getType().equals(Material.RED_SAND));
        e.blockList().removeIf(b -> b.getType().equals(Material.ACACIA_FENCE));
        e.blockList().removeIf(b -> b.getType().equals(Material.ACACIA_SLAB));
        e.blockList().removeIf(b -> b.getType().equals(Material.ACACIA_PLANKS));
        e.blockList().removeIf(b -> b.getType().equals(Material.STONE_BRICKS));
        e.blockList().removeIf(b -> b.getType().equals(Material.STONE));




}
    @EventHandler
    public void EntityExplosion(EntityExplodeEvent e){

        e.blockList().removeIf(b -> b.getType().equals(Material.RED_BED));
        e.blockList().removeIf(b -> b.getType().equals(Material.BLUE_BED));
        e.blockList().removeIf(b -> b.getType().equals(Material.LIME_BED));
        e.blockList().removeIf(b -> b.getType().equals(Material.YELLOW_BED));
        e.blockList().removeIf(b -> b.getType().equals(Material.SANDSTONE));
        e.blockList().removeIf(b -> b.getType().equals(Material.SANDSTONE_SLAB));
        e.blockList().removeIf(b -> b.getType().equals(Material.RED_SANDSTONE));
        e.blockList().removeIf(b -> b.getType().equals(Material.RED_SANDSTONE_SLAB));
        e.blockList().removeIf(b -> b.getType().equals(Material.SAND));
        e.blockList().removeIf(b -> b.getType().equals(Material.RED_SAND));
        e.blockList().removeIf(b -> b.getType().equals(Material.ACACIA_FENCE));
        e.blockList().removeIf(b -> b.getType().equals(Material.ACACIA_SLAB));
        e.blockList().removeIf(b -> b.getType().equals(Material.ACACIA_PLANKS));
        e.blockList().removeIf(b -> b.getType().equals(Material.STONE_BRICKS));
        e.blockList().removeIf(b -> b.getType().equals(Material.STONE));

    }

    public void elytra(Player p){
        task = Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Objects.requireNonNull(p.getInventory().getChestplate()).setType(Material.AIR);
        },80);


    }


    @EventHandler
    public void ElytraEquip(PlayerInteractEvent e){
        if (e.getItem() != null){
            if (e.getItem().getType().equals(Material.ELYTRA)){
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                    e.getPlayer().setVelocity(new Vector(0,(e.getPlayer().getLocation().getY() + 20),0));
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        e.getPlayer().getInventory().getChestplate().addEnchantment(Enchantment.BINDING_CURSE,1);
                    },1);
                    elytra(e.getPlayer());
                }
            }
        }

    }







}
