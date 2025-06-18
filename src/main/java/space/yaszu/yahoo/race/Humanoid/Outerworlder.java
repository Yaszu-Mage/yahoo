package space.yaszu.yahoo.race.Humanoid;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.race.RaceBase;
import space.yaszu.yahoo.util.AttributeHolder;

import java.util.ArrayList;

public class Outerworlder implements RaceBase {
    public Listener OtherworldAbilities = new OtherworldAbilities();

    @Override
    public @NotNull ArrayList<AttributeHolder> attributes() {
        ArrayList<AttributeHolder> attributes = new ArrayList<>();
        attributes.add(new AttributeHolder(Attribute.MOVEMENT_SPEED, 0.11));

        return attributes;
    }

    @Override
    public Sound sound() {
        return Sound.BLOCK_AMETHYST_BLOCK_CHIME;
    }

    @Override
    public @NotNull ArrayList<PotionEffect> potionEffects() {
        ArrayList<PotionEffect> attributes = new ArrayList<>();
        attributes.add(new PotionEffect(PotionEffectType.HASTE,PotionEffect.INFINITE_DURATION,1,true,false,false));
        return attributes;
    }

    @NotNull
    public void register(){
        Bukkit.getPluginManager().registerEvents(OtherworldAbilities, Bukkit.getPluginManager().getPlugin("Yahoo"));
    }

} class OtherworldAbilities implements Listener {

}
