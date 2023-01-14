package missingdrift.survivalcore.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import missingdrift.survivalcore.Survivalcore;

public class AsyncPlayerChatEvents implements Listener {
    public Survivalcore plugin;

    public AsyncPlayerChatEvents(Survivalcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("svcore.chatformat.color"))
            e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        String format = "";
        if (this.plugin.getConfig().getConfigurationSection("groups." ) != null) {
            format = this.plugin.getConfig().getString("groups." + ".format");
        } else {
            format = this.plugin.getConfig().getString("default_format");
        }
        format = format.replace("{player_name}", p.getName());
        format = format.replace("{display_name}", p.getDisplayName());
        format = format.replace("{world}", p.getWorld().getName());
        format = replaceVault(p, format);
        format = ChatColor.translateAlternateColorCodes('&', format);
        format = format.replace("%", "%%");
        format = format.replace("{message}", "%2$s");
        e.setFormat(format);
    }

    public String replaceVault(Player p, String message) {
        String holders = message;
        String rank = null;
        String prefix = null;
        String suffix = null;
        holders = holders.replace("{prefix}", prefix);
        holders = holders.replace("{suffix}", suffix);
        holders = holders.replace("{rank}", rank);
        return holders;
    }
}
