package space.yaszu.yahoo.items;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class item_registry implements Listener {
    @EventHandler
    public void interact(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getActiveItem();
        assert item != null;
        String custom = NBT.get(item,nbt -> {
             return nbt.getString("custom");
        });
        if (custom.equals("true") ) {
            String type = NBT.get(item,nbt -> {
                return nbt.getString("type");
            });
            if (type.equals("coke")) {
                item.subtract(1);
                player.playSound(player.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1.0f,1.0f);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA,200,1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,200,1));
            }
        }

    }
}
