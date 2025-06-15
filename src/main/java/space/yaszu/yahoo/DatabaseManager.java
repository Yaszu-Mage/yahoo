package space.yaszu.yahoo;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.sql.*;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

public class DatabaseManager {

    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:plugins/Yahoo/database.db");
        Statement initial = connection.createStatement();
        String initialsql1 = "CREATE DATABASE IF NOT EXISTS yahoo";
        String initialsql = "CREATE TABLE IF NOT EXISTS inventories";
        Statement statement = connection.createStatement();
        statement.execute(initialsql1);
        statement.execute(initialsql);
        return DriverManager.getConnection("jdbc:sqlite:plugins/Yahoo/database.db");
    }

    public Inventory getinventory(UUID uuid, Inventory baseinventory) throws SQLException, ClassNotFoundException, IOException {
        Statement statement = connect().createStatement();
        String tablesql = "SELECT" + "*" + "FROM inventories";
        ResultSet resultSet = statement.executeQuery(tablesql);
        String inventory = resultSet.getString(uuid.toString());
        ItemStack[] inventorylist = deserializeInventory(inventory);
        int index = 0;
        for (ItemStack item: inventorylist) {
            baseinventory.setItem(index,item);
            index++;
        }
        return baseinventory;


    }

    public boolean setinventory(UUID uuid, Inventory baseinventory) throws SQLException, ClassNotFoundException, IOException {
        boolean output = false;
        String inventory = serializeInventory(baseinventory.getContents());

        Statement statement = connect().createStatement();
        String tablesql = "update" + "inventories" + "set" + uuid.toString() + "=" + inventory;
        ResultSet resultSet = statement.executeQuery(tablesql);
        if (Objects.equals(resultSet.getString(uuid.toString()), inventory)) {
            output = true;
        }
        return output;
    }

    public static ItemStack[] deserializeInventory(String base64String) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(base64String));
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (ItemStack[]) ois.readObject();
    }

    public static String serializeInventory(ItemStack[] inventory) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(inventory);
        oos.flush();
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
