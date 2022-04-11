package com.example.finman.model.doa.sqltables.accountType;

import java.util.List;

public interface AccountTypeDAO {
    void addAccountType(AccountType accountType);
    List<String> getAllAccountNames();
    List<String> getAllAccountTypes();
}
