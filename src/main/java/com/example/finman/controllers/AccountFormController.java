package com.example.finman.controllers;

import com.example.finman.model.doa.sqlviews.accountWithType.AccountWithType;
import com.example.finman.model.doa.sqlviews.accountWithType.AccountWithTypeDAO;
import com.example.finman.model.doa.sqlviews.accountWithType.AccountWithTypeDAOSQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.finman.controllers.SceneController.switchScene;

public class AccountFormController implements Initializable {
    @FXML
    private Button addNewAccountBtn;
    @FXML
    private TableView<AccountWithType> accountsTable;
    @FXML
    private TableColumn<AccountWithType, Long> accountNumber;
    @FXML
    private TableColumn<AccountWithType, Double> balance;
    @FXML
    private TableColumn<AccountWithType, String> institution;
    @FXML
    private TableColumn<AccountWithType, String> accountName;
    @FXML
    private TableColumn<AccountWithType, String> accountType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        institution.setCellValueFactory(new PropertyValueFactory<>("institution"));
        accountName.setCellValueFactory(new PropertyValueFactory<>("accountName"));
        accountType.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        accountsTable.setItems(getAllAccountsWithType());
        addButtonColumnToTable();
    }

    private ObservableList<AccountWithType> getAllAccountsWithType() {
        AccountWithTypeDAO accountWithTypeDAO = new AccountWithTypeDAOSQLite();
        ObservableList<AccountWithType> list = FXCollections.observableArrayList();
        list.addAll(accountWithTypeDAO.getAllAccountsWithType());
        return list;
    }


    private void addButtonColumnToTable() {
        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<AccountWithType, String>, TableCell<AccountWithType, String>> cellFactory
                = //
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<AccountWithType, String> param) {
                        final TableCell<AccountWithType, String> cell = new TableCell<>() {

                            final Button btn = new Button("View Transactions");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        AccountWithType accountWithType = getTableView().getItems().get(getIndex());
                                        try {
                                            switchScene(event, "/fxml/view-account.fxml",
                                                    new ViewAccountController(accountWithType.getAccountNumber()));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        System.out.println(accountWithType);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);
        accountsTable.getColumns().add(actionCol);

    }


    @FXML
    public void handleAddNewAccountClick(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/fxml/add-account.fxml");
    }

    @FXML
    public void handleBackButtonClick(ActionEvent actionEvent) throws IOException {
        switchScene(actionEvent, "/fxml/main-view.fxml");
    }

}
