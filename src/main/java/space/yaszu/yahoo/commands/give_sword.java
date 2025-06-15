package space.yaszu.yahoo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.yaszu.yahoo.movesets.demon.swor;

public class give_sword implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        if (sender.isOp()) {
        Player player = (Player) sender;
        player.getInventory().addItem(swor.sword_item());

        player.sendMessage("§aYou have been given the demonic sword!");

        return true;
    }
return false;}
}
