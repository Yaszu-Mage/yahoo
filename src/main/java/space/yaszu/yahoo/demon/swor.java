package space.yaszu.yahoo.demon;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.eclipse.sisu.launch.Main;
import space.yaszu.yahoo.Yahoo;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class swor implements Listener {
    private static final Random random = new Random();
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private final HashMap<UUID, Long> piercecooldowns = new HashMap<>();// Cooldown storage
    private final long cooldownTime = 40000;
    private final long piercecooldownTime = 40000;
    public static ItemStack sword_item() {
        ItemStack sword = ItemStack.of(Material.DIAMOND_SWORD);
        ItemMeta swordItemMeta = sword.getItemMeta();
        swordItemMeta.setDisplayName(ChatColor.RED + "Demonic Sword");
        PersistentDataContainer cont = swordItemMeta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Yahoo.get_plugin(),"demonic_sword");
        cont.set(key, PersistentDataType.BOOLEAN, true);
        sword.setItemMeta(swordItemMeta);
        return sword;
    }

    @EventHandler
    public void sword_pierce(PlayerInteractEvent event) {
        long currentTime = System.currentTimeMillis();
        Player player = event.getPlayer();
        UUID playerUUID = event.getPlayer().getUniqueId();
        if (piercecooldowns.containsKey(playerUUID)) {
            long lastUsed = piercecooldowns.get(playerUUID);
            long timeLeft = piercecooldownTime - (currentTime - lastUsed);

            if (timeLeft > 0) {
                // Convert milliseconds to seconds and display in action bar
                double secondsLeft = timeLeft / 1000.0;
                player.sendActionBar(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                return;
            }
        }
        String type = "";
        PersistentDataContainer cont = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
        if (cont.has(key)) {
            type = cont.get(key, PersistentDataType.STRING);
        }
        if (type.equals("demon") && event.getHand() == EquipmentSlot.HAND) {
            piercecooldowns.put(playerUUID, System.currentTimeMillis());
            Location startLocation = player.getLocation();
            Vector direction = startLocation.getDirection().normalize().multiply(5);
            Location targetLocation = startLocation.add(direction);

            // Play particle effect along the path
            for (double i = 0; i <= 5; i += 0.5) {
                Location step = startLocation.clone().add(direction.clone().multiply(i / 5));
                player.getWorld().spawnParticle(Particle.SWEEP_ATTACK, step, 1, 0, 0, 0, 0);
                player.getWorld().spawnParticle(Particle.END_ROD, step, 40, 0.2, 0.2, 0.2, 0.1);
            }

            // Launch player forward
            player.setVelocity(direction);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.0f);

            // Damage the first entity hit
            List<Entity> entities = player.getNearbyEntities(5, 2, 5);
            for (Entity entity : entities) {
                if (entity instanceof Player && entity != player) {
                    ((Player) entity).damage(6.0, player); // Deal 6 damage
                    break; // Stop after hitting the first target
                }
            }
        }
    }

    @EventHandler
    public void sword_ability(PlayerInteractEvent event) {
        long currentTime = System.currentTimeMillis();
        Player player = event.getPlayer();
        UUID playerUUID = event.getPlayer().getUniqueId();
        if (cooldowns.containsKey(playerUUID)) {
            long lastUsed = cooldowns.get(playerUUID);
            long timeLeft = cooldownTime - (currentTime - lastUsed);

            if (timeLeft > 0) {
                // Convert milliseconds to seconds and display in action bar
                double secondsLeft = timeLeft / 1000.0;
                player.sendActionBar(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                return;
            }
        }
        String type = "";
        PersistentDataContainer cont = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
        if (cont.has(key)) {
            type = cont.get(key, PersistentDataType.STRING);
        }
        if (type.equals("demon") && event.getHand() == EquipmentSlot.OFF_HAND) {
            cooldowns.put(playerUUID, currentTime);
            ItemStack mainhand = player.getInventory().getItemInMainHand();
            if (mainhand.getType() != Material.AIR && mainhand.getItemMeta().equals(sword_item().getItemMeta())) {
                createSlashCloud(player,5,40,4);
            }
        }
    }
    public static void createSlashCloud(Player caster, double maxRadius, int duration, double maxDamage) {
        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks >= duration) {
                    cancel();
                    return;
                }

                Location center = caster.getLocation();

                // Outer Ring - Fixed Slashes
                for (int i = 0; i < 8; i++) {
                    double angle = Math.toRadians(i * 45);
                    double x = maxRadius * Math.cos(angle);
                    double z = maxRadius * Math.sin(angle);
                    Location particleLoc = center.clone().add(x, 1.5, z);
                    caster.getWorld().spawnParticle(Particle.SWEEP_ATTACK, particleLoc, 1);
                }

                // Inner Crit Hits - Randomized
                int critCount = random.nextInt(3) + 2; // Random 2-4 crit effects per cycle
                for (int i = 0; i < critCount; i++) {
                    double radius = maxRadius * Math.sqrt(random.nextDouble()) * 0.7; // Bias toward inner area
                    double angle = Math.toRadians(random.nextInt(360));
                    double x = radius * Math.cos(angle);
                    double z = radius * Math.sin(angle);
                    Location critLoc = center.clone().add(x, 1.5, z);
                    caster.getWorld().spawnParticle(Particle.CRIT, critLoc, 2);
                }

                // Play slashing sound effect
                caster.getWorld().playSound(center, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.1f + random.nextFloat() * 0.2f);

                // Damage players within the radius based on distance
                for (Player target : Bukkit.getOnlinePlayers()) {
                    if (!target.equals(caster) && target.getWorld().equals(caster.getWorld())) {
                        double distance = target.getLocation().distance(center);
                        if (distance <= maxRadius) {
                            double damageFactor = (distance / maxRadius); // Closer = less damage
                            double damage = maxDamage * (1.0 - damageFactor); // Scale damage down closer to center
                            target.damage(damage, caster);
                        }
                    }
                }

                // Adjust tick interval: Faster slashes when closer to center
                int fireRate = (int) (10 - (5 * (ticks / (double) duration))); // Starts at 10 ticks, goes to 5 ticks
                ticks += fireRate;
            }
        }.runTaskTimer(Yahoo.get_plugin(), 0, 5); // Initial faster interval (5 ticks)
    }
}

