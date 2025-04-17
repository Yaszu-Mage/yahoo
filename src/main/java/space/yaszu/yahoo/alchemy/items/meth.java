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
import space.yaszu.yahoo.key;

public class meth implements Listener {
    //this needs to be in its own file
    public static key keygen = new key();
    public static ItemStack item() {
        ItemStack meth = ItemStack.of(Material.PUFFERFISH);
        ItemMeta meta = meth.getItemMeta();
        meta.getPersistentDataContainer().set(keygen.get_key("meth"), PersistentDataType.BOOLEAN,true);
        meta.displayName(MiniMessage.miniMessage().deserialize("<obf>| <reset><color:#11C7C7>meth</color> <obf>|"));
        meth.setItemMeta(meta);
        return meth;
    }
    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null) {
            if (item.getPersistentDataContainer().has(keygen.get_key("meth"), PersistentDataType.BOOLEAN)) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,600,4,false,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 2400, 2, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 100, 7, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 10, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 2400, 1, false, false));
                item.subtract(1);
            }
        }
    }
}