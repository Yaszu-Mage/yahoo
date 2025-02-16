package space.yaszu.yahoo.player_info;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class player_info_register {
    public static void register(Player player, String tag) {
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
        String has_type = player.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        player.getPersistentDataContainer().set(key, PersistentDataType.STRING, tag);
    }

}
