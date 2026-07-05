package r0bos.fun.fillWorld;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class FillWorldCommand implements CommandExecutor {
    Plugin plugin;
    public FillWorldCommand(Plugin plugin){
        this.plugin = plugin;
    }



    static boolean fillWorldActive = false;
    BukkitTask fillWorldCalc;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
    if (!fillWorldActive) {
        if (strings[0] == null) {
            Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "You need a block to enter to fill the world");
            return false;
        }

        for (Material m : Material.values()) {
            if (strings[0].equalsIgnoreCase(m.name())) {
                fillWorldActive = true;
                fillWorldCalc = new FillWorldCalc(m).runTaskTimer(plugin,0,5);
                Bukkit.broadcastMessage(ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + "Fill World has started with the block: " + m.name());
            }
        }
        if (fillWorldCalc == null){
            Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "You need a block to enter to fill the world");
        }
    } else{
        if (!fillWorldCalc.isCancelled()) {
            fillWorldCalc.cancel();
            Bukkit.broadcastMessage(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Fill World has stopped");
        }
    }



        return false;
    }
}
