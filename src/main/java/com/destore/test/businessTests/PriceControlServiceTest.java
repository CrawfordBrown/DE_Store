package com.destore.test.businessTests;


import com.destore.business.PriceControlService;
import com.destore.data.ProductDAO;
import com.destore.model.Customer;
import com.destore.model.Product;
import com.destore.model.ProductEntry;
import com.destore.model.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceControlServiceTest {

    private PriceControlService priceControlService;
    private ProductDAO mockProductDAO;

    @BeforeEach
    public void setUp() {
        ShoppingCart shoppingCart = new ShoppingCart(new Customer());
        priceControlService = new PriceControlService(mockProductDAO, shoppingCart);
    }



    @Test
    public void testApply3For2Offer() {
        // Create a ProductDAO using the existing connection
        ProductDAO productDAO = new ProductDAO();

        // Create a shopping cart with the test product
        ShoppingCart shoppingCart = new ShoppingCart(new Customer());
        Product testProduct = productDAO.getProductById(2); // Assuming the product with ID 1 exists
        shoppingCart.addProduct(testProduct, 3);

        double priceBeforeOffer = shoppingCart.calculateTotalPrice();

        // Create a PriceControlService with the existing productDAO and shoppingCart
        PriceControlService priceControlService = new PriceControlService(productDAO, shoppingCart);

        // Apply the 3-for-2 offer
        priceControlService.apply3For2Offer(shoppingCart);

        double priceAfterOffer = shoppingCart.calculateTotalPrice();

        assertEquals(89.97, priceBeforeOffer, 0.01);
        assertEquals(59.98, priceAfterOffer, 0.01);
    }



    @Test
    public void testApplyBuyOneGetOneFreeOffer() {

        // Create a ProductDAO using the existing connection
        ProductDAO productDAO = new ProductDAO();

        // Create a shopping cart with the test product
        ShoppingCart shoppingCart = new ShoppingCart(new Customer());
        Product testProduct = productDAO.getProductById(2);
        shoppingCart.addProduct(testProduct, 2);

        double priceBeforeOffer = shoppingCart.calculateTotalPrice();

        // Create a PriceControlService with the existing productDAO and shoppingCart
        PriceControlService priceControlService = new PriceControlService(productDAO, shoppingCart);

        // Apply the buy one get one free offer
        priceControlService.applyBuyOneGetOneFreeOffer(shoppingCart);

        double priceAfterOffer = shoppingCart.calculateTotalPrice();

        assertEquals(59.98, priceBeforeOffer, 0.01);
        assertEquals(29.99, priceAfterOffer, 0.01);

    }

    }

