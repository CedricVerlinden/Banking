package com.cedricverlinden.banking.controllers;

import javafx.fxml.FXML;

public class ToolsController extends BaseController {

    @FXML
    private void goToHome() {
        getScreenManager().setScreen("Home");
    }

    @FXML
    private void goToAccounts() {
        getScreenManager().setScreen("Accounts");
    }
}
