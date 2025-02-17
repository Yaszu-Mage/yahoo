package space.yaszu.yahoo.events;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;

import java.util.Random;

public class black_flash implements Listener {
    Random random = new Random();

    @EventHandler
    public void blackflash(PrePlayerAttackEntityEvent event) {
        Player attacker = event.getPlayer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "black_flash");

        if (!attacker.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
            attacker.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 0);
        }

        int additive = attacker.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
        Entity attacked = event.getAttacked();

        if (!attacker.equals(attacked)) {
            int value = random.nextInt(201);
            if (value <= 195) {
                Yahoo.getPlugin(Yahoo.class).getLogger().info(attacker.getDisplayName() + " hit a black flash");

                attacker.getWorld().playSound(attacker.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 1.0f, 0.7f);

                // Spawn Black and Dark Red particles
                attacker.getWorld().spawnParticle(Particle.LARGE_SMOKE, attacked.getLocation(), 50, 0.5, 0.5, 0.5, 0.02);
                attacker.getWorld().spawnParticle(Particle.FLAME, attacked.getLocation(), 50, 0.5, 0.5, 0.5);

                // Apply effects
                attacker.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 10, 6));
                attacker.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 10, 19));

                // Reset counter
                attacker.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 0);
            } else {
                attacker.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, additive + 1);
            }
        }
    }
}
