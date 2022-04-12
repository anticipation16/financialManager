package com.example.finman.model.doa.sqltables.account;

import java.util.List;
public interface AccountDAO {
    void addAccount(Account a);
    List<Account> getAllAccounts();
    List<Account> getAccountsOfType(String type);
    long getBalanceByType(String type);
}
