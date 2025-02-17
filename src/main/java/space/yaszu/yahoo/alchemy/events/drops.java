package space.yaszu.yahoo.alchemy.events;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataType;

public class drops implements Listener {
    @EventHandler
    public void dontdrop(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        NamespacedKey dontdrop = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"DontDrop");
        if (entity.getPersistentDataContainer().has(dontdrop, PersistentDataType.BOOLEAN)) {
            event.getDrops().clear();
            event.setDroppedExp(0);
        } else {
            //pass
        }
    }
}
