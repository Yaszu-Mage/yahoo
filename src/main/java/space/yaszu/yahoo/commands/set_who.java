package space.yaszu.yahoo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.player_info.player_info_register;

public class set_who implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        check_player("Yaszu","porter");
        check_player("1nZ4ne","glitch");
        check_player("GhostboyGamer", "flamer");
        check_player("OvaAlpha3", "demon");
        check_player("Jetbiopen", "demon");
        return true;
    }

    public void check_player(String player, String tag){
        if (Bukkit.getPlayer(player) != null){
            Player play = Bukkit.getPlayer(player);
            player_info_register.register(play, tag);
        }
    }

}
