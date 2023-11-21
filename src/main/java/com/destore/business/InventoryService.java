package com.destore.business;

import com.destore.data.EmailDAO;
import com.destore.data.InventoryDAO;
import com.destore.data.ManagerDAO;
import com.destore.data.ProductDAO;
import com.destore.model.Email;
import com.destore.model.Inventory;
import com.destore.model.Manager;
import com.destore.model.Product;

import java.util.List;

public class InventoryService implements iInventoryService {
    private final ProductDAO productDAO;
    private final InventoryDAO inventoryDAO;
    private final ManagerDAO managerDAO;


    public InventoryService(ProductDAO productDAO, InventoryDAO inventoryDAO, ManagerDAO managerDAO) {
        this.productDAO = productDAO;
        this.inventoryDAO = inventoryDAO;
        this.managerDAO = managerDAO;

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

    public void checkStock() {
        List<Product> productList = productDAO.getAllProducts(); // Assuming you have a method to get all products

        for (Product product : productList) {
            int productId = product.getId();
            int currentQuantity = inventoryDAO.getInventoryQuantity(productId);

            if (currentQuantity < 10) {
                // Product is below 10, send email to store manager
                List<Manager> storeManagers = managerDAO.getManagersByRole("Store Manager");

                for (Manager manager : storeManagers) {
                    // Send email to the store manager with the product information
                    sendEmail(manager.getEmail(), "Low Stock Alert!\n\n" + "Product: " + product.getId() + "\n" + product.getName() +
                            " is below 10 units in stock.");
                }
            }
        }
    }


    private void sendEmail(String email, String message) {
        // Update the email table in the database
        EmailDAO emailDAO = new EmailDAO();

        int managerId = emailDAO.getManagerIdByEmail(email);

        // Create an Email object
        Email emailObject = new Email();
        emailObject.setManager_id(managerId);
        emailObject.setEmail_Address(email);
        emailObject.setEmail_message(message);

        // Add the email to the database
        emailDAO.addEmail(emailObject);

        System.out.println("Email sent to: " + email  + "Message: " + message);
    }


}
