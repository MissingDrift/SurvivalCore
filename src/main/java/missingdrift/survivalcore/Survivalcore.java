package missingdrift.survivalcore;

import java.io.File;
import java.security.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import missingdrift.survivalcore.commands.BackCMD;
import missingdrift.survivalcore.commands.EnchantCMD;
import missingdrift.survivalcore.commands.FlyCMD;
import missingdrift.survivalcore.commands.GameModeCMD;
import missingdrift.survivalcore.commands.HomeCMD;
import missingdrift.survivalcore.commands.MsgCMD;
import missingdrift.survivalcore.commands.ReloadCMD;
import missingdrift.survivalcore.events.AsyncPlayerChatEvents;
import missingdrift.survivalcore.events.PlayerDeathEvents;
import missingdrift.survivalcore.events.PlayerJoinEvents;
import missingdrift.survivalcore.events.PlayerTeleportEvents;

public final class Survivalcore extends JavaPlugin {

    private String authors;
    private FileConfiguration customMessagesConfig;
    private String rutaConfig;
    private FileConfiguration customUsersConfig;
    private Object perms;


    @Override
    public void onEnable() {
        String rutaConfig;
        File customUsersConfigFile;
        File customMessagesConfigFile;
        FileConfiguration customUsersConfig;
        FileConfiguration customMessagesConfig;
        PluginDescriptionFile pdffile = getDescription();
        Permission perms = null;

        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8---------- &2SurvivalCore &8----------"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&f* Authors&8: MissingDrift"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&f* Version&8: 1.0"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4(!) &cVault not found"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSurvivalCore disabled"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8---------- &2SurvivalCore &8----------"));
        }
    }


    private void Chat() {
    }

    public void onDisable () {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8---------- &2SurvivalCore &8----------"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&f* Authors&8: &7MissingDrift"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&f* Version&8: &71.0"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSurvivalCore disabled"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8---------- &2SurvivalCore &8----------"));
            this.perms = null;
        }

        public void registerCommands () {
            getCommand("svcore").setExecutor((CommandExecutor) new ReloadCMD(this));
            getCommand("home").setExecutor((CommandExecutor) new HomeCMD(this));
            getCommand("fly").setExecutor((CommandExecutor) new FlyCMD(this));
            getCommand("message").setExecutor((CommandExecutor) new MsgCMD(this));
            getCommand("gamemode").setExecutor((CommandExecutor) new GameModeCMD(this));
            getCommand("enchant").setExecutor((CommandExecutor) new EnchantCMD(this));
            getCommand("back").setExecutor((CommandExecutor) new BackCMD(this));
        }

        public void registerEvents () {
            getServer().getPluginManager().registerEvents((Listener) new AsyncPlayerChatEvents(this), (Plugin) this);
            getServer().getPluginManager().registerEvents((Listener) new PlayerJoinEvents(this), (Plugin) this);
            getServer().getPluginManager().registerEvents((Listener) new PlayerTeleportEvents(this), (Plugin) this);
            getServer().getPluginManager().registerEvents((Listener) new PlayerDeathEvents(this), (Plugin) this);
        }

        public void registerConfig () {
            File config = new File(getDataFolder(), "config.yml");
            this.rutaConfig = config.getPath();
            if (!config.exists()) {
                getConfig().options().copyDefaults(true);
                saveConfig();
            }
            createCustomConfig();
        }

    private void createCustomConfig() {
    }

    public FileConfiguration getCustomUsersConfig () {
            return this.customUsersConfig;
        }

        public FileConfiguration getCustomMessagesConfig () {
            return this.customMessagesConfig;
        }
        
            }