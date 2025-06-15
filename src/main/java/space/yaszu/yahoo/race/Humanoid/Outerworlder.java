package space.yaszu.yahoo.race.Humanoid;

import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.race.RaceBase;
import space.yaszu.yahoo.util.AttributeHolder;

import java.util.ArrayList;

public class Outerworlder implements RaceBase {
    @Override
    public @NotNull ArrayList<AttributeHolder> attributes() {
        ArrayList<AttributeHolder> attributes = new ArrayList<>();
        attributes.add(new AttributeHolder(Attribute.GRAVITY, 0.01));
        return attributes;
    }

    @Override
    public Sound sound() {
        return Sound.BLOCK_AMETHYST_BLOCK_CHIME;
    }
}
