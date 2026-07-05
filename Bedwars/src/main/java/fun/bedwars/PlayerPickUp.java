package fun.bedwars;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.BoundingBox;

import java.util.Objects;


public class PlayerPickUp implements Listener {


    GenCommand genCommand;
    public PlayerPickUp(GenCommand command){
        this.genCommand = command;
    }

    @EventHandler
    public void playerPickUp(EntityPickupItemEvent e){
        if (e.getEntity() instanceof Player){
            if (e.getItem().getItemStack().getType().equals(Material.EMERALD) && e.getItem().getPersistentDataContainer().has(genCommand.key, PersistentDataType.STRING)){
                e.getItem().getPersistentDataContainer().remove(genCommand.key);
                genCommand.emamount = 0;


            } else if (e.getItem().getItemStack().getType().equals(Material.EMERALD) && e.getItem().getPersistentDataContainer().has(genCommand.key2,PersistentDataType.STRING)) {
                e.getItem().getPersistentDataContainer().remove(genCommand.key2);
                genCommand.emamount2 = 0;




            }
        }
    }
}
