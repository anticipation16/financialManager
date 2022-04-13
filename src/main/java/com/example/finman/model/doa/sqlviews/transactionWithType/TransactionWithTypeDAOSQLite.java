package com.example.finman.model.doa.sqlviews.transactionWithType;

import com.example.finman.utility.DateTimeParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.example.finman.utility.QueryCache.load;
import static java.sql.DriverManager.getConnection;

public class TransactionWithTypeDAOSQLite implements TransactionWithTypeDAO {
    private final String DB_URL = "jdbc:sqlite:finance.db";

    @Override
    public List<TransactionWithType> getRecentTransactionsWithType(int requiredNumber) {
        List<TransactionWithType> list = new ArrayList<>();
        String sql = load("/sql/queries/transactionWithType/getRecentTransactionsWithType.sql");
        return getTransactionWithTypesListFromSQL(requiredNumber, sql, list);
    }

    @Override
    public List<TransactionWithType> getTransactionsFor(long accountNumber, int requiredNumber) {
        String sql = load("/sql/queries/transactionWithType/getTransactionsForAccount.sql");
        List<TransactionWithType> transactionForAccount = new ArrayList<>();

        try (
                Connection con = getConnection(DB_URL);
                PreparedStatement statement = con.prepareStatement(sql);
        ) {
            statement.setLong(1, accountNumber);
            statement.setInt(2, requiredNumber);

            ResultSet resultSet = statement.executeQuery();
            transactionForAccount = transactionWithTypeListFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactionForAccount;
    }

    @Override
    public List<TransactionWithType> getTopExpensesForThisMonth(int requiredNumber) {
        String isoDate = Instant.now().toString();
        String storedDate = DateTimeParser.getDateTimeString(isoDate);
        String month = storedDate.substring(5, 7);
        String year = storedDate.substring(0, 4);
        String sql = load("/sql/queries/transactionWithType/getTopExpensesThisMonth.sql");

        List<TransactionWithType> listOfTopExpensesThisMonth = new ArrayList<>();
        return getTransactionWithTypesListFromSQL(requiredNumber, sql, listOfTopExpensesThisMonth);
    }

    @Override
    public List<TransactionWithType> getAllTransactionsWithType() {
        String sql = "select * from vw_transactions_with_type order by transaction_number DESC";
        return getTransactionWithTypesListFromSQL(sql);
    }

    @Override
    public List<TransactionWithType> getExpensesForThisMonth() {
        String sql = load("/sql/queries/transactionWithType/getExpensesForThisMonth.sql");
        return getTransactionWithTypesListFromSQL(sql);
    }

    @Override
    public List<TransactionWithType> getExpensesForToday() {
        String sql = load("/sql/queries/transactionWithType/getExpensesForToday.sql");
        return getTransactionWithTypesListFromSQL(sql);
    }

    @Override
    public List<TransactionWithType> getExpensesForThisYear() {
        String sql = load("/sql/queries/transactionWithType/getExpensesForThisYear.sql");
        return getTransactionWithTypesListFromSQL(sql);
    }

    @Override
    public List<TransactionWithType> getExpensesForThisWeek() {
        String sql = load("/sql/queries/transactionWithType/getExpensesForThisWeek.sql");
        return getTransactionWithTypesListFromSQL(sql);
    }

    private List<TransactionWithType> getTransactionWithTypesListFromSQL(int requiredNumber,
                                                                         String sql,
                                                                         List<TransactionWithType>
                                                                                 listOfTopExpensesThisMonth)    {
        try (
                Connection connection = getConnection(DB_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, requiredNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            listOfTopExpensesThisMonth = transactionWithTypeListFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfTopExpensesThisMonth;
    }

    private List<TransactionWithType> getTransactionWithTypesListFromSQL(String sql) {
        List<TransactionWithType> list = new ArrayList<>();
        try (
                Connection connection = getConnection(DB_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            list = transactionWithTypeListFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }


    private List<TransactionWithType> transactionWithTypeListFromResultSet(final ResultSet resultSet)
            throws SQLException {
        List<TransactionWithType> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new TransactionWithType(
                    resultSet.getLong("transaction_number"),
                    resultSet.getLong("account_number"),
                    resultSet.getString("transaction_category"),
                    resultSet.getDouble("amount"),
                    resultSet.getString("created_at"),
                    resultSet.getString("type")));
        }
        return list;
    }
}
