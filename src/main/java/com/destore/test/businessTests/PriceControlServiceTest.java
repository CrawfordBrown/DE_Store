package com.destore.test.businessTests;

import com.destore.business.PriceControlService;
import com.destore.data.InventoryDAO;
import com.destore.data.ProductDAO;
import com.destore.model.Inventory;
import com.destore.model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceControlServiceTest {
    @Test
    public void testSetProductPrice() {
        ProductDAO productDAO = new ProductDAO();
        PriceControlService priceControlService = new PriceControlService(productDAO, null);

        // Assuming you have a product in the database with relevant data
        int productId = 1;
        double newPrice = 15.99;

        priceControlService.setProductPrice(productId, newPrice);

        // Retrieve the updated product and assert that the price is set correctly
        Product updatedProduct = productDAO.getProductById(productId);
        assertEquals(newPrice, updatedProduct.getPrice());
    }

//    @Test
//    public void testApply3For2Offer() {
//        ProductDAO productDAO = new ProductDAO();
//        InventoryDAO inventoryDAO = new InventoryDAO();
//        PriceControlService priceControlService = new PriceControlService(productDAO, inventoryDAO);
//
//        // Assuming you have a product and inventory in the database with relevant data
//        int productId = 1;
//
//        Product product = productDAO.getProductById(productId);
//        Inventory inventory = inventoryDAO.getInventoryByProductId(productId);
//
//        // Record the original price for later comparison
//        double originalPrice = product.getPrice();
//
//        // Apply the 3-for-2 offer
//        priceControlService.applySaleOffer(productId, "3for2");
//
//        // Retrieve the updated product and assert that the price is discounted correctly
//        Product updatedProduct = productDAO.getProductById(productId);
//        double discountedPrice = updatedProduct.getPrice();
//        assertEquals(Math.round(originalPrice * 2.0 / 3.0 * 100.0) / 100.0, discountedPrice, 0.1);
//    }
//
//    @Test
//    public void testApplyBuyOneGetOneFreeOffer() {
//        ProductDAO productDAO = new ProductDAO();
//        InventoryDAO inventoryDAO = new InventoryDAO();
//        PriceControlService priceControlService = new PriceControlService(productDAO, inventoryDAO);
//
//        // Assuming you have a product and inventory in the database with relevant data
//        int productId = 1;
//
//        Product product = productDAO.getProductById(productId);
//        Inventory inventory = inventoryDAO.getInventoryByProductId(productId);
//
//        // Record the original price for later comparison
//        double originalPrice = product.getPrice();
//
//        // Apply the Buy One Get One Free offer
//        priceControlService.applySaleOffer(productId, "buyonegetonefree");
//
//        // Retrieve the updated product and assert that the price is discounted correctly
//        Product updatedProduct = productDAO.getProductById(productId);
//        double discountedPrice = updatedProduct.getPrice();
//        assertEquals(Math.round(originalPrice / 2.0 * 100.0) / 100.0, discountedPrice);
//    }
}

