package space.yaszu.yahoo.items;

import de.tr7zw.nbtapi.NBT;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.glitch.glitched_gem_item;
import space.yaszu.yahoo.key;
import java.util.*;
import space.yaszu.yahoo.alchemy.items.space_warper;
import space.yaszu.yahoo.alchemy.items.invisibility_cloak;

public class item_register {
    public void itemregister(String shapea, String shapeb, String shapec, String name, Collection<String> values, Dictionary<String, Material> dict, String tag) {
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), name);
        ItemStack item = ItemStack.of(Material.RECOVERY_COMPASS);
        ItemMeta meta = item.getItemMeta();
        NamespacedKey key2 = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"Yah_ID");
        meta.displayName(Component.text(name));
        meta.setItemModel(NamespacedKey.minecraft(tag));
        meta.getPersistentDataContainer().set(key2, PersistentDataType.STRING, tag);
        item.setItemMeta(meta);
        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(shapea,shapeb,shapec);
        for (String s : values) {
            char value = s.charAt(0);
            recipe.setIngredient(value,dict.get(s));
        }
        NBT.modify(item, nbt -> {
            nbt.setString("Yah_ID", tag);
        });
        NBT.get(item, nbt ->{
            Bukkit.getPluginManager().getPlugin("Yahoo").getLogger().info("Tag " + nbt.getString("Yah_ID"));
        });
        Bukkit.getPluginManager().getPlugin("Yahoo").getLogger().info("Registered " + name);
        Bukkit.getServer().addRecipe(recipe);
    }
    private final Yahoo yahoo;
    public item_register(Yahoo yahoo) {
        this.yahoo = yahoo;
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
        Collection<String> gun = new ArrayList<>();
        gun.add("N");
        gun.add("I");
        gun.add("D");
        gun.add("C");
        gun.add("B");
        Dictionary<String, Material> gundict = new Hashtable<>();
        gundict.put("N",Material.IRON_NUGGET);
        gundict.put("I",Material.IRON_INGOT);
        gundict.put("D",Material.DIAMOND);
        gundict.put("C",Material.CROSSBOW);
        gundict.put("B",Material.IRON_BLOCK);
        itemregister(" N ",
                "IDC",
                "B  ",
                "Piercer",
                gun,gundict,
                "gun");
        Collection<String> alchemic = new ArrayList<>();
        alchemic.add("L");
        alchemic.add("L");
        alchemic.add("D");
        alchemic.add("L");
        alchemic.add("L");
        Dictionary<String, Material> alchemicdict = new Hashtable<>();
        alchemicdict.put("L",Material.LEATHER);
        alchemicdict.put("D",Material.DIAMOND);
        itemregister(" L ",
                "LDL",
                " L ",
                "Alchemic_Bag",
                alchemic,alchemicdict,
                "alchemic_bag");
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"gem");
        ItemStack gem = glitched_gem_item.gem();
        ShapedRecipe recipe = new ShapedRecipe(key, gem);
        recipe.shape("ANS","NCN","DNA");
        recipe.setIngredient('A', Material.AMETHYST_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('C', Material.END_CRYSTAL);
        recipe.setIngredient('S', Material.NETHER_STAR);
        Bukkit.getServer().addRecipe(recipe);
        key keygen = new key();
        ItemStack warper = space_warper.warper();
        ShapedRecipe recipe2 = new ShapedRecipe(keygen.get_key("spacewarper"),warper);
        recipe2.shape("IAI","ADA","IAI");
        recipe2.setIngredient('I', Material.IRON_INGOT);
        recipe2.setIngredient('A', Material.AMETHYST_SHARD);
        recipe2.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(recipe2);
        ItemStack magical_cloth = ItemStack.of(Material.WHITE_WOOL);
        ItemMeta magical_cloth_meta = magical_cloth.getItemMeta();
        magical_cloth_meta.setDisplayName("Magical Cloth");
        magical_cloth_meta.setEnchantmentGlintOverride(true);
        magical_cloth.setItemMeta(magical_cloth_meta);
        ShapedRecipe cloth_recipe = new ShapedRecipe(keygen.get_key("magical_cloth"),magical_cloth);
        cloth_recipe.shape("WWW","WAW","WWW");
        cloth_recipe.setIngredient('W',Material.WHITE_WOOL);
        cloth_recipe.setIngredient('A',Material.AMETHYST_SHARD);
        Bukkit.addRecipe(cloth_recipe);
        ItemStack cloak = invisibility_cloak.cloak();
        ShapedRecipe cloak_recipe = new ShapedRecipe(keygen.get_key("invisibility_cloak"),cloak);
        cloak_recipe.shape("MMM","MCM","MMM");
        cloak_recipe.setIngredient('M',magical_cloth);
        cloth_recipe.setIngredient('C',Material.LEATHER_CHESTPLATE);
        ItemStack fast_boots = boots_of_swiftness.boots();
        ShapedRecipe fast_boots_recipe = new ShapedRecipe(keygen.get_key("fast_boots"),fast_boots);
        fast_boots_recipe.shape("MMM","MBM","MMM");
        fast_boots_recipe.setIngredient('M',magical_cloth);
        fast_boots_recipe.setIngredient('B',Material.LEATHER_BOOTS);
        Bukkit.addRecipe(fast_boots_recipe);
    }

}
//GUN//