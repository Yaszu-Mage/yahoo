package space.yaszu.yahoo.glitch;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.yaszu.yahoo.Yahoo;

import java.util.Random;

import static org.bukkit.Bukkit.getPluginManager;

public class buff implements Runnable{
    private final Yahoo yahoo;
    public Random random = new Random();
    public buff(Yahoo yahoo) {
        this.yahoo = yahoo;
    }

    @Override
    public void run() {
        if (Bukkit.getPlayerExact("1nZ4ne") != null) {
            Player zane = Bukkit.getPlayer("1nZ4ne");
            int zane_fx = random.nextInt(33);
            switch (zane_fx) {
                case 0:
                    zane.sendRawMessage("You feel... faster");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,1000,1));
                case 1:
                    zane.sendRawMessage("You feel... slower");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,1000,1));
                case 2:
                    zane.sendRawMessage("You yearn for the mines");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.HASTE,1000,1));
                case 3:
                    zane.sendRawMessage("When did everyone get so fast?");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE,1000,1));
                case 4:
                    zane.sendRawMessage("You feel stronger.");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,1000,1));
                case 5:
                    zane.sendRawMessage("You feel replenished");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH,1000,1));
                case 6:
                    zane.sendRawMessage("You hurt yourself.");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE,1000,1));
                case 7:
                    zane.sendRawMessage("Hop to it");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,1000,1));
                case 8:
                    zane.sendRawMessage("You ate something funny");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA,1000,1));
                case 9:
                    zane.sendRawMessage("You feel your strength come back");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,1000,1));
                case 10:
                    zane.sendRawMessage("Survival.");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,1000,1));
                case 11:
                    zane.sendRawMessage("FIRE!");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,1000,1));
                case 12:
                    zane.sendRawMessage("Blub blub blub");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,1000,1));
                case 13:
                    zane.sendRawMessage("Nothing Happened");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,1000,1));
                case 14:
                    zane.sendRawMessage("You can't see.");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,1000,1));
                case 15:
                    zane.sendRawMessage("You can see everything");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,1000,1));
                case 16:
                    zane.sendRawMessage("You want Taco bell.");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,1000,1));
                case 17:
                    zane.sendRawMessage("You feel weak.");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,1000,1));
                case 18:
                    zane.sendRawMessage("You ate something funny... It hurts.");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.POISON,1000,1));
                case 19:
                    zane.sendRawMessage("You feel yourself wither away.");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,1000,1));
                case 20:
                    zane.sendRawMessage("You feel healthy");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,1000,1));
                case 21:
                    zane.sendRawMessage("You feel magic course through your veins");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,1000,1));
                case 22:
                    zane.sendRawMessage("You feel full");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,1000,1));
                case 23:
                    zane.sendRawMessage("You feel watched");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,1000,1));
                case 24:
                    zane.sendRawMessage("You feel lighter");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,1000,1));
                case 25:
                    zane.sendRawMessage("Hurry! Hit the slots while you can!");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,1000,1));
                case 26:
                    zane.sendRawMessage("Unlucky...");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,1000,1));
                case 27:
                    zane.sendRawMessage("You feel lighter");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,1000,1));
                case 28:
                    zane.sendRawMessage("The water calls");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,1000,1));
                case 29:
                    zane.sendRawMessage("The water cries your name");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,1000,1));
                case 30:
                    zane.sendRawMessage("You feel like something happened");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN,10000,1));
                case 31:
                    zane.sendRawMessage("You feel like you accidentally stole someone's credit");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE,10000,1));
                case 32:
                    zane.sendRawMessage("You really can't see it?");
                    zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION,1f,1f);
                    zane.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,1000,1));


            }
            int time = random.nextInt(72000);
            Bukkit.getScheduler().runTaskLater(getPluginManager().getPlugin("Yahoo"),new buff(yahoo),time);
        }
    }
}
