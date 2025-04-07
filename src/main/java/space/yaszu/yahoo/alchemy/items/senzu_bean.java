package space.yaszu.yahoo.alchemy.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.key;

import java.util.Random;

public class senzu_bean implements Listener {
    public Random random = new Random();
    public void drop(BlockBreakEvent blockBreakEvent) {
        Block eventblock = blockBreakEvent.getBlock();
        if (check_block.check_if_broke(blockBreakEvent, BlockType.POTATOES)) {
            int value = random.nextInt(1000);
            if (value > 900) {
                eventblock.getDrops().add(bean());
                Yahoo.getlog().info("A SENZU BEAN HAS SPAWNED");
            }
        };
    }

    public ItemStack bean() {
        ItemStack bong = ItemStack.of(Material.BAKED_POTATO);
        ItemMeta meta = bong.getItemMeta();
        meta.displayName(Component.text("Senzu Bean"));
        meta.getPersistentDataContainer().set(new key().get_key("item_id"), PersistentDataType.STRING,"senzu_bean");
        bong.setItemMeta(meta);
        return bong;
    }
}
class check_block implements Listener{
    public static boolean check_if_broke(BlockBreakEvent event, BlockType block){
        boolean result = false;
        Block event_block = event.getBlock();
        if (block == event_block.getType().asBlockType()) {
            result = true;
        }
        return result;
    }
}