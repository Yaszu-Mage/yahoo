package space.yaszu.yahoo.alchemy.items;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.yaszu.yahoo.Yahoo;

import java.util.Collection;

public class invisibility_cloak implements Listener {
    public ItemStack cloak() {
        ItemStack item = ItemStack.of(Material.LEATHER_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Invisibility Cloak");
        item.setItemMeta(meta);
        return item;
    }
    @EventHandler
    public void on_armor_put(PlayerArmorChangeEvent event) {
        ItemStack item = event.getNewItem();
        if (item.getItemMeta().equals(cloak().getItemMeta())) {
            Collection players = Bukkit.getOnlinePlayers();
            for (Object player : players) {
                if (player instanceof Player) {
                    Player local = event.getPlayer();
                    if (player != local) {
                        ((Player) player).hidePlayer(Yahoo.get_plugin(),local);
                    }
                }
            }
        }
        ItemStack old_item = event.getOldItem();
        ItemMeta old_meta = old_item.getItemMeta();
        if (old_meta.equals(cloak().getItemMeta())) {
            Collection players = Bukkit.getOnlinePlayers();
            for (Object player : players) {
                if (player instanceof Player) {
                    Player local = event.getPlayer();
                    if (player != local) {
                        ((Player) player).showPlayer(Yahoo.get_plugin(),local);
                    }
                }
            }
        }
    }
}
