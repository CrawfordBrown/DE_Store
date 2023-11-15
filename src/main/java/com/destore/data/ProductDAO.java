package com.destore.data;

import com.destore.model.Product;  // Add this import statement

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    public Product getProductById(int productId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM products WHERE product_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, productId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Product product = new Product();
                        product.setId(resultSet.getInt("product_id"));
                        product.setName(resultSet.getString("name"));
                        product.setPrice(resultSet.getDouble("price"));
                        return product;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addProduct(Product product) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, product.getName());
                statement.setDouble(2, product.getPrice());
                statement.executeUpdate();
                System.out.println("Product added to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE products SET name = ?, price = ? WHERE product_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, product.getName());
                statement.setDouble(2, product.getPrice());
                statement.setInt(3, product.getId());
                statement.executeUpdate();
                System.out.println("Product updated in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a product and its associated records
    public boolean deleteProduct(int productId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            // Delete from inventory first
            String deleteInventorySql = "DELETE FROM inventory WHERE product_id = ?";
            try (PreparedStatement deleteInventoryStatement = connection.prepareStatement(deleteInventorySql)) {
                deleteInventoryStatement.setInt(1, productId);
                deleteInventoryStatement.executeUpdate();
            }

            // Then delete the product
            String deleteProductSql = "DELETE FROM products WHERE product_id = ?";
            try (PreparedStatement deleteProductStatement = connection.prepareStatement(deleteProductSql)) {
                deleteProductStatement.setInt(1, productId);
                int rowsAffected = deleteProductStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

