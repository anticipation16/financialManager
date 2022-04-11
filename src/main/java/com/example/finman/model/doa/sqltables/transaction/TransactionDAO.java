package com.example.finman.model.doa.sqltables.transaction;

import java.util.List;

public interface TransactionDAO {
    void addTransaction(Transaction transaction);
    List<Transaction> getRecentTransactions(int requiredNumber);
    long getNumberOfTransactions();
}
