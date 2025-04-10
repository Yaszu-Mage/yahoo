package space.yaszu.yahoo.glitch;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.Yahoo;

import java.util.HashMap;
import java.util.UUID;

public class gem_port implements Listener {
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();// Cooldown storage
    private final long cooldownTimeGem = 60000;
    @EventHandler
    public void gem_teleport(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        NamespacedKey state = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "state");

        PersistentDataContainer cont = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
        UUID playerUUID = player.getUniqueId();
        NamespacedKey fallkey = new NamespacedKey(Yahoo.get_plugin(),"nofall");
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
        String type = "";
        if (cont.has(key)) {
            type = cont.get(key, PersistentDataType.STRING);
        }
        if (type.equals("glitch")) {
            @NotNull ItemStack offhand = player.getInventory().getItemInOffHand();
            @NotNull ItemMeta meta = offhand.getItemMeta();
            if (meta == null) {return;}
            if (!(offhand == null)) {
        if (meta.equals(glitched_gem_item.gem().getItemMeta())) {
            player.sendMessage("Running...");
            Location start_loc = player.getLocation();
            if (player.getWorld() == Bukkit.getWorld("world")) {
                Location start_loc_loc = start_loc.clone();
                start_loc_loc.setWorld(Bukkit.getWorld("Glitch"));
                start_loc_loc.setY(150);
                player.sendMessage("To Glitch...");
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getWorld().equals(player.getWorld())) {
                        if (start_loc.distance(p.getLocation()) < 3) {
                            p.teleport(start_loc_loc);
                            NamespacedKey nofall = new NamespacedKey(Yahoo.get_plugin(),"nofall");
                            p.getPersistentDataContainer().set(nofall,PersistentDataType.BOOLEAN,true);
                            Bukkit.getScheduler().runTaskLater(Yahoo.get_plugin(), new Runnable() {
                                @Override
                                public void run() {
                                    p.getPersistentDataContainer().remove(nofall);
                                }
                            },220);
                        }
                    }}
                player.teleport(start_loc_loc);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 220,0));
            } else {
                player.sendMessage("To Other...");
                Location start_loc_loc = start_loc.clone();
                start_loc_loc.setWorld(Bukkit.getWorld("world"));
                start_loc_loc.setY(150);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getWorld().equals(player.getWorld())) {
                        if (start_loc.distance(p.getLocation()) < 3) {
                            p.teleport(start_loc_loc);
                            // My MANS this shit is such a pain in the ass
                            NamespacedKey nofall = new NamespacedKey(Yahoo.get_plugin(),"nofall");
                            p.getPersistentDataContainer().set(nofall,PersistentDataType.BOOLEAN,true);
                            Bukkit.getScheduler().runTaskLater(Yahoo.get_plugin(), new Runnable() {
                                @Override
                                public void run() {
                                    p.getPersistentDataContainer().remove(nofall);
                                }
                            },220);
                        }}
                }
                player.teleport(start_loc_loc);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 300, 0));
            }

            cooldowns.put(playerUUID, System.currentTimeMillis());
        }}}


    }
}
