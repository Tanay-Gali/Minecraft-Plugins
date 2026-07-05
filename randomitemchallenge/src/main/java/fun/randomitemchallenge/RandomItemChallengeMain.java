package fun.randomitemchallenge;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RandomItemChallengeMain extends BukkitRunnable implements Listener {
    private static final ArrayList<Material> materials = new ArrayList<>();
    World world;
    Plugin plugin;
    public RandomItemChallengeMain(World world, Plugin plugin){
        this.world = world;
        this.plugin = plugin;
    }



    @Override
    public void run() {

        for (String item : plugin.getConfig().getStringList("items")) {
            for (Material m : Material.values()) {
                if (item.equalsIgnoreCase(m.name())) {
                    materials.add(m);
                }
            }
        }




        for (Player p : world.getPlayers()) {
            if (!p.getGameMode().equals(GameMode.SPECTATOR)){
                p.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "→ITEM DROP HAS ARRIVED!");
                Location spawnitems = p.getLocation();
                Random random = new Random();
                int length = plugin.getConfig().getStringList("items").size();
                int rng = random.nextInt(length);
                ItemStack selected = new ItemStack(materials.get(rng), 1);
                if (selected.getType().getMaxStackSize() == 16) {
                    selected = new ItemStack(materials.get(rng), 16);
                } else if (selected.getType().getMaxStackSize() == 1) {
                    selected = new ItemStack(materials.get(rng), 1);
                } else if (selected.getType().getMaxStackSize() == 64) {
                    selected = new ItemStack (materials.get(rng), 64);
                }

                ItemMeta meta = selected.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.WHITE + p.getName());
                meta.setLore(lore);
                selected.setItemMeta(meta);

                for (int i = 0; i < 15; ++i) {
                    world.dropItem(spawnitems, selected);
                }
            }

        }


    }


}

