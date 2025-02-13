package space.yaszu.yahoo.magic.logic;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class holder implements Listener {
    public String magic(Player player) {
        NBT.modify(player,nbt ->{
            if (!(nbt.hasTag("magic"))){
                return;
            }

        });
        return "balls";
    }
}
