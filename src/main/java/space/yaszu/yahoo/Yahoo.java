package space.yaszu.yahoo;

import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.LoggerFactory;
import space.yaszu.yahoo.events.black_flash;
import space.yaszu.yahoo.glitch.glitch_port;
import space.yaszu.yahoo.glitch.glitched;
import space.yaszu.yahoo.items.item_event;
import space.yaszu.yahoo.items.item_register;
import java.util.Random;

public final class Yahoo extends JavaPlugin{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Yahoo.class);
    public Random random = new Random();
    private static Yahoo instance;
    @Override
    public void onEnable() {
        enable_listeners();
        register_items();
        getLogger().info("Plugin has been enabled");

    }
    public static Yahoo getInstance() {return instance;}
    public void enable_listeners() {
        getServer().getPluginManager().registerEvents(new glitched(),this);
        getServer().getPluginManager().registerEvents(new glitch_port(),this);
        getServer().getPluginManager().registerEvents(new black_flash(),this);
        getServer().getPluginManager().registerEvents(new item_event(), this);
    }
    public void register_items() {
        item_register register = new item_register();
        register.register();
        getLogger().info("Registered Items!");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }}

