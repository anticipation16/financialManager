package com.example.finman.model.doa.sqltables.accountType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class AccountTypeDAOSQLite implements AccountTypeDAO {
    private final String DB_URL = "jdbc:sqlite:finance.db";

    @Override
    public void addAccountType(AccountType accountType) {
        String sql = "insert into account_type (account_name, account_type) VALUES(?,?)";
        try (
                Connection connection = getConnection(DB_URL);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, accountType.accountName().toLowerCase());
            statement.setString(2, accountType.accountType().toLowerCase());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAllAccountNames() {
        String sql = "select account_name from account_type";
        return getStrings(sql, "account_name");
    }

    @Override
    public List<String> getAllAccountTypes() {
        return List.of("asset", "liability", "investment");
    }

    private List<String> getStrings(String sql, String column_name) {
        List<String> resultList = new ArrayList<>();
        try (
                Connection connection = getConnection(DB_URL);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultList.add(resultSet.getString(column_name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
