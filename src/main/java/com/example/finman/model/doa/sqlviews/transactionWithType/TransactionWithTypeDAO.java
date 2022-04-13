package com.example.finman.model.doa.sqlviews.transactionWithType;

import java.sql.SQLException;
import java.util.List;

public interface TransactionWithTypeDAO {
    List<TransactionWithType> getRecentTransactionsWithType(int requiredNumber) throws SQLException;

    List<TransactionWithType> getTransactionsFor(long accountNumber, int requiredNumber) throws SQLException;

    List<TransactionWithType> getTopExpensesForThisMonth(int requiredNumber) throws SQLException;

    List<TransactionWithType> getAllTransactionsWithType() throws SQLException;

    List<TransactionWithType> getExpensesForThisMonth() throws SQLException;

    List<TransactionWithType> getExpensesForToday() throws SQLException;

    List<TransactionWithType> getExpensesForThisYear() throws SQLException;

    List<TransactionWithType> getExpensesForThisWeek() throws SQLException;

    List<GroupedTransaction> getTopExpenditureTransactionGroupedForThisMonth() throws SQLException;
}
