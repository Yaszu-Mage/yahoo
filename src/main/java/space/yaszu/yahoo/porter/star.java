package space.yaszu.yahoo.porter;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.events.new_runnables.unfreeze;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class star implements Listener {
    private final HashMap<UUID, Long> cooldowns = new HashMap<>(); // Cooldown storage
    private final long cooldownTime = 5000; // 5 seconds in milliseconds



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
        String type = "";
        PersistentDataContainer cont = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
        if (cont.has(key)) {
            type = cont.get(key, PersistentDataType.STRING);
        }
        // Check if the player is sneaking while jumping
        if (player.isSneaking() && type.equals("porter")) {
            NamespacedKey fallkey = new NamespacedKey(Yahoo.get_plugin(),"nofall");
            cont.set(fallkey,PersistentDataType.BOOLEAN,true);
            Vector direction = player.getLocation().getDirection();
            direction.setY(0); // Keep movement horizontal
            direction.normalize().multiply(2.2); // Move 5 blocks forward

            player.setVelocity(direction);
            cooldowns.put(playerUUID, currentTime); // Set new cooldown time

            // Display success message in the action bar
            player.sendActionBar(Component.text("§aAir Step activated!"));

            // Start particle effect
            spawnStarTrail(player);
        }
    }

    private void spawnStarTrail(Player player) {
        Bukkit.getScheduler().runTaskTimer(Yahoo.get_plugin(), new Runnable() {
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
    private final HashMap<UUID, Long> dialcooldowns = new HashMap<>(); // Cooldown storage
    private final long dialationcooldownTime = 600000;
    @EventHandler
    public void time(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer data = player.getPersistentDataContainer();
        UUID playerUUID = player.getUniqueId();

        long currentTime = System.currentTimeMillis();
        if (player == null) return;

        ItemStack off = player.getInventory().getItemInOffHand();
        NamespacedKey glove = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "time");
        NamespacedKey time_cooldown = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "time_cooldown");

        if (player.getPersistentDataContainer().get(time_cooldown, PersistentDataType.STRING) == null) {
            player.getPersistentDataContainer().set(time_cooldown, PersistentDataType.STRING, "off");
        }
        if (dialcooldowns.containsKey(playerUUID)) {
            long lastUsed = dialcooldowns.get(playerUUID);
            long timeLeft = dialationcooldownTime - (currentTime - lastUsed);

            if (timeLeft > 0) {
                // Convert milliseconds to seconds and display in action bar
                double secondsLeft = timeLeft / 1000.0;
                player.sendActionBar(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                return;
            }
        }
        if (off.getPersistentDataContainer().has(glove, PersistentDataType.STRING) && player.isSneaking()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 6000, 3));
            PotionEffect fx = new PotionEffect(PotionEffectType.SLOWNESS, 600, 3);

            ServerTickManager tickManager = Bukkit.getServerTickManager();
            tickManager.setFrozen(true);
            player.getPersistentDataContainer().set(time_cooldown, PersistentDataType.STRING, "on");

            // Apply slowness effect to all players within a 20-block radius
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getLocation().distance(player.getLocation()) <= 20 && !p.equals(player)) {
                    p.addPotionEffect(fx);
                }
            }
            dialcooldowns.put(playerUUID, currentTime);
            // Schedule unfreeze and cooldown reset
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"),
                    new unfreeze(Yahoo.getInstance(), player), 600);
        }
    }

    }
