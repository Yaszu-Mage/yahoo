package space.yaszu.yahoo.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.util.key;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class backpack implements Listener {

    public static key keygen = new key();
    public Map<UUID, main_inventory> inventoryMap = new HashMap<>();
    public File dbfile = new File(Bukkit.getPluginManager().getPlugin("Yahoo").getDataFolder(),"db.yml");
    public YamlConfiguration db = YamlConfiguration.loadConfiguration(dbfile);

    public static ItemStack backpack_item() {
        ItemStack back = ItemStack.of(Material.RECOVERY_COMPASS);
        ItemMeta meta = back.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<obf>| <reset><white>Backpack</white> <obf>|"));
        meta.getPersistentDataContainer().set(keygen.get_key("backpack"), PersistentDataType.BOOLEAN,true);
        back.setItemMeta(meta);
        return back;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) throws IOException, ClassNotFoundException {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null) {
            if (item.getPersistentDataContainer().has(keygen.get_key("backpack"))) {

            }
        }
    }

    @EventHandler
    public void onplayerleave(PlayerQuitEvent event) {

    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder(false) instanceof main_inventory main_inventory) {

        }
    }
    @EventHandler
    public void onItemChange(InventoryClickEvent event) {
        Player player;
        if (event.getWhoClicked() instanceof Player) { player = (Player) event.getWhoClicked(); }
        Inventory inventory = event.getInventory();
        // Check if the holder is our MyInventory,
        // if yes, use instanceof pattern matching to store it in a variable immediately.
        if (!(inventory.getHolder(false) instanceof main_inventory main_inventory)) {
            // It's not our inventory, ignore it.
            return;
        }

    }
    public static main_inventory inventory(){
        return new main_inventory();
    }


}
class main_inventory implements InventoryHolder {
    public static Inventory inventory;
    public static int size = 9;
    @Override
    public @NotNull Inventory getInventory() {
        inventory = Bukkit.createInventory(this, size, "Backpack");
        return inventory;
    }

    public String serialize(){
        return Base64.getEncoder().encodeToString(inventory.getContents().toString().getBytes());
    }



    public static void add_item(ItemStack item, int spot){
        inventory.setItem(spot,item);
    }
}