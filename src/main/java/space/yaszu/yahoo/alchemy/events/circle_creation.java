package space.yaszu.yahoo.alchemy.events;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class circle_creation implements Listener {
    @EventHandler
    public void create_circle(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        event.getClickedBlock();
        if (player.isSneaking()) {
            // TODO check structure
            player.sendActionBar(Component.text("Created Alchemical Circle"));
        }
    }

}
