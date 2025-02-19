package space.yaszu.yahoo.alchemy.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class soul {
    public static ItemStack soul_item (){
        ItemStack essence = ItemStack.of(Material.RECOVERY_COMPASS);
        ItemMeta essence_meta = essence.getItemMeta();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"Yah_ID");
        essence_meta.getPersistentDataContainer().set(key, PersistentDataType.STRING,"Soul");
        essence_meta.setDisplayName(ChatColor.RED + "Human-Like Soul");
        essence.setItemMeta(essence_meta);
        return essence;
    }
}
