package com.destore.data;

import com.destore.model.Manager;
import com.destore.model.Email;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailDAO {
    public List<Email> getEmailsByManagerId(int managerId) {
        List<Email> emails = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM email WHERE manager_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, managerId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Email email = new Email();
                        email.setEmail_id(resultSet.getInt("email_id"));
                        email.setManager_id(resultSet.getInt("manager_id"));
                        email.setEmail_Address(resultSet.getString("email_address"));
                        email.setEmail_Address(resultSet.getString("email_message"));
                        emails.add(email);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emails;
    }

    public void addEmail(Email email) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO email (manager_id, email_address, email_message) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, email.getManager_id());
                statement.setString(2, email.getEmail_Address());
                statement.setString(3, email.getEmail_message());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getManagerIdByEmail(String email) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT manager_id FROM managers WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("manager_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 or any value indicating failure
    }

}
