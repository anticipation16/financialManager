package com.example.finman.model.doa.sqltables.transaction;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.finman.utility.QueryCache.load;
import static java.sql.DriverManager.getConnection;


public class TransactionDAOSQLite implements TransactionDAO {
    private final String DB_URL = "jdbc:sqlite:finance.db";

    @Override
    public void addTransaction(Transaction transaction) throws SQLException {
        String insertionSQL = load("/sql/queries/transaction/addTransaction.sql");
        String updateAccountSQL = load("/sql/queries/account/updateAccount.sql");
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        try (
                Connection connection = getConnection(DB_URL, config.toProperties());
                PreparedStatement statement = connection.prepareStatement(insertionSQL);
                PreparedStatement updateAccountStmt = connection.prepareStatement(updateAccountSQL);
        ) {
            statement.setLong(1, transaction.accountNumber());
            statement.setDouble(2, transaction.amount());
            statement.setString(3, transaction.createdAt());
            statement.setString(4, transaction.category().toLowerCase());
            updateAccountStmt.setDouble(1, transaction.amount());
            updateAccountStmt.setLong(2, transaction.accountNumber());
            statement.executeUpdate();
            updateAccountStmt.executeUpdate();
        }
    }
}
