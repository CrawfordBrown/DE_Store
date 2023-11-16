package com.destore.business;

import com.destore.data.ProductDAO;
import com.destore.data.InventoryDAO;
import com.destore.model.Product;
import com.destore.model.ProductEntry;
import com.destore.model.ShoppingCart;


public class PriceControlService {
    private final ProductDAO productDAO;
    private final ShoppingCart shoppingCart;

    public PriceControlService(ProductDAO productDAO, ShoppingCart shoppingCart) {
        this.productDAO = productDAO;
        this.shoppingCart = shoppingCart;
    }

    public void setProductPrice(int productId, double newPrice) {
        // Retrieve the product from the database
        Product product = productDAO.getProductById(productId);

        if (product != null) {
            // Update the product's price
            product.setPrice(newPrice);

            // Save the updated product back to the database
            productDAO.updateProduct(product);

            System.out.println("Price set successfully for product ID: " + productId);
        } else {
            System.out.println("Product not found for ID: " + productId);
        }

    }
    public void apply3For2Offer(ShoppingCart shoppingCart) {
        for (ProductEntry entry : shoppingCart.getProductEntries()) {
            int quantity = entry.getQuantity();
            int freeItems = quantity / 3;

            // Ensure there are enough items for the offer
            if (freeItems > 0) {
                double originalPrice = entry.getProduct().getPrice();
                double discountedPrice = (quantity - freeItems) * originalPrice / quantity;

                // Update the product's price with the discounted price
                entry.getProduct().setPrice(discountedPrice);

                System.out.println("3-for-2 offer applied for product: " + entry.getProduct().getName());
            } else {
                System.out.println("Not enough quantity for 3-for-2 offer for product: " + entry.getProduct().getName());
            }
        }
    }

    public void applyBuyOneGetOneFreeOffer(ShoppingCart shoppingCart) {
        for (ProductEntry entry : shoppingCart.getProductEntries()) {
            int quantity = entry.getQuantity();
            int freeItems = quantity / 2;

            double originalPrice = entry.getProduct().getPrice();
            double discountedPrice = (quantity - freeItems) * originalPrice / quantity;

            // Update the product's price with the discounted price
            entry.getProduct().setPrice(discountedPrice);

            System.out.println("Buy one get one free offer applied for product: " + entry.getProduct().getName());
        }
    }
}
