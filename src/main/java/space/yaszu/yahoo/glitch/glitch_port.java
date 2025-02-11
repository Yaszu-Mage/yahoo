package space.yaszu.yahoo.glitch;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.Random;

public class glitch_port implements Listener {
    public Random random = new Random();
    @EventHandler
    public void sneak_port(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Location playerLoc = player.getLocation();

        double TELEPORT_RADIUS = 10.0;
        int random_chance = random.nextInt(101);
        if (random_chance >= 99 && player.getName().equals("1nZ4ne")) {
            Location teleportlocation = playerLoc;
            player.spawnParticle(Particle.PORTAL,player.getLocation(),32);

            while (!isSafeLocation(teleport(playerLoc))){
                //waiting for a safe location
                teleportlocation = teleport(playerLoc);
                player.playSound(playerLoc, Sound.ENTITY_ENDERMAN_TELEPORT,1.0f,1.0f);
                player.teleport(teleport(playerLoc));
            }
            player.playSound(playerLoc, Sound.ENTITY_ENDERMAN_TELEPORT,1.0f,1.0f);
            player.teleport(teleportlocation);

        }
    }
    private static boolean isSafeLocation(Location loc) {
        // Implement your logic to check if a location is safe.
        // This could involve checking for solid ground, air above, etc.
        // Example (basic check - improve this):
        return loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ()).getType().isSolid() && // solid block below
                loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()).getType().isAir() && // air at the location
                loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType().isAir(); // air above
    }
    public Location teleport(Location playerLoc){
        double TELEPORT_RADIUS = 10.0;
        double x = playerLoc.getX() + (random.nextDouble() * 2 * TELEPORT_RADIUS - TELEPORT_RADIUS);
        double z = playerLoc.getZ() + (random.nextDouble() * 2 * TELEPORT_RADIUS - TELEPORT_RADIUS);
        double y = playerLoc.getY();
        Location teleportLoc = new Location(playerLoc.getWorld(), x, y, z);
        return teleportLoc;
    }
}
