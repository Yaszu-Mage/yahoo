package space.yaszu.yahoo.features;

import de.oliver.fancyholograms.api.FancyHologramsPlugin;
import de.oliver.fancyholograms.api.HologramManager;
import de.oliver.fancyholograms.api.data.TextHologramData;
import de.oliver.fancyholograms.api.hologram.Hologram;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.entity.Display;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import space.yaszu.yahoo.util.key;

public class Chat implements Listener {
public key key = new key();
public HologramManager manager = FancyHologramsPlugin.get().getHologramManager();
public void PlayerChatEvent(PlayerChatEvent event) {
    Player player = event.getPlayer();
    TextHologramData hologramData = new TextHologramData(player.getName() + String.valueOf(System.currentTimeMillis()),player.getLocation());
    hologramData.setBackground(Color.WHITE);
    hologramData.setBillboard(Display.Billboard.FIXED);
    Hologram hologram = manager.create(hologramData);
    manager.addHologram(hologram);
    displaygram(event.getMessage(),player,hologram.getName());

}


// meant for typing out the message and playing sounds depending on race
public Runnable displaygram(String message,Player player,String hologramname) {
    return new Runnable() {
        public void run() {

        }
    };
}

}
