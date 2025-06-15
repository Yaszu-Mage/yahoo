package space.yaszu.yahoo.movesets.planter;

import com.destroystokyo.paper.ParticleBuilder;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import space.yaszu.yahoo.Yahoo;

public class planter implements Listener {
    @EventHandler
    public static void onPlayerJump(PlayerJumpEvent event) throws InterruptedException {

        Player player = event.getPlayer();
        Location center = player.getLocation();
        PersistentDataContainer data = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
        if (data.has(key)){
        if (player.isSneaking()) {
            Particle leaf = new ParticleBuilder(Particle.CHERRY_LEAVES).color(Color.GREEN).particle();
            NamespacedKey fallkey = new NamespacedKey(Yahoo.get_plugin(),"nofall");
            player.getPersistentDataContainer().set(fallkey, PersistentDataType.BOOLEAN,true);
            Bukkit.getScheduler().runTaskLater(Yahoo.get_plugin(),new Runnable() {
                @Override
                public void run() {
                    player.getPersistentDataContainer().remove(fallkey);
                }
            },1000);
            for (int i = 0; i < 45; i++) {
                for (int x = 0; x < 8; x += 1) {
                    runner(player, x).runTaskTimer(Yahoo.get_plugin(), 0, 2);
                }
                Location playerloc = player.getLocation();
                playerloc.add(new Location(player.getWorld(), 0, 0.5, 0));
                player.teleport(playerloc);
            }
        }
    }}

    public static BukkitRunnable runner(Player player, int f){
        return new BukkitRunnable() {
            @Override
            public void run() {
                Location center = player.getLocation();
                Particle leaf = new ParticleBuilder(Particle.CHERRY_LEAVES).color(Color.GREEN).particle();
                    double angle = Math.toRadians(f * 45);
                    double x = 3 * Math.cos(angle);
                    double z = 3 * Math.sin(angle);
                    Location particleLoc = center.clone().add(x, 1.5, z);
                    player.getWorld().spawnParticle(leaf, particleLoc, 1);

                    }};}

@EventHandler
    public static void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

    }

}


