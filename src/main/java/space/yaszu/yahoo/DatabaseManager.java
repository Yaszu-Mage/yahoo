package space.yaszu.yahoo;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.*;
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

    public Inventory getinventory(UUID uuid) throws SQLException, ClassNotFoundException {
        Statement statement = connect().createStatement();
        String tablesql = "SELECT" + "*" + "FROM inventories";
        ResultSet resultSet = statement.executeQuery(tablesql);
        Array inventory_array = resultSet.getArray(uuid.toString());


    }
}
