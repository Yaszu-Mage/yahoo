package space.yaszu.yahoo.movesets.zombie;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.util.key;
public class sunlight {
    public key key = new key();
    @EventHandler
    public void suncheck(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer playercont = event.getPlayer().getPersistentDataContainer();
        if (playercont.has(key.get_key("zombified"))) {
        Block blockAbove = player.getLocation().getBlock().getRelative(0,1,0);
        int sunlightlevel = blockAbove.getLightFromSky();
        if (sunlightlevel >= 10 && !player.isInWater()) {
            //Exposed to sun
            if (player.getInventory().getItemInMainHand() != null) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(key.get_key("umbrella"))) {
                    //pass
                }

            } else if (player.getInventory().getItemInOffHand() != null) {
                if (player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(key.get_key("umbrella"))) {
                    //pass
                }
            } else {
                player.setFireTicks(200);
                Bukkit.getScheduler().runTaskTimer(Yahoo.get_plugin(),new Runnable(){
                    public void run(){
                        Block blockAbove = player.getLocation().getBlock().getRelative(0,1,0);
                        int sunlightlevel = blockAbove.getLightFromSky();
                        if (sunlightlevel >= 10 && (!player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(key.get_key("umbrella")))) {
                            player.setFireTicks(200);
                        } else {
                            this.cancel();
                        }
                    }
                    private void cancel() {
                        Bukkit.getScheduler().cancelTask(this.hashCode());
                    }
                },0,200);
            }

        }
        }
    }




    public ItemStack umbrella(){
        ItemStack baseitem = ItemStack.of(Material.IRON_SWORD,1);
        ItemMeta meta = baseitem.getItemMeta();
        meta.getPersistentDataContainer().set(key.get_key("umbrella"), PersistentDataType.STRING,"umbrella");
        meta.displayName(MiniMessage.miniMessage().deserialize("|<color:#d2042d> Umbrella </color>|"));

        baseitem.setItemMeta(meta);
        return baseitem;
    }

    public void onLivingSetTarget(EntityTargetLivingEntityEvent event) {
        if (event.getTarget() instanceof Player) {
            if (event.getEntity().getType() == EntityType.ZOMBIE || event.getEntity().getType() == EntityType.SKELETON || event.getEntity().getType() == EntityType.WITHER_SKELETON || event.getEntity().getType() == EntityType.HUSK || event.getEntity().getType() == EntityType.DROWNED || event.getEntity().getType() == EntityType.STRAY || event.getEntity().getType() == EntityType.BOGGED || event.getEntity().getType() == EntityType.ZOMBIFIED_PIGLIN || event.getEntity().getType() == EntityType.ZOGLIN || event.getEntity().getType() ==EntityType.WITHER) {
                Player player = (Player) event.getTarget();
                if (player.getPersistentDataContainer().has(key.get_key("zombified"))) {
                    event.setCancelled(true);
                }
            }
        }
    }


}
