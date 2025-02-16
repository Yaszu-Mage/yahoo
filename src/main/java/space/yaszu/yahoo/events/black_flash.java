package space.yaszu.yahoo.events;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;

import java.util.Random;
import java.util.logging.Logger;

public class black_flash implements Listener {
    Random random = new Random();
    @EventHandler
    public void blackflash(PrePlayerAttackEntityEvent event) {
        Player attacker = event.getPlayer();
        Entity attacked = event.getAttacked();
        if (!attacker.equals(attacked)) {
//rip Yaszu//
            int value = random.nextInt(201);
            if (value >= 195) {
                attacker.displayName();
                space.yaszu.yahoo.Yahoo.getPlugin(Yahoo.class).getLogger().info(attacker.getDisplayName() + " hit a black flash");
                attacker.getWorld().playSound(attacker.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE,1.0f,1.0f);
                attacker.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,10,6));
                attacker.addPotionEffect(new PotionEffect(PotionEffectType.HASTE,10,19));
            }
        }
    }
}
