package fun.randomitemchallenge;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RandomItemCommand implements CommandExecutor, Listener {
    boolean activated;

    BukkitTask task;

    long num;
    long delay = 300;

    Plugin plugin;

    public RandomItemCommand(Plugin plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {






        if (commandSender instanceof Player) {
            Player p = (Player)commandSender;
            if (p.hasPermission("randomitemchallenge.admin")) {
                if (strings.length < 1) {
                    p.sendMessage(ChatColor.RED + ("Invalid Usage. Correct Usage: /randomitemchallenge <on/off> or the delay"));
                    return false;
                }
                if (!strings[0].equalsIgnoreCase("off") && !strings[0].equalsIgnoreCase("disable")) {
                    try{
                        num = Long.parseLong(strings[0]);
                    } catch (Exception e){

                    }
                    if (num != 0){
                        delay = num;
                        commandSender.sendMessage("You set the delay to " + num);
                    }

                    if (strings[0].equalsIgnoreCase("on") || strings[0].equalsIgnoreCase("enable")) {
                        World world = p.getWorld();
                        p.sendMessage("Random Item Challenge activated in world " + world.getName());
                        activated = true;
                        for (Player player : Bukkit.getOnlinePlayers()){
                            player.teleport(((Player) commandSender).getWorld().getSpawnLocation());
                            player.setFoodLevel(20);
                            player.setHealth(20);
                            player.getInventory().clear();

                        }
                        task = new RandomItemChallengeMain(((Player) commandSender).getWorld(),plugin).runTaskTimer(plugin,delay*20,delay*20);
                    }




                } else {
                    World world = p.getWorld();
                    p.sendMessage("Random Item Challenge de-activated in world " + world.getName());
                    task.cancel();
                    for (Player player : Bukkit.getOnlinePlayers()){
                        player.teleport(((Player) commandSender).getWorld().getSpawnLocation());
                        player.setFoodLevel(20);
                        player.setHealth(20);
                        player.getInventory().clear();
                    }
                    activated = false;
                }
            } else {
                p.sendMessage(ChatColor.RED + ("You do not have permission to execute this command!"));
            }
        }



        return false;
    }
    @EventHandler
    public void FoodLevel(FoodLevelChangeEvent e){
        if (activated){
            e.getEntity().setFoodLevel(20);
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void PlayerPickup(EntityPickupItemEvent e){
        if (e.getEntity() instanceof Player){
            ItemMeta itemMeta = e.getItem().getItemStack().getItemMeta();
            List<String> lore = itemMeta.getLore();
            if (activated && lore != null){
                if ((Objects.requireNonNull(lore).contains(ChatColor.WHITE + Objects.requireNonNull(((Player) e.getEntity()).getPlayer()).getName()))){
                    e.setCancelled(false);
                } else {
                    e.setCancelled(true);
                }
            } else if (activated) {
                e.setCancelled(true);

            }
        }
    }
    @EventHandler
    public void BlockBreak(BlockBreakEvent e){
        if (activated){
            e.setDropItems(false);
        }
    }
}
