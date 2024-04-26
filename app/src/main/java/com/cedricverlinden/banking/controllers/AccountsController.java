package com.cedricverlinden.banking.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.cedricverlinden.banking.models.Account;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class AccountsController extends BaseController implements Initializable {

    @FXML
    private TableView<Account> accounts;

    @FXML
    private TableColumn<?, ?> tableId;

    @FXML
    private TableColumn<?, ?> tableName;

    @FXML
    private TableColumn<?, ?> tableBalance;

    @FXML
    private void goToHome() {
        getScreenManager().setScreen("Dashboard");
    }

    @FXML
    private void goToConverter() {
        getScreenManager().setScreen("Converter");
    }

    @FXML
    private void addAccount() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add Account");

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContentText("Enter account details:");
        TextField accountNameField = new TextField();
        dialogPane.setContent(accountNameField);

        // Add buttons to the dialog
        ButtonType addButton = new ButtonType("Add", ButtonData.OK_DONE);
        dialogPane.getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Set the result converter
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return accountNameField.getText();
            }
            return null;
        });

        // Show the dialog and wait for user input
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(accountName -> {
            getDatabase().addAccount(accountName);

            accounts.getItems().clear();
            ArrayList<Account> accountList = getDatabase().getAccounts();
            accountList.forEach(account -> {
                accounts.getItems().add(account);
            });
        });
    }

    @FXML
    private void newTransaction() {
        Dialog<ArrayList<String>> dialog = new Dialog<>();
        dialog.setTitle("New transaction");
        dialog.setResizable(false);

        Label label1 = new Label("Account");
        Label label2 = new Label("Amount");
        ComboBox<String> accountList = new ComboBox<>();
        TextField text2 = new TextField();

        ArrayList<Account> accountsOptions = getDatabase().getAccounts();
        accountsOptions.forEach(account -> {
            accountList.getItems().add(account.getName());
        });

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(accountList, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(text2, 2, 2);
        dialog.getDialogPane().setContent(grid);

        ButtonType withdrawButton = new ButtonType("Withdraw", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(withdrawButton);

        ButtonType despositButton = new ButtonType("Deposit", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(despositButton);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == withdrawButton || dialogButton == despositButton) {
                return new ArrayList<String>() {
                    {
                        add(accountList.getValue());
                        add(text2.getText());
                        add(dialogButton == despositButton ? "yes" : "no");
                    }
                };
            }
            return null;
        });

        // Show the dialog and wait for user input
        Optional<ArrayList<String>> result = dialog.showAndWait();
        result.ifPresent(transaction -> {
            getDatabase().createTransaction(transaction.get(0), Double.parseDouble(transaction.get(1)),
                    transaction.get(2).equals("yes"));

            accounts.getItems().clear();
            ArrayList<Account> newAccounts = getDatabase().getAccounts();
            newAccounts.forEach(account -> {
                accounts.getItems().add(account);
            });
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableId.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));

        ArrayList<Account> accountList = getDatabase().getAccounts();
        accountList.forEach(account -> {
            accounts.getItems().add(account);
        });
    }
}
