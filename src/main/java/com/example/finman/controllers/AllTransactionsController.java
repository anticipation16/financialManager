package com.example.finman.controllers;

import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithType;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAO;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAOSQLite;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.finman.controllers.SceneController.switchScene;

public class AllTransactionsController implements Initializable {
    @FXML
    private TableView<TransactionWithType> allTransactionsTable;
    @FXML
    private TableColumn<TransactionWithType, Long> transactionNumber;
    @FXML
    private TableColumn<TransactionWithType, Double> amount;
    @FXML
    private TableColumn<TransactionWithType, Long> account;
    @FXML
    private TableColumn<TransactionWithType, String> category;
    @FXML
    private TableColumn<TransactionWithType, String> type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAllTransactionsTable();
    }

    private void setAllTransactionsTable() {
        transactionNumber.setCellValueFactory(new PropertyValueFactory<>("transactionNumber"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        account.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        type.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        TransactionWithTypeDAO dao = new TransactionWithTypeDAOSQLite();
        List<TransactionWithType> allTransactions = dao.getAllTransactionsWithType();
        allTransactionsTable.setItems(FXCollections.observableArrayList(allTransactions));
    }

    @FXML
    public void handleBackButtonClick(ActionEvent e) throws IOException {
        switchScene(e, "/fxml/main-view.fxml");
    }
}
