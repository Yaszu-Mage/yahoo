package space.yaszu.yahoo.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import com.destroystokyo.paper.profile.*;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import space.yaszu.yahoo.util.key;

import java.util.*;
/*
  _______ _             _____                       __          __                        _     ____
 |__   __| |           / ____|                      \ \        / /                       | |  _|___ \
    | |  | |__   ___  | (___  _ __   __ _  ___ ___   \ \  /\  / /_ _ _ __ _ __   ___ _ __| | (_) __) |
    | |  | '_ \ / _ \  \___ \| '_ \ / _` |/ __/ _ \   \ \/  \/ / _` | '__| '_ \ / _ \ '__| |    |__ <
    | |  | | | |  __/  ____) | |_) | (_| | (_|  __/    \  /\  / (_| | |  | |_) |  __/ |  |_|  _ ___) |
    |_|  |_| |_|\___| |_____/| .__/ \__,_|\___\___|     \/  \/ \__,_|_|  | .__/ \___|_|  (_) (_)____/
                             | |                                         | |
                             |_|                                         |_|
*/
public class space_warper implements Listener {
    public static Map<UUID, menu> player_inventory = new HashMap<>();
    public static Map<UUID, accept_menu> accept = new HashMap<>();
    public static Map<UUID, Map<UUID, Boolean>> active_requests = new HashMap<>();
    public static Map<UUID, Map<String,Location>> player_waypoints = new HashMap<>();
    public static ItemStack warper() {
        ItemStack item = ItemStack.of(Material.RECOVERY_COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new key().get_key("itemid"), PersistentDataType.STRING,"space_warper");
        meta.displayName(MiniMessage.miniMessage().deserialize("<dark_purple>|</dark_purple> <gradient:#ff55ff:#0dc6ff:#f79459>Space Warper</gradient> <dark_purple>|</dark_purple>"));
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public static void check_action(PlayerDropItemEvent event)  {
        Player player = event.getPlayer();
        ItemStack offhand = event.getItemDrop().getItemStack();
        ItemStack mainhand = event.getPlayer().getInventory().getItemInMainHand();
        if (!offhand.equals(null) && player.isSneaking()) {
            if (offhand.getPersistentDataContainer().has(new key().get_key("itemid"))) {
                if (offhand.getPersistentDataContainer().get(new key().get_key("itemid"),PersistentDataType.STRING).equals("space_warper") && player.getCooldown(warper()) == 0) {
                    menu instance = new menu();
                    player_inventory.putIfAbsent(player.getUniqueId(),instance);
                    player.openInventory(player_inventory.get(player.getUniqueId()).getInventory());
                    event.setCancelled(true);
                }
            }

        }
    }
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (!(inventory.getHolder(false) instanceof accept_menu accept_menu)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        accept_menu = accept.get(player.getUniqueId());
        @NotNull ItemStack clicked = event.getCurrentItem();
        if (!(clicked == null)) {
            if (clicked.equals(inventory.getItem(0))) {
                player.closeInventory();
                player.openInventory(player_inventory.get(player.getUniqueId()).getInventory());
            }
            if (clicked.equals(inventory.getItem(8))) {
                // Maybe Waypoint Menu?
                //TODO make waypoint menu
            }
            if (clicked.getType().equals(Material.PLAYER_HEAD)) {
                accept_menu.accept_request(event.getCurrentItem());
                String display_name = "";
                display_name = display_name.replace(" [TO]","");
                display_name = display_name.replace(" [HERE]", "");
                Player offplayer = Bukkit.getPlayer(display_name);
                player.setCooldown(space_warper.warper(),1200);
                active_requests.remove(player.getUniqueId());
                active_requests.remove(offplayer.getUniqueId());
                player.closeInventory();
            }
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void ontparequest(TPARequestSendEvent event) {
        Collection online_players = Bukkit.getOnlinePlayers();
        for (Object player : online_players) {
            if (player instanceof Player) {
                player = (Player) player;
                //event goes sender, reciever
                accept_menu menu = accept.get(((Player) player).getUniqueId());
                if (menu == null) {
                    accept_menu instace = new accept_menu(event.getInfo().receiver);
                    menu = instace;
                }
                if (player.equals(event.getInfo().receiver)){
                    menu.add_request(event.getInfo().receiver, event.getInfo().direction);
                    Map<UUID, Boolean> map = new HashMap<>(); map.put(event.getInfo().receiver.getUniqueId(),event.getInfo().direction);
                    event.getInfo().sender.sendMessage(MiniMessage.miniMessage().deserialize("<gold>! </gold><color:#ff55ff>You feel as if someone is calling to you</color><gold> !</gold>"));
                    event.getInfo().receiver.playSound(event.getInfo().receiver.getLocation(),Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1f,4f);
                    space_warper.active_requests.put(event.getInfo().sender.getUniqueId(),map);
                }
                menu.render_requests();
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
        HumanEntity humanplayer = event.getWhoClicked();
        Player player = (Player) humanplayer;
        menu = player_inventory.get(player.getUniqueId());
        if (!(clicked == null)) {
            if (clicked.getType().equals(Material.PLAYER_HEAD)) {
                SkullMeta meta = (SkullMeta) clicked.getItemMeta();
                OfflinePlayer offplayer = meta.getOwningPlayer();
                if (!Bukkit.getPlayer(offplayer.getName()).equals(null)) {
                    if (menu.gettowards()) {
                        Player_Info info = new Player_Info(offplayer.getPlayer(),player,menu.gettowards());

                        TPARequestSendEvent instance_event = new TPARequestSendEvent(info);
                        instance_event.callEvent();
                        //Teleport TO someone
                        /*player.getWorld().playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),128);
                        event.getWhoClicked().teleport(offplayer.getLocation());
                        player.getWorld().playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),128);*/
                    }else {
                        //true is to false is to here
                        Player_Info info = new Player_Info(offplayer.getPlayer(),player,menu.gettowards());
                        TPARequestSendEvent instance_event = new TPARequestSendEvent(info);
                        instance_event.callEvent();
                        //Teleport someone HERE
                        /*player.getWorld().playSound(offplayer.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                        player.getWorld().spawnParticle(Particle.PORTAL,offplayer.getLocation(),128);
                        offplayer.getPlayer().teleport(player.getLocation());
                        player.getWorld().playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
                        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),128);*/
                    }
                    inventory.close();
                    player.setCooldown(space_warper.warper(),1200);
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
                    accept_menu instance = new accept_menu(player);
                    accept.putIfAbsent(player.getUniqueId(),instance);
                    player.openInventory(accept.get(player.getUniqueId()).getInventory());
                    accept_menu accept_menu = accept.get(player.getUniqueId());
                    accept_menu.render_requests();
                    accept_menu.set_items();
                    accept_menu.render_requests();
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
class waypoint_menu implements InventoryHolder {
    private final Inventory inventory;
    private final Player player;
    public key keygen = new key();
    public Map<ItemStack,Location> map = new HashMap<>();
    public waypoint_menu(Player player) {
        this.inventory = Bukkit.createInventory(this,9,"Space Warper");
        this.player = player;
    }
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
    public void set_items(){
        ItemStack right = ItemStack.of(Material.IRON_BARS);
        ItemMeta meta = right.getItemMeta();
        meta.setDisplayName("Next Menu");
        right.setItemMeta(meta);
        ItemStack left = ItemStack.of(Material.IRON_BARS);
        ItemMeta leftmeta = right.getItemMeta();
        leftmeta.setDisplayName("Back Menu");
        left.setItemMeta(leftmeta);
        if (player.getPersistentDataContainer().get(keygen.get_key("waypoints"),PersistentDataType.LIST.strings()) == null) {

        }
    }
}
class accept_menu implements InventoryHolder {
    private final Inventory inventory;
    public static Map<UUID,Boolean> active_requests = new HashMap<>();
    private final Player player;
    public accept_menu(Player player) {
        this.inventory = Bukkit.createInventory(this,36,"Space Warper");
        this.player = player;
    }
    public void accept_request(ItemStack clicked) {
        SkullMeta meta = (SkullMeta) clicked.getItemMeta();
        String display_name = meta.getDisplayName();
        if (meta.getDisplayName().contains(" [TO]")) {
            display_name = display_name.replace(" [TO]","");
            Player offplayer = Bukkit.getPlayer(display_name);
            space_warper.active_requests.remove(player.getUniqueId());
            player.getWorld().playSound(offplayer.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
            player.getWorld().spawnParticle(Particle.PORTAL,offplayer.getLocation(),128);
            offplayer.getPlayer().teleport(player.getLocation());
            player.getWorld().playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
            player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),128);
        } else if (meta.getDisplayName().contains(" [HERE]")) {
            display_name = display_name.replace(" [HERE]","");
            Player offplayer = Bukkit.getPlayer(display_name);
            space_warper.active_requests.remove(player.getUniqueId());
            player.getWorld().playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
            player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),128);
            player.teleport(offplayer.getLocation());
            player.getWorld().playSound(player.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
            player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),128);
        }
    }
    public void add_request(Player sender, Boolean direction){
        render_requests();
    }
    public void render_requests() {
        int iteration = 0;
        for (UUID uuid : space_warper.active_requests.keySet()) {
            Map<UUID,Boolean> ls = space_warper.active_requests.get(uuid);;
            if (uuid == player.getUniqueId()) {

            for (UUID uuid2 : ls.keySet()) {
                ItemStack skull = getSkull(Bukkit.getPlayer(uuid2));
                SkullMeta meta = (SkullMeta) skull.getItemMeta();
                String direction = "";
                if (ls.get(uuid2)) {
                    direction = " [TO]";
                } else {
                    direction = " [HERE]";
                }
                meta.setDisplayName(meta.getOwningPlayer().getName() + direction);
                skull.setItemMeta(meta);
                inventory.setItem(iteration + 18,skull);
                iteration = iteration + 1;
            }} else {
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
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
    public void set_items() {
        for (int iteration = 0; iteration < inventory.getSize(); iteration = iteration + 1) {
            if (iteration != inventory.getSize()) {
                ItemStack line = ItemStack.of(Material.GRAY_STAINED_GLASS_PANE);
                ItemMeta linemeta = line.getItemMeta();
                linemeta.setDisplayName("");
                line.setItemMeta(linemeta);
                inventory.setItem(iteration, line);
            }
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
        inventory.setItem(1,getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NiODgyMjVlZTRhYjM5ZjdjYmY1ODFmMjJjYmYwOGJkY2MzMzg4NGYxZmY3NDc2ODkzMTI4NDE1MTZjMzQ1In19fQ=="));
        inventory.setItem(2,getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2VkOWY0MzFhOTk3ZmNlMGQ4YmUxODQ0ZjYyMDkwYjE3ODNhYzU2OWM5ZDI3OTc1MjgzNDlkMzdjMjE1ZmNjIn19fQ=="));
        inventory.setItem(3,getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmY3MmNjZWI0YTU2NTQ3OGRlNWIwYjBlNzI3OTQ2ZTU0OTgzNGUzNmY2ZTBlYzhmN2RkN2Y2MzI3YjE1YSJ9fX0="));
        inventory.setItem(4,getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZkYzRmMzIxYzc4ZDY3NDg0MTM1YWU0NjRhZjRmZDkyNWJkNTdkNDU5MzgzYTRmZTlkMmY2MGEzNDMxYTc5In19fQ=="));
        inventory.setItem(5,getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2VkOWY0MzFhOTk3ZmNlMGQ4YmUxODQ0ZjYyMDkwYjE3ODNhYzU2OWM5ZDI3OTc1MjgzNDlkMzdjMjE1ZmNjIn19fQ=="));
        inventory.setItem(6,getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWYyMmQ3Y2Q1M2Q1YmZlNjFlYWZiYzJmYjFhYzk0NDQzZWVjMjRmNDU1MjkyMTM5YWM5ZmJkYjgzZDBkMDkifX19"));
        inventory.setItem(7,getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmMyZmNiYzI0ZTczODJhYzExMmJiMmMwZDVlY2EyN2U5ZjQ4ZmZjYTVhMTU3ZTUwMjYxN2E5NmQ2MzZmNWMzIn19fQ=="));
        inventory.setItem(0,left);
        inventory.setItem(8,right);
    }
    public ItemStack getCustomSkull(String base64) {

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (base64.isEmpty()) return head;

        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        PlayerProfile profile = skullMeta.getPlayerProfile();
        profile = Bukkit.createProfile(UUID.randomUUID(),null);
        profile.getProperties().add(new ProfileProperty("textures", base64));
        skullMeta.setPlayerProfile(profile);
        head.setItemMeta(skullMeta);
        return head;
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
        for (int x = 0; x <= 5; x = x + 1){
            ItemStack empty = ItemStack.of(Material.COAL);
            ItemMeta meta = empty.getItemMeta();
            meta.setDisplayName("EMPTY");
            empty.setItemMeta(meta);
            if (x>=3) {
                if (x+3 == 9) {
                    //pass
                } else {

                        inventory.setItem(x + 3, empty);
                    }
            } else {
                    inventory.setItem(x, empty);
            }
        }
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
    public Player sender;
    public Player receiver;
    public boolean direction;
    public Player_Info(Player sender, Player receiver,boolean direction) {
        this.sender = sender;
        this.receiver = receiver;
        this.direction = direction;
    }
}