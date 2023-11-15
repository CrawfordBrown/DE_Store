package com.destore.data;

import com.destore.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public Transaction getTransactionById(int transactionId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, transactionId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return extractTransactionFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM transactions";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    transactions.add(extractTransactionFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO transactions (customer_id, date, total_amount, status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, transaction.getCustomerId());
                statement.setDate(2, transaction.getTransactionDate());
                statement.setDouble(3, transaction.getTotalAmount());
                statement.setString(4, transaction.getStatus());
                statement.executeUpdate();
                System.out.println("Transaction added to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTransaction(Transaction transaction) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE transactions SET customer_id = ?, date = ?, total_amount = ?, status = ? WHERE transaction_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, transaction.getCustomerId());
                statement.setDate(2, transaction.getTransactionDate());
                statement.setDouble(3, transaction.getTotalAmount());
                statement.setString(4, transaction.getStatus());
                statement.setInt(5, transaction.getTransactionId());
                statement.executeUpdate();
                System.out.println("Transaction updated in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteTransaction(int transactionId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "DELETE FROM transactions WHERE transaction_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, transactionId);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Transaction extractTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(resultSet.getInt("transaction_id"));
        transaction.setCustomerId(resultSet.getInt("customer_id"));
        transaction.setTransactionDate(resultSet.getDate("date"));
        transaction.setTotalAmount(resultSet.getDouble("total_amount"));
        transaction.setStatus(resultSet.getString("status"));
        return transaction;
    }
}
