package space.yaszu.yahoo.alchemy.items;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;

import java.util.Collection;

import static space.yaszu.yahoo.items.boots_of_swiftness.keygen;

public class invisibility_cloak implements Listener {
    public static ItemStack cloak() {
        ItemStack item = ItemStack.of(Material.LEATHER_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<color:#0062ff>Invisibility Cloak</color>"));
        meta.getPersistentDataContainer().set(keygen.get_key("invisibility_cloak"),PersistentDataType.BOOLEAN,true);
        item.setItemMeta(meta);
        return item;
    }
    @EventHandler
    public void on_armor_put(PlayerArmorChangeEvent event) {
        ItemStack new_item = event.getNewItem();
        ItemStack old_item = event.getOldItem();
        Player player = event.getPlayer();
        set_armor_attribute(new_item,old_item,
                "invisibility_cloak",
                new AttributeModifier(keygen.get_key("invis_cloak"),1, AttributeModifier.Operation.ADD_NUMBER),
                Attribute.TEMPT_RANGE,
                player,
                new PotionEffect(PotionEffectType.INVISIBILITY,PotionEffect.INFINITE_DURATION,20));
    }

    public void set_armor_attribute(ItemStack new_item, ItemStack old_item, String key, AttributeModifier modifier, Attribute attribute,Player player, PotionEffect potionEffect){
        if (new_item.getPersistentDataContainer().get(keygen.get_key(key), PersistentDataType.BOOLEAN) == null) {
            if (old_item.getPersistentDataContainer().get(keygen.get_key(key),PersistentDataType.BOOLEAN) == null) {
                return;
            } else {
                if (old_item.getPersistentDataContainer().get(keygen.get_key(key),PersistentDataType.BOOLEAN)) {
                    //Take off
                    if (potionEffect != null) {
                        player.removePotionEffect(potionEffect.getType());
                    }
                    if (attribute != null) {
                        player.getAttribute(attribute).removeModifier(keygen.get_key(key));
                    }
                }
            }
        } else {
            if (new_item.getPersistentDataContainer().get(keygen.get_key(key),PersistentDataType.BOOLEAN)) {
                if (potionEffect != null) {
                    player.addPotionEffect(potionEffect);
                }
                if (attribute != null) {
                    player.getAttribute(attribute).addModifier(modifier);
                }
                //Put on
            }
        }
    }
}
