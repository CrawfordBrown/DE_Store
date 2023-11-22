package com.destore.application;

import com.destore.model.Transaction;

import java.util.List;

public interface iTransactionController {
    Transaction getTransactionById(int transactionId);

    List<Transaction> getAllTransactions();

    void addTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);

    boolean deleteTransaction(int transactionId);
}
