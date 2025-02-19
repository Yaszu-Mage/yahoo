package space.yaszu.yahoo.events;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.ServerTickManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.events.new_runnables.unfreeze;

import java.util.HashMap;
import java.util.UUID;

public class dialation implements Listener {
    private final HashMap<UUID, Long> cooldowns = new HashMap<>(); // Cooldown storage
    private final long dialationcooldownTime = 60000;
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
        if (cooldowns.containsKey(playerUUID)) {
            long lastUsed = cooldowns.get(playerUUID);
            long timeLeft = dialationcooldownTime - (currentTime - lastUsed);

            if (timeLeft > 0) {
                // Convert milliseconds to seconds and display in action bar
                double secondsLeft = timeLeft / 1000.0;
                player.sendActionBar(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                return;
            }
        }
        if (player.getPersistentDataContainer().get(time_cooldown, PersistentDataType.STRING).equals("off")) {
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

                // Schedule unfreeze and cooldown reset
                Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"),
                        new unfreeze(Yahoo.getInstance(), player), 600);
            }
        }
    }
}
