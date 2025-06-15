package space.yaszu.yahoo.util;

import org.bukkit.NamespacedKey;
import space.yaszu.yahoo.Yahoo;

public class key {
    public NamespacedKey get_key(String value){
        return new NamespacedKey(Yahoo.get_plugin(), value);
    }
}
