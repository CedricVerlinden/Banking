package com.cedricverlinden.banking.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
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
    private DatePicker dateOfBirth;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordConfirmation;

    @FXML
    private void registerNewUser() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String firstName = this.firstname.getText();
        String lastName = this.lastname.getText();
        String email = this.email.getText();
        String phoneNumber = this.phone.getText();
        String street = this.street.getText();
        String number = this.number.getText();
        Date dateOfBirth = Date.valueOf(this.dateOfBirth.getValue());
        String password = this.password.getText();
        String passwordConfirmation = this.passwordConfirmation.getText();

        if (!password.equals(passwordConfirmation)) {
            this.password.clear();
            this.passwordConfirmation.clear();
            return;
        }

        try {
            getDatabase().createUser(firstName, lastName, email, phoneNumber, street, number, password, dateOfBirth);

            getScreenManager().setScreen("Login");
        } catch (Exception e) {
            // TODO: Add error message
            e.printStackTrace();
        }
    }

    @FXML
    private void alreadyHasAccount() {
        getScreenManager().setScreen("Login");
    }
}
