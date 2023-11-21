// com.destore.business.iCustomerController.java
package com.destore.application;

import com.destore.model.Customer;

import java.util.List;

public interface iCustomerController {
    List<Customer> getAllCustomers();

    Customer getCustomerById(int customerId);

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    boolean deleteCustomer(int customerId);
}
