package space.yaszu.yahoo.alchemy.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.key;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class backpack implements Listener {

    public static key keygen = new key();
    public Yahoo yahoo = Yahoo.getInstance();
    public Map<UUID, main_inventory> inventoryMap = new HashMap<>();
    public File dbfile = new File(yahoo.getDataFolder(),"db.yml");
    public YamlConfiguration db = YamlConfiguration.loadConfiguration(dbfile);

    public static ItemStack backpack_item() {
        ItemStack back = ItemStack.of(Material.RECOVERY_COMPASS);
        ItemMeta meta = back.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<obf>| <reset><white>Backpack</white> <obf>|"));
        meta.getPersistentDataContainer().set(keygen.get_key("backpack"), PersistentDataType.BOOLEAN,true);
        return back;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null) {
            if (item.getPersistentDataContainer().has(keygen.get_key("backpack"))) {
                if (db.get(player.getUniqueId().toString()) != null) {

                } else {
                    db.set(player.getUniqueId().toString(),true);
                    main_inventory holder = new main_inventory();
                    db.set("backpack/" + player.getUniqueId(),holder);
                }
            }
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
}
class main_inventory implements InventoryHolder {
    public Inventory inventory;
    public int size = 9;
    @Override
    public @NotNull Inventory getInventory() {
        inventory = Bukkit.createInventory(this, size, "Backpack");
        return inventory;
    }

}