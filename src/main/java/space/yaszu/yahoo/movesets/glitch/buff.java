package space.yaszu.yahoo.movesets.glitch;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
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
        for (Player p : Bukkit.getOnlinePlayers()) {
            String type = "";
            PersistentDataContainer cont = p.getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(Bukkit.getPluginManager().getPlugin("Yahoo"), "Yah_Player_Type");
            if (cont.has(key)) {
                type = cont.get(key, PersistentDataType.STRING);
            } else {
                return;
            }
            Player zane = p;
            int zane_fx = random.nextInt(33);
            if (type.equals("glitch")) {
            if (zane_fx == 0) {
                zane.sendRawMessage("You feel... faster");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000, 1));
            } else if (zane_fx == 1) {
                zane.sendRawMessage("You feel... slower");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 1000, 1));
            } else if (zane_fx == 2) {
                zane.sendRawMessage("You yearn for the mines");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 1000, 1));
            } else if (zane_fx == 3) {
                zane.sendRawMessage("When did everyone get so fast?");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 1000, 1));
            } else if (zane_fx == 4) {
                zane.sendRawMessage("You feel stronger.");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 1000, 1));
            } else if (zane_fx == 5) {
                zane.sendRawMessage("You feel replenished");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1000, 1));
            } else if (zane_fx == 6) {
                zane.sendRawMessage("You hurt yourself.");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 1000, 1));
            } else if (zane_fx == 7) {
                zane.sendRawMessage("Hop to it");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 1000, 1));
            } else if (zane_fx == 8) {
                zane.sendRawMessage("You ate something funny");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 1000, 1));
            } else if (zane_fx == 9) {
                zane.sendRawMessage("You feel your strength come back");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000, 1));
            } else if (zane_fx == 10) {
                zane.sendRawMessage("Survival.");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 1000, 1));
            } else if (zane_fx == 11) {
                zane.sendRawMessage("FIRE!");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000, 1));
            } else if (zane_fx == 12) {
                zane.sendRawMessage("Blub blub blub");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1000, 1));
            } else if (zane_fx == 13) {
                zane.sendRawMessage("Nothing Happened");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000, 1));
            } else if (zane_fx == 14) {
                zane.sendRawMessage("You can't see.");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000, 1));
            } else if (zane_fx == 15) {
                zane.sendRawMessage("You can see everything");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000, 1));
            } else if (zane_fx == 16) {
                zane.sendRawMessage("You want Taco bell.");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 1000, 1));
            } else if (zane_fx == 17) {
                zane.sendRawMessage("You feel weak.");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000, 1));
            } else if (zane_fx == 18) {
                zane.sendRawMessage("You ate something funny... It hurts.");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1000, 1));
            } else if (zane_fx == 19) {
                zane.sendRawMessage("You feel yourself wither away.");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000, 1));
            } else if (zane_fx == 20) {
                zane.sendRawMessage("You feel healthy");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 1000, 1));
            } else if (zane_fx == 21) {
                zane.sendRawMessage("You feel magic course through your veins");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1000, 1));
            } else if (zane_fx == 22) {
                zane.sendRawMessage("You feel full");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000, 1));
            } else if (zane_fx == 23) {
                zane.sendRawMessage("You feel watched");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000, 1));
            } else if (zane_fx == 24) {
                zane.sendRawMessage("You feel lighter");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1000, 1));
            } else if (zane_fx == 25) {
                zane.sendRawMessage("Hurry! Hit the slots while you can!");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 1000, 1));
            } else if (zane_fx == 26) {
                zane.sendRawMessage("Unlucky...");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 1000, 1));
            } else if (zane_fx == 27) {
                zane.sendRawMessage("You feel lighter");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 1000, 1));
            } else if (zane_fx == 28) {
                zane.sendRawMessage("The water calls");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 1000, 1));
            } else if (zane_fx == 29) {
                zane.sendRawMessage("The water cries your name");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 1000, 1));
            } else if (zane_fx == 30) {
                zane.sendRawMessage("You feel like something happened");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 10000, 1));
            } else if (zane_fx == 31) {
                zane.sendRawMessage("You feel like you accidentally stole someone's credit");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 10000, 1));
            } else if (zane_fx == 32) {
                zane.sendRawMessage("You really can't see it?");
                zane.getWorld().playSound(zane.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1f, 1f);
                zane.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 1000, 1));
            }}
        }
        int time = random.nextInt(72000);
        Bukkit.getScheduler().runTaskLater(getPluginManager().getPlugin("Yahoo"),new buff(yahoo),time + 1);




    }
}
