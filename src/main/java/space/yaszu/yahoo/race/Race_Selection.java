package space.yaszu.yahoo.race;

import com.sun.tools.javac.Main;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.util.key;

import java.util.ArrayList;
import java.util.List;
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
    public void InventorySelectEvent (InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        // Check if the holder is our MyInventory,
        // if yes, use instanceof pattern matching to store it in a variable immediately.
        if (!(inventory.getHolder(false) instanceof MainInventory inventorye)) {
            // It's not our inventory, ignore it.
            return;
        }

        if (event.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
            inventorye.cyclepoint = inventorye.cyclepoint - 1;
            inventorye.cyclechange();
        }

        if (event.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)) {
            inventorye.cyclepoint = inventorye.cyclepoint + 1;
            inventorye.cyclechange();
        }
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
    public int cyclepoint = 0;

    public void cyclechange() {
        if (cyclepoint >= 9) {
            cyclepoint = 0;
            cyclechange();
        } else if (cyclepoint <= -1) {
            cyclepoint = 9;
            cyclechange();
        }
        switch (cyclepoint) {
            case 0:
                ItemStack stack = ItemStack.of(Material.CRAFTING_TABLE);
                ItemMeta meta = stack.getItemMeta();
                meta.displayName(MiniMessage.miniMessage().deserialize("<aqua><u>Player</u></aqua>"));
                List<Component> lore = new ArrayList<Component>();
                lore.add(MiniMessage.miniMessage().deserialize("Just an average Joe."));
                meta.lore(lore);
                stack.setItemMeta(meta);
                inventory.setItem(13,stack);
                break;
            case 1:
                ItemStack stack1 = ItemStack.of(Material.FIRE_CHARGE);
                ItemMeta meta1 = stack1.getItemMeta();
                meta1.displayName(MiniMessage.miniMessage().deserialize("<red><u>DevilSpawn</u></red>"));
                List<Component> loredevilspawn = new ArrayList<Component>();
                loredevilspawn.add(MiniMessage.miniMessage().deserialize("<u>Raining from the Nether</u>"));
                meta1.lore(loredevilspawn);
                stack1.setItemMeta(meta1);
                inventory.setItem(13,stack1);
                break;
            case 2:
                ItemStack stacker = ItemStack.of(Material.END_CRYSTAL);
                ItemMeta meta2 = stacker.getItemMeta();
                meta2.displayName(MiniMessage.miniMessage().deserialize("<u><light_purple>Otherworlder</light_purple></u>"));
                List<Component> loreotherworld = new ArrayList<Component>();
                loreotherworld.add(MiniMessage.miniMessage().deserialize("<u>Not from here.</u>"));
                meta2.lore(loreotherworld);
                stacker.setItemMeta(meta2);
                inventory.setItem(13,stacker);
                break;
            case 3:
                ItemStack Reptilis = ItemStack.of(Material.TURTLE_SCUTE);
                ItemMeta Reptilis_Meta = Reptilis.getItemMeta();
                Reptilis_Meta.displayName(MiniMessage.miniMessage().deserialize("<green>Reptilis</green>"));
                List<Component> lorereptilis = new ArrayList<Component>();
                lorereptilis.add(MiniMessage.miniMessage().deserialize("<u><color:#002daa>Reigning from the waters.</color></u>"));
                Reptilis_Meta.lore(lorereptilis);
                Reptilis.setItemMeta(Reptilis_Meta);
                inventory.setItem(13,Reptilis);
                break;
            case 4:
                ItemStack Specter = ItemStack.of(Material.SOUL_LANTERN);
                ItemMeta Specter_Meta = Specter.getItemMeta();

        }
    }

    @Override
    public org.bukkit.inventory.@NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 27, "Race Selection");
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
