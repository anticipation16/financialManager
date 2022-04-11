package com.example.finman.controllers;

import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithType;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAO;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAOSQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.finman.controllers.SceneController.switchScene;

public class ViewAccountController implements Initializable, Controller {

    private final long accountNumber;

    public ViewAccountController(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @FXML
    private TableColumn<TransactionWithType, String> transactionType;
    @FXML
    private TableColumn<TransactionWithType, String> createdAt;
    @FXML
    private TableView<TransactionWithType> transactionsFromAccountTable;
    @FXML
    private TableColumn<TransactionWithType, Long> amount;
    @FXML
    private TableColumn<TransactionWithType, Long> account;
    @FXML
    private TableColumn<TransactionWithType, String> category;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTransactionsFromAccountTable();
    }

    private void setTransactionsFromAccountTable() {
        final int TRANSACTIONS_VIEWABLE_AT_ONCE = 6;
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        account.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        transactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        transactionsFromAccountTable.setItems(getTransactionsWithTypeForAccount(TRANSACTIONS_VIEWABLE_AT_ONCE));
    }

    private ObservableList<TransactionWithType> getTransactionsWithTypeForAccount(int requiredNumber) {
        TransactionWithTypeDAO transactionWithTypeDAO = new TransactionWithTypeDAOSQLite();
        ObservableList<TransactionWithType> transactions = FXCollections.observableArrayList();
        transactions.addAll(transactionWithTypeDAO.getTransactionsFor(accountNumber,
                requiredNumber));
        return transactions;
    }

    @FXML
    public void handleBackButtonClick(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/fxml/view-accounts.fxml");
    }
}
