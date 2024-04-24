package com.cedricverlinden.banking.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cedricverlinden.banking.models.Account;
import com.cedricverlinden.banking.models.Role;
import com.cedricverlinden.banking.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController extends BaseController {

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextField street;

    @FXML
    private TextField number;

    @FXML
    private LocalDate dateOfBirth;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordConfirmation;

    @FXML
    private void registerNewUser() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String firstname = this.firstname.getText();
        String lastname = this.lastname.getText();
        String email = this.email.getText();
        String phone = this.phone.getText();
        String street = this.street.getText();
        String number = this.number.getText();
        LocalDate dateOfBirth = this.dateOfBirth;
        String password = this.password.getText();
        String passwordConfirmation = this.passwordConfirmation.getText();

        List<Account> accounts = new ArrayList<>();

        if (!password.equals(passwordConfirmation)) {
            this.firstname.clear();
            this.lastname.clear();
            this.email.clear();
            this.phone.clear();
            this.street.clear();
            this.number.clear();
            this.dateOfBirth = null;
            this.password.clear();
            this.passwordConfirmation.clear();
            return;
        }

        User user = new User(Role.CLIENT, firstname, lastname, email, phone, street + " " + number, password, accounts,
                dateOfBirth);
        try {
            getDatabase().createUser(user);

            if (getScreenManager() != null) {
                getScreenManager().setScreen("Login");
            }
        } catch (Exception e) {
            // TODO: Add error message
            e.printStackTrace();
        }
    }

    @FXML
    private void alreadyHasAccount() {
        if (getScreenManager() != null) {
            getScreenManager().setScreen("Login");
        }
    }
}
