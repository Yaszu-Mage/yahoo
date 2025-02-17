package space.yaszu.yahoo.alchemy;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;

public class alchemic_bag implements Listener {
    private final Yahoo yahoo;
    public alchemic_bag(Yahoo yahoo) {
        this.yahoo = yahoo;
    }
    @EventHandler
    public void right_click(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInOffHand();
        String id;
        if (item.getAmount() != 0) {


            ItemMeta meta = item.getItemMeta();
            NamespacedKey key2 = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"Yah_ID");
            id = meta.getPersistentDataContainer().get(key2, PersistentDataType.STRING);
            if (id != null) {
                space.yaszu.yahoo.Yahoo.getPlugin(Yahoo.class).getLogger().info(id + " was used");
                if (id.equals("alchemic_bag")){
                    ItemStack main_hand = player.getInventory().getItemInMainHand();
                    PersistentDataContainer playercont = player.getPersistentDataContainer();
                    NamespacedKey alchemic_cooldown = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"alchemic_cooldown");
                    if (playercont.get(alchemic_cooldown,PersistentDataType.BOOLEAN) == null) {
                        playercont.set(alchemic_cooldown,PersistentDataType.BOOLEAN,false);
                    }
                    if (main_hand.getType().equals(Material.LEATHER) && main_hand.getAmount() >= 5 && playercont.get(alchemic_cooldown,PersistentDataType.BOOLEAN) == false) {
                        playercont.set(alchemic_cooldown, PersistentDataType.BOOLEAN, true);
                        player.sendRawMessage("Petah... The Horse is here");
                        main_hand.subtract(5);
                        Horse h = (Horse) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.HORSE);
                        NamespacedKey dontdrop = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"DontDrop");
                        h.getPersistentDataContainer().set(dontdrop,PersistentDataType.BOOLEAN,true);
                        h.setTamed(true);
                        h.setOwner(player);
                        h.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
                        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"), /* Lambda: */ () -> {
                            h.getWorld().spawnParticle(Particle.POOF,h.getEyeLocation(),128);
                            h.remove();
                            playercont.set(alchemic_cooldown, PersistentDataType.BOOLEAN, false);
                        }, /* End of the lambda */ 6000);
                    }
                }
             }
        }
    }
}
//i wish to kill myself because of this texture pack//
// I wish to kill myself because of this plugin