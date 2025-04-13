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
import space.yaszu.yahoo.key;

import java.util.List;

public class glaive implements Listener {
    public key keygen = new key();
    public ItemStack glaive_item(){
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.getAttributeModifiers().get(Attribute.ATTACK_SPEED).add(new AttributeModifier(keygen.get_key("glaive"),1, AttributeModifier.Operation.ADD_NUMBER));
        meta.displayName(MiniMessage.miniMessage().deserialize("<obf>| <reset><dark_green>Glaive</dark_green> <obf>|"));
        meta.lore((List<? extends Component>) MiniMessage.miniMessage().deserialize("<obf>| <reset><dark_green>A Recipe decended from forest guardians</dark_green> <obf>|"));
        item.setItemMeta(meta);
        return item;
    }
    @EventHandler
    public void onItemInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getPersistentDataContainer().has(keygen.get_key("glaive")) && player.isSneaking()){

        }
    }
}
