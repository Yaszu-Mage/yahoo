package space.yaszu.yahoo.events;

import net.kyori.adventure.text.Component;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class determination implements Listener {
    Random random = new Random();
    @EventHandler
    public void determination(PlayerDeathEvent event) {
        int chance = random.nextInt(1001);
        if (chance >= 980) {
            event.setShouldPlayDeathSound(false);
            List<ItemStack> drops = event.getDrops();
            Player player = event.getPlayer();
            player.getWorld().setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
            event.deathMessage(Component.text("But " + player.displayName() + " refused."));
            player.teleport(event.getEntity().getLocation());
            player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE,1f,1f);
            event.getDrops().clear();
        }

    }
}
