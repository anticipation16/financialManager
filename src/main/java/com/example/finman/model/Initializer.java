package com.example.finman.model;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Initializer {

    private void initialize() {
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Opened database successfully");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            c = DriverManager.getConnection("jdbc:sqlite:finance.db", config.toProperties());
            stmt = c.createStatement();
            String createAccountSQL =
                    "CREATE TABLE IF NOT EXISTS account(" +
                            "account_number INTEGER PRIMARY KEY  NOT NULL CHECK (account_number>0)," +
                            "balance  DECIMAL(10,2)   NOT NULL," +
                            "institution VARCHAR(10) NOT NULL," +
                            "account_name VARCHAR(20) NOT NULL," +
                            "FOREIGN KEY(account_name) REFERENCES account_type(account_name))";


            // TODO do not hardcode the categories
            String createAccountTypeSQL =
                    "CREATE TABLE IF NOT EXISTS account_type (" +
                            "account_name VARCHAR(20) PRIMARY KEY NOT NULL," +
                            "account_type TEXT NOT NULL " +
                            "CHECK(account_type in ('asset', 'liability', 'investment', 'income')))";

            String createTransactionTypeSQL =
                    "CREATE TABLE IF NOT EXISTS txn_type(" +
                            "transaction_category VARCHAR(20) PRIMARY KEY  NOT NULL," +
                            "transaction_type VARCHAR(10) NOT NULL " +
                            "CHECK (transaction_type in ('income', 'transfer', 'expense')))";

            String createTransactionSQL =
                    " CREATE TABLE IF NOT EXISTS txn" +
                            " (transaction_number INTEGER  not null primary key autoincrement," +
                            "account_number INTEGER NOT NULL CHECK(account_number>0)," +
                            "amount  DECIMAL(10,2)    NOT NULL," +
                            "created_at TEXT NOT NULL," + // dates are assumed to be stored as ISO8601 strings
                            "transaction_category TEXT VARCHAR(10) NOT NULL," +
                            "FOREIGN KEY(transaction_category) REFERENCES  txn_type(transaction_category)," +
                            "FOREIGN KEY(account_number) REFERENCES  account(account_number))";

            // create view for accounts by type
            String createAccountsWithTypeViewSQL = "CREATE VIEW IF NOT EXISTS vw_accounts_with_type " +
                    "AS SELECT account.*, account_type.account_type as type FROM account JOIN account_type " +
                    "ON account.account_name = account_type.account_name";

            String createTransactionsWithTypeViewSQL = "CREATE VIEW IF NOT EXISTS vw_transactions_with_type " +
                    "AS SELECT txn.*, txn_type.transaction_type as type FROM txn JOIN txn_type " +
                    "ON txn.transaction_category = txn_type.transaction_category";

            stmt.executeUpdate(createAccountSQL);
            stmt.executeUpdate(createAccountTypeSQL);
            stmt.executeUpdate(createTransactionTypeSQL);
            stmt.executeUpdate(createTransactionSQL);

            stmt.executeUpdate(createAccountsWithTypeViewSQL);
            stmt.executeUpdate(createTransactionsWithTypeViewSQL);
            stmt.close();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void main(String[] args) {
        Initializer init = new Initializer();
        init.initialize();
    }
}