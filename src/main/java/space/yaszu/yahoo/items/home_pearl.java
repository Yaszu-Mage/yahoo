package space.yaszu.yahoo.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class home_pearl {
    public static ItemStack home_pearl_item (){
        ItemStack essence = ItemStack.of(Material.RECOVERY_COMPASS);
        ItemMeta essence_meta = essence.getItemMeta();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"Yah_ID");
        essence_meta.getPersistentDataContainer().set(key, PersistentDataType.STRING,"homepearl");
        essence_meta.setDisplayName(ChatColor.GREEN + "Home Pearl");
        essence.setItemMeta(essence_meta);
        return essence;
    }
}
