package r0bos.fun.pillarsoffortune;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class PillarsOfFortune extends JavaPlugin implements CommandExecutor  {
    static boolean status = false;
    BukkitTask task;
    private static final ArrayList<Material> materials = new ArrayList<>();
    @Override
    public void onEnable() {

            materials.addAll(Arrays.asList(Material.values()));
            getCommand("pillar").setExecutor(this);


    }




    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        status = !status;
        if (status) {
            Random random = new Random();
            task = Bukkit.getScheduler().runTaskTimer(this, () -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.getInventory().addItem(new ItemStack(materials.get(random.nextInt(materials.size()))));
                }
            }, 40, 40);
        } else {
            task.cancel();
        }


        return false;
    }
}
