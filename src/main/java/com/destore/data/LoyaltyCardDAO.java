package com.destore.data;

import com.destore.model.LoyaltyCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoyaltyCardDAO {

    public LoyaltyCard getLoyaltyCardById(int loyaltyCardId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM loyalty_cards WHERE loyalty_card_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, loyaltyCardId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        LoyaltyCard loyaltyCard = new LoyaltyCard();
                        loyaltyCard.setCustomerId(resultSet.getInt("customer_id"));
                        loyaltyCard.setPoints(resultSet.getInt("points"));
                        return loyaltyCard;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LoyaltyCard> getAllLoyaltyCards() {
        List<LoyaltyCard> loyaltyCards = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM loyalty_cards";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        LoyaltyCard loyaltyCard = new LoyaltyCard();
                        loyaltyCard.setCustomerId(resultSet.getInt("customer_id"));
                        loyaltyCard.setPoints(resultSet.getInt("points"));
                        loyaltyCards.add(loyaltyCard);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loyaltyCards;
    }

    public void addLoyaltyCard(LoyaltyCard loyaltyCard) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO loyalty_cards (customer_id, points) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, loyaltyCard.getCustomerId());
                statement.setInt(2, loyaltyCard.getPoints());
                statement.executeUpdate();
                System.out.println("Loyalty Card added to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLoyaltyCard(LoyaltyCard loyaltyCard) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE loyalty_cards SET points = ? WHERE customer_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, loyaltyCard.getPoints());
                statement.setInt(2, loyaltyCard.getCustomerId());
                statement.executeUpdate();
                System.out.println("Loyalty Card updated in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteLoyaltyCard(int customerId) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "DELETE FROM loyalty_cards WHERE customer_id = ?";
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
}
