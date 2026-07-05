package fun.compasstime;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Compass implements CommandExecutor {

    public static Player sender;
    public static ItemStack item;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            item = new ItemStack(Material.COMPASS, 1);
            ((Player) commandSender).getInventory().addItem(item);
            sender = (Player) commandSender;


        }


        return false;
    }
}
