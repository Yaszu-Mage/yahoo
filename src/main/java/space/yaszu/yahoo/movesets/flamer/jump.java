package space.yaszu.yahoo.movesets.flamer;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class jump implements Listener {
    private final HashMap<UUID, Long> cooldowns = new HashMap<>(); // Cooldown storage
    private final long jumpcooldownTime = 60000;
    @EventHandler
    public void jumper(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "glove");
        UUID playerUUID = player.getUniqueId();
        ItemStack held = player.getInventory().getItemInOffHand();
        long currentTime = System.currentTimeMillis();
        if (cooldowns.containsKey(playerUUID)) {
            long lastUsed = cooldowns.get(playerUUID);
            long timeLeft = jumpcooldownTime - (currentTime - lastUsed);

            if (timeLeft > 0) {
                // Convert milliseconds to seconds and display in action bar
                double secondsLeft = timeLeft / 1000.0;
                player.sendActionBar(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                return;
            }
        }
        if(held.getPersistentDataContainer().has(key, PersistentDataType.STRING) && !player.getWorld().hasStorm() && player.isSneaking() ) {
            Vector direction = player.getLocation().getDirection();
            direction.setX(0);
            direction.setZ(0);
            direction.setY(3);
            direction.normalize().multiply(2.2);// Move 5 blocks forward

            player.setVelocity(direction);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,140,1));
            cooldowns.put(playerUUID,currentTime);
        }
    }
}
