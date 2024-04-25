package com.cedricverlinden.banking.controllers;

import com.cedricverlinden.banking.ScreenManager;
import com.cedricverlinden.banking.database.Database;

import javafx.fxml.FXML;

public class BaseController {
    
    private final Database database;

    private ScreenManager screenManager;

    public BaseController() {
        this.database = Database.getInstance();
    }

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }

    public Database getDatabase() {
        return database;
    }

    @FXML
    private void logout() {
        screenManager.setScreen("Login");
    }
}
