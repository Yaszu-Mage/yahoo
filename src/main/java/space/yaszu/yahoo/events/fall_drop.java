package space.yaszu.yahoo.events;

import org.bukkit.NamespacedKey;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataContainer;
import space.yaszu.yahoo.Yahoo;

public class fall_drop implements Listener {
    // I hate this thing I can't just disable I have to do all these acrobatics
    // What else do I work on
    //I have done alot recently for this plugin what else do I do? Maybe Alchemy but I want to do multiblock structures but thats a pain in my asshole
    // It's not really, I would need a mouse and shit, and I don't exactly have that ( I hate modeling with a track pad)
    // ALSO I think imma start on Augurmov's thing soon? Bro kinda isn't sure what he wants for his grenade other than smoke bomb
    // I suggested that but idk dude
    // Nexo asked for a "Lightning strike when crit"
    // So I guess? Maybe?
    // I do want more stuff for Alchemy though, I am going to add human transmutation to everyones abilities all at once but I want to finish everyone first
    //BRO THAT WOULD BE SO FUNNY I STRIKE YOU DOWN WITH THE HAND OF ZEUS THERE GOD FUCKING DAMN IT
    // Should we make domain expansions?
    // I think that may be funny
    // WAIT ITEM IDEA, INVISIBILITY CLOAK!!!
    //Figura models if possible
    //Throwable tnt ig... Idk
    //What if you give Nexo an ability that strikes everyone in a certain range for a few seconds, once per second
    //Wouldn't you need to find a way to hide the armor too?
    //that literally doesn't render the player
    //Now I have to know, can you make it so the player is only visible at the side of the screen?
    // Maybe, it would have to be a complex conditional, to check if they are in visible radius... requiring...
    //VECTOR MATH GOD DAMN IT
    //tf do you mean
    // Ive played forsaken, I just don't know how you would recreate it with purely plugins
    // and why you would, since it would be just a little diffucult
    //Can you build the lab soon? I have an idea how to do a forsaken thing, just we would need an "Arena"
    // alr I kinda have to go, I need to work on robotics stuff, and I will not be available until like... 12 ish
    // Just start the lab
    //You should fr fr just recreate forsaken in Minecraft
    //I have a game to show you now...
    //The characters or something
    //Sillies
    //Alrighty
    public void fall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();
            if (event.getDamageSource().getDamageType() == DamageType.FALL) {
                NamespacedKey key = new NamespacedKey(Yahoo.get_plugin(),"nofall");
                PersistentDataContainer cont = player.getPersistentDataContainer();
                if (cont.has(key)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
