package com.cedricverlinden.banking.controllers;

import javafx.fxml.FXML;

public class AccountsController extends BaseController {

    @FXML
    private void goToHome() {
        getScreenManager().setScreen("Home");
    }

    @FXML
    private void goToTools() {
        getScreenManager().setScreen("Tools");
    }
}
