package space.yaszu.yahoo.movesets.flamer;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import space.yaszu.yahoo.Yahoo;

import java.util.HashMap;
import java.util.UUID;

public class snapv2 implements Listener {
    private final HashMap<UUID, Long> cooldowns = new HashMap<>(); // Cooldown storage
    private final long firecooldownTime = 60000;
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent interact) {
        Player player = interact.getPlayer();
        NamespacedKey cooldown = new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("Yahoo"), "cooldown");
        ItemStack held = player.getInventory().getItemInOffHand();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "glove");
        UUID playerUUID = player.getUniqueId();

        long currentTime = System.currentTimeMillis();

        // Check cooldown
        if (cooldowns.containsKey(playerUUID)) {
            long lastUsed = cooldowns.get(playerUUID);
            long timeLeft = firecooldownTime - (currentTime - lastUsed);

            if (timeLeft > 0) {
                // Convert milliseconds to seconds and display in action bar
                double secondsLeft = timeLeft / 1000.0;
                player.sendActionBar(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                return;
            }
        }
        if (held.getPersistentDataContainer().has(key, PersistentDataType.STRING) && !player.getWorld().hasStorm() && !player.isSneaking()) {
            Location Spewpoint = player.getEyeLocation();
            spawnParticlesAndDamage(player);
            cooldowns.put(playerUUID, currentTime);

        }
    }
    private void spawnParticlesAndDamage(final Player player) {
        // This task will run repeatedly to keep spawning particles and damaging entities
        new BukkitRunnable() {
            private int ticks = 0;
            private final double damagePerSecond = 5;

            @Override
            public void run() {
                if (ticks >= 100) { // After 10 seconds, stop the task
                    cancel();
                    return;
                }

                // Get the direction the player is facing and spawn particles along it
                Vector direction = player.getLocation().getDirection().normalize();
                Location start = player.getLocation().add(0, 1.5, 0); // Start from the player's head height
                double length = 5.0; // The length of the line in front of the player

                // Spawn particles in a line
                for (double i = 0; i < length; i += 0.2) {
                    Location particleLocation = start.clone().add(direction.clone().multiply(i));
                    player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, particleLocation, 1, 0, 0, 0, 0);

                    // Check entities in the particle area and damage them
                    for (Entity entity : player.getWorld().getNearbyEntities(particleLocation, 0.5, 0.5, 0.5)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            // Apply damage to entities that are not the player
                            ((LivingEntity) entity).damage(damagePerSecond,player);
                        }
                    }
                }

                ticks++;
            }
        }.runTaskTimer(Yahoo.get_plugin(), 0L, 1L); // Run every tick (1/20 of a second)
    }
}
