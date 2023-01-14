package missingdrift.survivalcore.commands;

import missingdrift.survivalcore.Survivalcore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import missingdrift.survivalcore.Survivalcore;

public class EnchantCMD implements CommandExecutor {
    private Survivalcore plugin;

    public EnchantCMD(Survivalcore plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        FileConfiguration messages = this.plugin.getCustomMessagesConfig();
        Player p = (Player)sender;
        PlayerInventory pi = p.getInventory();
        if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must be a player"));
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("enchant")) {
            int EL = Integer.parseInt(args[1]);
            if (args.length < 2) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', messages.getString("UnkownCommand"))).replace("{usage}", "/enchant <enchantment> <level>"));
                return true;
            }
            if (args.length == 2)
                if (p.hasPermission("svcore.enchant")) {
                    if (args[0].equalsIgnoreCase("sharpness") || args[0].equalsIgnoreCase("damage_all")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Sharpness")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("protection") || args[0].equalsIgnoreCase("protection_environmental")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Protection")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("unbreaking") || args[0].equalsIgnoreCase("durability")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.DURABILITY, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Unbreaking")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("fire_protection") || args[0].equalsIgnoreCase("protection_fire")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Fire Protection")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("blast_protection") || args[0].equalsIgnoreCase("protection_explosions")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Blast Protection")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("projectile_protection") || args[0].equalsIgnoreCase("protection_projectile")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Projectile Protection")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("thorns")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.THORNS, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Thorns")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("knockback")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.KNOCKBACK, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Knockback")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("looting") || args[0].equalsIgnoreCase("loot_bonus_mobs")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Looting")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("fortune") || args[0].equalsIgnoreCase("loot_bonus_blocks")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Fortune")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("flame") || args[0].equalsIgnoreCase("arrow_fire")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_FIRE, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Flame")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("power") || args[0].equalsIgnoreCase("arrow_damage")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Power")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("infinite") || args[0].equalsIgnoreCase("arrow_infinite")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_INFINITE, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Infinite")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("punch") || args[0].equalsIgnoreCase("arrow_knockback")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Punch")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("silk_touch")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.SILK_TOUCH, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Silk Touch")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("efficiency") || args[0].equalsIgnoreCase("dig_speed")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.DIG_SPEED, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Efficiency")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("feather_falling") || args[0].equalsIgnoreCase("protection_fall")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_FALL, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Feather Falling")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("fire") || args[0].equalsIgnoreCase("fire_aspect")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.FIRE_ASPECT, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Fire Aspect")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("damage_arthropods") || args[0].equalsIgnoreCase("arthropods_damage")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Bane of the Arthrpods")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("depth_strider")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Depth Strider")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("respiration") || args[0].equalsIgnoreCase("oxygen")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.OXYGEN, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Respiration")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("smite") || args[0].equalsIgnoreCase("damage_undead")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Smite")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("aqua_affinity") || args[0].equalsIgnoreCase("water_worker")) {
                        pi.getItemInHand().addUnsafeEnchantment(Enchantment.WATER_WORKER, EL);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("EnchantmentSet"))
                                .replace("{enchantment}", "Aqua Affinity")
                                .replace("{level}", args[1])
                                .replace("{item}", pi.getItemInHand().getType().name()));
                    }
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("NoPermission")));
                }
        }
        return true;
    }
}