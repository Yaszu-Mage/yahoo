package space.yaszu.yahoo.alchemy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import space.yaszu.yahoo.alchemy.items.home_pearl;
public class run_register {
    public static void register(){
        ItemStack homepearl = home_pearl.home_pearl_item();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_ID");
        ShapedRecipe recipe = new ShapedRecipe(homepearl);
        recipe.shape(" E ","EDE"," E ");
        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(recipe);
    }
}
