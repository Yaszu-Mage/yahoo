package space.yaszu.yahoo.items;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
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
import space.yaszu.yahoo.Yahoo;

import java.util.HashMap;
import java.util.UUID;

public class alchemic_bag implements Listener {
    private final Yahoo yahoo;
    public alchemic_bag(Yahoo yahoo) {
        this.yahoo = yahoo;
    }
    private final HashMap<UUID, Long> cooldowns = new HashMap<>(); // Cooldown storage
    private long cooldownTime = 5000;

    @EventHandler
    public void right_click(PlayerInteractEvent event) throws InterruptedException {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInOffHand();
        String id;
        UUID playerUUID = player.getUniqueId();

        long currentTime = System.currentTimeMillis();
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
        if (item.getAmount() != 0) {

            ItemMeta meta = item.getItemMeta();
            NamespacedKey sin = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"sin");
            NamespacedKey key2 = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"Yah_ID");

            id = meta.getPersistentDataContainer().get(key2, PersistentDataType.STRING);
            if (id != null) {
                space.yaszu.yahoo.Yahoo.getPlugin(Yahoo.class).getLogger().info(id + " was used");
                if (id.equals("alchemic_bag")){
                    ItemStack main_hand = player.getInventory().getItemInMainHand();
                    PersistentDataContainer playercont = player.getPersistentDataContainer();
                    NamespacedKey alchemic_cooldown = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"alchemic_cooldown");
                    if (playercont.get(alchemic_cooldown,PersistentDataType.BOOLEAN) == null) {
                        playercont.set(alchemic_cooldown,PersistentDataType.BOOLEAN,false);
                    }
                    boolean cooldown = playercont.get(alchemic_cooldown,PersistentDataType.BOOLEAN);
                    if (main_hand.getType().equals(Material.LEATHER) && main_hand.getAmount() >= 5) {
                        playercont.set(alchemic_cooldown, PersistentDataType.BOOLEAN, true);
                        cooldownTime = 60000;
                        cooldowns.put(playerUUID, currentTime);
                        player.sendRawMessage("Petah... The Horse is here");
                        main_hand.subtract(5);
                        Horse h = (Horse) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.HORSE);
                        NamespacedKey dontdrop = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"DontDrop");
                        h.getPersistentDataContainer().set(dontdrop,PersistentDataType.BOOLEAN,true);
                        h.setTamed(true);
                        h.setOwner(player);
                        h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));

                        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"), /* Lambda: */ () -> {
                            h.getWorld().spawnParticle(Particle.POOF,h.getEyeLocation(),128);
                            h.remove();
                            playercont.set(alchemic_cooldown, PersistentDataType.BOOLEAN, false);
                        }, /* End of the lambda */ 6000);
                    }
                    if (main_hand.getItemMeta().equals(soul.soul_item().getItemMeta())) {
                        // Human transmutation. Using Human souls as a power source
                        playercont.set(alchemic_cooldown, PersistentDataType.BOOLEAN, true);
                        if (!playercont.has(sin)) {
                            player.sendRawMessage("You have commited a grave sin.");
                            playercont.set(sin,PersistentDataType.INTEGER,1);
                        } else {
                            player.sendRawMessage("You have sinned again.");
                            playercont.set(sin, PersistentDataType.INTEGER, playercont.get(sin, PersistentDataType.INTEGER) + 1);
                        }
                        main_hand.subtract(1);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,600,4));
                        cooldownTime = 30000;
                        cooldowns.put(playerUUID, currentTime);
                    }
                    if (main_hand.getItemMeta().equals(home_pearl.home_pearl_item().getItemMeta())) {
                        main_hand.subtract(1);
                        cooldownTime = 3000;
                        cooldowns.put(playerUUID, currentTime);
                        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),20);
                        player.getWorld().spawnParticle(Particle.POOF,player.getLocation(),120);
                        player.playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1.0f,1.0f);
                        player.getWorld().spawnParticle(Particle.PORTAL,player.getRespawnLocation(),20);
                        player.teleport(player.getWorld().getSpawnLocation());
                        player.getWorld().spawnParticle(Particle.POOF,player.getLocation(),120);
                        player.playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1.0f,1.0f);
                        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),20);

                    }
                    
                }
             }
        }
    }
}
//i wish to kill myself because of this texture pack//
// I wish to kill myself because of this plugin