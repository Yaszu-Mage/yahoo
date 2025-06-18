package space.yaszu.yahoo.race;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.util.key;

public class RaceEnforcer implements Listener {
    public key key = new key();


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!event.getPlayer().getPersistentDataContainer().has(key.get_key("race"))) {
            Race_Selection.startselection(event.getPlayer());
        }

    }
    @EventHandler
    public void Chat(PlayerChatEvent event) {
        Race_Selection.startselection(event.getPlayer());
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().getPersistentDataContainer().has(key.get_key("race"))) {
            Race_Selection.startselection(event.getPlayer());
        }
    }

}
