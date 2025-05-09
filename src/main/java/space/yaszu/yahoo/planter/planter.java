package space.yaszu.yahoo.planter;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInputEvent;

public class planter implements Listener {
    @EventHandler
    public static void onPlayerInteract(PlayerInputEvent event) {

        Player player = event.getPlayer();
        if (event.getInput().isJump() && player.isSneaking()) {

        }
    }
}
