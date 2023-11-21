// com.destore.business.iCustomerService.java
package com.destore.business;

import com.destore.model.Customer;

import java.util.List;

public interface iCustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(int customerId);

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    boolean deleteCustomer(int customerId);
}
