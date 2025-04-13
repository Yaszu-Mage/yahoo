package space.yaszu.yahoo.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class time_dialator implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        if (sender.isOp()){
        Player player = (Player) sender;
        NamespacedKey glove = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "time");
        // Give the player a Recovery Compass
        ItemStack recoveryCompass = new ItemStack(Material.RECOVERY_COMPASS);
        ItemMeta meta = recoveryCompass.getItemMeta();
        meta.getPersistentDataContainer().set(glove, PersistentDataType.STRING, "time");
        meta.setItemModel(NamespacedKey.minecraft("pocket_watch"));
        meta.setDisplayName(ChatColor.GOLD + "Pocket Watch");
        recoveryCompass.setItemMeta(meta);
        player.getInventory().addItem(recoveryCompass);

        player.sendMessage("§aYou have been given a pocket watch!");

        return true;
    }
        return false;}}
