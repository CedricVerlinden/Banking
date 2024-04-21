package com.cedricverlinden.banking.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProfileController extends BaseController {
    
    @FXML
    private void handleGoToHome() {
        if (getScreenManager() != null) {
            getScreenManager().setScreen("Home");
        }
    }

    @FXML
    private void handleShowMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Profile Message");
        alert.setContentText("This is a message from the Profile screen.");
        alert.showAndWait();
    }
}
