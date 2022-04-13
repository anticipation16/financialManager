package com.example.finman.model.doa.sqltables.accountType;

import java.sql.SQLException;
import java.util.List;

public interface AccountTypeDAO {
    void addAccountType(AccountType accountType) throws SQLException;
    List<String> getAllAccountNames() throws SQLException;
    List<String> getAllAccountTypes() throws SQLException;
}
