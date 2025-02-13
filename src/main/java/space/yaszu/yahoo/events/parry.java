package space.yaszu.yahoo.events;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class parry implements Listener {
    @EventHandler
    public void parry_action(PrePlayerAttackEntityEvent Attack, PlayerSwapHandItemsEvent Swap) {
        if (!Attack.equals(null) && !Swap.equals(null)){
            Player attacker = Attack.getPlayer();
            Entity defender = Attack.getAttacked();
            if (defender instanceof Player && defender.equals(Swap.getPlayer())) {
                defender.getWorld().playSound(defender.getLocation(), Sound.ITEM_TOTEM_USE,1f,2f);
                ((Player) defender).addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,25,10));
                ItemStack main_hand = ((Player) defender).getActiveItem();
                ItemStack offhand = Swap.getOffHandItem();
                ((Player) defender).getInventory().setItemInMainHand(main_hand);
                ((Player) defender).getInventory().setItemInOffHand(offhand);
            }
        }
    }
}
