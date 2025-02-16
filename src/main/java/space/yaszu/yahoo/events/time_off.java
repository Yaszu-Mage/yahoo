package space.yaszu.yahoo.events;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;

public class time_off implements Runnable {
    private final Yahoo yahoo;
    private final Player player;
    private final NamespacedKey key;
    private final String keystring;
    public time_off(Yahoo yahoo, Player player, NamespacedKey key, String keystring) {
        this.yahoo = yahoo;
        this.player = player;
        this.key = key;
        this.keystring = keystring;
    }
    @Override
    public void run() {
        NamespacedKey cooldown = new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("Yahoo"), "time_cooldown");
        player.getPersistentDataContainer().set(cooldown, PersistentDataType.STRING, "off");
        if (Bukkit.getPlayer(player.getDisplayName()) == null) {
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"),new time_off(Yahoo.getInstance(),player,cooldown,"time_cooldown"),1);
        }
    }
}
