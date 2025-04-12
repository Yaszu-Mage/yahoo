package space.yaszu.yahoo.alchemy.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.yaszu.yahoo.key;

public class scythe implements Listener {
    public static key key = new key();
    public ItemStack scythe_item() {
        ItemStack item = ItemStack.of(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE,new AttributeModifier(key.get_key("scythe_damage"),1, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, new AttributeModifier(key.get_key("scythe_speed"),-1, AttributeModifier.Operation.ADD_NUMBER));
        meta.displayName(MiniMessage.miniMessage().deserialize("<gray>|</gray><gradient:#ff55ff:#6c00aa> Scythe<reset> <gray>|</gray>"));
        item.setItemMeta(meta);
        return item;
    }


    @EventHandler
    public void event(){

    }
}
