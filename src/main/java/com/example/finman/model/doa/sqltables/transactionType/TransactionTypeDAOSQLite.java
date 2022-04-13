package com.example.finman.model.doa.sqltables.transactionType;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.finman.utility.QueryCache.load;
import static java.sql.DriverManager.getConnection;

public class TransactionTypeDAOSQLite implements TransactionTypeDAO {
    String DB_URL = "jdbc:sqlite:finance.db";

    @Override
    public void addTransactionType(TransactionType newType) throws SQLException {
        String sql = load("/sql/queries/transactionType/addTransactionType.sql");
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        try (
                Connection connection = getConnection(DB_URL, config.toProperties());
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, newType.transactionCategory().toLowerCase());
            statement.setString(2, newType.transactionType().toLowerCase());
            statement.executeUpdate();
        }
    }

    @Override
    public List<String> getAllTransactionTypes() {
        return List.of("income", "transfer", "expense");
    }

    @Override
    public List<String> getAllTransactionCategories() throws SQLException {
        String sql = load("/sql/queries/transactionType/getAllTransactionCategories.sql");
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        List<String> categories = new ArrayList<>();
        try (
                Connection connection = getConnection(DB_URL, config.toProperties());
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String category = rs.getString("transaction_category");
                categories.add(category);
            }
        }
        return categories;
    }
}
