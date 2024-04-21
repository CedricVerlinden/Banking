package com.cedricverlinden.banking.controllers;

import javafx.fxml.FXML;

public class HomeController extends BaseController {

    @FXML
    private void handleGoToProfile() {
        if (getScreenManager() != null) {
            getScreenManager().setScreen("Profile");
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
