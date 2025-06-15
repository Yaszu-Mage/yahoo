package space.yaszu.yahoo.items;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.util.key;

public class boots_of_swiftness implements Listener {
    public static key keygen = new key();

    @EventHandler
    public void on_armor_equip(PlayerArmorChangeEvent event){
        ItemStack new_item = event.getNewItem();
        ItemStack old_item = event.getOldItem();
        Player player = event.getPlayer();

        if (new_item.getPersistentDataContainer().get(keygen.get_key("boots_of_swiftness"),PersistentDataType.BOOLEAN) == null) {
            if (old_item.getPersistentDataContainer().get(keygen.get_key("boots_of_swiftness"),PersistentDataType.BOOLEAN) == null) {
                return;
            } else {
                if (old_item.getPersistentDataContainer().get(keygen.get_key("boots_of_swiftness"),PersistentDataType.BOOLEAN)) {

                    Yahoo.getlog().info("walkspeed");
                    player.getAttribute(Attribute.MOVEMENT_SPEED).removeModifier(keygen.get_key("boots_of_swiftness"));
                }
            }
        } else {
            if (new_item.getPersistentDataContainer().get(keygen.get_key("boots_of_swiftness"),PersistentDataType.BOOLEAN)) {

                Yahoo.getlog().info("walkspeed");
                player.getAttribute(Attribute.MOVEMENT_SPEED).addModifier(new AttributeModifier(keygen.get_key("boots_of_swiftness"),0.14,AttributeModifier.Operation.ADD_NUMBER));
            }
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
