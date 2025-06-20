package space.yaszu.yahoo.movesets.glitch;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class glitched_gem implements Listener {
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();// Cooldown storage
    private final HashMap<Location,BlockData> originals= new HashMap<>();
    private final HashMap<UUID,Long> openers = new HashMap<>();
    private final HashMap<Location,Long> opened_portal = new HashMap<>();
    private final long cooldownTimeGem = 60000;
    private final long opened = 30000;
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();
        NamespacedKey state = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "state");

        PersistentDataContainer cont = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
        UUID playerUUID = player.getUniqueId();

        long currentTime = System.currentTimeMillis();

        // Check cooldown
        if (cooldowns.containsKey(playerUUID)) {
            long lastUsed = cooldowns.get(playerUUID);
            long timeLeft = cooldownTimeGem - (currentTime - lastUsed);

            if (timeLeft > 0) {
                // Convert milliseconds to seconds and display in action bar
                double secondsLeft = timeLeft / 1000.0;
                player.sendMessage(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                player.sendActionBar(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                return;
            }
        }
        if (openers.containsKey(playerUUID)) {
            long lastUsed = openers.get(playerUUID);
            long timeLeft = opened - (currentTime - lastUsed);
        }
        String type = "";
        if (cont.has(key)) {
            type = cont.get(key, PersistentDataType.STRING);
        }
        if (type.equals("glitch")) {
            if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                if (player.getInventory().getItemInMainHand().getItemMeta().equals(glitched_gem_item.gem().getItemMeta())) {
                    LivingEntity slowed = ((LivingEntity) event.getRightClicked());
                    Location teleportationspot = slowed.getLocation();
                    player.getWorld().playSound(teleportationspot, Sound.ENTITY_PLAYER_HURT_FREEZE, 1, 1);
                    cooldowns.put(playerUUID, System.currentTimeMillis());
                    slow(slowed, teleportationspot);// deprecated code that isnt used
                }
            }
        }
    }




    private void slow(LivingEntity entity,Location location) {
        Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("Yahoo"), new Runnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks > 100) { // Stops after 10 ticks (0.5 seconds)
                    this.cancel();
                    return;
                }

                // Spawn "star-like" particles at player's previous location
                entity.teleport(location);

                ticks++;
            }

            private void cancel() {
                Bukkit.getScheduler().cancelTask(this.hashCode());
            }
        }, 0L, 2L); // Runs every 2 ticks (0.1 sec)
    }
}
