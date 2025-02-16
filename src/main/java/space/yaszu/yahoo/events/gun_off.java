package space.yaszu.yahoo.events;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;

public class gun_off implements Runnable {
    private final Yahoo yahoo;
    public gun_off(Yahoo yahoo) {
        this.yahoo = yahoo;
    }
    @Override
    public void run() {
        Player player = Bukkit.getPlayer( "Yaszu");
        NamespacedKey cooldown = new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("Yahoo"), "gun_cooldown");
        if (player != null) {
            player.getPersistentDataContainer().set(cooldown, PersistentDataType.STRING, "off");

        }
    }
}
