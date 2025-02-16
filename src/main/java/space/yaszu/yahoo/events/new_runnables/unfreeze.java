package space.yaszu.yahoo.events.new_runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;

public class unfreeze implements Runnable {
    private final Yahoo yahoo;;
    private final Player yaszu;
    public unfreeze(Yahoo yahoo, Player yaszu) {
        this.yahoo = yahoo;
        this.yaszu = yaszu;
    }
    @Override
    public void run() {
        Bukkit.getServerTickManager().setFrozen(false);
        yaszu.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,600,1));
        yaszu.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,600,1));
    }
}
