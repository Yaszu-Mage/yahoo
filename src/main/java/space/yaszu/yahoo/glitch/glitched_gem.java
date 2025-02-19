package space.yaszu.yahoo.glitch;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.EndGateway;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.glitch.glitched_gem_item;

import java.util.HashMap;
import java.util.UUID;

public class glitched_gem implements Listener {
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();// Cooldown storage
    private final HashMap<Location,BlockData> originals= new HashMap<>();
    private final HashMap<UUID,Long> openers = new HashMap<>();
    private final HashMap<Location,Long> opened_portal = new HashMap<>();
    private final long cooldownTimeGem = 300000;
    private final long opened = 30000;
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();
        NamespacedKey state = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"state");

        PersistentDataContainer cont = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
        UUID playerUUID = player.getUniqueId();

        long currentTime = System.currentTimeMillis();

        // Check cooldown
        if (cooldowns.containsKey(playerUUID)) {
            long lastUsed = cooldowns.get(playerUUID);
            long timeLeft = cooldownTimeGem - (currentTime - lastUsed);

            if (timeLeft > 0) {
                // Convert milliseconds to seconds and display in action bar
                double secondsLeft = timeLeft / 1000.0;
                player.sendMessage(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                player.sendActionBar(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                return;
            }
        }
        if (openers.containsKey(playerUUID)) {
            long lastUsed = openers.get(playerUUID);
            long timeLeft = opened - (currentTime - lastUsed);
        }
        String type = "";
        if (cont.has(key)) {
            type = cont.get(key, PersistentDataType.STRING);
        }
        if (player.getInventory().getItemInMainHand().getItemMeta().equals(glitched_gem_item.gem()) && type.equals("glitch")) {
            if (player.getInventory().getItemInMainHand().getPersistentDataContainer().get(state,PersistentDataType.INTEGER) == 0) {
                LivingEntity slowed = ((LivingEntity) event.getRightClicked());
                Location teleportationspot = slowed.getLocation();
                player.getWorld().playSound(teleportationspot, Sound.ENTITY_PLAYER_HURT_FREEZE, 1, 1);
                cooldowns.put(playerUUID, System.currentTimeMillis());
                slow(slowed,teleportationspot);// deprecated code that isnt used
            } else {
                Block prev_block = player.getWorld().getBlockAt(player.getLocation());
                BlockData prev_block_data = player.getWorld().getBlockData(player.getLocation());
                originals.put(player.getLocation(),prev_block_data);
                openers.put(playerUUID,System.currentTimeMillis());
                Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"), new Runnable() {
                    @Override
                    public void run() {
                        player.getWorld().setBlockData(player.getLocation(),originals.get(player.getLocation()));
                        openers.remove(playerUUID);
                        opened_portal.remove(player.getLocation());
                        originals.remove(player.getLocation());
                    }
                },600);
            }
        } else {
            player.sendMessage(Component.text(TextColor.color(123, 0, 32) + "You don't know what you're getting yourself into. I should stop you here."));
        }
    }
    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        ItemStack gem = event.getPlayer().getInventory().getItemInMainHand();
        NamespacedKey state = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"state");
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"gem");
        int gem_state = gem.getPersistentDataContainer().get(state,PersistentDataType.INTEGER);
        if (gem.getItemMeta().equals(glitched_gem_item.gem().getItemMeta())) {
            if (gem_state == 0) {
                gem.getItemMeta().getPersistentDataContainer().set(state, PersistentDataType.INTEGER, 1);
            } else {
                gem.getItemMeta().getPersistentDataContainer().set(state, PersistentDataType.INTEGER, 0);
            }
        }
    }
    @EventHandler
    public void onport(PlayerPortalEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (openers.get(playerUUID) != null || opened_portal.get(event.getPlayer().getLocation()) != null) {
            if (player.getWorld().equals(Yahoo.get_glitched())) {
                event.setTo(new Location(Bukkit.getWorld("world"),0,100,0));
            } else {
                event.setTo(new Location(Yahoo.get_glitched(),0,100,0));
            }
        }
    }

    private void slow(LivingEntity entity,Location location) {
        Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("Yahoo"), new Runnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks > 100) { // Stops after 10 ticks (0.5 seconds)
                    this.cancel();
                    return;
                }

                // Spawn "star-like" particles at player's previous location
                entity.teleport(location);

                ticks++;
            }

            private void cancel() {
                Bukkit.getScheduler().cancelTask(this.hashCode());
            }
        }, 0L, 2L); // Runs every 2 ticks (0.1 sec)
    }
}
