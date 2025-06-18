package space.yaszu.yahoo.race;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.util.AttributeHolder;
import space.yaszu.yahoo.util.key; // go to this directory
public interface RaceBase extends Listener {

    //We use this to define race easier
    @NotNull
    public ArrayList<AttributeHolder> attributes();

    @NotNull
    public static Sound sound() {
        return null;
    }

    @NotNull
    public ArrayList<PotionEffect> potionEffects();

}
