package r0bos.fun.shareInventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

    static boolean isEnabled = false;
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        isEnabled = !isEnabled;

        Bukkit.broadcastMessage(isEnabled ? ChatColor.GREEN + ChatColor.BOLD.toString() +
                "ShareInventory Enabled" : ChatColor.RED + ChatColor.BOLD.toString() + "ShareInventory Disabled");
        return false;
    }
}
