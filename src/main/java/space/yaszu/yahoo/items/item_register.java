package space.yaszu.yahoo.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.alchemy.items.*;
import space.yaszu.yahoo.events.Ritual;
import space.yaszu.yahoo.glitch.glitched_gem_item;
import space.yaszu.yahoo.key;
import java.util.*;

public class item_register {
    public static key keygen = new key();
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
        item.getItemMeta().getPersistentDataContainer().set(keygen.get_key("Yah_ID"),PersistentDataType.STRING,tag);
        Yahoo.getlog().info("Added " + tag);
        Bukkit.getPluginManager().getPlugin("Yahoo").getLogger().info("Registered " + name);
        Bukkit.getServer().addRecipe(recipe);
    }
    private final Yahoo yahoo;
    public item_register(Yahoo yahoo) {
        this.yahoo = yahoo;
    }
    public void register(){
        Yahoo.getlog().info(
                "ADDING ITEMS" +
                        "________________________"
        );
        Bukkit.resetRecipes();
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
        ItemStack magical_cloth = ItemStack.of(Material.WHITE_WOOL,8);
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
        cloak_recipe.shape("M M","MMM","MMM");
        cloak_recipe.setIngredient('M',magical_cloth);
        Bukkit.addRecipe(cloak_recipe);
        ItemStack fast_boots = boots_of_swiftness.boots();
        ShapedRecipe fast_boots_recipe = new ShapedRecipe(keygen.get_key("fast_boots"),fast_boots);
        fast_boots_recipe.shape("MMM","MBM","MMM");
        fast_boots_recipe.setIngredient('M',magical_cloth);
        fast_boots_recipe.setIngredient('B',Material.LEATHER_BOOTS);
        Bukkit.addRecipe(fast_boots_recipe);
        ItemStack sciencebook = Ritual.science_book();
        ShapedRecipe science_recipe = new ShapedRecipe(keygen.get_key("science_book"),sciencebook);
        science_recipe.shape("SB ");
        science_recipe.setIngredient('S', soul.soul_item());
        science_recipe.setIngredient('B', Material.BOOK);
        Bukkit.addRecipe(science_recipe);
        ItemStack jetstrash = ItemStack.of(Material.POISONOUS_POTATO);
        ItemMeta meta = jetstrash.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<color:#ff0099><color:#f7889a>Jet's Trash</color></color>"));
        jetstrash.setItemMeta(meta);
        ShapedRecipe jetstrashrecipe = new ShapedRecipe(keygen.get_key("jetstrash"),jetstrash);

        jetstrashrecipe.shape("SCO","LA ","   ");
        jetstrashrecipe.setIngredient('S', ItemStack.of(Material.OAK_SIGN,2));
        jetstrashrecipe.setIngredient('L', ItemStack.of(Material.OAK_LOG,54));
        jetstrashrecipe.setIngredient('C', ItemStack.of(Material.CHERRY_LOG,9));
        jetstrashrecipe.setIngredient('O',ItemStack.of(Material.OAK_PLANKS,3));
        jetstrashrecipe.setIngredient('A',ItemStack.of(Material.STONE_AXE,1));
        Bukkit.addRecipe(jetstrashrecipe);
        ItemStack scythe_weapon = scythe.scythe_item();
        ShapedRecipe scythe_weapon_recipe = new ShapedRecipe(keygen.get_key("scythe"),scythe_weapon);
        scythe_weapon_recipe.shape("DDD"," SD","S  ");
        scythe_weapon_recipe.setIngredient('S', ItemStack.of(Material.STICK));
        scythe_weapon_recipe.setIngredient('D', ItemStack.of(Material.DIAMOND));
        Bukkit.addRecipe(scythe_weapon_recipe);
        ItemStack impish_helmet = impish_armor.impish_helmet();
        ItemStack impish_chestplate = impish_armor.impish_chestplate();
        ItemStack impish_leggings = impish_armor.impish_leggings();
        ItemStack impish_boots = impish_armor.impish_boots();
        ShapedRecipe impish_helmet_recipe = new ShapedRecipe(keygen.get_key("impish_helmet"),impish_helmet);
        ShapedRecipe impish_chestplate_recipe = new ShapedRecipe(keygen.get_key("impish_chestplate"),impish_chestplate);
        ShapedRecipe impish_leggings_recipe = new ShapedRecipe(keygen.get_key("impish_leggings"),impish_leggings);
        ShapedRecipe impish_boots_recipe = new ShapedRecipe(keygen.get_key("impish_boots"),impish_boots);
        impish_helmet_recipe.shape("MBM","BDB","MBM");
        impish_chestplate_recipe.shape("MBM","BDB","MBM");
        impish_leggings_recipe.shape("MBM","BDB","MBM");
        impish_boots_recipe.shape("MBM","BDB","MBM");
        impish_helmet_recipe.setIngredient('B',Material.BLAZE_POWDER);
        impish_chestplate_recipe.setIngredient('B',Material.BLAZE_POWDER);
        impish_leggings_recipe.setIngredient('B',Material.BLAZE_POWDER);
        impish_boots_recipe.setIngredient('B',Material.BLAZE_POWDER);
        impish_helmet_recipe.setIngredient('M',Material.MAGMA_CREAM);
        impish_chestplate_recipe.setIngredient('M',Material.MAGMA_CREAM);
        impish_leggings_recipe.setIngredient('M',Material.MAGMA_CREAM);
        impish_boots_recipe.setIngredient('M',Material.MAGMA_CREAM);
        impish_helmet_recipe.setIngredient('D',Material.DIAMOND_HELMET);
        impish_chestplate_recipe.setIngredient('D',Material.DIAMOND_CHESTPLATE);
        impish_leggings_recipe.setIngredient('D',Material.DIAMOND_LEGGINGS);
        impish_boots_recipe.setIngredient('D',Material.DIAMOND_BOOTS);
        Bukkit.addRecipe(impish_helmet_recipe);
        Bukkit.addRecipe(impish_chestplate_recipe);
        Bukkit.addRecipe(impish_leggings_recipe);
        Bukkit.addRecipe(impish_boots_recipe);
        ItemStack glaive_weapon = glaive.glaive_item();
        ShapedRecipe glaive_recipe = new ShapedRecipe(keygen.get_key("glaive"),glaive_weapon);
        glaive_recipe.shape("  D"," S ","S  ");
        glaive_recipe.setIngredient('S', ItemStack.of(Material.STICK));
        glaive_recipe.setIngredient('D', ItemStack.of(Material.DIAMOND));
        Bukkit.getServer().addRecipe(glaive_recipe);
        ItemStack cherry_coke = space.yaszu.yahoo.alchemy.items.cherry_coke.item();
        ShapedRecipe cherry_coke_recipe = new ShapedRecipe(keygen.get_key("cherry_coke"),cherry_coke);
        cherry_coke_recipe.shape("SGS","GBG","SGS");
        cherry_coke_recipe.setIngredient('S',Material.SUGAR);
        cherry_coke_recipe.setIngredient('G',Material.GUNPOWDER);
        cherry_coke_recipe.setIngredient('B',Material.GLASS_BOTTLE);
        Bukkit.addRecipe(cherry_coke_recipe);
        //you dont neccesarily have to do that I have no clue why it did it there, try just using meth.item()
        ItemStack leaper_item = space.yaszu.yahoo.alchemy.items.leaper.item(); // :D
        //Java is a little finnicky and isn't always the same so if it doesn't say error then yaya!
        //BTW if you ever want to test, ask and and I can build it then the server ip is play.yaszu.xyz:30000
        ItemStack meth_item = space.yaszu.yahoo.alchemy.items.meth.item();
        ShapedRecipe meth_recipe = new ShapedRecipe(keygen.get_key("meth"),meth_item);
        meth_recipe.shape(
                "GGG",
                "SSS",
                "SBS");
        meth_recipe.setIngredient('S',Material.SUGAR);
        meth_recipe.setIngredient('G',Material.DIAMOND);
        meth_recipe.setIngredient('B',Material.GLASS_BOTTLE);
        Bukkit.addRecipe(meth_recipe);
        ItemStack backpack_item = backpack.backpack_item();
        ShapedRecipe backpack_recipe = new ShapedRecipe(keygen.get_key("backpack"),backpack_item);
        backpack_recipe.shape("S  ","LCL","LLL");
        backpack_recipe.setIngredient('S',Material.STRING);
        backpack_recipe.setIngredient('L',Material.LEATHER);
        backpack_recipe.setIngredient('C',Material.CHEST);
        Bukkit.addRecipe(backpack_recipe);
    }

}
//GUN//