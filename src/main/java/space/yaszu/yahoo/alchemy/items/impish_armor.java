package space.yaszu.yahoo.alchemy.items;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static space.yaszu.yahoo.items.boots_of_swiftness.keygen;

public class impish_armor implements Listener {
    //TODO
    /*
        - Make Item
        - Style Item
        - Make Func
     */
    public static key keygen = new key();
    public static ItemStack impish_helmet(){
        ItemStack helmet = ItemStack.of(Material.DIAMOND_HELMET);
        ItemMeta meta = helmet.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<dark_purple><obf>|</dark_purple> <dark_red>Impish Helmet</dark_red> <dark_purple><obf>|</dark_purple>"));
        meta.setLore(Arrays.asList(String.valueOf(MiniMessage.miniMessage().deserialize("<dark_purple><obf>|</dark_purple> <dark_red>An piece of armor whos design was created by the imps</dark_red> <dark_purple><obf>|</dark_purple>"))));
        meta.getPersistentDataContainer().set(keygen.get_key("impish_helmet"), PersistentDataType.BOOLEAN,true);
        meta.getPersistentDataContainer().set(keygen.get_key("impish"),PersistentDataType.BOOLEAN,true);
        helmet.setItemMeta(meta);
        return helmet;
    }
    public static ItemStack impish_chestplate(){
        ItemStack chestplate = ItemStack.of(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = chestplate.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<dark_purple><obf>|</dark_purple> <dark_red>Impish Chestplate</dark_red> <dark_purple><obf>|</dark_purple>"));
        meta.setLore(Arrays.asList(String.valueOf(MiniMessage.miniMessage().deserialize("<dark_purple><obf>|</dark_purple> <dark_red>An piece of armor whos design was created by the imps</dark_red> <dark_purple><obf>|</dark_purple>"))));
        meta.getPersistentDataContainer().set(keygen.get_key("impish_chestplate"), PersistentDataType.BOOLEAN,true);
        meta.getPersistentDataContainer().set(keygen.get_key("impish"),PersistentDataType.BOOLEAN,true);
        chestplate.setItemMeta(meta);
        return chestplate;
    }
    public static ItemStack impish_leggings() {
        ItemStack leggings = ItemStack.of(Material.DIAMOND_LEGGINGS);
        ItemMeta meta = leggings.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<dark_purple><obf>|</dark_purple> <dark_red>Impish Leggings</dark_red> <dark_purple><obf>|</dark_purple>"));
        meta.setLore(Arrays.asList(String.valueOf(MiniMessage.miniMessage().deserialize("<dark_purple><obf>|</dark_purple> <dark_red>An piece of armor whos design was created by the imps</dark_red> <dark_purple><obf>|</dark_purple>"))));
        meta.getPersistentDataContainer().set(keygen.get_key("impish_leggings"), PersistentDataType.BOOLEAN,true);
        meta.getPersistentDataContainer().set(keygen.get_key("impish"),PersistentDataType.BOOLEAN,true);
        leggings.setItemMeta(meta);
        return leggings;
    }
    public static ItemStack impish_boots(){
        ItemStack boots = ItemStack.of(Material.DIAMOND_BOOTS);
        ItemMeta meta = boots.getItemMeta();
        meta.displayName(MiniMessage.miniMessage().deserialize("<dark_purple><obf>|</dark_purple> <dark_red>Lil Booties</dark_red> <dark_purple><obf>|</dark_purple>"));
        meta.setLore(Arrays.asList(String.valueOf(MiniMessage.miniMessage().deserialize("<dark_purple><obf>|</dark_purple> <dark_red>An piece of armor whos design was created by the imps</dark_red> <dark_purple><obf>|</dark_purple>"))));
        meta.getPersistentDataContainer().set(keygen.get_key("impish_boots"), PersistentDataType.BOOLEAN,true);
        meta.getPersistentDataContainer().set(keygen.get_key("impish"),PersistentDataType.BOOLEAN,true);
        boots.setItemMeta(meta);
        return boots;
    }

    @EventHandler
    public void on_armor_put(PlayerArmorChangeEvent event){
        Player player = event.getPlayer();
        ItemStack old_item = event.getOldItem();
        if (get_set_matches(player,"impish")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,PotionEffect.INFINITE_DURATION,1,false,false));
        } else if (player.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE) && old_item.getPersistentDataContainer().has(keygen.get_key("impish"))){
         player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        }
    }

    public boolean get_set_matches(Player player,String key){
        boolean output = true;
        int armor_match = 0;
        for (ItemStack armor : player.getInventory().getArmorContents()){
            if (armor.getPersistentDataContainer().has(keygen.get_key(key))) {
                armor_match = armor_match + 1;
            }
        }
        if (armor_match == 4){
            return true;
        }else {
            return false;
        }

    }

    
    }

