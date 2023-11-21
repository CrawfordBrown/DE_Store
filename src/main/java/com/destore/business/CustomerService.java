package com.destore.business;

import com.destore.data.CustomerDAO;
import com.destore.model.Customer;

import java.util.List;

public class CustomerService implements iCustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    public Customer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    public boolean deleteCustomer(int customerId) {
        return customerDAO.deleteCustomer(customerId);
    }
}
