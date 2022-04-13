package com.example.finman.controllers;

import com.example.finman.model.doa.sqltables.account.AccountDAO;
import com.example.finman.model.doa.sqltables.account.AccountDAOSQLite;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithType;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAO;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAOSQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.finman.controllers.SceneController.switchScene;

public class HomeController implements Initializable {
    @FXML
    private Button viewAccountsBtn;
    @FXML
    private TableColumn<TransactionWithType, String> transactionType;
    @FXML
    private TableColumn<TransactionWithType, String> createdAt;
    @FXML
    private TableView<TransactionWithType> recentTransactionsTable;

    @FXML
    private TableColumn<TransactionWithType, Double> amount;
    @FXML
    private TableColumn<TransactionWithType, Long> account;
    @FXML
    private TableColumn<TransactionWithType, String> category;
    // @FXML
    // private Button newTransactionButton;
    @FXML
    private TableView<TransactionWithType> topExpensesTable;
    @FXML
    private TableColumn<TransactionWithType, String> createdAtExpenses;
    @FXML
    private TableColumn<TransactionWithType, Double> amountExpenses;
    @FXML
    private TableColumn<TransactionWithType, String> categoryExpenses;
//    @FXML
//    private TableColumn<Transaction, Long> accountExpenses;
//    @FXML
//    private TableColumn<Transaction, Long> txnNumber;

    @FXML
    private Text netWorthText;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setNetWorthText();
        setRecentTransactionsTable();
        setTopExpensesTable();
    }

    @FXML
    public void handleAddNewTransactionClick(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/fxml/add-transaction.fxml");
    }

    @FXML
    public void handleViewAccountsClick(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/fxml/view-accounts.fxml");
    }


    private ObservableList<TransactionWithType> getRecentTransactionsWithType(int requiredNumber) {
        ObservableList<TransactionWithType> list = FXCollections.observableArrayList();
        TransactionWithTypeDAO tDAO = new TransactionWithTypeDAOSQLite();
        list.addAll(tDAO.getRecentTransactionsWithType(requiredNumber));
        return list;
    }

    private ObservableList<TransactionWithType> getTopExpenses(int requiredNumber) {
        ObservableList<TransactionWithType> list = FXCollections.observableArrayList();
        TransactionWithTypeDAO tDao = new TransactionWithTypeDAOSQLite();
        list.addAll(tDao.getTopExpensesForThisMonth(requiredNumber));
        return list;
    }

    private void setTopExpensesTable() {
        createdAtExpenses.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        categoryExpenses.setCellValueFactory(new PropertyValueFactory<>("category"));
        amountExpenses.setCellValueFactory(new PropertyValueFactory<>("amount"));
        topExpensesTable.setItems(getTopExpenses(7));
    }

    private void setNetWorthText() {
        AccountDAO accountDAO = new AccountDAOSQLite();
        double assets = accountDAO.getBalanceByType("asset");
        double investments = accountDAO.getBalanceByType("investment");
        double liabilities = accountDAO.getBalanceByType("liability");
        double netWorth = assets + investments - liabilities;
        netWorthText.setText(Double.toString(netWorth));
    }

    private void setRecentTransactionsTable() {
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        account.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        transactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        recentTransactionsTable.setItems(getRecentTransactionsWithType(7));
    }


    @FXML
    public void handleAllTransactionsClick(ActionEvent event) throws IOException {
        switchScene(event, "/fxml/all-transactions.fxml");
    }


}