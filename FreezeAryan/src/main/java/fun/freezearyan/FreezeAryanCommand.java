package fun.freezearyan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeAryanCommand implements CommandExecutor, Listener {



    public static boolean aryan = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender.getName().equals("Pr0_U1tr4"))){

                if (Bukkit.getPlayerExact("Pr0_U1tr4") != null) {
                    if (!aryan) {
                        aryan = true;
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendMessage(ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + "Aryan is frozen");
                        }
                    } else {
                        aryan = false;
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendMessage(ChatColor.DARK_RED + String.valueOf(ChatColor.BOLD) +"Aryan is free");
                        }

                    }


                } else {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage(ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "aryan is not on server");
                    }
                }

        } else {
            commandSender.sendMessage(ChatColor.YELLOW + String.valueOf(ChatColor.BOLD) + "Aryan... you cant run this command");
        }
        return false;
    }




    @EventHandler
    public void Playermove(PlayerMoveEvent e){
        if (e.getPlayer().getDisplayName().equals("Pr0_U1tr4")){
            if (aryan){
                e.setCancelled(true);
            }
        }
    }
}
