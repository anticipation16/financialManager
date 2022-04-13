package com.example.finman.model.doa.sqltables.transactionType;

import java.sql.SQLException;
import java.util.List;

public interface TransactionTypeDAO {
    void addTransactionType(TransactionType newType) throws SQLException;

    List<String> getAllTransactionTypes() throws SQLException;

    List<String> getAllTransactionCategories() throws SQLException;
}
