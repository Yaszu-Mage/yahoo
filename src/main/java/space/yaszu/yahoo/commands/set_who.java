package space.yaszu.yahoo.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.key;
import space.yaszu.yahoo.player_info.player_info_register;

public class set_who implements CommandExecutor {

    public static key keygen = new key();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        check_player("Yaszu","star", "origin");
        check_player("1nZ4ne","glitch", "origin");
        check_player("GhostboyGamer", "flamer", "origin");
        check_player("OvaAlpha3", "demon", "origin");
        check_player("Jetbiopen", "demon", "fire");

        return true;
    }

    public static void check_player(String player, String tag,String subtag){
        if (Bukkit.getPlayer(player) != null){
            Player play = Bukkit.getPlayer(player);
            player_info_register.register(play, tag);
            NamespacedKey subkey = keygen.get_key("subtype");
            play.getPersistentDataContainer().set(subkey, PersistentDataType.STRING,subtag);
        }
    }

}
