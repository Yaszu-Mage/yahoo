package space.yaszu.yahoo.events.new_runnables;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import space.yaszu.yahoo.util.key;

public class night implements Runnable {
    public void run() {
        Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_WEATHER_CYCLE, true);
        World world = Bukkit.getWorld("world");
        world.setStorm(true);
        world.setThundering(true);

    }
} class night_damage implements Listener {
    public static key keygen = new key();


    @EventHandler
    public void death(PlayerDeathEvent event) {
        if (Bukkit.getWorld("world").getPersistentDataContainer().has(keygen.get_key("eternal_night"))) {
         event.getPlayer().setGameMode(GameMode.SPECTATOR);
        }
    }
}
