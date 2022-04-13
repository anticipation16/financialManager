package com.example.finman.controllers;

import com.example.finman.model.doa.sqltables.transactionType.TransactionType;
import com.example.finman.model.doa.sqltables.transactionType.TransactionTypeDAO;
import com.example.finman.model.doa.sqltables.transactionType.TransactionTypeDAOSQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.finman.controllers.SceneController.switchScene;

public class AddTransactionTypeController implements Initializable {
    @FXML
    private ChoiceBox<String> transactionTypeChoiceBox;
    @FXML
    private TextField transactionCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTransactionTypeChoiceBox();
    }

    private void setTransactionTypeChoiceBox() {
        ObservableList<String> typeList = FXCollections.observableArrayList();
        TransactionTypeDAO dao = new TransactionTypeDAOSQLite();
        try {
            typeList.addAll(dao.getAllTransactionTypes());
        } catch (SQLException e) {
            SQLExceptionController.displayAlert(e);
        }
        transactionTypeChoiceBox.setItems(typeList);
    }

    public void handleAddTransactionType(ActionEvent actionEvent) throws IOException {
        TransactionTypeDAO dao = new TransactionTypeDAOSQLite();
        TransactionType newTransactionType = new TransactionType(transactionCategory.getText(),
                transactionTypeChoiceBox.getValue());

        try {
            dao.addTransactionType(newTransactionType);
        } catch (SQLException e) {
            SQLExceptionController.displayAlert(e);
        }
        switchScene(actionEvent, "/fxml/add-transaction.fxml");
    }

    public void handleBackButtonClick(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/fxml/add-transaction.fxml");
    }
}
