package space.yaszu.yahoo.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class determination implements Listener {
    Random random = new Random();
    @EventHandler
    public void determination(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        if (damaged instanceof Player) {
            Player player = ((Player) damaged);
            if (player.getHealth() - event.getFinalDamage() < 0.5) {
                int chance = random.nextInt(1001);
                if (chance >= 999) {
                    player.setHealth(20);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 4));
                    player.getWorld().playSound(player.getLocation(),Sound.ITEM_TOTEM_USE,1f,1f);
                    player.getWorld().sendMessage(Component.text("But " + player.getDisplayName() + " refused.", TextColor.color(148, 0, 5)));
                    event.setCancelled(true);
                }
            }
        }
    }
}
