package r0bos.fun.voting;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class ButtonCommand implements CommandExecutor, Listener {

    static Location button;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        button = ((Player) commandSender).getLocation().getBlock().getLocation();

        return false;
    }


    @EventHandler
    public void playerInteract(PlayerInteractEvent e){
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = e.getClickedBlock();


            if (clickedBlock != null && clickedBlock.getType() == Material.STONE_BUTTON) {
                if (clickedBlock.getLocation().equals(button)){
                    Voting.pressed = e.getPlayer();
                    Voting.startMeeting();

                    button.getBlock().setType(Material.AIR);
                }
            }
        }
    }
}
