package missingdrift.survivalcore.commands;

import missingdrift.survivalcore.Survivalcore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import missingdrift.survivalcore.Survivalcore;

public class ReloadCMD implements CommandExecutor {
    private Survivalcore plugin;

    public ReloadCMD(Survivalcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration messages = this.plugin.getCustomMessagesConfig();
        Player p = (Player)sender;
        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender c = (ConsoleCommandSender)sender;
            if (args.length != 0 && args[0].equalsIgnoreCase("reload"))
                for (String pr : messages.getStringList("PluginReloaded"))
                    c.sendMessage(ChatColor.translateAlternateColorCodes('&', pr));
            return false;
        }
        if (args.length != 0 && args[0].contains("reload"))
            if (p.hasPermission("svcore.reload")) {
                this.plugin.reloadConfig();
                for (String pr : messages.getStringList("PluginReloaded"))
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', pr));
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("NoPermission")));
            }
        return false;
    }
}
