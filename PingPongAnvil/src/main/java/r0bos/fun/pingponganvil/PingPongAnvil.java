package r0bos.fun.pingponganvil;


import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import javax.annotation.PropertyKey;
import java.util.*;

public final class PingPongAnvil extends JavaPlugin implements Listener {




    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getItem() != null){
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                if (e.getItem().getType().equals(Material.ANVIL)) {
                    anvil(e.getPlayer());
                }
            }
        }
    }
    public void anvil(Player p){

        FallingBlock anvil = p.getWorld().spawnFallingBlock(p.getEyeLocation(),Material.ANVIL.createBlockData());
        anvil.setVelocity(p.getEyeLocation().getDirection().multiply(3));
        p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_PLACE,1f,1.01f);
        new AnvilTimer(anvil,this).runTaskTimer(this,0,1);
    }





}

