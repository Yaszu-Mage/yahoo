package space.yaszu.yahoo.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class soul {
    public static ItemStack soul_item (){
        ItemStack essence = ItemStack.of(Material.RECOVERY_COMPASS);
        ItemMeta essence_meta = essence.getItemMeta();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"Yah_ID");
        essence_meta.getPersistentDataContainer().set(key, PersistentDataType.STRING,"Soul");
        essence_meta.displayName(MiniMessage.miniMessage().deserialize("<dark_purple><obf>d</dark_purple> <reset><shadow:#000000FF><b><color:#ff002b>Human Like Soul</color> <dark_purple><obf>d</dark_purple>"));
        essence.setItemMeta(essence_meta);
        return essence;
    }
}
