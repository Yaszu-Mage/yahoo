package space.yaszu.yahoo.alchemy.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.key;

import java.util.Collections;
import java.util.List;

public class glaive implements Listener {
    public static key keygen = new key();
    public static ItemStack glaive_item(){
        ItemStack item = new ItemStack(Material.TRIDENT);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers().get(Attribute.ATTACK_SPEED).add(new AttributeModifier(keygen.get_key("glaive"),1, AttributeModifier.Operation.ADD_NUMBER));
        meta.displayName(MiniMessage.miniMessage().deserialize("<obf>| <reset><dark_green>Glaive</dark_green> <obf>|"));
        meta.setLore(Collections.singletonList("A Recipe decended from forest guardians"));
        meta.getPersistentDataContainer().set(keygen.get_key("glaive"), PersistentDataType.BOOLEAN,true);
        item.setItemMeta(meta);
        return item;
    }
    @EventHandler
    public void onItemInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getPersistentDataContainer().has(keygen.get_key("glaive")) && player.isSneaking()){

        }
    }
    @EventHandler
    public void onItemThrow(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (player.isSneaking() && item != null){
            if (item .getPersistentDataContainer().has(keygen.get_key("glaive")) && player.isSneaking()){
                //Block here
                event.setCancelled(true);
            }
        }

    }
}
