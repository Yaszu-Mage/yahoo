package space.yaszu.yahoo.events;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;

import java.util.Random;
import java.util.logging.Logger;

public class black_flash implements Listener {
    Random random = new Random();
    public void black_flash(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity attacked = event.getEntity();
        if (attacker instanceof Player) {
            int value = random.nextInt(101);
            if (value >= 90) {
                space.yaszu.yahoo.Yahoo.getPlugin(Yahoo.class).getLogger().info("Black Flash");
                attacker.getWorld().playSound(attacker.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE,1.0f,1.0f);
                ((Player) attacker).addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,256,3));
            }
        }
    }
}
