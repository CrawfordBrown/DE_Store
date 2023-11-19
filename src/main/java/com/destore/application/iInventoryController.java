package com.destore.application;

public interface iInventoryController {
    void addToInventory(int productId, int quantity);

    void checkStock();
}
