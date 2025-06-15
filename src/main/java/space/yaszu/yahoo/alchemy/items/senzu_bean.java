package space.yaszu.yahoo.alchemy.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.util.key;

import java.util.Random;

public class senzu_bean implements Listener {
    public Random random = new Random();
    @EventHandler
    public void drop(BlockBreakEvent blockBreakEvent) {
        Block eventblock = blockBreakEvent.getBlock();
        if (check_block.check_if_broke(blockBreakEvent, BlockType.POTATOES)) {
            int value = random.nextInt(1000);
            if (value > 900) {
                eventblock.getDrops().add(bean());
                blockBreakEvent.getPlayer().give(bean());
                Yahoo.getlog().info("A SENZU BEAN HAS SPAWNED");
            }
        };
    }
    @EventHandler
    public void eat(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        if (item.getItemMeta().equals(bean().getItemMeta())) {
            Player player = event.getPlayer();
            player.setHealth(player.getMaxHealth());
            player.setFoodLevel(20);
            player.setSaturation(20);
            NamespacedKey sin = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"sin");
            if (player.getPersistentDataContainer().has(sin)){
                if (player.getPersistentDataContainer().get(sin,PersistentDataType.INTEGER) > 0) {
                    player.getPersistentDataContainer().set(sin,PersistentDataType.INTEGER,player.getPersistentDataContainer().get(sin,PersistentDataType.INTEGER) - 1);
                };
            };
            player.setCooldown(bean(),6000);
            player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE,1,1);
        }
    }

    public ItemStack bean() {
        ItemStack bong = ItemStack.of(Material.TROPICAL_FISH);
        ItemMeta meta = bong.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<dark_purple>|</dark_purple> <color:#3cff00>Senzu Bean</color> <dark_purple>|</dark_purple>"));
        meta.getPersistentDataContainer().set(new key().get_key("item_id"), PersistentDataType.STRING,"senzu_bean");
        meta.setItemModel(NamespacedKey.minecraft("senzu_bean"));
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