package space.yaszu.yahoo.flamer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class snap implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent interact) {
        Player player = interact.getPlayer();
        if (interact.getAction().isRightClick() && player.isSneaking() && player.getDisplayName().equals("Yaszu")) {
            Location start = interact.getClickedBlock().getLocation();

            if (!start.getWorld().getBlockAt(start).isSolid()) { // Check if clicked block is solid.

                BlockFace direction = interact.getBlockFace(); // Get the direction of the click.

                // Calculate the start location offset by one block in the clicked direction
                Location fireStart = start.getBlock().getRelative(direction).getLocation();

                // Create the 5x3 fire area
                for (int x = -2; x <= 2; x++) {
                    for (int z = -1; z <= 1; z++) {
                        Location fireLocation = fireStart.clone().add(direction.getDirection().multiply(x)).add(direction.getDirection().getCrossProduct(getPerpendicularVector(direction.getDirection())).multiply(z));


                        if (fireLocation.getWorld().getBlockAt(fireLocation).getType() != Material.AIR) continue; // Skip if a block is already there

                        fireLocation.getWorld().getBlockAt(fireLocation).setType(Material.FIRE);
                    }
                }
            }
        }
    }

    // Helper function to get a perpendicular vector
    private Vector getPerpendicularVector(Vector vector) {
        if (vector.getX() != 0) {
            return new Vector(0, 1, 0); // Up
        } else if (vector.getY() != 0) {
            return new Vector(0, 0, 1); // Towards positive Z
        } else {
            return new Vector(1, 0, 0); // Towards positive X
        }
    }
}