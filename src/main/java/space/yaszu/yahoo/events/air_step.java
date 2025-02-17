package space.yaszu.yahoo.events;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class air_step implements Listener {

    private final HashMap<UUID, Long> cooldowns = new HashMap<>(); // Cooldown storage
    private final long cooldownTime = 5000; // 5 seconds in milliseconds
    private final JavaPlugin plugin;

    public air_step(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer data = player.getPersistentDataContainer();
        UUID playerUUID = player.getUniqueId();

        long currentTime = System.currentTimeMillis();

        // Check cooldown
        if (cooldowns.containsKey(playerUUID)) {
            long lastUsed = cooldowns.get(playerUUID);
            long timeLeft = cooldownTime - (currentTime - lastUsed);

            if (timeLeft > 0) {
                // Convert milliseconds to seconds and display in action bar
                double secondsLeft = timeLeft / 1000.0;
                player.sendActionBar(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                return;
            }
        }

        // Check if the player is sneaking while jumping
        if (player.isSneaking()) {
            Vector direction = player.getLocation().getDirection();
            direction.setY(0); // Keep movement horizontal
            direction.normalize().multiply(5); // Move 5 blocks forward

            player.setVelocity(direction);
            cooldowns.put(playerUUID, currentTime); // Set new cooldown time

            // Display success message in the action bar
            player.sendActionBar(Component.text("§aAir Step activated!"));

            // Start particle effect
            spawnStarTrail(player);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,40,1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,40,1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,40,1));
        }
    }

    private void spawnStarTrail(Player player) {
        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks > 10) { // Stops after 10 ticks (0.5 seconds)
                    this.cancel();
                    return;
                }

                // Spawn "star-like" particles at player's previous location
                player.getWorld().spawnParticle(Particle.FIREWORK,
                        player.getLocation(),
                        5, // Amount of particles
                        0.3, 0.3, 0.3, // Offset (spread)
                        0.01); // Speed

                ticks++;
            }

            private void cancel() {
                Bukkit.getScheduler().cancelTask(this.hashCode());
            }
        }, 0L, 2L); // Runs every 2 ticks (0.1 sec)
    }
}
