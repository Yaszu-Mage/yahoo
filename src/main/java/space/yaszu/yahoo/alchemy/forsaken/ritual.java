package space.yaszu.yahoo.alchemy.forsaken;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import space.yaszu.yahoo.items.soul;

import java.util.List;

public class ritual implements Listener {
    World world;
    // Multiblock Detection
    @EventHandler
    public void checkforcircle(BlockPlaceEvent event) {
        Block original_block = event.getBlock();
        if (original_block.getType().equals("Lecturn")) {
            checkLecternRedstoneCircle(event.getPlayer(),original_block.getLocation());
        }
    }

    public boolean isRedstoneCircle(Location center) {
        // Define offsets for a rough "circle" shape
        int[][] offsets = {
                {-3, 0}, {-3, -1}, {-3, 1}, {-2, -2}, {-2, 2}, {-1, -3}, {-1, 3}, {0, -3},
                {0, 3}, {1, -3}, {1, 3}, {2, -2}, {2, 2}, {3, -1}, {3, 0}, {3, 1}
        };

        for (int[] offset : offsets) {
            Block block = center.clone().add(offset[0], 0, offset[1]).getBlock();
            if (block.getType() != Material.REDSTONE_WIRE) {
                return false;
            }
        }
        return true;
    }

    public void checkLecternRedstoneCircle(Player player, Location lecternLocation) {
        Block block = lecternLocation.getBlock();

        // Check if it's a lectern
        if (!(block.getState() instanceof Lectern lectern)) {
            player.sendMessage("No lectern found at this location.");
            return;
        }

        // Check for 6x6 redstone circle
        if (!isRedstoneCircle(lecternLocation)) {
            player.sendMessage("Redstone circle is incomplete.");
            return;
        }

        // Read book from lectern
        ItemStack book = lectern.getInventory().getItem(0);
        if (book == null || !(book.getItemMeta() instanceof BookMeta bookMeta)) {
            player.sendMessage("No book in the lectern.");
            return;
        }

        String bookContent = String.join(" ", bookMeta.getPages()).toLowerCase();

        // Run functions based on book content
        if (bookContent.contains("forsake")) {
            List<Entity> entities = player.getNearbyEntities(50,50,50);
            createWorld();
            for (Entity e : entities) {
                if (e instanceof Player) {
                    Player player1 = (Player) e;
                    
                }
            }
            player.sendMessage("You have commited a grave sin.");
        } else if (bookContent.contains("sacrifice me")) {
            player.getWorld().dropItem(lecternLocation, soul.soul_item());
            player.sendMessage("Death.");
        } else {
            player.sendMessage("No valid command found in the book.");
        }
    }


    public void createWorld(){
        WorldCreator c = new WorldCreator("forsaken");
        c.generator(new world());
        c.generateStructures(false);
        world = c.createWorld();
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setTime(236927);
        world.setThundering(true);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setStorm(true);

    }
}
