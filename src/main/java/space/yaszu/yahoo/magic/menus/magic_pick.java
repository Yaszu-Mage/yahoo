package space.yaszu.yahoo.magic.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class magic_pick {
    public static Inventory inventory = Bukkit.createInventory(null,9,"Magic");
    static {
        inventory.setItem(0,new ItemStack(Material.TINTED_GLASS,1));
        inventory.setItem(1,new ItemStack(Material.TINTED_GLASS,1));
    }
}
