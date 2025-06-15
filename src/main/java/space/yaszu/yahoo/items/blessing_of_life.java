package space.yaszu.yahoo.items;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;

public class blessing_of_life implements Listener {
    static NamespacedKey life_key = new NamespacedKey(Yahoo.get_plugin(),"life");
    static NamespacedKey sin = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"),"sin");
    public static ItemStack item() {
        ItemStack life = ItemStack.of(Material.RECOVERY_COMPASS);
        ItemMeta metalife = life.getItemMeta();
        metalife.displayName(Component.text( "Gift of Life"));
        PersistentDataContainer cont = metalife.getPersistentDataContainer();
        cont.set(life_key, PersistentDataType.BOOLEAN,true);
        life.setItemMeta(metalife);
        return life;
    }

    public static void register_recipe() {
        ShapedRecipe recipe = new ShapedRecipe(life_key,item());
        recipe.shape("TSD","   ","   ");
        recipe.setIngredient('T',Material.TOTEM_OF_UNDYING);
        recipe.setIngredient('S',soul.soul_item());
        recipe.setIngredient('D',Material.DIAMOND);
        Bukkit.addRecipe(recipe);
    }
    @EventHandler
    public void event_listener(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack main_hand = player.getInventory().getItemInMainHand();
        if (main_hand.getType() != Material.AIR) {
            PersistentDataContainer cont = player.getPersistentDataContainer();
            if (cont.has(life_key)) {
                if (cont.get(life_key,PersistentDataType.BOOLEAN) == true) {
                    return;
                } else {
                    if (main_hand.getItemMeta().equals(item().getItemMeta())) {
                        main_hand.subtract();
                        cont.set(life_key,PersistentDataType.BOOLEAN, true);
                        cont.set(sin, PersistentDataType.INTEGER, cont.get(sin, PersistentDataType.INTEGER) + 1);
                        player.sendRawMessage("You will now not die once, if you are struck down. You have sinned.");
                    }
                }
            } else {
                if (main_hand.getItemMeta().equals(item().getItemMeta())) {
                    main_hand.subtract();
                    cont.set(life_key,PersistentDataType.BOOLEAN, true);
                    cont.set(sin, PersistentDataType.INTEGER, cont.get(sin, PersistentDataType.INTEGER) + 1);
                    player.sendRawMessage("You will now not die once, if you are struck down. You have sinned.");
                }
            }
        }
    }

    @EventHandler
    public void prevent_death(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player){
            Player player = (Player) entity;
            if (player.getHealth() - event.getFinalDamage() < 0) {
                PersistentDataContainer cont = player.getPersistentDataContainer();
                if (cont.has(life_key)) {
                    if (cont.get(life_key,PersistentDataType.BOOLEAN)){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600,0));
                        player.setHealth(20);
                        player.getWorld().playSound(player, Sound.ITEM_TOTEM_USE,1f,1f);
                        player.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING,player.getLocation(),128);
                        player.getWorld().spawnParticle(Particle.END_ROD,player.getLocation(),12);
                        player.playSound(player.getLocation(),Sound.ENTITY_GOAT_SCREAMING_DEATH,1f,1f);
                        player.sendMessage(Component.text("You hear a scream as your wounds heal"));
                        cont.set(life_key,PersistentDataType.BOOLEAN,false);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
