package com.destore.test.dataTests;

import com.destore.data.LoyaltyCardDAO;
import com.destore.model.LoyaltyCard;

import java.sql.Date;
import java.util.List;

public class LoyaltyCardDAOTest {

    public static void main(String[] args) {
        testGetLoyaltyCardById();
        testGetAllLoyaltyCards();
        testAddLoyaltyCard();
        testUpdateLoyaltyCard();
//        testDeleteLoyaltyCard();
    }

    private static void testGetLoyaltyCardById() {
        LoyaltyCardDAO loyaltyCardDAO = new LoyaltyCardDAO();
        int customerId = 1; // Replace with a valid customer ID

        LoyaltyCard loyaltyCard = loyaltyCardDAO.getLoyaltyCardById(customerId);

        if (loyaltyCard != null) {
            System.out.println("Retrieved Loyalty Card:");
            System.out.println("Customer ID: " + loyaltyCard.getCustomerId());
            System.out.println("Points: " + loyaltyCard.getPoints());
            System.out.println();
        } else {
            System.out.println("Loyalty Card not found.");
        }
    }

    private static void testGetAllLoyaltyCards() {
        LoyaltyCardDAO loyaltyCardDAO = new LoyaltyCardDAO();

        List<LoyaltyCard> loyaltyCards = loyaltyCardDAO.getAllLoyaltyCards();

        System.out.println("All Loyalty Cards:");
        for (LoyaltyCard loyaltyCard : loyaltyCards) {
            System.out.println("Customer ID: " + loyaltyCard.getCustomerId());
            System.out.println("Points: " + loyaltyCard.getPoints());
            System.out.println();
        }
    }

    private static void testAddLoyaltyCard() {
        LoyaltyCardDAO loyaltyCardDAO = new LoyaltyCardDAO();
        LoyaltyCard newLoyaltyCard = new LoyaltyCard();
        newLoyaltyCard.setCustomerId(2);
        newLoyaltyCard.setPoints(100);

        loyaltyCardDAO.addLoyaltyCard(newLoyaltyCard);
    }

    private static void testUpdateLoyaltyCard() {
        LoyaltyCardDAO loyaltyCardDAO = new LoyaltyCardDAO();
        int customerIdToUpdate = 3;
        LoyaltyCard updatedLoyaltyCard = new LoyaltyCard();
        updatedLoyaltyCard.setCustomerId(customerIdToUpdate);
        updatedLoyaltyCard.setPoints(120);

        loyaltyCardDAO.updateLoyaltyCard(updatedLoyaltyCard);
    }

//    private static void testDeleteLoyaltyCard() {
//        LoyaltyCardDAO loyaltyCardDAO = new LoyaltyCardDAO();
//        int customerIdToDelete = 4; // Replace with a valid customer ID
//
//        loyaltyCardDAO.deleteLoyaltyCard(customerIdToDelete);
//    }
}
