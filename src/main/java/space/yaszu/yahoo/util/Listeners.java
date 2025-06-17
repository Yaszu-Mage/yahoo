package space.yaszu.yahoo.util;

import io.papermc.paper.event.player.PlayerStopUsingItemEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;

public class Listeners implements Listener {
    public key key = new key();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.getPersistentDataContainer().has(key.get_key("can_swap"))) {
            player.getPersistentDataContainer().set(key.get_key("can_swap"), PersistentDataType.BOOLEAN, true);
        }
    }
    @EventHandler
    public void onPlayerItemSwap(PlayerItemHeldEvent event) {
        if (!event.getPlayer().getPersistentDataContainer().get(key.get_key("can_swap"),PersistentDataType.BOOLEAN)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void fall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getDamageSource().getDamageType() == DamageType.FALL) {
                NamespacedKey key = new NamespacedKey(Yahoo.get_plugin(),"nofall");
                PersistentDataContainer cont = player.getPersistentDataContainer();
                if (cont.has(key)) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
