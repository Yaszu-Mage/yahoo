package space.yaszu.yahoo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import space.yaszu.yahoo.commands.give_glove;
import space.yaszu.yahoo.commands.time_dialator;
import space.yaszu.yahoo.events.*;
import space.yaszu.yahoo.events.new_runnables.speed;
import space.yaszu.yahoo.events.new_runnables.teleport;
import space.yaszu.yahoo.flamer.snap;
import space.yaszu.yahoo.glitch.buff;
import space.yaszu.yahoo.glitch.glitch_port;
import space.yaszu.yahoo.glitch.glitched;
import space.yaszu.yahoo.items.item_event;
import space.yaszu.yahoo.items.item_register;
import space.yaszu.yahoo.porter.local_teleportation;

import java.util.Random;

import static org.bukkit.Bukkit.getPluginManager;

public final class Yahoo extends JavaPlugin{
    private static Yahoo instance;
    @Override
    public void onEnable() {
        enable_listeners();
        register_items();
        schedule_zane();
        getCommand("give_glove").setExecutor(new give_glove());
        getCommand("pocket_time").setExecutor(new time_dialator());
        getLogger().info("Plugin has been enabled");
    }

    public void schedule_zane() {
        BukkitScheduler schedule = this.getServer().getScheduler();
        Random random = new Random();
        int time = random.nextInt(72000);
        int time2 = random.nextInt(18000);
        int time3 = random.nextInt(1);
        schedule.runTaskLater(getPluginManager().getPlugin("Yahoo"),new buff(this),time + 1);
        Bukkit.getScheduler().runTaskLater(getPluginManager().getPlugin("Yahoo"),new set_who(this),12000);
        Bukkit.getScheduler().runTaskLater(getPluginManager().getPlugin("Yahoo"),new teleport(this),time2);
    }
    public static Yahoo getInstance() {
        return instance;
    }

    public static java.util.logging.Logger getlog() {
        return Bukkit.getPluginManager().getPlugin("Yahoo").getLogger();
    }

    public void enable_listeners() {
        getServer().getPluginManager().registerEvents(new glitched(),this);
        getServer().getPluginManager().registerEvents(new glitch_port(),this);
        getServer().getPluginManager().registerEvents(new black_flash(),this);
        getServer().getPluginManager().registerEvents(new item_event(this), this);
        getServer().getPluginManager().registerEvents(new parry(),this);
        getServer().getPluginManager().registerEvents(new determination(), this);
        getServer().getPluginManager().registerEvents(new pet(), this);
        getServer().getPluginManager().registerEvents(new snap(this), this);
        getServer().getPluginManager().registerEvents(new local_teleportation(), this);
        getServer().getPluginManager().registerEvents(new dialation(),this);
    }
    public void register_items() {
        item_register register = new item_register(this);
        register.register();
        getLogger().info("Registered Items!");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }}

