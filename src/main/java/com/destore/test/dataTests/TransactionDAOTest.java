package com.destore.test.dataTests;

import com.destore.data.TransactionDAO;
import com.destore.model.Transaction;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class TransactionDAOTest {

    public static void main(String[] args) {
        testGetTransactionById();
        testGetAllTransactions();
        testAddTransaction();
        testUpdateTransaction();
        testDeleteTransaction();
    }

    private static void testGetTransactionById() {
        TransactionDAO transactionDAO = new TransactionDAO();
        int transactionId = 1;
        Transaction transaction = transactionDAO.getTransactionById(transactionId);

        if (transaction != null) {
            System.out.println("Retrieved Transaction:");
            System.out.println("Transaction ID: " + transaction.getTransactionId());
            System.out.println("Customer ID: " + transaction.getCustomerId());
            System.out.println("Date: " + transaction.getTransactionDate());
            System.out.println("Total Amount: " + transaction.getTotalAmount());
            System.out.println("Status: " + transaction.getStatus());
            System.out.println();
        } else {
            System.out.println("Transaction not found.");
        }
    }

    private static void testGetAllTransactions() {
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transaction> transactions = transactionDAO.getAllTransactions();

        if (!transactions.isEmpty()) {
            System.out.println("Retrieved All Transactions:");
            for (Transaction transaction : transactions) {
                System.out.println("Transaction ID: " + transaction.getTransactionId());
                System.out.println("Customer ID: " + transaction.getCustomerId());
                System.out.println("Date: " + transaction.getTransactionDate());
                System.out.println("Total Amount: " + transaction.getTotalAmount());
                System.out.println("Status: " + transaction.getStatus());
                System.out.println();
            }
        } else {
            System.out.println("No transactions found.");
        }
    }

    private static void testAddTransaction() {
        TransactionDAO transactionDAO = new TransactionDAO();
        Transaction newTransaction = new Transaction();
        newTransaction.setCustomerId(1);
        newTransaction.setTransactionDate(Date.valueOf("2023-11-15"));
        newTransaction.setTotalAmount(100.00);
        newTransaction.setStatus("Completed");

        transactionDAO.addTransaction(newTransaction);
    }

    private static void testUpdateTransaction() {
        TransactionDAO transactionDAO = new TransactionDAO();
        int transactionIdToUpdate = 2;  // Assuming a valid transaction ID
        Transaction updatedTransaction = new Transaction();
        updatedTransaction.setTransactionId(transactionIdToUpdate);
        updatedTransaction.setCustomerId(2);
        updatedTransaction.setTransactionDate(Date.valueOf("2023-11-16"));
        updatedTransaction.setTotalAmount(150.00);
        updatedTransaction.setStatus("Shipped");

        transactionDAO.updateTransaction(updatedTransaction);
    }

    private static void testDeleteTransaction() {
        TransactionDAO transactionDAO = new TransactionDAO();
        int transactionIdToDelete = 3;  // Assuming a valid transaction ID

        transactionDAO.deleteTransaction(transactionIdToDelete);
    }
}
