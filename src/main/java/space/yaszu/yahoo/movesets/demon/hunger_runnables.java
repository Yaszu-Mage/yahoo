package space.yaszu.yahoo.movesets.demon;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static space.yaszu.yahoo.Yahoo.get_plugin;

public class hunger_runnables implements Runnable {
    @EventHandler
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()){
            String type = "";
            PersistentDataContainer cont = p.getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
            if (cont.has(key)) {
                type = cont.get(key, PersistentDataType.STRING);
            }
            if (type.equals("Demon")) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,60000,255,true,false));
            }
        }
        Bukkit.getScheduler().runTaskLater(get_plugin(),new hunger_runnables(),600);
    }
}
