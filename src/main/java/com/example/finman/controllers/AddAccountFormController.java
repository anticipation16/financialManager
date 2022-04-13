package com.example.finman.controllers;

import com.example.finman.model.doa.sqltables.account.Account;
import com.example.finman.model.doa.sqltables.account.AccountDAO;
import com.example.finman.model.doa.sqltables.account.AccountDAOSQLite;
import com.example.finman.model.doa.sqltables.accountType.AccountTypeDAO;
import com.example.finman.model.doa.sqltables.accountType.AccountTypeDAOSQLite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.finman.controllers.SceneController.switchScene;

public class AddAccountFormController implements Initializable, Controller {
    @FXML
    public ChoiceBox<String> accountNameChoiceBox;
    @FXML
    private Button backButton;
    @FXML
    private TextField accountNumber;
    @FXML
    private Button addAccountButton;
    @FXML
    private TextField balance;
    @FXML
    private TextField institution;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccountTypeDAO accountTypeDAO = new AccountTypeDAOSQLite();
        try {
            accountNameChoiceBox.getItems().addAll(accountTypeDAO.getAllAccountNames());
        } catch (SQLException e) {
            SQLExceptionController.displayAlert(e);
        }
    }

    @FXML
    public void handleAddAccountClick(ActionEvent actionEvent) throws IOException {
        AccountDAO accountDAO = new AccountDAOSQLite();
        try {
            accountDAO.addAccount(new Account(
                    Long.parseLong(accountNumber.getCharacters().toString()),
                    Double.parseDouble(balance.getCharacters().toString()),
                    institution.getCharacters().toString(),
                    accountNameChoiceBox.getValue()));
        } catch (SQLException e) {
            SQLExceptionController.displayAlert(e);
        }
        switchScene(actionEvent, "/fxml/main-view.fxml", "/css/pieChart.css");
    }

    @FXML
    public void handleAddAccountNameClick(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/fxml/add-account-type.fxml");
    }

    @FXML
    public void handleBackButtonClick(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/fxml/view-accounts.fxml");
    }

}
