package com.destore.test;

import com.destore.data.CustomerDAO;
import com.destore.data.ProductDAO;
import com.destore.model.Customer;
import com.destore.model.Product;


public class CustomerDAOTest {
    public static void main(String[] args) {
        testGetCustomerById();
        testAddCustomer();
        testUpdateCustomer();
//        testDeleteCustomer();
    }

    private static void testGetCustomerById() {
        CustomerDAO customerDAO = new CustomerDAO();
        int customerId = 1; // Replace with a valid customer ID from your database
        Customer customer = customerDAO.getCustomerById(customerId);

        if (customer != null) {
            System.out.println("Retrieved Customer:");
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Customer Name: " + customer.getName());
            System.out.println();  // Add other customer attributes
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void testAddCustomer() {
        CustomerDAO customerDAO = new CustomerDAO();
        Customer newCustomer = new Customer();
        newCustomer.setName("John Smith");
        // Set other attributes as needed

        customerDAO.addCustomer(newCustomer);
    }

    private static void testUpdateCustomer() {
        CustomerDAO customerDAO = new CustomerDAO();
        int customerIdToUpdate = 2;  // Assuming a valid customer ID
        Customer updatedCustomer = new Customer();
        updatedCustomer.setCustomerId(customerIdToUpdate);
        updatedCustomer.setName("Updated Customer");
        // Set other attributes as needed

        customerDAO.updateCustomer(updatedCustomer);
    }

//    private static void testDeleteCustomer() {
//        CustomerDAO customerDAO = new CustomerDAO();
//        int customerIdToDelete = 1;  // Assuming a valid customer ID
//
//        customerDAO.deleteCustomer(customerIdToDelete);
//    }
}

