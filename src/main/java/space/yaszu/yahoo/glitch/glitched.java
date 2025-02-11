package space.yaszu.yahoo.glitch;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class glitched implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {
        Random random = new Random();
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = block.getType(); // Get the type of block broken
        int random_int = random.nextInt(101);


        // Example using player name (less reliable):
        if (player.getName().equals("1nZ4ne") && random_int >= 95) {  // Replace with the actual name
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 1.0f,1.0f);
            int random_int2 = random.nextInt(5);
            switch (random_int2) {
                case 0:
                    block.setType(Material.DIRT);
                case 1:
                    block.setType(Material.AIR);
                case 2:
                    block.setType(Material.SOUL_SAND);
                case 3:
                    block.setType(Material.AIR);
                    player.getWorld().spawnEntity(block.getLocation(),EntityType.CHICKEN);
                case 4:
                    block.setType(Material.AIR);
                    player.getWorld().spawnEntity(block.getLocation(), EntityType.EGG);
            }

        } else if (random_int >= 99){
            player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 1.0f,1.0f);
            player.sendRawMessage("Something is off...");
        }
    }
}
