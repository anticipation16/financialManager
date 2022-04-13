package com.example.finman.model;

import com.example.finman.utility.ScriptRunner;
import org.sqlite.SQLiteConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Initializer {
    private final String DB_URL = "jdbc:sqlite:finance.db";

    public void initializeTables() throws SQLException, IOException, URISyntaxException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        Connection connection;
        connection = DriverManager.getConnection(DB_URL, config.toProperties());
        try {
            ScriptRunner runner = new ScriptRunner(connection, false, false);
//            System.out.println(getClass().getResource("").toURI());
            runner.runScript(new BufferedReader(
                    new InputStreamReader(Objects.requireNonNull(
                            Initializer.class.getClassLoader().getResourceAsStream(
                                    "sql/ddl/financeDDL.sql")))));

            runner.runScript(new BufferedReader(
                    new InputStreamReader(Objects.requireNonNull(
                            Initializer.class.getClassLoader().getResourceAsStream(
                                    "sql/ddl/financeInitialData.sql")))));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

}