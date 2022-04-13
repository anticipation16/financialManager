package com.example.finman.controllers;

import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithType;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAO;
import com.example.finman.model.doa.sqlviews.transactionWithType.TransactionWithTypeDAOSQLite;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.finman.controllers.SceneController.switchScene;

public class AllExpensesController implements Initializable {
    @FXML
    private ChoiceBox<String> expensesForChoiceBox;
    private List<String> choiceBoxValues;
    @FXML
    private TableView<TransactionWithType> expensesForTable;
    @FXML
    private TableColumn<TransactionWithType, Double> amount;
    @FXML
    private TableColumn<TransactionWithType, String> category;
    @FXML
    private TableColumn<TransactionWithType, String> createdAt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setChoiceBoxValues();
        setChoiceBox();
        initializeTable();
    }

    private void initializeTable() {
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        createdAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
    }

    private void setChoiceBoxValues() {
        choiceBoxValues = new ArrayList<>();
        choiceBoxValues.add("Today");
        choiceBoxValues.add("This Week");
        choiceBoxValues.add("This Month");
        choiceBoxValues.add("This Year");
    }

    private void setChoiceBox() {
        expensesForChoiceBox.setItems(FXCollections.observableArrayList(choiceBoxValues));
        expensesForChoiceBox.setValue(expensesForChoiceBox.getItems().get(0));
    }

    @FXML
    public void handleChoiceBoxChange() {
        String choice = expensesForChoiceBox.getValue();
        TransactionWithTypeDAO typeDAO = new TransactionWithTypeDAOSQLite();
        switch (choice) {
            case "This Week" ->
                    expensesForTable.setItems(FXCollections.observableArrayList(typeDAO.getExpensesForThisWeek()));
            case "This Month" ->
                    expensesForTable.setItems(FXCollections.observableArrayList(typeDAO.getExpensesForThisMonth()));
            case "This Year" ->
                    expensesForTable.setItems(FXCollections.observableArrayList(typeDAO.getExpensesForThisYear()));
            default ->
                    expensesForTable.setItems(FXCollections.observableArrayList(typeDAO.getExpensesForToday()));
        }
    }

    @FXML
    public void handleBackButtonClick(ActionEvent e) throws IOException {
        switchScene(e, "/fxml/main-view.fxml", "/css/pieChart.css");
    }

}
