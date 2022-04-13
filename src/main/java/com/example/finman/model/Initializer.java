package com.example.finman.model;

import com.example.finman.utility.ScriptRunner;
import org.sqlite.SQLiteConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Initializer {
    private final String DB_URL = "jdbc:sqlite:finance.db";

    public void initializeTables() throws SQLException, IOException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        Connection connection;
        connection = DriverManager.getConnection(DB_URL, config.toProperties());
        try {
            ScriptRunner runner = new ScriptRunner(connection, false, false);
            runner.runScript(new BufferedReader(
                    new FileReader(Objects.requireNonNull(Initializer.class.getResource(
                            "/sql/ddl/financeDDL.sql")).getFile())));

            runner.runScript(new BufferedReader(
                    new FileReader(Objects.requireNonNull(Initializer.class.getResource(
                            "/sql/ddl/financeInitialData.sql")).getFile())));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

}