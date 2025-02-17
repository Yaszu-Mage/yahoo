package space.yaszu.yahoo.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import space.yaszu.yahoo.events.new_runnables.fire_res;

public class join_effect implements Listener {
    public void join_event(PlayerJoinEvent event) {
        if (event.getPlayer().getDisplayName().equals("GhostboyGamer")) {
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"),new fire_res(),1);
        }
    }
}
