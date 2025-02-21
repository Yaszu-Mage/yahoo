package space.yaszu.yahoo;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.alchemy.alchemic_bag;
import space.yaszu.yahoo.alchemy.events.drops;
import space.yaszu.yahoo.alchemy.run_register;
import space.yaszu.yahoo.commands.give_glove;
import space.yaszu.yahoo.commands.grenade;
import space.yaszu.yahoo.commands.reset_cooldowns;
import space.yaszu.yahoo.commands.time_dialator;
import space.yaszu.yahoo.events.*;
import space.yaszu.yahoo.events.new_runnables.insanity;
import space.yaszu.yahoo.events.new_runnables.teleport;
import space.yaszu.yahoo.flamer.jump;
import space.yaszu.yahoo.flamer.snap;
import space.yaszu.yahoo.flamer.snapv2;
import space.yaszu.yahoo.glitch.*;
import space.yaszu.yahoo.items.item_event;
import space.yaszu.yahoo.items.item_register;
import space.yaszu.yahoo.porter.local_teleportation;

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
        createWorld();
        check_glitch();
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
        getServer().getPluginManager().registerEvents(new local_teleportation(), this);
        getServer().getPluginManager().registerEvents(new dialation(),this);
        getServer().getPluginManager().registerEvents(new alchemic_bag(this),this);
        getServer().getPluginManager().registerEvents(new drops(),this);
        getServer().getPluginManager().registerEvents(new air_step(this),this);
        getServer().getPluginManager().registerEvents(new glitched_gem(),this);
        getServer().getPluginManager().registerEvents(new jump(),this);
        getServer().getPluginManager().registerEvents(new gem_port(),this);
    }
    public void register_items() {
        item_register register = new item_register(this);
        register.register();
        run_register.register();

        getLogger().info("Registered Items!");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void createWorld(){
        WorldCreator c = new WorldCreator("Glitch");
        c.type(WorldType.AMPLIFIED);
        c.biomeProvider(glitch_provide());
        c.generateStructures(true);
        world = c.createWorld();

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
                return Biome.WARPED_FOREST;
            }

            @Override
            public @NotNull List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
                return List.of(Biome.WARPED_FOREST,Biome.BADLANDS,Biome.SMALL_END_ISLANDS);
            }

        };
        return provide;
    }

}

