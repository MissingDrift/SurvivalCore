package missingdrift.survivalcore.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import missingdrift.survivalcore.Survivalcore;

public class PlayerJoinEvents implements Listener {
    public Survivalcore plugin;

    public PlayerJoinEvents(Survivalcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        FileConfiguration config = this.plugin.getConfig();
        FileConfiguration messages = this.plugin.getCustomMessagesConfig();
        Player p = e.getPlayer();
        boolean hasjoined = p.hasPlayedBefore();
        if (!hasjoined) {
            config.set("UserData.", p.getName());
            this.plugin.saveConfig();
        }
        if (config.getBoolean("joinmessage") == true)
            for (String jm : messages.getStringList("JoinMessage"))
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', jm).replace("{player}", p.getName()));
        if (config.getBoolean("joinplayermessage") == true) {
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("JoinPlayerMessage")).replace("{player}", p.getName()));
        } else {
            e.setJoinMessage(null);
        }
    }
}