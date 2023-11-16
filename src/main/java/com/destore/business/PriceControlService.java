package com.destore.business;

import com.destore.data.ProductDAO;
import com.destore.data.InventoryDAO;
import com.destore.model.Product;
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

//    public void applySaleOffer(int productId, String offerType) {
//        // Retrieve the product from the shopping cart
//        Product product = shoppingCart.getProductById(productId);
//
//        if (product != null) {
//            // Apply the sale offer based on the offerType
//            switch (offerType.toLowerCase()) {
//                case "3for2":
//                    apply3For2Offer(product);
//                    break;
//                case "buyonegetonefree":
//                    applyBuyOneGetOneFreeOffer(product);
//                    break;
//
//                default:
//                    System.out.println("Invalid offer type: " + offerType);
//            }
//
//            System.out.println("Sale offer applied successfully for product ID: " + productId);
//        } else {
//            System.out.println("Product not found in the shopping cart for ID: " + productId);
//        }
//    }
//
//    private void apply3For2Offer(Product product) {
//        // Implement 3 for 2 offer logic
//        int quantityInCart = shoppingCart.getProductQuantity(product);
//        int freeItems = quantityInCart / 3;
//
//        // Ensure there are enough items for the offer
//        if (freeItems > 0) {
//            double originalPrice = product.getPrice();
//            double discountedPrice = (quantityInCart - freeItems) * originalPrice / quantityInCart;
//
//            // Update the product's price in the shopping cart
//            product.setPrice(discountedPrice);
//
//            System.out.println("3-for-2 offer applied. Price set to: " + discountedPrice);
//        } else {
//            System.out.println("Not enough quantity for 3-for-2 offer.");
//        }
//    }
//
//    private void applyBuyOneGetOneFreeOffer(Product product) {
//        // Implement buy one get one free offer logic
//        int quantityInCart = shoppingCart.getProductQuantity(product);
//        int freeItems = quantityInCart / 2;
//
//        double originalPrice = product.getPrice();
//        double discountedPrice = (quantityInCart - freeItems) * originalPrice / quantityInCart;
//
//        // Update the product's price in the shopping cart
//        product.setPrice(discountedPrice);
//    }
}
