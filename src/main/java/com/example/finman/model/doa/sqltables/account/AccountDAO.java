package com.example.finman.model.doa.sqltables.account;

import java.sql.SQLException;
import java.util.List;
public interface AccountDAO {
    void addAccount(Account a) throws SQLException;
    List<Account> getAllAccounts() throws SQLException;
    List<Account> getAccountsOfType(String type) throws SQLException;
    long getBalanceByType(String type) throws SQLException;
}
