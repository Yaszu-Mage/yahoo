package space.yaszu.yahoo.events.new_runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.util.player_info_register;

import static org.bukkit.Bukkit.getPluginManager;

public class set_who implements Runnable {
    private final Yahoo yahoo;
    public set_who(Yahoo yahoo) {
        this.yahoo = yahoo;
    }
    public void run() {
        check_player("Yaszu","star");
        check_player("1nZ4ne","glitch");
        check_player("GhostboyGamer", "flamer");
        check_player("OvaAlpha3", "demon");
        check_player("sweetcherriezz","zombified");
        Bukkit.getScheduler().runTaskLater(getPluginManager().getPlugin("Yahoo"),new set_who(yahoo),12000);
    }
    public void check_player(String player, String tag){
        if (Bukkit.getPlayer(player) != null){
            Player play = Bukkit.getPlayer(player);
            player_info_register.register(play, tag);
        }
    }
}
