package shateq.easilysit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.Objects;

public final class Easilysit extends JavaPlugin implements Listener {

    public Easilysit() {}

    @Override
    public void onEnable() {
        try {
            Objects.requireNonNull(getCommand("sit")).setExecutor(new Nice());
			Bukkit.getServer().getPluginManager().registerEvents(this, this);
            Bukkit.getLogger().info("No ice");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void stopSitting(EntityDismountEvent e) {
        if(e.getDismounted() instanceof Arrow) {
            Entity ent = e.getDismounted();
            Player p = (Player) e.getEntity();
            ent.remove();
            Bukkit.getScheduler().runTaskLater(this, () -> p.teleport(p.getLocation().add(0, 1, 0)), 1);
        }
    }
}
