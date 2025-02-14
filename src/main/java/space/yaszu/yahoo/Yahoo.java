package space.yaszu.yahoo;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import space.yaszu.yahoo.events.black_flash;
import space.yaszu.yahoo.events.determination;
import space.yaszu.yahoo.events.parry;
import space.yaszu.yahoo.events.pet;
import space.yaszu.yahoo.flamer.snap;
import space.yaszu.yahoo.glitch.buff;
import space.yaszu.yahoo.glitch.glitch_port;
import space.yaszu.yahoo.glitch.glitched;
import space.yaszu.yahoo.items.item_event;
import space.yaszu.yahoo.items.item_register;

import java.util.Objects;
import java.util.Random;

import static org.bukkit.Bukkit.getPluginManager;

public final class Yahoo extends JavaPlugin{
    @Override
    public void onEnable() {
        enable_listeners();
        register_items();
        schedule_zane();
        getLogger().info("Plugin has been enabled");

    }

    public void schedule_zane() {
        BukkitScheduler schedule = this.getServer().getScheduler();
        Random random = new Random();
        int time = random.nextInt(72000);
        schedule.runTaskLater(getPluginManager().getPlugin("Yahoo"),new buff(this),time + 1);
    }


    public void enable_listeners() {
        getServer().getPluginManager().registerEvents(new glitched(),this);
        getServer().getPluginManager().registerEvents(new glitch_port(),this);
        getServer().getPluginManager().registerEvents(new black_flash(),this);
        getServer().getPluginManager().registerEvents(new item_event(), this);
        getServer().getPluginManager().registerEvents(new parry(),this);
        getServer().getPluginManager().registerEvents(new determination(), this);
        getServer().getPluginManager().registerEvents(new pet(), this);
        getServer().getPluginManager().registerEvents(new snap(), this);
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

