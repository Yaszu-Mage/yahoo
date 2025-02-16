package space.yaszu.yahoo.flamer;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.joml.Vector3d;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.events.off;
import space.yaszu.yahoo.events.set_who;
import space.yaszu.yahoo.glitch.buff;

import javax.xml.stream.events.Namespace;
import java.util.ArrayList;

import static org.bukkit.Bukkit.getPluginManager;

public class snap implements Listener {
    private final Yahoo yahoo;
    public snap(Yahoo yahoo) {
        this.yahoo = yahoo;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent interact) {
        Player player = interact.getPlayer();
        NamespacedKey cooldown = new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("Yahoo"), "cooldown");
        ItemStack held = player.getInventory().getItemInOffHand();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "glove");
        if (held.getPersistentDataContainer().has(key, PersistentDataType.STRING) && !player.getWorld().hasStorm()) {


        if (player.getPersistentDataContainer().get(cooldown, PersistentDataType.STRING) == null) {
            player.getPersistentDataContainer().set(cooldown, PersistentDataType.STRING, "off");
        }
        if (player.getPersistentDataContainer().get(cooldown, PersistentDataType.STRING).equals("off") && player.isSneaking()) {
            World world = player.getWorld();
            Location playerLoc = player.getLocation();

            // 1. Get the player's eye location:
            Location eyeLoc = player.getEyeLocation();

            // 2. Calculate the fireball's spawn location (slightly in front of the eyes):
            Vector direction = player.getEyeLocation().getDirection(); // Direction vector
            double fireballSpawnDistance = 1.5; // Adjust this value (e.g., 0.5, 1.0, 1.5)
            Location fireballLoc = eyeLoc.clone().add(direction.getX() * fireballSpawnDistance, direction.getY() * fireballSpawnDistance, direction.getZ() * fireballSpawnDistance);


            // 3. Spawn the fireball at the calculated location:
            Entity fireball = world.spawnEntity(fireballLoc, EntityType.FIREBALL);
            player.setInvulnerable(true);

            // 4. (Optional but Recommended) Set the fireball's direction
            if (fireball instanceof Fireball) {
                ((Fireball) fireball).setDirection(direction);
                player.setInvulnerable(false);// Launch the fireball in the player's looking direction
            }
            player.getPersistentDataContainer().set(cooldown, PersistentDataType.STRING, "on");
            Bukkit.getScheduler().runTaskLater(getPluginManager().getPlugin("Yahoo"),new off(yahoo,player,cooldown,"cooldown"),20);

        }}
    }
}