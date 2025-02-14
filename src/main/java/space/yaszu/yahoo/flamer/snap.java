package space.yaszu.yahoo.flamer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;

public class snap implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent interact) {
        boolean sneak = interact.getPlayer().isSneaking();
        if (interact != null && sneak) {
            if ( interact.getPlayer().getDisplayName().equals("Yaszu")) {
                Player Ghost = interact.getPlayer();
                Location start = interact.getClickedBlock().getLocation();
                ArrayList<Location> Spots = new ArrayList<Location>();
                Spots.add(start);
                start.getDirection();
                Ghost.getWorld().getBlockAt(start).setType(Material.FIRE);
            }
        }
    }
}
