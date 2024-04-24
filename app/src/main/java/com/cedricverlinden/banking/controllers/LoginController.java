package com.cedricverlinden.banking.controllers;

import javafx.fxml.FXML;

public class LoginController extends BaseController {

    @FXML
    private void doesntHaveAccount() {
        if (getScreenManager() != null) {
            getScreenManager().setScreen("Register");
        }
    }

    @FXML
    private void login() {
        if (getScreenManager() != null) {
            getScreenManager().setScreen("Home");
        }
    }
}
