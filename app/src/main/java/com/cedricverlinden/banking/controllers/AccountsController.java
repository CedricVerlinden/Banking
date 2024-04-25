package com.cedricverlinden.banking.controllers;

import javafx.fxml.FXML;

public class AccountsController extends BaseController {

    @FXML
    private void goToHome() {
        getScreenManager().setScreen("Dashboard");
    }

    @FXML
    private void goToConverter() {
        getScreenManager().setScreen("Converter");
    }
}
