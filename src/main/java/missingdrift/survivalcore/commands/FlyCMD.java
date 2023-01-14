package missingdrift.survivalcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import missingdrift.survivalcore.Survivalcore;

public class FlyCMD implements CommandExecutor {
    private Survivalcore plugin;

    public FlyCMD(Survivalcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must be a player"));
            return true;
        }
        Player p = (Player)sender;
        FileConfiguration messages = this.plugin.getCustomMessagesConfig();
        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (args.length > 1) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', messages.getString("UnkownCommand"))).replace("{usage}", "/fly [player]"));
                return true;
            }
            if (args.length == 0)
                if (p.hasPermission("svcore.fly")) {
                    if (!p.getAllowFlight()) {
                        p.setAllowFlight(true);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("FlyON")).replace("{player}", p.getName()));
                    } else {
                        p.setAllowFlight(false);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("FlyOFF")).replace("{player}", p.getName()));
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("NoPermission")));
                }
            if (args.length == 1) {
                Player a = Bukkit.getPlayer(args[0]);
                if (a == null) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("PlayerNull")).replace("{player}", args[0]));
                    return true;
                }
                if (p.hasPermission("svcore.fly.others")) {
                    if (!a.getAllowFlight()) {
                        a.setAllowFlight(true);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("FlyOthersON")).replace("{player}", a.getName()));
                        a.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("FlyON")).replace("{player}", a.getName()));
                    } else {
                        a.setAllowFlight(false);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("FlyOthersOFF")).replace("{player}", a.getName()));
                        a.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("FlyOFF")).replace("{player}", a.getName()));
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("NoPermission")));
                }
            }
        }
        return true;
    }
}