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
        AccountType at2 = new AccountType("Current Account", "asset");
        AccountType at3 = new AccountType("Investment Account", "investment");
        AccountType at4 = new AccountType("Credit Card Account", "liability");

        Account a1 = new Account(1231, 1000, "HDFC", "Savings Account");
        Account a2 = new Account(1232, 2000, "HDFC", "Current Account");
        Account a3 = new Account(1233, 5000, "Axis Bank", "Investment Account");
        Account a4 = new Account(1234, 1500, "Indian Bank", "Credit Card Account");

        TransactionType t1 = new TransactionType("Gas", "expense");
        TransactionType t2 = new TransactionType("Salary", "income");
        TransactionType t3 = new TransactionType("Food", "expense");
        TransactionType t4 = new TransactionType("Transport", "expense");

        Transaction txn1 = new Transaction(-1, 1231, "Gas",
                -200, DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn2 = new Transaction(-1, 1232, "Salary", 10000,
                DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn3 = new Transaction(-1, 1232, "Food", -100,
                DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn4 = new Transaction(-1, 1233, "Transport", -900,
                DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn5 = new Transaction(-1, 1234, "Transport", -500,
                DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn6 = new Transaction(-1, 1231, "Salary", 12000,
                DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn7 = new Transaction(-1, 1231, "Food", -700,
                DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn8 = new Transaction(-1, 1233, "Salary", 9000,
                DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn9 = new Transaction(-1, 1234, "Salary", 11000,
                DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn10 = new Transaction(-1, 1233, "Food", -400,
                DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn11 = new Transaction(-1, 1231, "Transport", -300,
                DateTimeParser.getDateTimeString(Instant.now().toString()));
        Transaction txn12 = new Transaction(-1, 1232, "Gas", -1000,
                DateTimeParser.getDateTimeString(Instant.now().toString()));


        AccountTypeDAO atDao = new AccountTypeDAOSQLite();
        AccountDAO aDao = new AccountDAOSQLite();
        TransactionDAO tDAO = new TransactionDAOSQLite();
        TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAOSQLite();
//
//        atDao.addAccountType(at1);
//        atDao.addAccountType(at2);
//        atDao.addAccountType(at3);
//        atDao.addAccountType(at4);
//        aDao.addAccount(a1);
//        aDao.addAccount(a2);
//        aDao.addAccount(a3);
//        aDao.addAccount(a4);
        transactionTypeDAO.addTransactionType(t1);
        transactionTypeDAO.addTransactionType(t2);
        transactionTypeDAO.addTransactionType(t3);
        transactionTypeDAO.addTransactionType(t4);
        tDAO.addTransaction(txn1);
        tDAO.addTransaction(txn2);
        tDAO.addTransaction(txn3);
        tDAO.addTransaction(txn4);
        tDAO.addTransaction(txn5);
        tDAO.addTransaction(txn6);
        tDAO.addTransaction(txn7);
        tDAO.addTransaction(txn8);
        tDAO.addTransaction(txn9);
        tDAO.addTransaction(txn10);
        tDAO.addTransaction(txn11);
        tDAO.addTransaction(txn12);
    }

    private void printWithType() {
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
        System.out.println(DateTimeParser.getDateTimeString(Instant.now().toString()));
    }
}
