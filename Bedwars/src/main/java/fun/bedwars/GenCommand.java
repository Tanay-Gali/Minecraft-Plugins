package fun.bedwars;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.concurrent.atomic.AtomicInteger;

public class GenCommand implements CommandExecutor {

    Plugin plugin;
    BukkitTask irontask;
    BukkitTask goldtask;
    BukkitTask emeraldgenerator;
    BukkitTask lapisgenerator;
    NamespacedKey key;
    NamespacedKey key2;

    int emamount;
    int emamount2;


    public GenCommand(Plugin plugin){
        this.plugin = plugin;

    }

    public boolean gen;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        emamount2 = 0;
        emamount = 0;
        Vector vector = new Vector(0.5,0.5,0.5);
        if (!gen){
            gen = true;
            Player p = (Player) commandSender;
            p.sendMessage(ChatColor.GREEN + "Generator's are on");
            irontask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                Item item1 = p.getWorld().dropItem(new Location(p.getWorld(),-634,78,-246).add(0.5,0.5,0.5),new ItemStack(Material.IRON_INGOT));
                Item item2 = p.getWorld().dropItem(new Location(p.getWorld(),-518,78,-246).add(0.5,0.5,0.5),new ItemStack(Material.IRON_INGOT));
                Item item3 = p.getWorld().dropItem(new Location(p.getWorld(),-576,78,-304).add(0.5,0.5,0.5),new ItemStack(Material.IRON_INGOT));
                Item item4 = p.getWorld().dropItem(new Location(p.getWorld(),-576,78,-188).add(0.5,0.5,0.5),new ItemStack(Material.IRON_INGOT));
                item1.setVelocity(item1.getVelocity().zero());
                item2.setVelocity(item1.getVelocity().zero());
                item3.setVelocity(item1.getVelocity().zero());
                item4.setVelocity(item1.getVelocity().zero());


            },1,25);
            goldtask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                Item item1 = p.getWorld().dropItem(new Location(p.getWorld(),-634,78,-246).add(vector),new ItemStack(Material.GOLD_INGOT));
                Item item2 = p.getWorld().dropItem(new Location(p.getWorld(),-518,78,-246).add(vector),new ItemStack(Material.GOLD_INGOT));
                Item item3 = p.getWorld().dropItem(new Location(p.getWorld(),-576,78,-304).add(vector),new ItemStack(Material.GOLD_INGOT));
                Item item4 = p.getWorld().dropItem(new Location(p.getWorld(),-576,78,-188).add(vector),new ItemStack(Material.GOLD_INGOT));
                item1.setVelocity(item1.getVelocity().zero());
                item2.setVelocity(item1.getVelocity().zero());
                item3.setVelocity(item1.getVelocity().zero());
                item4.setVelocity(item1.getVelocity().zero());

            },1,130);
            emeraldgenerator = Bukkit.getScheduler().runTaskTimer(plugin, () -> {

                if (emamount < 7){
                    Item item1 = p.getWorld().dropItem(new Location(p.getWorld(), -576, 88,-246).add(vector),new ItemStack(Material.EMERALD));
                    key = new NamespacedKey(plugin,"emerald");
                    PersistentDataContainer emcontainer = item1.getPersistentDataContainer();
                    emcontainer.set(key, PersistentDataType.STRING,"emerald");
                    item1.setVelocity(item1.getVelocity().zero());
                    item1.setUnlimitedLifetime(true);
                    emamount++;





                }
                if (emamount2 < 7){
                    Item item2 = p.getWorld().dropItem(new Location(p.getWorld(), -576, 77,-246).add(vector),new ItemStack(Material.EMERALD));

                    key2 = new NamespacedKey(plugin,"emerald1");
                    PersistentDataContainer emcontainer1 = item2.getPersistentDataContainer();
                    emcontainer1.set(key2, PersistentDataType.STRING,"emerald1");
                    item2.setVelocity(item2.getVelocity().zero());
                    item2.setUnlimitedLifetime(true);
                    emamount2++;

                }




            },1,45*20);
            lapisgenerator = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                Item item1 = p.getWorld().dropItem(new Location(p.getWorld(),-551,73,-271).add(0.5,0.5,0.5),new ItemStack(Material.LAPIS_LAZULI));
                Item item2 = p.getWorld().dropItem(new Location(p.getWorld(),-601,73,-271).add(0.5,0.5,0.5),new ItemStack(Material.LAPIS_LAZULI));
                Item item3 = p.getWorld().dropItem(new Location(p.getWorld(),-601,73,-221).add(0.5,0.5,0.5),new ItemStack(Material.LAPIS_LAZULI));
                Item item4 = p.getWorld().dropItem(new Location(p.getWorld(),-551,73,-221).add(0.5,0.5,0.5),new ItemStack(Material.LAPIS_LAZULI));
                item1.setVelocity(item1.getVelocity().zero());
                item2.setVelocity(item1.getVelocity().zero());
                item3.setVelocity(item1.getVelocity().zero());
                item4.setVelocity(item1.getVelocity().zero());

            },1,30*20);







        } else {
            Player p = (Player) commandSender;
            p.sendMessage(ChatColor.GREEN + "Generator's are off");
            irontask.cancel();
            goldtask.cancel();
            emeraldgenerator.cancel();
            lapisgenerator.cancel();
            gen = false;


        }






        return false;
    }
}

