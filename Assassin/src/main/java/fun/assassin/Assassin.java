package fun.assassin;

import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;


public final class Assassin extends JavaPlugin implements CommandExecutor, Listener {

    @Override
    public void onEnable() {
        new AssassinLoop(this).runTaskTimer(this,0,1);




        getServer().getPluginManager().registerEvents(this, this);

        getCommand("assassin").setExecutor(this);




    }

    



    public Set<UUID> assassinCollection = new HashSet<>();
    public Map<UUID, Long> noMove = new HashMap<>();



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player p = (Player) commandSender;
        if (strings.length < 1) {
            p.sendMessage(ChatColor.RED + ("Invalid Usage. Correct Usage: /assassin player"));
            return false;
        }
        if (strings[0] != null) {
            if (Bukkit.getPlayerExact(strings[0]) != null) {
                    if (!(assassinCollection.contains(Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).getUniqueId()))) {
                        assassinCollection.add(Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).getUniqueId());
                        Bukkit.getPlayerExact(strings[0]).getInventory().addItem(new ItemStack(Material.COMPASS));
                        Bukkit.broadcastMessage(ChatColor.GREEN + strings[0] + " is an assassin. Watch out :eyes:");






                    } else {
                        assassinCollection.remove(Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).getUniqueId());
                        Bukkit.broadcastMessage(ChatColor.RED + strings[0] + " is no longer an assassin");
                    }

                } else {
                    commandSender.sendMessage(ChatColor.RED + "Player is not online");
                }

        } else {
            commandSender.sendMessage(ChatColor.RED + "Enter a player name for second argument");
        }
        return false;
    }


    @EventHandler
    public void PlayerMove(PlayerMoveEvent e) {

            long time = (Long) this.noMove.getOrDefault(e.getPlayer().getUniqueId(), 0L);
            if (time > System.currentTimeMillis()) {
                if (e.getPlayer().getVehicle() == null){
                    e.setCancelled(true);
                }

            } else {
                this.noMove.remove(e.getPlayer().getUniqueId());

            }

    }


    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        this.noMove.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (e.getItem() != null && assassinCollection.contains(e.getPlayer().getUniqueId())){
            CompassMeta compassMeta2 = (CompassMeta) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
            if (e.getItem().getType().equals(Material.COMPASS) || Objects.requireNonNull(compassMeta2).hasLodestone()){
                Player player = e.getPlayer();
                Player result = null;
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
                    double lastDistance = Double.MAX_VALUE;
                    for(Player p : player.getWorld().getPlayers()) {
                        if(player == p || assassinCollection.contains(p.getUniqueId())) {
                            continue;
                        }

                        double distance = player.getLocation().distance(p.getLocation());
                        if(distance < lastDistance) {
                            lastDistance = distance;
                            result = p;

                        }

                    }

                }




                if(result != null) {
                    CompassMeta compassMeta = (CompassMeta) e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                    assert compassMeta != null;
                    compassMeta.setLodestoneTracked(false);
                    compassMeta.setLodestone(result.getLocation());
                    e.getPlayer().getInventory().getItemInMainHand().setItemMeta(compassMeta);
                    player.sendMessage(ChatColor.GREEN + "You are tracking " + result.getDisplayName());
                } else {
                    player.sendMessage(ChatColor.RED + "There are no players to track");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        if (assassinCollection.contains(event.getEntity().getUniqueId())) {
            event.getDrops().removeIf((next) -> next.getType() == Material.COMPASS);
        }

    }

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        if (assassinCollection.contains(event.getPlayer().getUniqueId()) && event.getItemDrop().getItemStack().getType() == Material.COMPASS) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (assassinCollection.contains(player.getUniqueId())) {
            player.getInventory().addItem(new ItemStack(Material.COMPASS));
        }

    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (this.noMove.containsKey(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        if (this.noMove.containsKey(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player) {
                if (!this.assassinCollection.contains(event.getEntity().getUniqueId()) && !this.noMove.containsKey(event.getDamager().getUniqueId())) {
                    if (this.assassinCollection.contains(event.getDamager().getUniqueId()) && !this.assassinCollection.contains(event.getEntity().getUniqueId())) {
                        event.setDamage(500.0);
                    }
                } else {
                    event.setCancelled(true);
                }
            } else if (event.getDamager() instanceof Arrow && ((Arrow)event.getDamager()).getShooter() instanceof Player && event.getEntity() instanceof Player) {
                event.setCancelled(true);
            }

        }

    }


    @EventHandler
    public void Advancement(PlayerAdvancementDoneEvent e){
        TextComponent hoverable = new TextComponent("§a" + Objects.requireNonNull(e.getAdvancement().getDisplay()).getTitle());
        hoverable.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new Text("§a" + e.getAdvancement().getDisplay().getTitle() + "\n" + e.getAdvancement().getDisplay().getDescription())));

        TextComponent textComponent = new TextComponent(e.getPlayer().getDisplayName() + " has made the advancement §a[");
        TextComponent textComponent1 = new TextComponent("§a]");
        textComponent.addExtra(hoverable);
        textComponent.addExtra(textComponent1);


        if (this.assassinCollection.contains(e.getPlayer().getUniqueId())){
            for (UUID id : this.assassinCollection){
                Objects.requireNonNull(Bukkit.getPlayer(id)).spigot().sendMessage(textComponent);
            }
        } else {
            Bukkit.spigot().broadcast(textComponent);
        }
    }
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e){
        if (Boolean.TRUE.equals(e.getPlayer().getWorld().getGameRuleValue(GameRule.ANNOUNCE_ADVANCEMENTS))) {
            e.getPlayer().getWorld().setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        }
    }











    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
