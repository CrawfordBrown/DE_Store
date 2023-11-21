package com.destore.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.destore.model.Customer;

public class CustomerDAO {
    public void addCustomer(Customer customer) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO customers (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, customer.getName());
                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    // Retrieve the auto-generated customer ID
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int customerId = generatedKeys.getInt(1);
                            customer.setCustomerId(customerId);
                            System.out.println("Customer added to the database with ID: " + customerId);
                        }
                    }
                } else {
                    System.out.println("Failed to add customer to the database.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Customer getCustomerById(int customerId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM customers WHERE customer_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, customerId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setCustomerId(resultSet.getInt("customer_id"));
                        customer.setName(resultSet.getString("name"));
                        // Set other attributes
                        return customer;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCustomer(Customer customer) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE customers SET name = ? WHERE customer_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, customer.getName());
                statement.setInt(2, customer.getCustomerId());
                statement.executeUpdate();
                System.out.println("Customer updated in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteCustomer(int customerId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "DELETE FROM customers WHERE customer_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, customerId);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM customers";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setCustomerId(resultSet.getInt("customer_id"));
                        customer.setName(resultSet.getString("name"));
                        // Set other attributes
                        customers.add(customer);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}

