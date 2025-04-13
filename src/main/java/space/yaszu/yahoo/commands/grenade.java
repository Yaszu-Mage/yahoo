package space.yaszu.yahoo.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class grenade implements CommandExecutor {
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
            Player player = (Player) sender;
            ItemStack grenade = new ItemStack(Material.RECOVERY_COMPASS);
            ItemMeta meta = grenade.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "Unusual Grenade");
            meta.setItemModel(NamespacedKey.minecraft("unusual_grenade"));
            player.give(grenade);
            player.sendMessage("§aYou have been given an unusual grenade.");
            return true;
        } else {
            return false;
        }
    }return false;
}}
