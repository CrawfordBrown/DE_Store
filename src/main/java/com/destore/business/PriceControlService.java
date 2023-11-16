package com.destore.business;

import com.destore.model.Product;
import com.destore.data.ProductDAO;

public class PriceControlService {
    private ProductDAO productDAO;

    public PriceControlService() {
        this.productDAO = new ProductDAO();
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
//    public void applySaleOffer(int productId, String offerType) {
//        // Retrieve the product from the database
//        Product product = productDAO.getProductById(productId);
//
//        if (product != null) {
//            // Retrieve the product's quantity from the inventory
//            int quantityInInventory = productDAO.getProductQuantityInInventory(productId);
//
//            // Apply the sale offer based on the offerType
//            switch (offerType.toLowerCase()) {
//                case "3for2":
//                    apply3For2Offer(product, quantityInInventory);
//                    break;
//                case "buyonegetonefree":
//                    applyBuyOneGetOneFreeOffer(product, quantityInInventory);
//                    break;
//                // Add more offer types as needed
//
//                default:
//                    System.out.println("Invalid offer type: " + offerType);
//            }
//
//            // Save the updated product back to the database
//            productDAO.updateProduct(product);
//
//            System.out.println("Sale offer applied successfully for product ID: " + productId);
//        } else {
//            System.out.println("Product not found for ID: " + productId);
//        }
//    }
//    private void apply3For2Offer(Product product) {
//        // Implement 3 for 2 offer logic
//        int quantity = product.getProductQuantityInInventory(); // Assuming a quantity field in the Product class
//        int freeItems = quantity / 3;
//        double originalPrice = product.getPrice();
//        double discountedPrice = (quantity - freeItems) * originalPrice / quantity;
//
//        // Update the product's price with the discounted price
//        product.setPrice(discountedPrice);
//    }
//
//    private void applyBuyOneGetOneFreeOffer(Product product) {
//        // Implement buy one get one free offer logic
//        int quantity = product.getProductQuantityInInventory(); // Assuming a quantity field in the Product class
//        int freeItems = quantity / 2;
//        double originalPrice = product.getPrice();
//        double discountedPrice = (quantity - freeItems) * originalPrice / quantity;
//
//        // Update the product's price with the discounted price
//        product.setPrice(discountedPrice);
//    }
}
