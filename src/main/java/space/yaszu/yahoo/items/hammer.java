package space.yaszu.yahoo.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import space.yaszu.yahoo.util.key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class hammer implements Listener {
    public key key = new key();

    public ItemStack base_hammer() {
        ItemStack base_item = ItemStack.of(Material.DIAMOND_AXE);
        ItemMeta meta = base_item.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("| <color:#00ff04>Hammer</color> |"));
        List<Component> lore = new ArrayList<Component>();
        lore.add(MiniMessage.miniMessage().deserialize("""
                <color:#00ff04>Chapter 4</color>, The
                Trials of the\s
                <dark_green>Holy Hammer</dark_green>."""));
        lore.add(MiniMessage.miniMessage().deserialize("""
                <green>A great smith</green> gives
                the heroes a
                <u><dark_red>terrible weapon.</dark_red></u>"""));
        meta.lore(lore);
        meta.getPersistentDataContainer().set(key.get_key("hammer"), PersistentDataType.BOOLEAN,true);
        meta.setItemModel(NamespacedKey.minecraft("hammer"));
        base_item.setItemMeta(meta);
        return base_item;
    }

    public ItemStack divinity_hammer() {
        ItemStack base_item = base_hammer();
        ItemMeta meta = base_item.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<b><i><aqua>Divinity's War Hammer</aqua></i></b>"));
        List<Component> lore = new ArrayList<Component>();
        lore.add(MiniMessage.miniMessage().deserialize(""));
        meta.lore(lore);
        meta.getPersistentDataContainer().set(key.get_key("divinity_hammer"), PersistentDataType.BOOLEAN,true);
        meta.setItemModel(NamespacedKey.minecraft("divinity_hammer"));
        base_item.setItemMeta(meta);
        return base_item;
    }


    public HashMap<UUID, Long> Gerson_Cooldown = new HashMap<>();
    public HashMap<UUID, Long> Necromancy_Cooldown = new HashMap<>();
    @EventHandler
    public void hammer(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        //Normal Hammer
        if (player.isSneaking() && player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(key.get_key("hammer"))) {
            player.setVelocity(new Vector(0,10,0));
            player.getInventory().getItemInMainHand().setType(Material.MACE);
            player.getPersistentDataContainer().set(key.get_key("can_swap"),PersistentDataType.BOOLEAN,false);
            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Yahoo"), new Runnable() {
                @Override
                public void run() {
                    player.getInventory().getItemInMainHand().setType(Material.DIAMOND_AXE);
                }
            },2000);
        }
        //Divinity Hammer
        if (player.isSneaking() && player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(key.get_key("divinity_hammer"))) {

        }
    }



}
