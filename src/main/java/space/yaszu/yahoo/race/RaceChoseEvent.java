package space.yaszu.yahoo.race;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;


public class RaceChoseEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Player player;
    private String race;
    public RaceChoseEvent(String race, Player player) {
        this.player = player;
        this.race = race;
    }
    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}