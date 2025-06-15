package space.yaszu.yahoo.race.Humanoid;

import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.race.RaceBase;
import space.yaszu.yahoo.race.Race_Selection;
import space.yaszu.yahoo.util.AttributeHolder;

import java.util.ArrayList;

public class Player implements RaceBase{
    @Override
    public @NotNull ArrayList<AttributeHolder> attributes() {
        return new ArrayList<AttributeHolder>();
    }

    @Override
    public Sound sound() {
        return Sound.BLOCK_CRAFTER_CRAFT;
    }

}
