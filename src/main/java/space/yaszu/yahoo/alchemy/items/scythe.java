package space.yaszu.yahoo.alchemy.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.yaszu.yahoo.key;
import org.bukkit.util.Vector;

import java.util.List;

public class scythe implements Listener {
    public static key key = new key();
    public ItemStack scythe_item() {
        ItemStack item = ItemStack.of(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(key.get_key("scythe_damage"),1, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, new AttributeModifier(key.get_key("scythe_speed"),-1, AttributeModifier.Operation.ADD_NUMBER));
        meta.displayName(MiniMessage.miniMessage().deserialize("<gray>|</gray><gradient:#ff55ff:#6c00aa> Scythe<reset> <gray>|</gray>"));
        item.setItemMeta(meta);
        return item;
    }


    @EventHandler
    public void event(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (player.isSneaking()) {
            Location startlocation = player.getLocation();
            Vector direction = startlocation.getDirection().normalize().multiply(2);
            Location targetLocation = startlocation.add(direction);
            for (double i = 0; i <= 5; i += 0.5) {
                Location step = startlocation.clone().add(direction.clone()).multiply(i / 2);
                player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME,step,8,0,0,0,0);
                player.getWorld().spawnParticle(Particle.SOUL,step,48,0.2,0.2,0.2,0.1);
            }
            player.setVelocity(direction);
            player.getWorld().playSound(startlocation, Sound.ENTITY_WITHER_HURT,1,1);
            List<Entity> entities = player.getNearbyEntities(5,2,5);
            for (Entity entity : entities) {
                if (entity instanceof  Player && entity != player) {
                    ((Player) entity).damage(4.0,player);
                    break;
                }
                if (entity instanceof LivingEntity) {
                    ((LivingEntity) entity).damage(4.0,player);
                    break;
                }
            }

        }
    }
}
