package shateq.easilysit;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Nice implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Location loc = p.getLocation();
            Block b = p.getWorld().getBlockAt(p.getLocation().subtract(0, 1, 0));
            if (!b.getType().equals(Material.AIR) && !b.getType().equals(Material.CAVE_AIR) && !b.getType().equals(Material.VOID_AIR)) {
                if (p.getVehicle() == null) {
                    Entity arrow = Objects.requireNonNull(loc.getWorld()).spawnEntity(Objects.requireNonNull(loc.clone().getWorld()).getBlockAt(loc).getLocation().add(0.5, -0.6, 0.5), EntityType.ARROW);
                    arrow.setGravity(false);
                    arrow.setSilent(true);
                    arrow.setInvulnerable(true);
                    arrow.addPassenger(p);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aDone."));
                    return true;
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have got a vehicle!"));
                    return true;
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot sit mid-air!"));
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "You have to use this command in-game!");
        }
        return false;
    }
}
