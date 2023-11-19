package com.destore.business;

import com.destore.data.InventoryDAO;
import com.destore.data.ProductDAO;
import com.destore.model.Inventory;
import com.destore.model.Product;

public class InventoryService {
    private final ProductDAO productDAO;
    private final InventoryDAO inventoryDAO;

    public InventoryService(ProductDAO productDAO, InventoryDAO inventoryDAO) {
        this.productDAO = productDAO;
        this.inventoryDAO = inventoryDAO;
    }

    // Add a specified quantity of a product to the inventory
    public void addToInventory(int productId, int quantity) {
        Product product = productDAO.getProductById(productId);

        if (product != null) {
            // Check if there is an existing inventory record for the product
            Inventory existingInventory = inventoryDAO.getInventoryByProductId(productId);

            if (existingInventory != null) {
                int currentQuantity = existingInventory.getQuantity();
                int newQuantity = currentQuantity + quantity;

                // Update the existing inventory record
                inventoryDAO.updateInventoryQuantity(productId, newQuantity);
            } else {
                // Create a new inventory record
                inventoryDAO.addInventory(productId, quantity);
            }

            System.out.println(quantity + " units of " + product.getName() + " added to inventory.");
        } else {
            System.out.println("Product not found for ID: " + productId);
        }
    }


}
