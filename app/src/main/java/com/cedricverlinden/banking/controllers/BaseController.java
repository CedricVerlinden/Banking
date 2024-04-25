package com.cedricverlinden.banking.controllers;

import com.cedricverlinden.banking.ScreenManager;
import com.cedricverlinden.banking.database.Database;

public class BaseController {

    private ScreenManager screenManager;
    private final Database database;

    public BaseController() {
        this.database = Database.getInstance();
    }

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }

    public void setScreen(String name) {
        if (screenManager == null) {
            return;
        }

        screenManager.setScreen(name);
    }

    public Database getDatabase() {
        return database;
    }
}
