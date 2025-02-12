package space.yaszu.yahoo.items;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;

public class item_event implements Listener {
    public void right_click(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        String id;
        Boolean nbt_val = NBT.get(item, nbt -> {
            return nbt.hasTag("Yah_ID");
        });
        if (nbt_val) {
            id = NBT.get(item,nbt ->{
              return nbt.getString("Yah_ID");
            });
            if (id.equals("coke")){
                item.subtract(1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,500,1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA,500,1));
            }
        }

    }
}
