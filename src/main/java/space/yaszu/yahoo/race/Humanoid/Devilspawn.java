package space.yaszu.yahoo.race.Humanoid;

import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.race.RaceBase;
import space.yaszu.yahoo.util.AttributeHolder;
import space.yaszu.yahoo.util.key;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class Devilspawn implements RaceBase {
    public key key = new key();
    @Override
    public @NotNull ArrayList<AttributeHolder> attributes() {
        return new ArrayList<AttributeHolder>();
    }

    @Override
    public Sound sound() {
        return Sound.PARTICLE_SOUL_ESCAPE;
    }

    // whats the sound name

    // yeah but im js vibine
    /*
        Makes DevilSpawn people immune to fire
     */
    @EventHandler
    public void on_player_dammage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getEntity().getPersistentDataContainer().has(key.get_key("race"))) {
            Player player = (Player) event.getEntity();
            if (player.getPersistentDataContainer().get(key.get_key("race"), PersistentDataType.STRING) == "devilspawn" && event.getCause() == EntityDamageEvent.DamageCause.FIRE) {
                event.setCancelled(true);
            }
        }
     }
}
