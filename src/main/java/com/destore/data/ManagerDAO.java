package com.destore.data;

import com.destore.model.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {

    // Implement logic to insert a new manager into the database
    public void addManager(Manager manager) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO managers (name, email) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, manager.getName());
                statement.setString(2, manager.getEmail());
                statement.executeUpdate();
                System.out.println("Manager added to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement logic to retrieve a manager by ID from the database
    public Manager getManagerById(int managerId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM managers WHERE manager_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, managerId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Manager manager = new Manager();
                        manager.setManagerId(resultSet.getInt("manager_id"));
                        manager.setName(resultSet.getString("name"));
                        manager.setEmail(resultSet.getString("email"));
                        return manager;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Implement logic to update a manager in the database
    public void updateManager(Manager manager) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE managers SET name = ?, email = ? WHERE manager_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, manager.getName());
                statement.setString(2, manager.getEmail());
                statement.setInt(3, manager.getManagerId());
                statement.executeUpdate();
                System.out.println("Manager updated in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement logic to delete a manager from the database
    public boolean deleteManager(int managerId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "DELETE FROM managers WHERE manager_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, managerId);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Manager getManagerByEmail(String email) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM managers WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Manager manager = new Manager();
                        manager.setManagerId(resultSet.getInt("manager_id"));
                        manager.setEmail(resultSet.getString("email"));
                        manager.setPassword(resultSet.getString("password"));
                        // Add other manager properties as needed
                        return manager;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPassword(String email) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT password FROM managers WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("password");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to retrieve managers by role
    public List<Manager> getManagersByRole(String role) {
        List<Manager> managers = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM managers WHERE role = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, role);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Manager manager = new Manager();
                        manager.setManagerId(resultSet.getInt("manager_id"));
                        manager.setName(resultSet.getString("name"));
                        manager.setEmail(resultSet.getString("email"));
                        // Add other manager properties as needed
                        managers.add(manager);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return managers;
    }

    public List<String> getEmailsByManagerId(int managerId) {

        List<String> emails = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection())  {
            String query = "SELECT email_message FROM email WHERE manager_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, managerId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String email = resultSet.getString("email_message");
                        emails.add(email);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }

        return emails;
    }
}

