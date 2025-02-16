package space.yaszu.yahoo.events;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.ServerTickManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.events.new_runnables.speed;
import space.yaszu.yahoo.events.new_runnables.unfreeze;

import java.util.List;

public class dialation implements Listener {
    @EventHandler
    public void time(PlayerInteractEvent event) {
        Player player = Bukkit.getPlayer("Yaszu");
        ItemStack off = player.getInventory().getItemInOffHand();
        NamespacedKey glove = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "time");
        NamespacedKey time_cooldown = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "time_cooldown");
        if (player.getPersistentDataContainer().get(time_cooldown, PersistentDataType.STRING) == null) {
            player.getPersistentDataContainer().set(time_cooldown,PersistentDataType.STRING,"off");
        }
        if (player.getPersistentDataContainer().get(time_cooldown,PersistentDataType.STRING).equals("off")) {
        if (off.getPersistentDataContainer().has(glove, PersistentDataType.STRING) && player.isSneaking() && player.getPersistentDataContainer().get(time_cooldown,PersistentDataType.STRING).equals("off")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 600, 3));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 3));
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 6000, 3));
            PotionEffect fx = new PotionEffect(PotionEffectType.SLOWNESS, 600, 3);
            ServerTickManager tickManager = Bukkit.getServerTickManager();
            tickManager.setFrozen(true);
            player.getPersistentDataContainer().set(time_cooldown,PersistentDataType.STRING,"on");
                List<Entity> near = player.getNearbyEntities(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
                for (Entity e : near) {
                    if (e instanceof LivingEntity) {
                        ((LivingEntity) e).addPotionEffect(fx);
                    }
                }
            player.getPersistentDataContainer().set(time_cooldown,PersistentDataType.STRING,"on");
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"),new time_off(Yahoo.getInstance(),player,time_cooldown,"time_cooldown"),6000);
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"),new unfreeze(Yahoo.getInstance(),player),600);
        }}
    }
}
