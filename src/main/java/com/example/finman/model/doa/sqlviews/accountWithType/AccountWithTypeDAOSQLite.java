package com.example.finman.model.doa.sqlviews.accountWithType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class AccountWithTypeDAOSQLite implements AccountWithTypeDAO {
    @Override
    public List<AccountWithType> getAllAccountsWithType() {
        List<AccountWithType> list = new ArrayList<>();
        String DB_URL = "jdbc:sqlite:finance.db";
        String sql = "select * from vw_accounts_with_type";
        try (Connection con = getConnection(DB_URL);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                list.add(new AccountWithType(resultSet.getLong("account_number"),
                        resultSet.getDouble("balance"),
                        resultSet.getString("institution"),
                        resultSet.getString("account_name"),
                        resultSet.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
