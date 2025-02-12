package space.yaszu.yahoo.items;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import space.yaszu.yahoo.Yahoo;

import java.util.Collection;

public class item_register_craft {

    public static void register_meth(){
        ItemStack item = new ItemStack(Material.HONEY_BOTTLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Coke");
        item.setItemMeta(meta);
        NBT.modify(item, nbt ->{
            nbt.setString("custom", "true");
            nbt.setString("type","coke");
        });
        NamespacedKey key = new NamespacedKey(Yahoo.getPlugin(Yahoo.class),"coke");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("M","E","T");
        recipe.setIngredient('E', Material.CARROT);
        recipe.setIngredient('T', Material.STICK);
        recipe.setIngredient('M', Material.SUGAR);
        Bukkit.addRecipe(recipe);
    }

}
