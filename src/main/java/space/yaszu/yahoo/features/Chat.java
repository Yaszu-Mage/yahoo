package space.yaszu.yahoo.features;

import de.oliver.fancyholograms.api.FancyHologramsPlugin;
import de.oliver.fancyholograms.api.HologramManager;
import de.oliver.fancyholograms.api.data.HologramData;
import de.oliver.fancyholograms.api.data.TextHologramData;
import de.oliver.fancyholograms.api.hologram.Hologram;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.entity.Display;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.persistence.PersistentDataType;
import org.w3c.dom.Text;
import space.yaszu.yahoo.util.key;

import java.util.ArrayList;
import java.util.List;

public class Chat implements Listener {
public key key = new key();
public HologramManager manager = FancyHologramsPlugin.get().getHologramManager();
@EventHandler
public void PlayerChatEvent(PlayerChatEvent event) {
    Player player = event.getPlayer();
    displaygram(event.getMessage(),player);

}


// meant for typing out the message and playing sounds depending on race
public Runnable displaygram(String message,Player player) {
    return new Runnable() {
        public void run() {
            String race = player.getPersistentDataContainer().get(key.get_key("race"), PersistentDataType.STRING);
            Sound sound;
            switch (race) {
                case "Player":
                    sound = space.yaszu.yahoo.race.Humanoid.Player.sound();
            }
            TextHologramData hologramData = new TextHologramData(player.getName() + String.valueOf(System.currentTimeMillis()),player.getLocation());
            hologramData.setBackground(Color.WHITE);
            hologramData.setBillboard(Display.Billboard.FIXED);
            List<String> msg= new ArrayList<String>();
            Hologram hologram = manager.create(hologramData);
            manager.addHologram(hologram);
            for (Character line : message.toCharArray()) {
                msg.add(line.toString());
                player.getWorld().playSound(player.getLocation(),);
            }
        }
    };
}

}
