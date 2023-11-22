package com.destore.business;


import com.destore.data.ProductDAO;
import com.destore.data.TransactionDAO;
import com.destore.model.ShoppingCart;
import com.destore.model.Transaction;

import java.util.List;


public class TransactionService implements iTransactionService {

    private final TransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }


    @Override
    public Transaction getTransactionById(int transactionId) {
        return transactionDAO.getTransactionById(transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactionDAO.addTransaction(transaction);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        transactionDAO.updateTransaction(transaction);
    }

    @Override
    public boolean deleteTransaction(int transactionId) {
        return transactionDAO.deleteTransaction(transactionId);
    }
}