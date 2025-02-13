package space.yaszu.yahoo.items;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;

public class item_event implements Listener {
    @EventHandler
    public void right_click(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        String id;
        if (item.getAmount() != 0) {


            ItemMeta meta = item.getItemMeta();
            NamespacedKey key2 = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"Yah_ID");
            id = meta.getPersistentDataContainer().get(key2, PersistentDataType.STRING);
            space.yaszu.yahoo.Yahoo.getPlugin(Yahoo.class).getLogger().info(id + " was used");
            if (id.equals("coke")) {
                item.subtract(1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,500,1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA,500,1));
            }
            if (id.equals("spawn_cry")) {
                item.subtract(1);
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 1f, 1f);
                player.spawnParticle(Particle.PORTAL,player.getLocation(),64);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,1000,1));
                player.teleport(new Location(player.getWorld(),0, 100,0));
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 1f, 1f);
            }

        }

    }
}
