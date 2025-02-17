package space.yaszu.yahoo.items;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.events.gun_off;
import space.yaszu.yahoo.events.off;

import static org.bukkit.Bukkit.createProfile;
import static org.bukkit.Bukkit.getPluginManager;

public class item_event implements Listener {
    private final Yahoo yahoo;
    public item_event(Yahoo yahoo) {
        this.yahoo = yahoo;
    }
    @EventHandler
    public void right_click(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        String id;
        if (item.getAmount() != 0) {


            ItemMeta meta = item.getItemMeta();
            NamespacedKey key2 = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"Yah_ID");
            id = meta.getPersistentDataContainer().get(key2, PersistentDataType.STRING);
            if (id != null) {
                space.yaszu.yahoo.Yahoo.getPlugin(Yahoo.class).getLogger().info(id + " was used");
                if (id.equals("coke")) {
                    item.subtract(1);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 500, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 500, 1));
                }
                if (id.equals("spawn_cry")) {
                    item.subtract(1);
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 1f, 1f);
                    player.spawnParticle(Particle.PORTAL, player.getLocation(), 64);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 1000, 1));
                    player.teleport(new Location(player.getWorld(), 0, 100, 0));
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 1f, 1f);
                }
                if (id.equals("glitch_cry")) {
                    if (player.getDisplayName() == "1nZ4ne") {
                        // Do actual things
                    } else {
                        player.sendRawMessage("You try and harness the power... but nothing happens.");
                    }
                }
                if (id.equals("gun")) {
                    double gunspeed = 3;
                    NamespacedKey cooldown = new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("Yahoo"), "gun_cooldown");

                    if (player.getDisplayName().equals("GhostboyGamer")) { // Use .equals() instead of ==
                        gunspeed = 4;
                    }

                    if (player.getPersistentDataContainer().get(cooldown, PersistentDataType.STRING).equals("off")) {
                        // Check if the player has an arrow
                        PlayerInventory inventory = player.getInventory();
                        if (inventory.contains(Material.ARROW)) { // Check if player has at least one arrow
                            inventory.removeItem(new ItemStack(Material.ARROW, 1)); // Remove one arrow

                            World world = player.getWorld();
                            Location playerLoc = player.getLocation();

                            // 1. Get the player's eye location:
                            Location eyeLoc = player.getEyeLocation();

                            // 2. Calculate the arrow's spawn location (slightly in front of the eyes):
                            Vector direction = eyeLoc.getDirection(); // Direction vector
                            double arrowSpawnDistance = 1.5; // Adjust this value
                            Location arrowLoc = eyeLoc.clone().add(direction.clone().multiply(arrowSpawnDistance));

                            // 3. Spawn the arrow at the calculated location:
                            Arrow arrow = world.spawn(arrowLoc, Arrow.class);

                            // 4. Set the arrow's velocity
                            arrow.setVelocity(direction.multiply(gunspeed)); // Use the correct gun speed

                            // 5. Make the arrow face the direction of the player
                            arrow.setRotation(player.getEyeLocation().getYaw(), player.getEyeLocation().getPitch());

                            // 6. Run cooldown task
                            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"), new gun_off(yahoo), 20);
                        } else {
                            player.sendMessage(ChatColor.RED + "You need arrows to shoot!");
                        }
                    }

                }
            }
        }

    }
}
