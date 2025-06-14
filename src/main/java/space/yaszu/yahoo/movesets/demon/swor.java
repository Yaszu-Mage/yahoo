package space.yaszu.yahoo.movesets.demon;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.items.soul;
import space.yaszu.yahoo.util.key;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class swor implements Listener {
    private static final Random random = new Random();
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private final HashMap<UUID, Long> piercecooldowns = new HashMap<>();// Cooldown storage
    private final long cooldownTime = 40000;
    private final long piercecooldownTime = 4000;
    public static key keygen = new key();
    NamespacedKey nofall = new NamespacedKey(Yahoo.get_plugin(),"nofall");
    public static ItemStack sword_item() {
        ItemStack sword = ItemStack.of(Material.DIAMOND_SWORD);
        ItemMeta swordItemMeta = sword.getItemMeta();
        swordItemMeta.setItemModel(NamespacedKey.minecraft("demonic_sword"));
        swordItemMeta.setDisplayName(ChatColor.RED + "Demonic Sword");
        PersistentDataContainer cont = swordItemMeta.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Yahoo.get_plugin(),"demonic_sword");
        cont.set(key, PersistentDataType.BOOLEAN, true);
        swordItemMeta.setUnbreakable(true);
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
        String subtype = "";
        PersistentDataContainer cont = player.getPersistentDataContainer();
        NamespacedKey type_key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
        if (cont.has(type_key)) {
            type = cont.get(type_key, PersistentDataType.STRING);
            subtype = cont.get(keygen.get_key("subtype"), PersistentDataType.STRING);
        }
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
        if (type.equals("demon") && event.getHand() == EquipmentSlot.HAND && player.getInventory().getItemInMainHand().getItemMeta().equals(sword_item().getItemMeta()) && player.isSneaking()) {
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
                if (entity instanceof LivingEntity && entity != player) {
                    if (subtype.equals("fire")) {
                        ((LivingEntity) entity).damage(5.5, player);
                        ((LivingEntity) entity).setFireTicks(2400);
                    }
                     // Deal 6 damage
                    break; // Stop after hitting the first target
                }
            }
            NamespacedKey fallkey = new NamespacedKey(Yahoo.get_plugin(),"nofall");
            cont.set(fallkey,PersistentDataType.BOOLEAN,true);
            Bukkit.getScheduler().runTaskLater(Yahoo.get_plugin(),new Runnable() {
                public void run() {
                    cont.remove(fallkey);
                }
            },1000);
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
        String subtype = "";
        PersistentDataContainer cont = player.getPersistentDataContainer();
        NamespacedKey typekey = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");

        if (cont.has(typekey)) {
            type = cont.get(typekey, PersistentDataType.STRING);
            subtype = cont.get(keygen.get_key("subtype"),PersistentDataType.STRING);
        }
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
        if (type.equals("demon") && event.getHand() == EquipmentSlot.OFF_HAND && !player.isSneaking()) {
            cooldowns.put(playerUUID, currentTime);
            ItemStack mainhand = player.getInventory().getItemInMainHand();
            if (mainhand.getType() != Material.AIR && mainhand.getItemMeta().equals(sword_item().getItemMeta())) {
                createSlashCloud(player,5,40,10);
            }
        }}
    }

    public static void register_recipe() {
        NamespacedKey key = new NamespacedKey(Yahoo.get_plugin(),"Demonic_Sword");
        ShapedRecipe recipe = new ShapedRecipe(key,sword_item());
        recipe.shape("SW ","   ","   ");
        recipe.setIngredient('S', soul.soul_item());
        recipe.setIngredient('W', Material.DIAMOND_SWORD);
        Bukkit.addRecipe(recipe);
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
                    if (caster.getPersistentDataContainer().has(keygen.get_key("subtype"))) {
                        if (caster.getPersistentDataContainer().get(keygen.get_key("subtype"), PersistentDataType.STRING).equals("fire")) {
                            caster.getWorld().spawnParticle(Particle.FLAME,critLoc,1);
                            caster.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME,critLoc,1);
                        }
                    }

                }

                // Play slashing sound effect
                caster.getWorld().playSound(center, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.1f + random.nextFloat() * 0.2f);


                for (Entity target : caster.getNearbyEntities(1.5,1.5,1.5)) {
                    if (!target.equals(caster) && target.getWorld().equals(caster.getWorld()));
                    double distance = target.getLocation().distance(center);
                    if (distance <= maxRadius && target instanceof LivingEntity){
                        double damageFactor = (distance / maxRadius);
                        double damage = maxDamage * (1.0 - damageFactor);
                        LivingEntity livingtarget = (LivingEntity) target;
                        if (caster.getPersistentDataContainer().has(keygen.get_key("subtype"))) {
                            if (caster.getPersistentDataContainer().get(keygen.get_key("subtype"), PersistentDataType.STRING).equals("fire")) {
                                livingtarget.damage(damage - 1,caster);
                                livingtarget.setFireTicks(2400);
                            } else {
                                livingtarget.damage(damage,caster);
                            }
                        }

                    }
                }
                // Damage players within the radius based on distance

                // Adjust tick interval: Faster slashes when closer to center
                int fireRate = (int) (10 - (5 * (ticks / (double) duration))); // Starts at 10 ticks, goes to 5 ticks
                ticks += fireRate;
            }
        }.runTaskTimer(Yahoo.get_plugin(), 0, 5); // Initial faster interval (5 ticks)
    }
}

