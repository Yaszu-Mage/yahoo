package space.yaszu.yahoo;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.alchemy.alchemic_bag;
import space.yaszu.yahoo.alchemy.events.drops;
import space.yaszu.yahoo.alchemy.items.*;
import space.yaszu.yahoo.alchemy.run_register;
import space.yaszu.yahoo.commands.*;
import space.yaszu.yahoo.movesets.demon.hunger;
import space.yaszu.yahoo.movesets.demon.hunger_runnables;
import space.yaszu.yahoo.movesets.demon.swor;
import space.yaszu.yahoo.events.*;
import space.yaszu.yahoo.events.new_runnables.insanity;
import space.yaszu.yahoo.events.new_runnables.teleport;
import space.yaszu.yahoo.events.new_runnables.set_who;
import space.yaszu.yahoo.movesets.flamer.jump;
import space.yaszu.yahoo.movesets.flamer.snapv2;
import space.yaszu.yahoo.movesets.glitch.*;
import space.yaszu.yahoo.items.boots_of_swiftness;
import space.yaszu.yahoo.items.item_event;
import space.yaszu.yahoo.items.item_register;

import java.util.List;
import java.util.Random;

import static org.bukkit.Bukkit.getPluginManager;

public final class Yahoo extends JavaPlugin{

    private static Yahoo instance;
    World world;
    @Override
    public void onEnable() {
        enable_listeners();
        register_items();
        schedule_zane();
        getCommand("give_glove").setExecutor(new give_glove());
        getCommand("pocket_time").setExecutor(new time_dialator());
        getCommand("reset_cooldowns").setExecutor(new reset_cooldowns());
        getCommand("give_grenade").setExecutor(new grenade());
        getCommand("set_who").setExecutor(new space.yaszu.yahoo.commands.set_who());
        getCommand("give_sword").setExecutor(new give_sword());
        createWorld();
        check_glitch();
        saveResource("db.yml", /* replace */ false);
        saveDefaultConfig();
        getResource("db.yml");
        getLogger().info("Plugin has been enabled");


    }




    public void check_glitch() {
        // bank
    }

    public void schedule_zane() {
        BukkitScheduler schedule = this.getServer().getScheduler();
        Random random = new Random();
        int time = random.nextInt(72000);
        int time2 = random.nextInt(18000);
        schedule.runTaskLater(getPluginManager().getPlugin("Yahoo"),new buff(this),time + 1);
        Bukkit.getScheduler().runTaskLater(getPluginManager().getPlugin("Yahoo"),new set_who(this),12000);
        Bukkit.getScheduler().runTaskLater(getPluginManager().getPlugin("Yahoo"),new teleport(this),time2);
        Bukkit.getScheduler().runTaskLater(get_plugin(),new insanity(),time2);
        Bukkit.getScheduler().runTaskLater(get_plugin(),new hunger_runnables(),600);

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
        getServer().getPluginManager().registerEvents(new determination(), this);
        getServer().getPluginManager().registerEvents(new pet(), this);
        getServer().getPluginManager().registerEvents(new snapv2(), this);
        getServer().getPluginManager().registerEvents(new alchemic_bag(this),this);
        getServer().getPluginManager().registerEvents(new drops(),this);
        getServer().getPluginManager().registerEvents(new glitched_gem(),this);
        getServer().getPluginManager().registerEvents(new jump(),this);
        getServer().getPluginManager().registerEvents(new gem_port(),this);
        getServer().getPluginManager().registerEvents(new hunger(),this);
        getServer().getPluginManager().registerEvents(new swor(),this);
        getServer().getPluginManager().registerEvents(new fall_drop(),this);
        getServer().getPluginManager().registerEvents(new blessing_of_life(),this);
        getServer().getPluginManager().registerEvents(new space.yaszu.yahoo.movesets.porter.star(),this);
        getServer().getPluginManager().registerEvents(new senzu_bean(),this);
        getServer().getPluginManager().registerEvents(new space_warper(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new boots_of_swiftness(),this);
        getServer().getPluginManager().registerEvents(new Ritual(),this);
        getServer().getPluginManager().registerEvents(new invisibility_cloak(),this);
        getServer().getPluginManager().registerEvents(new impish_armor(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new scythe(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new meth(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new glaive(),this);
    }
    public void register_items() {
        item_register register = new item_register(this);
        register.register();
        run_register.register();
        swor.register_recipe();
        blessing_of_life.register_recipe();
        getLogger().info("Registered Items!");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void createWorld(){
        WorldCreator c = new WorldCreator("Glitch");
        c.type(WorldType.AMPLIFIED);
        c.generateStructures(false);

        c.biomeProvider(glitch_provide());
        c.generateStructures(true);
        c.generatorSettings();
        world = c.createWorld();
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setTime(236927);
        world.setThundering(true);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setStorm(true);

    }

    public static Plugin get_plugin(){
        return Bukkit.getPluginManager().getPlugin("Yahoo");
    }

    public static World get_glitched() {
        return Bukkit.getWorld("Glitch");
    }



    public BiomeProvider glitch_provide() {
        BiomeProvider provide = new BiomeProvider() {
            @Override
            public @NotNull Biome getBiome(@NotNull WorldInfo worldInfo, int x, int y, int z) {
                return Biome.BIRCH_FOREST;
                
            }

            @Override
            public @NotNull List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
                return List.of(Biome.WARPED_FOREST,Biome.BADLANDS,Biome.SMALL_END_ISLANDS);
            }

        };
        return provide;
    }

}

