package space.yaszu.yahoo.glitch;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Random;

public class glitched implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {
        Random random = new Random();
        Player player = event.getPlayer();
        Block block = event.getBlock();// Get the type of block broken
        int random_int = random.nextInt(101);


        // Example using player name (less reliable):
        if (player.getName().equals("1nZ4ne") && random_int >= 95) {  // Replace with the actual name
            player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_TENDRIL_CLICKS, 1.0f,1.0f);
            int random_int2 = random.nextInt(4);
            if (random_int2 == 0) {
                player.sendRawMessage("It's Gone.");
                event.setDropItems(false);
            } else if (random_int2 == 1) {
                player.sendRawMessage("An egg?");
                block.setType(Material.AIR);
                event.setDropItems(false);
                player.getWorld().spawnEntity(block.getLocation(), EntityType.EGG);
            } else if (random_int2 == 2) {
                player.sendRawMessage("But there was only one before?");
                Collection<ItemStack> item = block.getDrops();
                for (ItemStack itemStack : item) {
                    ItemStack duplicate = itemStack.clone();
                    block.getWorld().dropItemNaturally(block.getLocation(), duplicate);
                    block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                }
                event.setDropItems(false);
            } else if (random_int2 == 3) {
                event.setDropItems(false);
                block.setType(Material.AIR);
                player.getWorld().spawnEntity(block.getLocation(), EntityType.MINECART);
            }


        } else if (random_int >= 99){
            player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 1.0f,1.0f);
            player.sendRawMessage("Something is off...");
        }
    }
}
