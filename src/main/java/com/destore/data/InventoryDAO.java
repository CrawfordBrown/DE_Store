package com.destore.data;

import com.destore.model.Inventory;
import com.destore.model.Product;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDAO {
    // Implement logic to insert a new inventory record into the database
    public void addInventory(int productId, int quantity) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO inventory (product_id, quantity) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, productId);
                statement.setInt(2, quantity);
                statement.executeUpdate();
                System.out.println("Inventory record added to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement logic to retrieve inventory by product ID from the database
    public Inventory getInventoryByProductId(int productId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM inventory WHERE product_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, productId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Inventory inventory = new Inventory();
                        inventory.setProduct_id(resultSet.getInt("product_id"));
                        inventory.setQuantity(resultSet.getInt("quantity"));
                        return inventory;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Implement logic to update inventory quantity in the database
    public void updateInventoryQuantity(int productId, int newQuantity) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE inventory SET quantity = ? WHERE product_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, newQuantity);
                statement.setInt(2, productId);
                statement.executeUpdate();
                System.out.println("Inventory quantity updated in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement logic to delete inventory record from the database
    public boolean deleteInventory(int productId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "DELETE FROM inventory WHERE product_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, productId);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get the quantity of a product in the inventory
    public int getInventoryQuantity(int productId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT quantity FROM inventory WHERE product_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, productId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("quantity");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if the product is not found in the inventory
    }
}

