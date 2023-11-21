// com.destore.business.CustomerController.java
package com.destore.application;

import com.destore.business.iCustomerService;
import com.destore.model.Customer;

import java.util.List;

public class CustomerController implements iCustomerController {
    private final iCustomerService customerService;

    public CustomerController(iCustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return customerService.getCustomerById(customerId);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        return customerService.deleteCustomer(customerId);
    }
}
