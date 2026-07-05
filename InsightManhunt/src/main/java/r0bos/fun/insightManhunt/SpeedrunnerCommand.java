package r0bos.fun.insightManhunt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class SpeedrunnerCommand implements CommandExecutor {

    InsightManhunt manhunt;

    public SpeedrunnerCommand(InsightManhunt manhunt){
        this.manhunt = manhunt;
    }



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if (strings.length < 1) {
            p.sendMessage(ChatColor.RED + ("Invalid Usage. Correct Usage: /runner player"));
            return false;
        }
        if (strings[0] != null) {
            if (Bukkit.getPlayerExact(strings[0]) != null) {
                if (!(manhunt.runners.contains(Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).getUniqueId()))) {
                    manhunt.runners.add(Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).getUniqueId());
                    ItemStack item = new ItemStack(Material.SPYGLASS);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ChatColor.GOLD + "Spectate Switch");
                    item.setItemMeta(meta);
                    Bukkit.getPlayerExact(strings[0]).getInventory().addItem(item);
                    Bukkit.broadcastMessage(ChatColor.GREEN + strings[0] + " is an runner. Watch out :eyes:");






                } else {
                    manhunt.runners.remove(Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).getUniqueId());
                    Bukkit.broadcastMessage(ChatColor.RED + strings[0] + " is no longer an runner");
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
