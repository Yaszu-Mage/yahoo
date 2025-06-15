package space.yaszu.yahoo.movesets.glitch;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class glitched_gem_item {
    public static ItemStack gem() {
        ItemStack gem = ItemStack.of(Material.RECOVERY_COMPASS);
        ItemMeta meta = gem.getItemMeta();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"gem");
        NamespacedKey state = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"state");
        meta.getPersistentDataContainer().set(state,PersistentDataType.INTEGER,0);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING,"glitched_gem");
        meta.setDisplayName("Glitch Infused Gem");
        meta.setItemModel(NamespacedKey.minecraft("glitched_gem"));
        gem.setItemMeta(meta);
        return gem;
    }
}
