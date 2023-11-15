package com.destore.business;

import com.destore.model.Product;
import com.destore.data.ProductDAO;

public class PriceControlService {
    private ProductDAO productDAO;

    public PriceControlService() {
        this.productDAO = new ProductDAO();
    }
    public void setProductPrice(int productId, double newPrice) {
        Product product = productDAO.getProductById(productId);

        if (product != null) {
            product.setPrice(newPrice);
            // You might want to update the product in the database here
            // productDAO.updateProduct(product);
            System.out.println("Price of Product ID " + productId + " set to: " + newPrice);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void applySaleOffer(int productId, String offerType) {
        Product product = productDAO.getProductById(productId);

        if (product != null) {
            // Implement logic to apply sale offers based on offerType
            // For example, 3 for 2, buy one get one free, etc.
            // Update the product in the database after applying the offer
             productDAO.updateProduct(product);
            System.out.println("Sale offer applied to Product ID " + productId + ": " + offerType);
        } else {
            System.out.println("Product not found.");
        }
    }
}
