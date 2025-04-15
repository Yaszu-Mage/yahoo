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

        if (new_item.getPersistentDataContainer().get(keygen.get_key("invisibility_cloak"), PersistentDataType.BOOLEAN) == null) {
            if (old_item.getPersistentDataContainer().get(keygen.get_key("invisibility_cloak"),PersistentDataType.BOOLEAN) == null) {
                return;
            } else {
                if (old_item.getPersistentDataContainer().get(keygen.get_key("invisibility_cloak"),PersistentDataType.BOOLEAN)) {

                    for (Player player1: Bukkit.getOnlinePlayers()) {
                        player1.showPlayer(Yahoo.get_plugin(),player);
                    }
                    player.removePotionEffect(PotionEffectType.WEAKNESS);
                    player.removePotionEffect(PotionEffectType.SLOWNESS);
                }
            }
        } else {
            if (new_item.getPersistentDataContainer().get(keygen.get_key("invisibility_cloak"),PersistentDataType.BOOLEAN)) {

                for (Player player2: Bukkit.getOnlinePlayers()) {
                    player2.hidePlayer(Yahoo.get_plugin(),player);

                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,PotionEffect.INFINITE_DURATION,3,false,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,PotionEffect.INFINITE_DURATION,6,false,false));
                Bukkit.getScheduler().runTaskLater(Yahoo.get_plugin(),new Runnable() {
                    public void run() {
                        if (player.getInventory().getChestplate() == new_item) {
                            player.give(new_item);
                            player.getInventory().getChestplate().subtract(1);
                        }
                    }
                },5020);
            }
        }
        }

}
