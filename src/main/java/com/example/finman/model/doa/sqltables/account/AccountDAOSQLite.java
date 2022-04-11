package com.example.finman.model.doa.sqltables.account;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;


public class AccountDAOSQLite implements AccountDAO {
    private final String DB_URL = "jdbc:sqlite:finance.db";

    @Override
    public void addAccount(Account a) {
        String sql = "insert into account(account_number, balance, institution, account_name) values(?,?,?,?)";
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        try (
                Connection connection = getConnection(DB_URL, config.toProperties());
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, a.accountNumber());
            statement.setDouble(2, a.balance());
            statement.setString(3, a.institution().toLowerCase());
            statement.setString(4, a.accountName().toLowerCase());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        String getSQL = "select * from account order by account_name";
        try (
                Connection con = getConnection(DB_URL);
                PreparedStatement stmt = con.prepareStatement(getSQL);
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Account current = new Account(rs.getLong("account_number"),
                        rs.getDouble("balance"),
                        rs.getString("institution"),
                        rs.getString("account_name"));
                accounts.add(current);
            }
        } catch (SQLException sqlException) {
        }
        return accounts;
    }

    @Override
    public List<Account> getAccountsOfType(String type) {
        List<Account> accounts = new ArrayList<>();
        String getSQL =
                "select * from account INNER JOIN account_type ON account.account_name=account_type.account_name " +
                        "where account_type=?";
        try (
                Connection con = getConnection("jdbc:sqlite:finance.db");
                PreparedStatement stmt = con.prepareStatement(getSQL);
        ) {
            stmt.setString(1, type.toLowerCase());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Account current = new Account(rs.getLong("account_number"),
                        rs.getDouble("balance"),
                        rs.getString("institution"),
                        rs.getString("account_name"));
                accounts.add(current);
            }
        } catch (SQLException sqlException) {
        }
        return accounts;
    }

    @Override
    public long getBalanceByType(String type) {
        List<Account> accountsOfType = getAccountsOfType(type);
        long sum = 0;
        for (Account a : accountsOfType) {
            sum += a.balance();
        }
        return sum;
    }


}