package space.yaszu.yahoo.events;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class pet implements Listener {
    @EventHandler
    public void pot(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        Entity pat = event.getRightClicked();
        player.spawnParticle(Particle.HEART,pat.getLocation(),64);
        player.sendRawMessage("You pet " + pat.getName());
    }
}
