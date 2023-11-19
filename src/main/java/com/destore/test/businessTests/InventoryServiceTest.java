package com.destore.test.businessTests;


import com.destore.business.InventoryService;
import com.destore.data.InventoryDAO;
import com.destore.data.ManagerDAO;
import com.destore.data.ProductDAO;
import com.destore.model.Inventory;
import com.destore.model.Manager;
import com.destore.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryServiceTest {

    private InventoryService inventoryService;
    private ProductDAO productDAO;
    private InventoryDAO inventoryDAO;
    private ManagerDAO managerDAO;

    @BeforeEach
    public void setUp() {
        productDAO = new ProductDAO();
        inventoryDAO = new InventoryDAO();
        managerDAO = new ManagerDAO();

        inventoryService = new InventoryService(productDAO, inventoryDAO, managerDAO);
    }

    @Test
    public void testAddToInventory() {
        // Arrange
        int productId = 1;
        int quantity = 5;

        // Act
        inventoryService.addToInventory(productId, quantity);

        // Assert
        assertEquals(60, inventoryDAO.getInventoryQuantity(productId));

    }

    @Test
    public void testCheckStock() {
        int productIdWithLowStock = 6;

        // Act
        inventoryService.checkStock();

        // Assert
        // Check if a manager with role "Store Manager" has been notified
        List<Manager> notifiedManagers = managerDAO.getManagersByRole("Store Manager");
        assertEquals(1, notifiedManagers.size());


    }

}

