package com.cedricverlinden.banking.controllers;

import com.cedricverlinden.banking.ScreenManager;
import com.cedricverlinden.banking.database.Database;
import com.cedricverlinden.banking.models.User;

import javafx.fxml.FXML;

public class BaseController {

    private final Database database;

    private ScreenManager screenManager;

    private User user;

    public BaseController() {
        this.database = Database.getInstance();
        this.user = new User();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void logout() {
        screenManager.setScreen("Login");
    }
}
