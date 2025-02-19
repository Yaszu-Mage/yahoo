package space.yaszu.yahoo.events.new_runnables;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;

import java.util.Collection;
import java.util.Random;

import static java.lang.Math.floor;
import static java.lang.Math.round;

public class insanity implements Runnable{
    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            // Do insanity here
            PersistentDataContainer cont = p.getPersistentDataContainer();
            NamespacedKey sin = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"sin");
            if (cont.has(sin,PersistentDataType.INTEGER)) {
                int sins = cont.get(sin, PersistentDataType.INTEGER);
                p.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,600, (int) floor(sins / 2)));
                p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,600, (int) floor(sins / 2)));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,600, (int) floor(sins / 2)));
                // Love it when my phone thing freezes
            }
        }
        Random random = new Random();
        int next = random.nextInt(18000);
        Bukkit.getScheduler().runTaskLater(Yahoo.get_plugin(),this,next);
    }
}
