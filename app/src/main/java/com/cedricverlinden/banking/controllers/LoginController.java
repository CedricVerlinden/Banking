package com.cedricverlinden.banking.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.cedricverlinden.banking.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends BaseController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private void doesntHaveAccount() {
        getScreenManager().setScreen("Register");
    }

    @FXML
    private void login() throws NoSuchAlgorithmException, InvalidKeySpecException {
        User verifiedUser = getDatabase().verifyUser(email.getText(), password.getText());
        email.clear();
        password.clear();

        if (verifiedUser == null) {
            return;
        }

        getScreenManager().setScreen("Dashboard");
    }
}
