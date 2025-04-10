package space.yaszu.yahoo.alchemy.items;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Skull;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.Yahoo;
import space.yaszu.yahoo.key;

import java.util.*;

public class space_warper implements Listener {
    public static Map<UUID, menu> player_inventory = new HashMap<>();
    public static Map<UUID, accept_menu> accept = new HashMap<>();

    public static ItemStack warper() {
        ItemStack item = ItemStack.of(Material.RECOVERY_COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new key().get_key("itemid"), PersistentDataType.STRING,"space_warper");
        meta.displayName(Component.text("Space Warper"));
        item.setItemMeta(meta);
        return item;
    }
    @EventHandler
    public static void check_action(PlayerSwapHandItemsEvent event)  {
        Player player = event.getPlayer();
        ItemStack offhand = event.getOffHandItem();
        ItemStack mainhand = event.getMainHandItem();
        if (!mainhand.equals(null)) {
            if (mainhand.getPersistentDataContainer().has(new key().get_key("itemid"))) {
                if (mainhand.getPersistentDataContainer().get(new key().get_key("itemid"),PersistentDataType.STRING).equals("space_warper")) {
                    menu instance = new menu();
                    player_inventory.putIfAbsent(player.getUniqueId(),instance);
                    player.openInventory(player_inventory.get(player.getUniqueId()).getInventory());
                    player.setCooldown(space_warper.warper(),1200);
                    event.setCancelled(true);
                }
            }

        }
    }
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (!(inventory.getHolder(false) instanceof accept_menu)) {
            return;
        }
        @NotNull ItemStack clicked = event.getCurrentItem();
        if (!(clicked == null)) {
            if (clicked.equals(inventory.getItem(0))) {
                Player player = (Player) event.getWhoClicked();
                player.closeInventory();
                player.openInventory(player_inventory.get(player.getUniqueId()).getInventory());
            }
            if (clicked.equals(inventory.getItem(8))) {
                // Maybe Waypoint Menu?
                //TODO make waypoint menu
            }
        }
    }
    @EventHandler
    public void ontparequest(TPARequestSendEvent event) {
        Collection online_players = Bukkit.getOnlinePlayers();
        for (Object player : online_players) {
            if (player instanceof Player) {
                player = (Player) player;
                accept_menu menu = accept.get(((Player) player).getUniqueId());

            }
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        // Check if the holder is our MyInventory,
        // if yes, use instanceof pattern matching to store it in a variable immediately.
        if (!(inventory.getHolder(false) instanceof menu menu)) {
            // It's not our inventory, ignore it.
            return;
        }
        @NotNull ItemStack clicked = event.getCurrentItem();
        if (!(clicked == null)) {
            if (clicked.getType().equals(Material.PLAYER_HEAD)) {
                SkullMeta meta = (SkullMeta) clicked.getItemMeta();
                OfflinePlayer offplayer = meta.getOwningPlayer();
                if (!Bukkit.getPlayer(offplayer.getName()).equals(null)) {
                    if (menu.gettowards()) {
                        HumanEntity humanplayer = event.getWhoClicked();
                        Player player = (Player) humanplayer;
                        player.getWorld().playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),128);
                        event.getWhoClicked().teleport(offplayer.getLocation());
                        player.getWorld().playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),128);
                    }else {
                        HumanEntity humanplayer = event.getWhoClicked();
                        Player player = (Player) humanplayer;
                        Player_Info info = new Player_Info(player,offplayer.getPlayer());
                        TPARequestSendEvent instance_event = new TPARequestSendEvent(info);
                        instance_event.callEvent();
                        /*player.getWorld().playSound(offplayer.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                        player.getWorld().spawnParticle(Particle.PORTAL,offplayer.getLocation(),128);
                        offplayer.getPlayer().teleport(player.getLocation());
                        player.getWorld().playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),128);*/
                    }
                    inventory.close();
                }
            } else {
                if (clicked.getType().equals(Material.REDSTONE_BLOCK) || clicked.getType().equals(Material.EMERALD_BLOCK)) {
                    menu.swap();
                }
                if (inventory.getItem(3).equals(clicked)) {
                    //back

                }
                if (inventory.getItem(5).equals(clicked)) {
                    //forward
                    inventory.close();
                    HumanEntity humanplayer = event.getWhoClicked();
                    Player player = (Player) humanplayer;
                    accept_menu instance = new accept_menu(player);
                    accept.putIfAbsent(player.getUniqueId(),instance);
                    player.openInventory(accept.get(player.getUniqueId()).getInventory());
                    accept_menu accept_menu = accept.get(player.getUniqueId());
                    accept_menu.set_items();
                }
            }
        }
        event.setCancelled(true);

    }
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event){
        Inventory inventory = event.getInventory();
        if (!(inventory.getHolder(false) instanceof menu menu)){
            return;
        }
        Player player = (Player) event.getPlayer();
        menu = player_inventory.get(player.getUniqueId());
        menu.set_inventory((Player) event.getPlayer());
        menu.gettowards();
    }
}
class accept_menu implements InventoryHolder {
    private final Inventory inventory;
    public static List<UUID> active_requests = new ArrayList<>();
    private final Player player;
    public accept_menu(Player player) {
        this.inventory = Bukkit.createInventory(this,36,"Space Warper");
        this.player = player;
    }

    public void add_request(Player sender){
        active_requests.addFirst(sender.getUniqueId());
    }
    public void render_requests() {
        for (int iteration = 0; iteration < active_requests.size(); iteration = iteration + 1) {
            if (iteration == active_requests.size()){
                return;
            }
            ItemStack skull = getSkull(Bukkit.getPlayer(active_requests.get(iteration)));
            SkullMeta meta = (SkullMeta) skull.getItemMeta();
            meta.setDisplayName(meta.getOwningPlayer().getName());
            skull.setItemMeta(meta);
            inventory.setItem(iteration + 17,skull);
        }
    }
    public ItemStack getSkull(Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        item.setItemMeta(meta);
        return item;
    }
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
    public void set_items() {
        for (int iteration = 0; iteration < inventory.getSize(); iteration = iteration + 1) {
            if (iteration != inventory.getSize()) {
                if (inventory.getItem(iteration) == null){
                    ItemStack line = ItemStack.of(Material.GRAY_STAINED_GLASS_PANE);
                    ItemMeta linemeta = line.getItemMeta();
                    linemeta.setDisplayName("");
                    line.setItemMeta(linemeta);
                inventory.setItem(iteration, ItemStack.of(Material.GRAY_STAINED_GLASS_PANE));
            }}
        }
        for (int iteration = 0; iteration < 9; iteration = iteration + 1) {
            ItemStack line = ItemStack.of(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta linemeta = line.getItemMeta();
            linemeta.setDisplayName("");
            line.setItemMeta(linemeta);
            inventory.setItem(iteration + 9, line);
        }
        ItemStack right = ItemStack.of(Material.IRON_BARS);
        ItemMeta meta = right.getItemMeta();
        meta.setDisplayName("Next Menu");
        right.setItemMeta(meta);
        ItemStack left = ItemStack.of(Material.IRON_BARS);
        ItemMeta leftmeta = right.getItemMeta();
        leftmeta.setDisplayName("Back Menu");
        left.setItemMeta(leftmeta);
        inventory.setItem(0,left);
        inventory.setItem(8,right);
    }
}
class menu implements InventoryHolder {
    private final Inventory inventory;
    private boolean towards = false;
    public menu() {

        this.inventory = Bukkit.createInventory(this, 9,"Space Warper");

    }
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
    Sort compare = new Sort();
    public @NotNull boolean gettowards(){
        if (towards) {
            ItemStack good = ItemStack.of(Material.EMERALD_BLOCK);
            ItemMeta meta = good.getItemMeta();
            meta.setDisplayName("Teleport TO");
            good.setItemMeta(meta);
            inventory.setItem(4,good);
        }else {
            ItemStack good = ItemStack.of(Material.REDSTONE_BLOCK);
            ItemMeta meta = good.getItemMeta();
            meta.setDisplayName("Teleport HERE");
            good.setItemMeta(meta);
            inventory.setItem(4, good);
        }
        ItemStack right = ItemStack.of(Material.IRON_BARS);
        ItemMeta meta = right.getItemMeta();
        meta.setDisplayName("Next Menu");
        right.setItemMeta(meta);
        ItemStack left = ItemStack.of(Material.IRON_BARS);
        ItemMeta leftmeta = right.getItemMeta();
        leftmeta.setDisplayName("Back Menu");
        left.setItemMeta(leftmeta);
        inventory.setItem(3,left);
        inventory.setItem(5,right);
        return towards;
    }
    public void swap() {
        towards = !towards;
        if (towards) {
            ItemStack good = ItemStack.of(Material.EMERALD_BLOCK);
            ItemMeta meta = good.getItemMeta();
            meta.setDisplayName("Teleport TO");
            good.setItemMeta(meta);
            inventory.setItem(4,good);
        }else {
            ItemStack good = ItemStack.of(Material.REDSTONE_BLOCK);
            ItemMeta meta = good.getItemMeta();
            meta.setDisplayName("Teleport HERE");
            good.setItemMeta(meta);
            inventory.setItem(4, good);
        }
    }

    public void set_inventory(Player player) {
        Collection onlineplayers = Bukkit.getOnlinePlayers();
        List<player_location> distance = new ArrayList<>();
        for (Object instance : onlineplayers) {
            if (instance instanceof Player) {
                if (instance != player) {
                    Player checkinstance = (Player) instance;
                    player_location loc = new player_location();
                    loc.location = checkinstance.getLocation();
                    loc.player = checkinstance.getPlayer();
                    distance.add(loc);
                }

            }
        }
        distance.sort(compare);
        int size = 6;
        if (distance.size() >= 6) {size = 6;} else {size = distance.size();}
        if (distance.size() == 0){
        }else {


        for (int i = 0; i < size; i = i + 1) {
            Yahoo.getlog().info(String.valueOf(i));
            ItemStack head = getSkull(distance.get(i).player);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setDisplayName(distance.get(i).player.getDisplayName());
            head.setItemMeta(meta);
            if (i >= 4) {
                inventory.setItem(i + 2,head);
            } else {
                inventory.setItem(i,head);
            }

        }
        }

        for (int x = 0; x <= 5; x = x + 1){
            ItemStack empty = ItemStack.of(Material.COAL);
            ItemMeta meta = empty.getItemMeta();
            meta.setDisplayName("EMPTY");
            empty.setItemMeta(meta);
            if (x>=3) {
                if (x+3 == 9) {
                    //pass
                } else {
                if (inventory.getItem(x + 3) == null) {

                    inventory.setItem(x + 3, empty);
                }}
            } else {
                if (inventory.getItem(x) == null) {
                    inventory.setItem(x, empty);
                }
            }
        }
    }
    public ItemStack getSkull(Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        item.setItemMeta(meta);
        return item;
    }
}
class player_location {
    public Player player;
    public Location location;
    public player_location() {
        this.location = location;
        this.player = player;
    }

}

class Sort implements Comparator {
    public int compare(Object obj1, Object obj2) {
        // Make sure that the objects are Car objects
        player_location a = (player_location) obj1;
        player_location b = (player_location) obj2;

        // Compare the objects
        if (a.location.distance(b.location) < b.location.distance(a.location)) return -1; // distance to a is shorter than b
        if (a.location.distance(b.location) > b.location.distance(a.location)) return 1;  // distance to a is greater than b
        return 0; // same distance
    }
}
class TPARequestSendEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private Player_Info info;
    public TPARequestSendEvent(Player_Info info){
        this.info = info;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
    public static HandlerList getHandlerList(){
        return HANDLER_LIST;
    }
    public Player_Info getInfo(){
        return this.info;
    }
    public void setInfo(Player_Info info){
        this.info = info;
    }
}
class Player_Info {
    public Player player1;
    public Player player2;

    public Player_Info(Player player1, Player player2) {
        this.player1 = player1;
        this.player1 = player2;
    }


}