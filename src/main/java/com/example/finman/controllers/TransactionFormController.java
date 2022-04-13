package com.example.finman.controllers;

import com.example.finman.model.doa.sqltables.transaction.Transaction;
import com.example.finman.model.doa.sqltables.transaction.TransactionDAO;
import com.example.finman.model.doa.sqltables.transaction.TransactionDAOSQLite;
import com.example.finman.model.doa.sqltables.transactionType.TransactionTypeDAO;
import com.example.finman.model.doa.sqltables.transactionType.TransactionTypeDAOSQLite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.finman.controllers.SceneController.switchScene;
import static com.example.finman.utility.DateTimeParser.getDateTimeString;

public class TransactionFormController implements Initializable {


    @FXML
    private TextField transaction_amount;
    @FXML
    private TextField transaction_account_number;
    @FXML
    private ChoiceBox<String> transaction_category;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("init");
        TransactionTypeDAO transactionTypeDAO = new TransactionTypeDAOSQLite();
        List<String> categories = transactionTypeDAO.getAllTransactionCategories();

        transaction_category.getItems().addAll(categories);
        if (categories.size() > 0)
            transaction_category.setValue(categories.get(0));
    }

    public void openHomeScene(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader homePaneLoader = new FXMLLoader(getClass().getResource("/fxml/main-view.fxml"));
        Parent homePane = homePaneLoader.load();
        Scene homeScene = new Scene(homePane);
        primaryStage.setScene(homeScene);
    }

    @FXML
    public void handleAddNewTransaction(ActionEvent e) throws IOException {
        long amount = Long.parseLong(transaction_amount.getCharacters().toString());
        long accountNum = Long.parseLong(transaction_account_number.getCharacters().toString());
        String category = transaction_category.getValue();
        TransactionDAO transactionDAO = new TransactionDAOSQLite();
        Transaction t = new Transaction(-1, accountNum, category, amount,
                getDateTimeString(Instant.now().toString()));
        transactionDAO.addTransaction(t);
        openHomeScene(e);
    }

    @FXML
    public void handleAddNewCategory(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/fxml/add-transaction-type.fxml");
    }

    public void handleBackToHome(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/fxml/main-view.fxml", "/css/pieChart.css" );
    }
}
