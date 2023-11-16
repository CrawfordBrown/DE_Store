package com.destore.test.modelTests;


import com.destore.model.Customer;
import com.destore.model.Product;
import com.destore.model.ShoppingCart;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    private ShoppingCart shoppingCart;
    private Product product1;
    private Product product2;

    @Before
    public void setUp() {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setName("John Doe");

        shoppingCart = new ShoppingCart(customer);

        product1 = new Product();
        product1.setId(1);
        product1.setName("Product 1");
        product1.setPrice(10.0);

        product2 = new Product();
        product2.setId(2);
        product2.setName("Product 2");
        product2.setPrice(15.0);
    }

    @Test
    public void testAddProductToCart() {
        shoppingCart.addProduct(product1, 2);

        assertEquals(1, shoppingCart.getProductEntries().size());
        assertEquals(2, shoppingCart.getProductEntries().get(0).getQuantity());
        assertEquals(product1, shoppingCart.getProductEntries().get(0).getProduct());
    }

    @Test
    public void testAddMultipleProductsToCart() {
        shoppingCart.addProduct(product1, 2);
        shoppingCart.addProduct(product2, 1);

        assertEquals(2, shoppingCart.getProductEntries().size());

        // Check the first product
        assertEquals(2, shoppingCart.getProductEntries().get(0).getQuantity());
        assertEquals(product1, shoppingCart.getProductEntries().get(0).getProduct());

        // Check the second product
        assertEquals(1, shoppingCart.getProductEntries().get(1).getQuantity());
        assertEquals(product2, shoppingCart.getProductEntries().get(1).getProduct());
    }

    @Test
    public void testRemoveProductFromCart() {
        shoppingCart.addProduct(product1, 3);
        shoppingCart.addProduct(product2, 1);

        // Remove product1 from the cart
        shoppingCart.removeProduct(product1);

        assertEquals(1, shoppingCart.getProductEntries().size());
        assertEquals(product2, shoppingCart.getProductEntries().get(0).getProduct());
    }

    @Test
    public void testRemoveNonExistingProductFromCart() {
        shoppingCart.addProduct(product1, 3);
        shoppingCart.addProduct(product2, 1);

        // Try to remove a product with a non-existing ID
        Product nonExistingProduct = new Product();
        nonExistingProduct.setId(3);
        nonExistingProduct.setName("Non-existing Product");
        nonExistingProduct.setPrice(20.0);
        shoppingCart.removeProduct(nonExistingProduct);

        assertEquals(2, shoppingCart.getProductEntries().size());
    }

    @Test
    public void testCalculateTotalPrice() {
        shoppingCart.addProduct(product1, 2);
        shoppingCart.addProduct(product2, 1);

        double totalPrice = shoppingCart.calculateTotalPrice();

        assertEquals(35.0, totalPrice, 0.01);
    }
}

