package missingdrift.survivalcore.events;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import missingdrift.survivalcore.Survivalcore;

public class PlayerTeleportEvents implements Listener {
    private Survivalcore plugin;

    public <SurvivalCore> PlayerTeleportEvents(SurvivalCore plugin) {
        this.plugin = (Survivalcore) plugin;
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        FileConfiguration users = this.plugin.getCustomUsersConfig();
        if (!e.getPlayer().hasPermission("svcore.back"))
            return;
        Location loc = p.getLocation();
        users.set(p.getName() + ".Locations.Back", loc);
        this.plugin.saveConfig();
    }
}