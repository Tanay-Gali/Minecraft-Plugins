package r0bos.fun.insightManhunt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class HunterCommand implements CommandExecutor {



    InsightManhunt manhunt;
    public HunterCommand(InsightManhunt manhunt){
        this.manhunt = manhunt;
    }




    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player p = (Player) commandSender;
        if (strings.length < 1) {
            p.sendMessage(ChatColor.RED + ("Invalid Usage. Correct Usage: /hunter player"));
            return false;
        }
        if (strings[0] != null) {
            if (Bukkit.getPlayerExact(strings[0]) != null) {
                if (!(manhunt.hunters.contains(Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).getUniqueId()))) {
                    manhunt.hunters.add(Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).getUniqueId());
                    Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).getInventory().addItem(new ItemStack(Material.COMPASS));
                    Bukkit.broadcastMessage(ChatColor.GREEN + strings[0] + " is an hunter. Watch out :eyes:");

                    Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).getInventory().addItem(new ItemStack(Material.COMPASS));






                } else {
                    manhunt.hunters.remove(Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).getUniqueId());
                    Bukkit.broadcastMessage(ChatColor.RED + strings[0] + " is no longer an hunter");
                }

            } else {
                commandSender.sendMessage(ChatColor.RED + "Player is not online");
            }

        } else {
            commandSender.sendMessage(ChatColor.RED + "Enter a player name for second argument");
        }



        return false;
    }
}
