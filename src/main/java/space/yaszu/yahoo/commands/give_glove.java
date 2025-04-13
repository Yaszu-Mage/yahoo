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

public class give_glove implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        if (sender.isOp()) {
        Player player = (Player) sender;
        NamespacedKey glove = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "glove");
        // Give the player a Recovery Compass
        ItemStack recoveryCompass = new ItemStack(Material.RECOVERY_COMPASS);
        ItemMeta meta = recoveryCompass.getItemMeta();
        meta.getPersistentDataContainer().set(glove, PersistentDataType.STRING, "glove");
        meta.setDisplayName(ChatColor.GOLD + "Flame Glove");
        recoveryCompass.setItemMeta(meta);
        player.getInventory().addItem(recoveryCompass);

        player.sendMessage("§aYou have been given the flame glove!");

        return true;
    }
return false;}
}

