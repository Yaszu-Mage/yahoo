package space.yaszu.yahoo.movesets.glitch;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.Random;

public class glitched implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {
        Random random = new Random();
        Player player = event.getPlayer();
        String type = "";
        PersistentDataContainer cont = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
        Block block = event.getBlock();// Get the type of block broken
        int random_int = random.nextInt(1001);
        if (cont.has(key)) {
            type = cont.get(key, PersistentDataType.STRING);
        }

        // Example using player name (less reliable):
        if (type.equals("glitch") && random_int >= 950) {  // Replace with the actual name
            player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_TENDRIL_CLICKS, 1.0f,1.0f);
            int random_int2 = random.nextInt(3);
            if (random_int2 == 0) {
                player.sendRawMessage("It's Gone.");
                player.getWorld().spawnParticle(Particle.POOF,block.getLocation(),16);
                event.setDropItems(false);
            } else if (random_int2 == 1) {
                player.sendRawMessage("An egg?");
                player.getWorld().playSound(block.getLocation(),Sound.ENTITY_CHICKEN_AMBIENT,1.0f,1.0f);
                block.setType(Material.AIR);
                event.setDropItems(false);
                player.getWorld().spawnEntity(block.getLocation(), EntityType.EGG);
            } else if (random_int2 == 2) {
                player.sendRawMessage("But there was only one before?");
                player.getWorld().spawnParticle(Particle.DRIPPING_OBSIDIAN_TEAR,block.getLocation(),16);
                Collection<ItemStack> item = block.getDrops();
                for (ItemStack itemStack : item) {
                    ItemStack duplicate = itemStack.clone();
                    block.getWorld().dropItemNaturally(block.getLocation(), duplicate);
                    block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                }
                event.setDropItems(false);
            }


        } else if (random_int >= 950){
            int random_reroll = random.nextInt(1001);
            if (random_reroll >= 980) {
                int random_msg = random.nextInt(3);
                if (random_msg == 0) {
                    player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 1.0f,1.0f);
                    player.sendRawMessage("Something is off...");
                } else if (random_msg == 1) {
                    player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 1.0f,1.0f);
                    player.sendRawMessage("You feel eyes watching you.");
                } else if (random_msg == 2) {
                    player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 1.0f,1.0f);
                    player.sendRawMessage("We can hear you.");
                }
            }

        }
    }
}
