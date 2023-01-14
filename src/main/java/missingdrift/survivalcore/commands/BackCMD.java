package missingdrift.survivalcore.commands;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import missingdrift.survivalcore.Survivalcore;

public class BackCMD implements CommandExecutor, Listener {
    public static HashMap<Player, Location> backloc = new HashMap<>();

    private Survivalcore plugin;

    public BackCMD(Survivalcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String laber, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must be a player"));
            return true;
        }
        FileConfiguration config = this.plugin.getConfig();
        FileConfiguration messages = this.plugin.getCustomMessagesConfig();
        FileConfiguration users = this.plugin.getCustomUsersConfig();
        Player p = (Player)sender;
        if (!p.hasPermission("svcore.back")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("NoPermission")));
            return true;
        }
        if (args.length == 0) {
            Location backLoc = getLocation(p);
            Location backLoc2 = p.getLocation();
            p.teleport(backLoc);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Back")));
            users.set(p.getName() + ".Locations.Back", backLoc2);
            this.plugin.saveConfig();
        }
        return true;
    }

    private Location getLocation(Player p) {
        FileConfiguration users = this.plugin.getCustomUsersConfig();
        return (Location)users.get(p.getName() + ".Locations.Back");
    }
}
