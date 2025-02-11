package space.yaszu.yahoo;

import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.LoggerFactory;
import space.yaszu.yahoo.glitch.glitch_port;
import space.yaszu.yahoo.glitch.glitched;
import space.yaszu.yahoo.items.item_registry;

import java.util.Random;
import java.util.logging.Logger;

public final class Yahoo extends JavaPlugin{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Yahoo.class);
    public Random random = new Random();
    public Logger logger = JavaPlugin.getPlugin(Yahoo.class).getLogger();
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new glitched(),this);
        getServer().getPluginManager().registerEvents(new glitch_port(),this);
        getServer().getPluginManager().registerEvents(new item_registry(),this);
        logger.info("Plugin has been enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    }

