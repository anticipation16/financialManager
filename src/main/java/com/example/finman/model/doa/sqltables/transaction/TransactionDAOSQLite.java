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
    public void addTransaction(Transaction transaction) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getRecentTransactions(int requiredNumber) {
        List<Transaction> transactions = new ArrayList<>(requiredNumber);
        String getSQL = load("/sql/queries/transaction/getRecentTransactions.sql");

        try (
                Connection con = getConnection(DB_URL);
                PreparedStatement stmt = con.prepareStatement(getSQL);
        ) {
            stmt.setInt(1, requiredNumber);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction current = new Transaction(
                        rs.getLong("transaction_number"),
                        rs.getLong("account_number"),
                        rs.getString("transaction_category"),
                        rs.getDouble("account_name"),
                        rs.getString("created_at"));
                transactions.add(current);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public long getNumberOfTransactions() {
        long count = 0;
        String getSQL = load("/sql/queries/transaction/getNumberOfTransactions.sql");
        try (
                Connection con = getConnection("jdbc:sqlite:finance.db");
                PreparedStatement stmt = con.prepareStatement(getSQL);
        ) {
            ResultSet rs = stmt.executeQuery();
            count = rs.getLong("num_of_txn");
        } catch (SQLException sqlException) {
        }
        return count;
    }
}
