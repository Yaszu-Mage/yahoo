package space.yaszu.yahoo.items;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.*;

public class item_register {
    public void itemregister(String shapea, String shapeb, String shapec, String name, Collection<String> values, Dictionary<String, Material> dict, String tag) {
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), name);
        ItemStack item = ItemStack.of(Material.RECOVERY_COMPASS);
        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(shapea,shapeb,shapec);
        for (String s : values) {
            char value = s.charAt(0);
            recipe.setIngredient(value,dict.get(s));
        }
        NBT.modify(item, nbt -> {
            nbt.setString("Yah_ID", tag);
        });
        Bukkit.getPluginManager().getPlugin("Yahoo").getLogger().info("Registered " + name);
        Bukkit.getServer().addRecipe(recipe);
    }

    public void register(){
        Collection<String> coke = new ArrayList<>();
        coke.add("M");
        coke.add("E");
        coke.add("T");
        Dictionary<String, Material> cokedict = new Hashtable<>();
        cokedict.put("M", Material.SUGAR);
        cokedict.put("E", Material.STICK);
        cokedict.put("T", Material.CARROT);
        itemregister("MET",
                    "   ",
                    "   ",
                    "Coke",
                            coke,
                            cokedict,
                        "coke");
        Collection<String> spawn_crystal = new ArrayList<>();
        spawn_crystal.add("E");
        spawn_crystal.add("A");
        spawn_crystal.add("E");
        spawn_crystal.add("A");
        spawn_crystal.add("D");
        spawn_crystal.add("A");
        spawn_crystal.add("E");
        spawn_crystal.add("A");
        spawn_crystal.add("E");
        Dictionary<String, Material> spawndict = new Hashtable<>();
        spawndict.put("E",Material.ENDER_PEARL);
        spawndict.put("A",Material.AMETHYST_SHARD);
        spawndict.put("D",Material.DIAMOND);
        itemregister("EAE",
                    "ADA",
                    "EAE",
                "Spawn_Crystal",
                        spawn_crystal,spawndict,
                    "spawn_cry");


    }
}
