package space.yaszu.yahoo.alchemy.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.util.key;

import java.util.Collection;
import java.util.Collections;

public class glaive implements Listener {
    public static key keygen = new key();
    public static ItemStack glaive_item(){
        ItemStack item = new ItemStack(Material.TRIDENT);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<obf>| <reset><dark_green>Glaive</dark_green> <obf>|"));
        meta.setLore(Collections.singletonList("A Recipe decended from forest guardians"));
        meta.getPersistentDataContainer().set(keygen.get_key("glaive"), PersistentDataType.BOOLEAN,true);
        item.setItemMeta(meta);
        return item;
    }
    @EventHandler
    public void onItemThrow(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (player.isSneaking() && item != null){
            if (item .getPersistentDataContainer().has(keygen.get_key("glaive")) && player.isSneaking()){
                //Block here
                Collection players = Bukkit.getOnlinePlayers();
                for (Object player_instance : players) {
                    if (player_instance instanceof Player){
                        if (((Player) player_instance).getLocation().distance(player.getLocation()) <= 5 && player_instance != player){
                            Location newloc = ((Player) player_instance).getLocation();
                            newloc.setY(newloc.getY() + 2);
                            ((Player) player_instance).teleport(newloc);
                            ((Player) player_instance).setVelocity(((Player) player_instance).getVelocity().setX(0).setZ(0).setY(1028));
                            ((Player) player_instance).damage(4, player);
                        }
                    }
                }
                player.setInvisible(true);

                Bukkit.getScheduler().runTaskLater(Yahoo.get_plugin(),new Runnable() {
                    public void run() {
                        player.setInvisible(false);
                    }
                },100);
                event.setCancelled(true);
            }
        }

    }
}
