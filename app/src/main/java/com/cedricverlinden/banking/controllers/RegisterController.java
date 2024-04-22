package com.cedricverlinden.banking.controllers;

import javafx.fxml.FXML;

public class RegisterController extends BaseController {

    @FXML
    private void registerNewUser() {
        System.out.println("Registering new user...");
    }

    @FXML
    private void alreadyHasAccount() {
        if (getScreenManager() != null) {
            getScreenManager().setScreen("Login");
        }
    }
}
