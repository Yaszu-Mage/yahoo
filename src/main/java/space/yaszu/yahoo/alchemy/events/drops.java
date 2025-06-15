package space.yaszu.yahoo.alchemy.events;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.items.soul;

import java.util.Random;

public class drops implements Listener {
    @EventHandler
    public void dontdrop(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        NamespacedKey dontdrop = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"DontDrop");
        if (entity.getPersistentDataContainer().has(dontdrop, PersistentDataType.BOOLEAN)) {
            event.getDrops().clear();
            event.setDroppedExp(0);
        } else {
            //pass
        }
        if (entity.getType().equals(EntityType.VILLAGER) || entity.getType().equals(EntityType.PLAYER)) {
            Random random = new Random();
            int value = random.nextInt(1001);
            if (value >= 850) {
                entity.getWorld().spawnParticle(Particle.SOUL,entity.getLocation(),128);
                ItemStack soul_item = soul.soul_item();
                event.getDrops().add(soul_item);
            } else {
                entity.getWorld().spawnParticle(Particle.SOUL,entity.getLocation(),128);
            }
        }
    }
}
