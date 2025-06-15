package space.yaszu.yahoo.alchemy.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.util.key;

public class cherry_coke implements Listener {
    public static key keygen = new key();
    public static ItemStack item() {
        ItemStack coke = ItemStack.of(Material.TROPICAL_FISH);
        ItemMeta meta = coke.getItemMeta();
        meta.getPersistentDataContainer().set(keygen.get_key("coke"), PersistentDataType.BOOLEAN,true);
        meta.displayName(MiniMessage.miniMessage().deserialize("<obf>| <reset><color:#ff00f2>Cherry Coke</color> <obf>|"));
        coke.setItemMeta(meta);
        return coke;
    }
    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null) {
            if (item.getPersistentDataContainer().has(keygen.get_key("coke"), PersistentDataType.BOOLEAN)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,1200,1,false,false));
                item.subtract(1);
            }
        }
    }



}

//Public classes need to be in its own file check meth.java
//okay







