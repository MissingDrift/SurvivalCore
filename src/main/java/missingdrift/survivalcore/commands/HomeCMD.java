package missingdrift.survivalcore.commands;

import missingdrift.survivalcore.Survivalcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import missingdrift.survivalcore.Survivalcore;

public class HomeCMD implements CommandExecutor {
    private Survivalcore plugin;

    public HomeCMD(Survivalcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must be a player"));
            return true;
        }
        FileConfiguration messages = this.plugin.getCustomMessagesConfig();
        FileConfiguration users = this.plugin.getCustomUsersConfig();
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("home")) {
            if (users.getString(p.getName() + ".home") == null) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("NoHomeSet")));
                return true;
            }
            if (p.hasPermission("svcore.home")) {
                World world = Bukkit.getWorld(users.getString("UserData." + p.getName() + ".home.world"));
                double x = users.getDouble(p.getName() + ".home.x");
                double y = users.getDouble(p.getName() + ".home.y");
                double z = users.getDouble(p.getName() + ".home.z");
                double yaw = users.getDouble(p.getName() + ".home.yaw");
                double pitch = users.getDouble(p.getName() + ".home.pitch");
                p.teleport(new Location(world, x, y, z, (float)yaw, (float)pitch));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("TpToHome")).replace("{player}", p.getName()));
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("NoPermission")));
            }
            if (args.length == 1) {
                Player a = Bukkit.getPlayer(args[0]);
                if (a == null) {
                    if (p.hasPermission("svcore.home.others")) {
                        World world = Bukkit.getWorld(users.getString("UserData." + p.getName() + ".home.world"));
                        double x = users.getDouble("UserData." + p.getName() + ".home.x");
                        double y = users.getDouble("UserData." + p.getName() + ".home.y");
                        double z = users.getDouble("UserData." + p.getName() + ".home.z");
                        double yaw = users.getDouble("UserData." + p.getName() + ".home.yaw");
                        double pitch = users.getDouble("UserData." + p.getName() + ".home.pitch");
                        a.teleport(new Location(world, x, y, z, (float)yaw, (float)pitch));
                        a.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("TpToHome")).replace("{player}", p.getName()));
                        users.set("UserData." + p.getName() + ".Locations.Back-X-2", Double.valueOf(p.getLocation().getX()));
                        users.set("UserData." + p.getName() + ".Locations.Back-Y-2", Double.valueOf(p.getLocation().getY()));
                        users.set("UserData." + p.getName() + ".Locations.Back-Z-2", Double.valueOf(p.getLocation().getZ()));
                        users.set("UserData." + p.getName() + ".Locations.Back-World-2", p.getLocation().getWorld().getName());
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("NoPermission")));
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7" + a.getName() + "are offline or not exist"));
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', messages.getString("UnkownCommand"))).replace("{usage}", "/fly <player>"));
            }
        }
        return false;
    }
}