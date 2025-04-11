package space.yaszu.yahoo.events;

import io.papermc.paper.event.player.PlayerInsertLecternBookEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Ritual implements Listener {
    public static Map<UUID,Ritualmenu> Ritualmap = new HashMap<>();
    public ItemStack science_book() {
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

    @EventHandler
    public void onPlayerLecturnClick(PlayerInsertLecternBookEvent event) {
        if (event.getBook().equals(science_book())) {
            if (Ritualmap.get(event.getPlayer().getUniqueId()) == null) {
                Ritualmenu menu = new Ritualmenu();
                Ritualmap.put(event.getPlayer().getUniqueId(),menu);
            }
            Ritualmap.get(event.getPlayer().getUniqueId()).set_items();
            event.getPlayer().openInventory(Ritualmap.get(event.getPlayer().getUniqueId()).getInventory());
        }
    }
}class Ritualmenu implements InventoryHolder {
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
}
