package com.destore;

import com.destore.data.ProductDAO;
import com.destore.model.Product;

public class ProductDAOTest {


    public static void main(String[] args) {
        testGetProductById();
        testAddProduct();
        testUpdateProduct();
        //testDeleteProduct();
    }

    private static void testGetProductById() {
        ProductDAO productDAO = new ProductDAO();
        int productId = 1;
        Product product = productDAO.getProductById(productId);

        if (product != null) {
            System.out.println("Retrieved Product:");
            System.out.println("Product ID: " + product.getId());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Product Price: " + product.getPrice());
            System.out.println();
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void testAddProduct() {
        ProductDAO productDAO = new ProductDAO();
        Product newProduct = new Product();
        newProduct.setName("New Product");
        newProduct.setPrice(19.99);

        productDAO.addProduct(newProduct);
    }

    private static void testUpdateProduct() {
        ProductDAO productDAO = new ProductDAO();
        int productIdToUpdate = 2;  // Assuming a valid product ID
        Product updatedProduct = new Product();
        updatedProduct.setId(productIdToUpdate);
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(29.99);

        productDAO.updateProduct(updatedProduct);
    }

//    private static void testDeleteProduct() {
//        ProductDAO productDAO = new ProductDAO();
//        int productIdToDelete = 3;  // Assuming a valid product ID
//
//        productDAO.deleteProduct(productIdToDelete);
//    }
}
