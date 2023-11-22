package com.destore.application;


import com.destore.business.LoyaltyCardService;
import com.destore.data.LoyaltyCardDAO;
import com.destore.data.TransactionDAO;
import com.destore.model.Transaction;
import com.destore.business.TransactionService;
import com.destore.business.iTransactionService;

import java.util.List;

public class TransactionController implements iTransactionController {

    private final iTransactionService transactionService;


    public TransactionController(TransactionDAO transactionDAO) {
        this.transactionService = new TransactionService(transactionDAO);
    }

    public Transaction getTransactionById(int transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactionService.addTransaction(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        transactionService.updateTransaction(transaction);
    }

    public boolean deleteTransaction(int transactionId) {
        return transactionService.deleteTransaction(transactionId);
    }
}
