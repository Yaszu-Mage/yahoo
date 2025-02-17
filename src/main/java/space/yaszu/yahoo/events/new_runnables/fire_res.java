package space.yaszu.yahoo.events.new_runnables;

import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class fire_res implements Runnable {

    @Override
    public void run() {
        if (Bukkit.getPlayerExact("GhostboyGamer") != null) {
            Bukkit.getPlayer("GhostboyGamer").addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,PotionEffect.INFINITE_DURATION,1));
        }
    }
}
