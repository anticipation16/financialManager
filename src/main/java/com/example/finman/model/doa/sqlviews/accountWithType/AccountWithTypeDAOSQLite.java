package com.example.finman.model.doa.sqlviews.accountWithType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.finman.utility.QueryCache.load;
import static java.sql.DriverManager.getConnection;

public class AccountWithTypeDAOSQLite implements AccountWithTypeDAO {
    @Override
    public List<AccountWithType> getAllAccountsWithType() {
        List<AccountWithType> list = new ArrayList<>();
        String DB_URL = "jdbc:sqlite:finance.db";
        String sql = load("/sql/queries/accountWithType/getAllAccountsWithType.sql");
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

    public List<AccountWithType> getAllAccountsOfSpecificType(String type) {
        List<AccountWithType> accounts = new ArrayList<>();
        String getSQL = "select * from vw_accounts_with_type where type=?";
        try (
                Connection con = getConnection("jdbc:sqlite:finance.db");
                PreparedStatement stmt = con.prepareStatement(getSQL);
        ) {
            stmt.setString(1, type.toLowerCase());
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                AccountWithType current = new AccountWithType(resultSet.getLong("account_number"),
                        resultSet.getDouble("balance"),
                        resultSet.getString("institution"),
                        resultSet.getString("account_name"),
                        resultSet.getString("type"));
                accounts.add(current);
            }
        } catch (SQLException sqlException) {
        }
        return accounts;
    }
}
