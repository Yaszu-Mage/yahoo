package space.yaszu.yahoo.util;

import org.bukkit.inventory.Inventory;

import java.io.*;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class InventoryStringDeSerializer {
    public static String serialize(ItemStack[] items) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            DataOutput output = new DataOutputStream(outputStream);
            output.writeInt(items.length);

            for (ItemStack item : items) {
                if (item == null) {
                    // Ensure the correct order by including empty/null items
                    // Simply remove the write line if you don't want this
                    output.writeInt(0);
                    continue;
                }

                byte[] bytes = item.serializeAsBytes();
                output.writeInt(bytes.length);
                output.write(bytes);
            }
            return Base64Coder.encodeLines(outputStream.toByteArray()); // Base64 encoding is not strictly needed
        } catch (IOException e) {
            throw new RuntimeException("Error while writing itemstack", e);
        }
    }

    public static ItemStack[] deserialize(String encodedItems) {
        byte[] bytes = Base64Coder.decodeLines(encodedItems); // Base64 encoding is not strictly needed
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
            DataInputStream input = new DataInputStream(inputStream);
            int count = input.readInt();
            ItemStack[] items = new ItemStack[count];
            for (int i = 0; i < count; i++) {
                int length = input.readInt();
                if (length == 0) {
                    // Empty item, keep entry as null
                    continue;
                }

                byte[] itemBytes = new byte[length];
                input.read(itemBytes);
                items[i] = ItemStack.deserializeBytes(itemBytes);
            }
            return items;
        } catch (IOException e) {
            throw new RuntimeException("Error while reading itemstack", e);
        }
    }
}