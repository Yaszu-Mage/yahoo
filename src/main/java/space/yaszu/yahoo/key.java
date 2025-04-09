package space.yaszu.yahoo;

import org.bukkit.NamespacedKey;

public class key {
    public NamespacedKey get_key(String value){
        return new NamespacedKey(Yahoo.get_plugin(), value);
    }
}
