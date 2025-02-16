package space.yaszu.yahoo.events.new_runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;

public class speed implements Runnable {
    private final Yahoo yahoo;;
    public speed(Yahoo yahoo) {
        this.yahoo = yahoo;
    }

    @Override
    public void run() {
        if (Bukkit.getPlayer("Yaszu") != null) {
         Player player = Bukkit.getPlayer("Yaszu");
         player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE,600,3));
         player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,600,3));
         Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"),new speed(this.yahoo),600);
        } else {
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"),new speed(this.yahoo),600);
        }
    }
}
