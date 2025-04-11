package space.yaszu.yahoo.items;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.key;

public class boots_of_swiftness implements Listener {
    public static key keygen = new key();

    @EventHandler
    public void on_armor_equip(PlayerArmorChangeEvent event){
        ItemStack new_item = event.getNewItem();
        ItemStack old_item = event.getOldItem();
        Player player = event.getPlayer();
        if (new_item.getItemMeta() == boots().getItemMeta()) {
            player.setWalkSpeed(0.4f);
        }
        if (old_item.getItemMeta() == boots().getItemMeta()) {
            player.setWalkSpeed(0.2f);
        }
    }

    public static ItemStack boots(){
        ItemStack item = ItemStack.of(Material.LEATHER_BOOTS);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.AQUA);
        meta.getPersistentDataContainer().set(keygen.get_key("boots_of_swiftness"), PersistentDataType.BOOLEAN,true);
        meta.setDisplayName("Boots of Swiftness");
        item.setItemMeta(meta);
        return item;
    }

}
