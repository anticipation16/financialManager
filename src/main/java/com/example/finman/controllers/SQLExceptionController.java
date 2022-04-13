package com.example.finman.controllers;

import javafx.scene.control.Alert;

import java.sql.SQLException;

public class SQLExceptionController {
    public static void displayAlert(SQLException sqlException){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText(sqlException.getMessage());
        errorAlert.showAndWait();
    }
}
