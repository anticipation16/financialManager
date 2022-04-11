package com.example.finman.model.doa.sqltables.transactionType;

import java.util.List;

public interface TransactionTypeDAO {
    void addTransactionType(TransactionType newType);

    List<String> getAllTransactionTypes();

    List<String> getAllTransactionCategories();
}
