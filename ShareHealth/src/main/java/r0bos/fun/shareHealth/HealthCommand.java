package r0bos.fun.shareHealth;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HealthCommand implements CommandExecutor {


    static boolean isSharing = false;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        isSharing = !isSharing;

        Bukkit.broadcastMessage(isSharing ? ChatColor.GREEN + ChatColor.BOLD.toString() + "Sharing damage is enabled" :
                ChatColor.RED + ChatColor.BOLD.toString() +"Sharing damage is disabled");


        return false;
    }
}
