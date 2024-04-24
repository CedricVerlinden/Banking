package com.cedricverlinden.banking.controllers;

import com.cedricverlinden.banking.ScreenManager;
import com.cedricverlinden.banking.database.Database;

public class BaseController {

    private ScreenManager screenManager;
    private Database database;

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }

    public Database getDatabase() {
        return database;
    }
}
