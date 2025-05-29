package space.yaszu.yahoo.porter;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.events.new_runnables.unfreeze;
import space.yaszu.yahoo.key;

import java.util.*;

public class star implements Listener {
    private final HashMap<UUID, Long> cooldowns = new HashMap<>(); // Cooldown storage
    private final long cooldownTime = 5000; // 5 seconds in milliseconds



    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer data = player.getPersistentDataContainer();
        UUID playerUUID = player.getUniqueId();

        long currentTime = System.currentTimeMillis();

        // Check cooldown
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
        // Check if the player is sneaking while jumping
        if (player.isSneaking() && type.equals("star")) {
            NamespacedKey fallkey = new NamespacedKey(Yahoo.get_plugin(),"nofall");
            cont.set(fallkey,PersistentDataType.BOOLEAN,true);
            Vector direction = player.getLocation().getDirection();
            direction.setY(0); // Keep movement horizontal
            direction.normalize().multiply(2.2); // Move 5 blocks forward

            player.setVelocity(direction);
            cooldowns.put(playerUUID, currentTime); // Set new cooldown time

            // Display success message in the action bar
            player.sendActionBar(Component.text("§aAir Step activated!"));

            // Start particle effect
            spawnStarTrail(player);
        }
    }

    private void spawnStarTrail(Player player) {
        Bukkit.getScheduler().runTaskTimer(Yahoo.get_plugin(), new Runnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks > 10) { // Stops after 10 ticks (0.5 seconds)
                    this.cancel();
                    return;
                }

                // Spawn "star-like" particles at player's previous location
                player.getWorld().spawnParticle(Particle.FIREWORK,
                        player.getLocation(),
                        5, // Amount of particles
                        0.3, 0.3, 0.3, // Offset (spread)
                        0.01); // Speed

                ticks++;
            }

            private void cancel() {
                Bukkit.getScheduler().cancelTask(this.hashCode());
            }
        }, 0L, 2L); // Runs every 2 ticks (0.1 sec)
    }
    private final HashMap<UUID, Long> dialcooldowns = new HashMap<>(); // Cooldown storage
    private final long dialationcooldownTime = 600000;
    @EventHandler
    public void time(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer data = player.getPersistentDataContainer();
        UUID playerUUID = player.getUniqueId();

        long currentTime = System.currentTimeMillis();
        if (player == null) return;

        ItemStack off = player.getInventory().getItemInOffHand();
        NamespacedKey glove = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "time");
        NamespacedKey time_cooldown = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "time_cooldown");

        if (player.getPersistentDataContainer().get(time_cooldown, PersistentDataType.STRING) == null) {
            player.getPersistentDataContainer().set(time_cooldown, PersistentDataType.STRING, "off");
        }
        if (dialcooldowns.containsKey(playerUUID)) {
            long lastUsed = dialcooldowns.get(playerUUID);
            long timeLeft = dialationcooldownTime - (currentTime - lastUsed);

            if (timeLeft > 0) {
                // Convert milliseconds to seconds and display in action bar
                double secondsLeft = timeLeft / 1000.0;
                player.sendActionBar(Component.text("§cCooldown: " + String.format("%.1f", secondsLeft) + "s"));
                return;
            }
        }
        if (off.getPersistentDataContainer().has(glove, PersistentDataType.STRING) && player.isSneaking()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 6000, 3));
            PotionEffect fx = new PotionEffect(PotionEffectType.SLOWNESS, 600, 3);

            ServerTickManager tickManager = Bukkit.getServerTickManager();
            tickManager.setFrozen(true);
            player.getPersistentDataContainer().set(time_cooldown, PersistentDataType.STRING, "on");

            // Apply slowness effect to all players within a 20-block radius
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getLocation().distance(player.getLocation()) <= 20 && !p.equals(player)) {
                    p.addPotionEffect(fx);
                }
            }
            dialcooldowns.put(playerUUID, currentTime);
            // Schedule unfreeze and cooldown reset
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"),
                    new unfreeze(Yahoo.getInstance(), player), 600);
        }
    }
    public void gamble(Player star, ItemStack watch){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        Component component = MiniMessage.miniMessage().deserialize("<shadow:#000000FF><b><color:#a600ff>Time to Gamble!</color> <reset> [");
        component = component.append(star.name());
        component = component.append(MiniMessage.miniMessage().deserialize("]"));
        ItemStack original_item = watch;
        send_to_nearby_players(star.getLocation(),component,10);
        int value = random.nextInt(1,4);
        switch (value) {
            case 0:
                //Archer
                component = MiniMessage.miniMessage().deserialize("");
                send_to_nearby_players(star.getLocation(),component,10);
                ItemMeta changer_meta = watch.getItemMeta();
                watch.setAmount(0);
                star.getInventory().setItemInMainHand(archer_bow());
                Bukkit.getScheduler().runTaskLater(Yahoo.get_plugin(),new Runnable() {@Override public void run() {star.getInventory().setItemInMainHand(original_item);}},5020);

            case 2:
            case 3:
            case 4:
        }
    }
    public key keygen = new key();



    @EventHandler
    public void onArcherShot(EntityShootBowEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (player.getInventory().getItemInMainHand().getPersistentDataContainer().has(keygen.get_key("soul_archer"))) {
                Timer timer = new Timer();
                event.getProjectile().getPersistentDataContainer().set(keygen.get_key("soul_archer"), PersistentDataType.BOOLEAN, true);
                ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
                PacketContainer packetContainer = protocolManager.createPacket(PacketType.Play.Server.ANIMATION);
                packetContainer.getIntegers().write(0, player.getEntityId());
                packetContainer.getIntegers().write(0, 1);
                Bukkit.getScheduler().runTaskTimer(Yahoo.get_plugin(), new Runnable() {
                    int ticks = 0;
                    Vector initialvelocity = event.getProjectile().getVelocity();
                    double initialhealth = player.getHealth();
                    @Override
                    public void run() {
                        if (ticks > 200) {
                            event.getProjectile().getPersistentDataContainer().remove(keygen.get_key("soul_archer"));
                            event.getProjectile().getPersistentDataContainer().set(keygen.get_key("soul_bullet"), PersistentDataType.BOOLEAN, true);
                            event.getProjectile().setVelocity(initialvelocity.multiply(8));
                            event.getProjectile().setNoPhysics(true);
                            this.cancel();
                        }
                        if (player.getHealth() > initialhealth) {
                            event.getProjectile().getPersistentDataContainer().remove(keygen.get_key("soul_archer"));
                            this.cancel();
                        }
                        event.getProjectile().setVelocity(event.getProjectile().getVelocity());
                        event.getProjectile().teleport(event.getProjectile().getLocation());
                        ticks++;
                    }
                    private void cancel(){Bukkit.getScheduler().cancelTask(this.hashCode());}
                },0,2L);
                Bukkit.getScheduler().runTaskTimer(Yahoo.get_plugin(), new Runnable() {
                    int ticks = 0;
                    double initialhealth = player.getHealth();
                    @Override
                    public void run() {
                        if (ticks > 200) {
                            this.cancel();
                        }
                        if (player.getHealth() < initialhealth) {
                            this.cancel();
                        }


                        Bukkit.getOnlinePlayers().forEach(player2 -> {protocolManager.sendServerPacket(player2, packetContainer);});
                         // seems to be how you send packet

                        player.teleport(player.getLocation());
                        for (int iteration = 0; iteration < 24; iteration += 1) {

                            Location loc = player.getLocation();
                            if (player.getLocation().getX() > 0) {
                                loc.setX(player.getLocation().getX() + Math.cos(iteration*(Math.PI/12)));
                            } else {
                                loc.setX(player.getLocation().getX() - Math.cos(iteration*(Math.PI/12)));
                            }
                            if (player.getLocation().getZ() < 0) {
                                loc.setZ(player.getLocation().getX() + Math.sin(iteration*(Math.PI/12)));
                            } else {
                                loc.setZ(player.getLocation().getX() - Math.sin(iteration*(Math.PI/12)));
                            }
                            player.getWorld().spawnParticle(Particle.FIREWORK,loc,8);
                        }
                        for (int iteration = 0; iteration < 48; iteration += 1) {

                            Location loc = player.getLocation();
                            if (player.getLocation().getX() > 0) {
                                loc.setX(player.getLocation().getX() + Math.cos(iteration*(Math.PI/24)));
                            } else {
                                loc.setX(player.getLocation().getX() - Math.cos(iteration*(Math.PI/24)));
                            }
                            if (player.getLocation().getZ() < 0) {
                                loc.setZ(player.getLocation().getX() + Math.sin(iteration*(Math.PI/24)));
                            } else {
                                loc.setZ(player.getLocation().getX() - Math.sin(iteration*(Math.PI/24)));
                            }

                            player.getWorld().spawnParticle(Particle.SCULK_SOUL,loc,8);
                        }
                        ticks++;

                    }
                    private void cancel(){Bukkit.getScheduler().cancelTask(this.hashCode());}


                },0,2L);
                Bukkit.getScheduler().runTaskTimer(Yahoo.get_plugin(),new Runnable() {

                    @Override
                    public void run() {
                        if (event.getProjectile().getPersistentDataContainer().has(keygen.get_key("bullet")) && event.getProjectile().isValid()) {
                            event.getProjectile().getWorld().spawnParticle(Particle.SONIC_BOOM,event.getProjectile().getLocation(),1);
                            event.getProjectile().getWorld().playSound(event.getProjectile().getLocation(),Sound.ENTITY_WARDEN_SONIC_BOOM,1,1);
                        } else {
                            this.cancel();
                        }
                    }
                    private void cancel(){Bukkit.getScheduler().cancelTask(this.hashCode());}
                },0,2L);
            }
        }

    }
    @EventHandler
    public void ArcherCheck(ProjectileHitEvent event) {
        Entity projectile = event.getEntity();
        if (projectile.getPersistentDataContainer().has(keygen.get_key("soul_archer"))) {
            event.setCancelled(true);
        }
        if (projectile.getPersistentDataContainer().has(keygen.get_key("soul_bullet"))) {
            if (event.getHitEntity().isValid()) {
                if (event.getHitEntity() instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) event.getHitEntity();
                    livingEntity.damage(8);
                }
            }
            event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(),8,true,true);
            projectile.remove();

        }
    }
    public ItemStack archer_bow(){ItemStack bow = new ItemStack(Material.BOW);ItemMeta bow_meta = bow.getItemMeta();bow_meta.displayName(MiniMessage.miniMessage().deserialize("<gradient:#55ffff:#ff8af5:#aa00aa>Soul Archer's Bow"));bow_meta.getPersistentDataContainer().set(keygen.get_key("soul_bow"), PersistentDataType.BOOLEAN, true);bow.setItemMeta(bow_meta);return bow;}
    public void send_action_bar(Player player, Component component) {player.sendActionBar(component);}
    public void send_to_nearby_players(Location origin, Component component, int distance) {Bukkit.getOnlinePlayers().forEach(player -> {if (player.getLocation().distance(origin) < distance) {send_action_bar(player, component);}});}
}