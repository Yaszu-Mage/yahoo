package space.yaszu.yahoo.events;

import io.papermc.paper.event.player.PlayerInsertLecternBookEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.player_info.player_info_register;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Ritual implements Listener {
    public static Map<UUID,Ritualmenu> Ritualmap = new HashMap<>();
    public static Map<String, RitualRecipe> RitualRecipeMap = new HashMap<>();
    public static ItemStack science_book() {
        ItemStack item = ItemStack.of(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) item.getItemMeta();
        meta.author(MiniMessage.miniMessage().deserialize("<color:#0e0087>Lesser Divinity</color>"));
        meta.title(MiniMessage.miniMessage().deserialize("<blue><color:#54006b><dark_red><obf>Sciency</obf></dark_red> Book</color></blue>"));
        for (int x = 1; x < 100; x = x + 1) {
            meta.addPages(MiniMessage.miniMessage().deserialize("<dark_red><obf>ScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScienceStuffScie</obf></dark_red>"));
        }
        item.setItemMeta(meta);
        return item;
    }
    public static void register_recipes() {
        RitualRecipe Glitch = new RitualRecipe(ItemStack.of(Material.OBSIDIAN),ItemStack.of(Material.NETHER_STAR),ItemStack.of(Material.OBSIDIAN),ItemStack.of(Material.END_CRYSTAL),ItemStack.of(Material.END_CRYSTAL));
        RitualRecipe Star = new RitualRecipe(ItemStack.of(Material.RABBIT_FOOT),ItemStack.of(Material.NETHER_STAR),ItemStack.of(Material.RABBIT_FOOT),ItemStack.of(Material.FEATHER),ItemStack.of(Material.DIAMOND));
        RitualRecipe Flamer = new RitualRecipe(ItemStack.of(Material.BLAZE_POWDER),ItemStack.of(Material.NETHER_WART),ItemStack.of(Material.BLAZE_POWDER),ItemStack.of(Material.IRON_BARS),ItemStack.of(Material.IRON_BARS));
        RitualRecipe Demon = new RitualRecipe(ItemStack.of(Material.BLAZE_POWDER),ItemStack.of(Material.NETHER_STAR),ItemStack.of(Material.BLAZE_POWDER),ItemStack.of(Material.IRON_BARS),ItemStack.of(Material.IRON_BARS));
        RitualRecipeMap.put("Glitch",Glitch);
        RitualRecipeMap.put("Star",Star);
        RitualRecipeMap.put("Flamer",Flamer);
        RitualRecipeMap.put("Demon",Demon);
    }
    @EventHandler
    public static void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        register_recipes();
        if (!(inventory.getHolder(false) instanceof Ritualmenu ritualmenu)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        ritualmenu = Ritualmap.get(player.getUniqueId());

        if (event.getClickedInventory() == player.getInventory() && (event.getClickedInventory().getHolder() instanceof Ritualmenu || event.getClickedInventory() == player.getInventory()) || event.getCurrentItem() == null || (event.getInventory().getHolder() instanceof Ritualmenu && (event.getSlot() == 11 || event.getSlot() == 13 || event.getSlot() == 15 || event.getSlot() == 29 || event.getSlot() == 33))) {return;}else {event.setCancelled(true);}
        if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
            //CRAFT
            if (!(inventory.getItem(11) != null && inventory.getItem(13) != null && inventory.getItem(15) != null && inventory.getItem(29) != null && inventory.getItem(33) != null)) {
                //Pass
            } else {
            RitualRecipe recipe_in = new RitualRecipe(inventory.getItem(11),inventory.getItem(13),inventory.getItem(15),inventory.getItem(29),inventory.getItem(33));
            String recipe_match = "";
            for (String key : RitualRecipeMap.keySet()) {
                Yahoo.getlog().info(key);
                Yahoo.getlog().info(RitualRecipeMap.get(key).Ingredient1.toString());
                Yahoo.getlog().info(String.valueOf(recipe_in.Ingredient1.getType() == RitualRecipeMap.get(key).Ingredient1.getType()));
                if (recipe_in.Ingredient1.getType() == RitualRecipeMap.get(key).Ingredient1.getType() && recipe_in.Ingredient2.getType() == RitualRecipeMap.get(key).Ingredient2.getType() && recipe_in.Ingredient3.getType() == RitualRecipeMap.get(key).Ingredient3.getType() && recipe_in.Ingredient4.getType() == RitualRecipeMap.get(key).Ingredient4.getType() && recipe_in.Ingredient5.getType() == RitualRecipeMap.get(key).Ingredient5.getType()){
                   recipe_match = key;
                }
            }
            if (!recipe_match.isEmpty()) {
                check_player(player.displayName().toString(),recipe_match.toLowerCase());
            }
        }}

    }
    public static void check_player(String player, String tag){
        if (Bukkit.getPlayer(player) != null){
            Player play = Bukkit.getPlayer(player);
            player_info_register.register(play, tag);
        }
    }
    @EventHandler
    public static void onPlayerLecturnClick(PlayerInsertLecternBookEvent event) {
        if (event.getBook().equals(science_book())) {
            if (Ritualmap.get(event.getPlayer().getUniqueId()) == null) {
                Ritualmenu menu = new Ritualmenu();
                Ritualmap.put(event.getPlayer().getUniqueId(),menu);
            }
            Ritualmap.get(event.getPlayer().getUniqueId()).set_items();
            event.getPlayer().openInventory(Ritualmap.get(event.getPlayer().getUniqueId()).getInventory());
            event.setCancelled(true);
        }
    }


}
class Ritualmenu implements InventoryHolder {
    private final Inventory inventory;
    public Ritualmenu () {
        this.inventory = Bukkit.createInventory(this,45,"Ritual");
    }
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
    public void set_items() {
        for (int x = 0; x < inventory.getSize(); x = x +1) {
            if (x == inventory.getSize()) {
                break;
            }
            ItemStack empty = ItemStack.of(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta meta = empty.getItemMeta();
            meta.displayName(MiniMessage.miniMessage().deserialize("<dark_gray><obf>blank</obf></dark_gray>"));
            empty.setItemMeta(meta);
            inventory.setItem(x,empty);
        }
        inventory.setItem(11,ItemStack.of(Material.AIR));
        inventory.setItem(13,ItemStack.of(Material.AIR));
        inventory.setItem(15,ItemStack.of(Material.AIR));
        ItemStack stack = ItemStack.of(Material.IRON_BARS);
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<yellow><-- Back</yellow>"));
        stack.setItemMeta(meta);
        inventory.setItem(18,stack);
        ItemStack stack2 = ItemStack.of(Material.IRON_BARS);
        ItemMeta meta2 = stack.getItemMeta();
        meta2.displayName(MiniMessage.miniMessage().deserialize("<yellow>Next --></yellow>"));
        stack2.setItemMeta(meta2);
        inventory.setItem(26,stack2);
        inventory.setItem(29,ItemStack.of(Material.AIR));
        inventory.setItem(31,ItemStack.of(Material.REDSTONE_BLOCK));
        inventory.setItem(33,ItemStack.of(Material.AIR));
    }
} class RitualRecipe {
    public ItemStack Ingredient1;
    public ItemStack Ingredient2;
    public ItemStack Ingredient3;
    public ItemStack Ingredient4;
    public ItemStack Ingredient5;
    public RitualRecipe(ItemStack ingredient1,ItemStack ingredient2, ItemStack ingredient3, ItemStack ingredient4,ItemStack ingredient5) {
        this.Ingredient1 = ingredient1;
        this.Ingredient2 = ingredient2;
        this.Ingredient3 = ingredient3;
        this.Ingredient4 = ingredient4;
        this.Ingredient5 = ingredient5;
    }
}
