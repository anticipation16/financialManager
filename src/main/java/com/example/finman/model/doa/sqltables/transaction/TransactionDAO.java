package com.example.finman.model.doa.sqltables.transaction;

import java.sql.SQLException;
import java.util.List;

public interface TransactionDAO {
    void addTransaction(Transaction transaction) throws SQLException;
}
