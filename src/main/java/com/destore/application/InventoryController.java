package com.destore.application;


import com.destore.business.iInventoryService;

public class InventoryController implements iInventoryController {
    private final iInventoryService inventoryService;

    public InventoryController(iInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    public void addToInventory(int productId, int quantity) {
        inventoryService.addToInventory(productId, quantity);
    }

    @Override
    public void checkStock() {
        inventoryService.checkStock();
    }
}
