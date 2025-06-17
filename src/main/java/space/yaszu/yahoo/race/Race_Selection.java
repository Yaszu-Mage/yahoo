package space.yaszu.yahoo.race;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.util.key;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static java.lang.Math.*;

public class Race_Selection implements Listener {
    public static key key = new key();
    public static void startselection(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,100,4));
        Bukkit.getAsyncScheduler().runAtFixedRate(Bukkit.getPluginManager().getPlugin("Yahoo"),spawn_circle(player,Particle.REVERSE_PORTAL),0,5000, TimeUnit.MILLISECONDS);
        Bukkit.getAsyncScheduler().runAtFixedRate(Bukkit.getPluginManager().getPlugin("Yahoo"),spawn_circle(player,Particle.LAVA),0,5500, TimeUnit.MILLISECONDS);
        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"),new Runnable() {
            public void run() {
                player.getWorld().setBlockData((int) player.getLocation().getX(),(int) player.getLocation().getY() - 2, (int) player.getLocation().getZ() - 1,Material.BARRIER.createBlockData());
            }
        },100);


        

    }
    public static @NotNull Consumer<ScheduledTask> spawn_circle(Player player,Particle particle) {
        return new Consumer<ScheduledTask>() {
            @Override
            public void accept(ScheduledTask scheduledTask) {
                run();
            }
            public void run() {
                for (double iterationx = 0; iterationx < 2 * PI; iterationx=iterationx + 0.1) {
                    for (double iterationz = 0; iterationz < 2 * PI; iterationz=iterationz + 0.1) {
                        if (player.getPotionEffect(PotionEffectType.LEVITATION) != null) {
                            return;
                        } else {
                            Location loc = player.getLocation();
                            Location loc2 = player.getLocation();
                            Location loc3 = player.getLocation();
                            loc.add(0, 0.9, 0);
                            loc2.add(0, 0.9, 0);
                            loc3.add(0, 0.9, 0);
                            loc.add(2 * cos(iterationx) * (2 * cos(iterationz)), (2 * sin(iterationx)) * (2 * cos(iterationz)), (4 * sin(iterationz)));
                            loc2.add(2 * cos(0) * 2 * cos(iterationz), 2 * sin(0) * 2 * cos(iterationz), (4 * sin(iterationz)));
                            loc3.add(2 * cos(iterationx) * 2 * cos(0), 2 * sin(iterationx) * 2 * cos(0), (4 * sin(0)));
                            player.getWorld().spawnParticle(particle, loc, 1);
                            player.getWorld().spawnParticle(particle, loc2, 1);
                            player.getWorld().spawnParticle(particle, loc3, 1);
                        }
                    }
                }
            }
        };
    }

} class MainInventory implements InventoryHolder {
    Inventory inventory;
    @Override
    public org.bukkit.inventory.@NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 9, "Race Selection");
        setInventory(inventory);
        return inventory;
    }

    public void setInventory(Inventory inventory) {

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, ItemStack.of(Material.BLACK_STAINED_GLASS));
        }

        this.inventory = inventory;
    }

    @EventHandler
    public static void PlayerMove(PlayerMoveEvent event) {
        key key = new key();
        if (event.getPlayer().hasPotionEffect(PotionEffectType.LEVITATION)) {
            return;
        }
        if (!event.getPlayer().getPersistentDataContainer().has(key.get_key("has_race")) && !event.getPlayer().hasPotionEffect(PotionEffectType.LEVITATION)) {
            event.setCancelled(true);
        }
    }
}
