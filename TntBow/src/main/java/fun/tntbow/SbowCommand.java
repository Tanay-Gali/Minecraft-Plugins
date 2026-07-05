package fun.tntbow;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class SbowCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            if (player.hasPermission("sbow.permission")){
                ItemStack bow = new ItemStack(Material.BOW,1);
                ItemMeta bowmeta = (ItemMeta) bow.getItemMeta();
                bowmeta.setDisplayName("S Bow");
                ((Damageable)bowmeta).setDamage(382);
                bow.setItemMeta(bowmeta);
                player.sendMessage(ChatColor.GREEN + "You have acquired the S bow");
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1,0);
                ((Player) commandSender).getInventory().addItem(bow);
            }

        }


        return false;
    }
}
