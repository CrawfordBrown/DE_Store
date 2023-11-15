package com.destore.test;

import com.destore.data.InventoryDAO;
import com.destore.data.ProductDAO;
import com.destore.model.Inventory;
import com.destore.model.Product;

public class InventoryDAOTest {
    public static void main(String[] args) {
        testGetInventoryByProductId();
        testUpdateInventoryQuantity();
        testDeleteInventory();
    }

    private static void testGetInventoryByProductId() {
        InventoryDAO inventoryDAO = new InventoryDAO();
        ProductDAO productDAO = new ProductDAO();

        int productId = 1;  // Assuming a valid product ID
        Product product = productDAO.getProductById(productId);

        if (product != null) {
            Inventory inventory = inventoryDAO.getInventoryByProductId(productId);

            if (inventory != null) {
                System.out.println("Retrieved Inventory:");
                System.out.println("Product ID: " + inventory.getProduct_id());
                System.out.println("Quantity: " + inventory.getQuantity());
                System.out.println();
            } else {
                System.out.println("Inventory not found for the product.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void testUpdateInventoryQuantity() {
        InventoryDAO inventoryDAO = new InventoryDAO();
        int productIdToUpdate = 2;  // Assuming a valid product ID
        int newQuantity = 50;  // Assuming a new quantity value

        inventoryDAO.updateInventoryQuantity(productIdToUpdate, newQuantity);
    }

    private static void testDeleteInventory() {
        InventoryDAO inventoryDAO = new InventoryDAO();
        int productIdToDelete = 3;  // Assuming a valid product ID

        boolean deleted = inventoryDAO.deleteInventory(productIdToDelete);

        if (deleted) {
            System.out.println("Inventory record deleted from the database.");
        } else {
            System.out.println("Failed to delete inventory record or record not found.");
        }
    }
}
