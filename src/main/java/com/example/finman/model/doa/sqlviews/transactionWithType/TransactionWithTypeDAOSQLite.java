package com.example.finman.model.doa.sqlviews.transactionWithType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class TransactionWithTypeDAOSQLite implements TransactionWithTypeDAO {
    private final String DB_URL = "jdbc:sqlite:finance.db";

    @Override
    public List<TransactionWithType> getRecentTransactionsWithType(int requiredNumber) {
        List<TransactionWithType> list = new ArrayList<>();

        String sql = "select * from vw_transactions_with_type order by created_at limit ?";
        try (Connection con = getConnection(DB_URL);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, requiredNumber);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                list.add(transactionWithTypeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<TransactionWithType> getTransactionsFor(long accountNumber, int requiredNumber) {
        String sql = "select * from vw_transactions_with_type where account_number = ?" +
                " order by created_at limit ?";
        List<TransactionWithType> transactionForAccount = new ArrayList<>();

        try (
                Connection con = getConnection(DB_URL);
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setLong(1, accountNumber);
            statement.setInt(2, requiredNumber);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transactionForAccount.add(transactionWithTypeFromResultSet(resultSet));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactionForAccount;
    }


    private TransactionWithType transactionWithTypeFromResultSet(final ResultSet resultSet) throws SQLException {
        return new TransactionWithType(
                resultSet.getLong("transaction_number"),
                resultSet.getLong("account_number"),
                resultSet.getString("transaction_category"),
                resultSet.getDouble("amount"),
                resultSet.getString("created_at"),
                resultSet.getString("type"));
    }
}
