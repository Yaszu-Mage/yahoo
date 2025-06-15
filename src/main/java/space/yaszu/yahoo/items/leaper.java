package space.yaszu.yahoo.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import space.yaszu.yahoo.util.key;
//Otherwise great job!
// Check item_register, and try and register your items :D
// thank you (:
public class leaper implements Listener {
    public static key keygen = new key();
    public static ItemStack item() {
        ItemStack leaper = ItemStack.of(Material.AMETHYST_SHARD);
        ItemMeta meta = leaper.getItemMeta();
        meta.getPersistentDataContainer().set(keygen.get_key("leaper"), PersistentDataType.BOOLEAN,true);
        meta.displayName(MiniMessage.miniMessage().deserialize("<obf>| <reset><color:#11C7C7>leaper</color> <obf>|"));
        leaper.setItemMeta(meta);
        return leaper;
    }
    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null) {
            if (item.getPersistentDataContainer().has(keygen.get_key("leaper"), PersistentDataType.BOOLEAN)) {
                Location startLocation = player.getLocation();
                Vector direction = startLocation.getDirection().normalize().multiply(5);
                player.setVelocity(direction);
                item.subtract(1);
            }
        }
    }
}
