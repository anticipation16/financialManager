package com.example.finman.model;

import com.example.finman.model.doa.sqltables.account.Account;
import com.example.finman.model.doa.sqltables.account.AccountDAO;
import com.example.finman.model.doa.sqltables.account.AccountDAOSQLite;
import com.example.finman.model.doa.sqltables.accountType.AccountType;
import com.example.finman.model.doa.sqltables.accountType.AccountTypeDAO;
import com.example.finman.model.doa.sqltables.accountType.AccountTypeDAOSQLite;
import com.example.finman.model.doa.sqltables.transaction.Transaction;
import com.example.finman.model.doa.sqltables.transaction.TransactionDAO;
import com.example.finman.model.doa.sqltables.transaction.TransactionDAOSQLite;
import com.example.finman.model.doa.sqltables.transactionType.TransactionType;
import com.example.finman.model.doa.sqltables.transactionType.TransactionTypeDAO;
import com.example.finman.model.doa.sqltables.transactionType.TransactionTypeDAOSQLite;
import com.example.finman.model.doa.sqlviews.accountWithType.AccountWithType;
import com.example.finman.model.doa.sqlviews.accountWithType.AccountWithTypeDAO;
import com.example.finman.model.doa.sqlviews.accountWithType.AccountWithTypeDAOSQLite;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithType;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAO;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAOSQLite;
import com.example.finman.utility.DateTimeParser;

import java.time.Instant;

public class Driver {
    private static void init() {
        AccountType at1 = new AccountType("Savings Account", "asset");
        Account a1 = new Account(12344, 1000, "HDFC", "Savings Account");

        TransactionType t1 = new TransactionType("Gas", "expense");
        TransactionType t2 = new TransactionType("Salary", "income");
        Transaction txn1 = new Transaction(-1, 12344, "Gas",
                -200, DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn2 = new Transaction(-1, 12344, "Salary", 100,
                DateTimeParser.getDateTimeString(Instant.now().toString()));


        AccountTypeDAO atDao = new AccountTypeDAOSQLite();
        AccountDAO aDao = new AccountDAOSQLite();
        TransactionDAO tDAO = new TransactionDAOSQLite();
        TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAOSQLite();

        atDao.addAccountType(at1);
        aDao.addAccount(a1);
        transactionTypeDAO.addTransactionType(t1);
        transactionTypeDAO.addTransactionType(t2);
        tDAO.addTransaction(txn1);
        tDAO.addTransaction(txn2);

    }

    private void printWithType(){
        AccountWithTypeDAO accountWithTypeDAO = new AccountWithTypeDAOSQLite();
        for (AccountWithType accountWithType : accountWithTypeDAO.getAllAccountsWithType()) {
            System.out.println(accountWithType);
        }

        TransactionWithTypeDAO transactionWithTypeDAO = new TransactionWithTypeDAOSQLite();
        for (TransactionWithType transactionWithType :
                transactionWithTypeDAO.getRecentTransactionsWithType(1)) {
            System.out.println(transactionWithType);
        }

    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("javafx.version"));
    }
}
