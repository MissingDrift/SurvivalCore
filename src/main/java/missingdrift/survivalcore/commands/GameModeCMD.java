package missingdrift.survivalcore.commands;

import missingdrift.survivalcore.Survivalcore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import missingdrift.survivalcore.Survivalcore;

public class GameModeCMD implements CommandExecutor {
    private Survivalcore plugin;

    public GameModeCMD(Survivalcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        Player p = (Player)sender;
        FileConfiguration messages = this.plugin.getCustomMessagesConfig();
        if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must be a player"));
        } else if (args.length == 1) {
            if (p.hasPermission("svcore.gamemode")) {
                if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("GameMode")).replace("{player}", p.getName()).replace("{gamemode}", "Survival"));
                } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("GameMode")).replace("{player}", p.getName()).replace("{gamemode}", "Creative"));
                } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("GameMode")).replace("{player}", p.getName()).replace("{gamemode}", "Adventure"));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', messages.getString("UnkownCommand"))).replace("{usage}", "/gamemode <0-1-2>"));
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("NoPermission")));
            }
        } else if (args.length == 2) {
            Player a = Bukkit.getPlayer(args[1]);
            if (a == null) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("PlayerNull")).replace("{player}", args[0]));
                return true;
            }
            if (p.hasPermission("svcore.gamemode.others")) {
                if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s")) {
                    a.setGameMode(GameMode.SURVIVAL);
                    a.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("GameModeOthers")).replace("{player}", p.getName()).replace("{gamemode}", "Survival"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("GameMode")).replace("{player}", p.getName()).replace("{gamemode}", "Survival"));
                } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c")) {
                    a.setGameMode(GameMode.CREATIVE);
                    a.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("GameModeOthers")).replace("{player}", p.getName()).replace("{gamemode}", "Creative"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("GameMode")).replace("{player}", p.getName()).replace("{gamemode}", "Creative"));
                } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a")) {
                    a.setGameMode(GameMode.ADVENTURE);
                    a.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("GameModeOthers")).replace("{player}", p.getName()).replace("{gamemode}", "Adventure"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("GameMode")).replace("{player}", p.getName()).replace("{gamemode}", "Adventure"));
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', messages.getString("UnkownCommand"))).replace("{usage}", "/gamemode <0-1-2> <player>"));
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("NoPermission")));
            }
        }
        return true;
    }
}