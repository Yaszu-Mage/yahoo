package space.yaszu.yahoo.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
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
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.util.key;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class scythe implements Listener {
    public static key key = new key();
    public HashMap<UUID, Long> cooldowns = new HashMap<>();
    private final long cooldownTime = 10000;
    public NamespacedKey nofall = key.get_key("nofall");



    public static ItemStack scythe_item() {
        ItemStack item = ItemStack.of(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(key.get_key("scythe"),PersistentDataType.BOOLEAN,true);
        meta.setItemModel(NamespacedKey.minecraft("scythe"));
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(key.get_key("scythe_damage"),7, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, new AttributeModifier(key.get_key("scythe_speed"),-2.7, AttributeModifier.Operation.ADD_NUMBER));
        meta.displayName(MiniMessage.miniMessage().deserialize("<gray>|</gray><gradient:#ff55ff:#6c00aa> Scythe<reset> <gray>|</gray>"));
        item.setItemMeta(meta);
        return item;
    }


    @EventHandler
    public void event(PlayerInteractEvent event){
        long currentTime = System.currentTimeMillis();
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (cooldowns.containsKey(playerUUID)) {

            long lastUsed = cooldowns.get(playerUUID);
            long timeLeft = cooldownTime - (currentTime - lastUsed);
            if (timeLeft > 0) {
                double secondsLeft = timeLeft / 1000.0;
                player.sendActionBar(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                return;
            }
        }
        if (player.getInventory().getItemInMainHand().getPersistentDataContainer().has(key.get_key("scythe"))) {
        if (player.isSneaking()) {
            cooldowns.put(playerUUID,currentTime);
            Location startlocation = player.getLocation();
            Vector direction = startlocation.getDirection().normalize().multiply(2);
            Location targetLocation = startlocation.add(direction);
            for (double i = 0; i <= 5; i += 0.5) {
                Location step = startlocation.clone().add(direction.clone()).multiply(i / 2);
                player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME,step,8,0,0,0,0);
                player.getWorld().spawnParticle(Particle.SOUL,step,48,0.2,0.2,0.2,0.1);
            }
            player.setVelocity(direction);
            player.getPersistentDataContainer().set(nofall, PersistentDataType.BOOLEAN,true);
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
            Bukkit.getScheduler().runTaskLater(Yahoo.get_plugin(), new Runnable() {
                @Override
                public void run() {
                   player.getPersistentDataContainer().remove(nofall);
                }
            },100);
        }
    }}
}
