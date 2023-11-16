package com.destore.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private Customer customer;
    private List<ProductEntry> productEntries;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.productEntries = new ArrayList<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<ProductEntry> getProductEntries() {
        return productEntries;
    }

    public void addProduct(Product product, int quantity) {
        // Check if the product is already in the cart
        for (ProductEntry entry : productEntries) {
            if (entry.getProduct().getId() == product.getId()) {
                entry.setQuantity(entry.getQuantity() + quantity);
                return;
            }
        }

        // If the product is not in the cart, add a new entry
        ProductEntry newEntry = new ProductEntry(product, quantity);
        productEntries.add(newEntry);
    }

    public void removeProduct(Product product) {
        // Iterate through the entries
        for (int i = 0; i < productEntries.size(); i++) {
            ProductEntry entry = productEntries.get(i);

            // Check if the current entry matches the specified product
            if (entry.getProduct().getId() == product.getId()) {
                // Remove the entry from the cart
                productEntries.remove(i);
                return; // Exit the method once the entry is removed
            }
        }

        // If the product is not found in the cart
        System.out.println("Product not found in the cart for removal: Product ID " + product.getId());
    }

    public double calculateTotalPrice() {
        // Calculate the total price of products in the shopping cart
        double totalPrice = 0.0;

        for (ProductEntry entry : productEntries) {
            totalPrice += entry.getProduct().getPrice() * entry.getQuantity();
        }

        return totalPrice;
    }

}

