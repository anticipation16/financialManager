package com.example.finman.controllers;

import com.example.finman.model.doa.sqlviews.accountWithType.AccountWithType;
import com.example.finman.model.doa.sqlviews.accountWithType.AccountWithTypeDAO;
import com.example.finman.model.doa.sqlviews.accountWithType.AccountWithTypeDAOSQLite;
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

public class AllInvestmentsController implements Initializable {
    @FXML
    private TableView<AccountWithType> accountsTableType;
    @FXML
    private TableColumn<AccountWithType, Long> accountNumberType;
    @FXML
    private TableColumn<AccountWithType, Double> balanceType;
    @FXML
    private TableColumn<AccountWithType, String> institutionType;
    @FXML
    private TableColumn<AccountWithType, String> accountNameType;
    @FXML
    private TableColumn<AccountWithType, String> accountTypeType;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAllInvestmentsTable();
    }


    private ObservableList<AccountWithType> getAllAccountsWithSpecificType(String type) {
        AccountWithTypeDAO accountWithTypeDAO = new AccountWithTypeDAOSQLite();
        ObservableList<AccountWithType> list = FXCollections.observableArrayList();
        list.addAll(accountWithTypeDAO.getAllAccountsOfSpecificType(type));
        return list;
    }

    private void setAllInvestmentsTable() {
        accountNameType.setCellValueFactory(new PropertyValueFactory<>("accountName"));
        balanceType.setCellValueFactory(new PropertyValueFactory<>("balance"));
        accountNumberType.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        institutionType.setCellValueFactory(new PropertyValueFactory<>("institution"));
        accountsTableType.setItems(getAllAccountsWithSpecificType("investment"));
    }

    @FXML
    public void handleBackButtonClick(ActionEvent e) throws IOException {
        switchScene(e, "/fxml/main-view.fxml", "/css/pieChart.css");
    }

}
