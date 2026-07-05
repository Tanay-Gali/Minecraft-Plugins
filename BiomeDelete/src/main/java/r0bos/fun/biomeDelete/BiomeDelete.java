package r0bos.fun.biomeDelete;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;



public final class BiomeDelete extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {


        getServer().getPluginManager().registerEvents(this,this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




    @EventHandler
    public void PlayerMove(PlayerMoveEvent e){

        Player p = e.getPlayer();

        Biome biome = p.getLocation().getBlock().getBiome();
        e.getPlayer().sendMessage(String.valueOf(ChatMessageType.ACTION_BAR),biome.toString());
    }

}
