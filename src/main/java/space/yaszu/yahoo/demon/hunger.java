package space.yaszu.yahoo.demon;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class hunger implements Listener {
    public void hungy(PlayerRespawnEvent event){
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
    }
}
