package com.example.finman.model.doa.sqlviews.accountWithType;

import java.sql.SQLException;
import java.util.List;

public interface AccountWithTypeDAO {
    List<AccountWithType> getAllAccountsWithType() throws SQLException;

    List<AccountWithType> getAllAccountsOfSpecificType(String type) throws SQLException;
}
