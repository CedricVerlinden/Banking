package com.cedricverlinden.banking.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DashboardController extends BaseController {

    @FXML
    Text greeting;
    
    @FXML
    private void goToAccounts() {
        getScreenManager().setScreen("Accounts");
    }

    @FXML
    private void goToTools() {
        getScreenManager().setScreen("Tools");
    }
 }
