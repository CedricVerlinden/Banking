package com.cedricverlinden.banking.controllers;

import javafx.fxml.FXML;

public class DashboardController extends BaseController {

    @FXML
    private void goToAccounts() {
        setScreen("Accounts");
    }

    @FXML
    private void goToTools() {
        setScreen("Tools");
    }
}
