package space.yaszu.yahoo.explosion;

import io.papermc.paper.persistence.PersistentDataContainerView;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

public class grenade_use implements Listener {
    @EventHandler
    public void onGrenadeUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack main_hand = player.getInventory().getItemInMainHand();
        ItemStack off_hand = player.getInventory().getItemInOffHand();
        PersistentDataContainerView main_hand_data =  main_hand.getPersistentDataContainer();
        PersistentDataContainerView off_hand_data = off_hand.getPersistentDataContainer();
        // Check where the grenade is

    }
}
