package missingdrift.survivalcore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import missingdrift.survivalcore.Survivalcore;

public class MsgCMD implements CommandExecutor {
    private Survivalcore plugin;

    public MsgCMD(Survivalcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        String msg = "";
        Player p = (Player)sender;
        FileConfiguration messages = this.plugin.getCustomMessagesConfig();
        if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must be a player"));
            return true;
        }
        if (args.length >= 2) {
            try {
                Player z = sender.getServer().getPlayerExact(args[0]);
                for (int i = 1; i < args.length; i++)
                    msg = msg + args[i] + "";
                z.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("MsgFormat")).replace("{sender}", p.getName()).replace("{receiver}", z.getName()).replace("{message}", msg));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("MsgFormat")).replace("{sender}", p.getName()).replace("{receiver}", z.getName()).replace("{message}", msg));
            } catch (NullPointerException e) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("PlayerNull")).replace("{player}", args[0]));
            }
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', messages.getString("UnkownCommand"))).replace("{usage}", "/msg <player> <message>"));
        }
        return true;
    }
}
