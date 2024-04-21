package com.cedricverlinden.banking.controllers;

import com.cedricverlinden.banking.ScreenManager;

public class BaseController {
    private ScreenManager screenManager;

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }
}

