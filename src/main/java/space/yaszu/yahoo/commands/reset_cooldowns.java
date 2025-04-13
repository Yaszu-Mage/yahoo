package space.yaszu.yahoo.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class reset_cooldowns implements CommandExecutor {

    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player) {
            if (sender.isOp()) {
            PersistentDataContainer player_date = ((Player) sender).getPersistentDataContainer();
            NamespacedKey time_cooldown = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "time_cooldown");
            NamespacedKey gun_cooldown = new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("Yahoo"), "gun_cooldown");
            NamespacedKey cooldown = new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("Yahoo"), "cooldown");
            NamespacedKey alchemic_cooldown = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"alchemic_cooldown");
            player_date.set(time_cooldown, PersistentDataType.STRING, "off");
            player_date.set(gun_cooldown, PersistentDataType.STRING, "off");
            player_date.set(cooldown, PersistentDataType.STRING, "off");
            player_date.set(alchemic_cooldown, PersistentDataType.BOOLEAN, false);
            ((Player) sender).sendRawMessage("Cooldowns Cleared!");
            return true;
        } else {
            return false;
        }
    }
return false;}}
