package com.example.finman.model.doa.sqlviews.transactionWithType;

import java.util.List;

public interface TransactionWithTypeDAO {
    List<TransactionWithType> getRecentTransactionsWithType(int requiredNumber);
    List<TransactionWithType> getTransactionsFor(long accountNumber, int requiredNumber);
    List<TransactionWithType> getTopExpensesForThisMonth(int requiredNumber);
    List<TransactionWithType> getAllTransactionsWithType();
}
