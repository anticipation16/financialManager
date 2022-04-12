package com.example.finman.controllers;

import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithType;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAO;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAOSQLite;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.finman.controllers.SceneController.switchScene;

public class ViewAccountController implements Initializable, Controller {

    private final long accountNumber;

    public ViewAccountController(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @FXML
    private Pagination pagination;

    private List<TransactionWithType> transactionsFromAccount;
    private final int DATA_SIZE = 100;
    private final int ROWS_PER_PAGE = 5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTransactionFromAccount();
        pagination.setPageCount(10);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(this::createPage);
    }

    public Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, transactionsFromAccount.size());
        TableView<TransactionWithType> transactionsForTheAccountTable = new TableView<>();
        TableColumn<TransactionWithType, Long> column1 = new TableColumn<>("Created At");
        TableColumn<TransactionWithType, Long> column2 = new TableColumn<>("Category");
        TableColumn<TransactionWithType, Long> column3 = new TableColumn<>("Amount");
        TableColumn<TransactionWithType, Long> column4 = new TableColumn<>("Transaction Type");

        column1.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        column2.setCellValueFactory(new PropertyValueFactory<>("category"));
        column3.setCellValueFactory(new PropertyValueFactory<>("amount"));
        column4.setCellValueFactory(new PropertyValueFactory<>("transactionType"));

        transactionsForTheAccountTable.getColumns().addAll(column1, column2, column3, column4);
        transactionsForTheAccountTable.
                setItems(FXCollections.observableArrayList(transactionsFromAccount.subList(fromIndex, toIndex)));
        BorderPane b = new BorderPane(transactionsForTheAccountTable);
        b.setMinSize(390, 170);
        return b;
    }

    private void loadTransactionFromAccount() {
        TransactionWithTypeDAO dao = new TransactionWithTypeDAOSQLite();
        transactionsFromAccount = dao.getTransactionsFor(accountNumber, DATA_SIZE);
    }

    @FXML
    public void handleBackButtonClick(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/fxml/view-accounts.fxml");
    }
}
