package com.example.finman.controllers;

import com.example.finman.model.doa.sqltables.accountType.AccountType;
import com.example.finman.model.doa.sqltables.accountType.AccountTypeDAO;
import com.example.finman.model.doa.sqltables.accountType.AccountTypeDAOSQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.finman.controllers.SceneController.switchScene;

public class AddAccountTypeController implements Initializable {
    @FXML
    private TextField accountName;
    @FXML
    private ChoiceBox<String> accountTypesChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountTypesChoiceBox.setItems(getAccountTypesList());
    }

    private ObservableList<String> getAccountTypesList() {
        ObservableList<String> accountTypesList = FXCollections.observableArrayList();
        AccountTypeDAO dao = new AccountTypeDAOSQLite();
        accountTypesList.addAll(dao.getAllAccountTypes());
        return accountTypesList;
    }

    public void handleAddAccountTypeClick(ActionEvent actionEvent) throws IOException {
        AccountType newAccountType = new AccountType(accountName.getText(), accountTypesChoiceBox.getValue());
        AccountTypeDAO accountTypeDAO = new AccountTypeDAOSQLite();
        accountTypeDAO.addAccountType(newAccountType);
        switchScene(actionEvent, "/fxml/add-account.fxml");
    }
}
