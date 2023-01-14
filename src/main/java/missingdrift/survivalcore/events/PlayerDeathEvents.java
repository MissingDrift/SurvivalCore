package missingdrift.survivalcore.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import missingdrift.survivalcore.Survivalcore;

public class PlayerDeathEvents implements Listener {
    private Survivalcore plugin;

    public PlayerDeathEvents(Survivalcore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBackDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Location loc = p.getLocation();
        FileConfiguration messages = this.plugin.getCustomMessagesConfig();
        FileConfiguration users = this.plugin.getCustomUsersConfig();
        if (!(e.getEntity() instanceof Player))
            return;
        if (!p.hasPermission("svcore.back"))
            return;
        users.set(p.getName() + ".Locations.Back", loc);
        this.plugin.saveConfig();
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("BackToDeathPoint")));
    }
}
