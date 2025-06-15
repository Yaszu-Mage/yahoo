package space.yaszu.yahoo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.util.key;

public class zombify implements CommandExecutor {
    public key key = new key();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        if (sender.isOp()) {
            if (Bukkit.getPlayer(args[0]) != null) {

                sender.sendMessage("Player " + args[0] + " has been zombified!!");
                Bukkit.getPlayer(args[0]).getPersistentDataContainer().set(key.get_key("zombified"), PersistentDataType.BOOLEAN, true);
            } else {
                sender.sendMessage("Player " + args[0] + " not found!");
            }
            return true;
        }
        return false;
    }
}
