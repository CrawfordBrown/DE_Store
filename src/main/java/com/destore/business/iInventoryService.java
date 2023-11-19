package com.destore.business;

public interface iInventoryService {

    void addToInventory(int productId, int quantity);

    void checkStock();
}


