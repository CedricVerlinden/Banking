package com.cedricverlinden.banking.models;

import java.time.LocalDate;
import java.util.List;

public class User {

    private String firstName;
    private String lastName;

    private String email;
    private String phoneNumber;
    private String address;

    private String password;
    private List<Account> accounts;

    private LocalDate dateOfBirth;
    private LocalDate lastLogin;
    
    public User() {}

    public User(String firstName, String lastName, String email, String phoneNumber, String address, String password, List<Account> accounts, LocalDate dateOfBirth, LocalDate lastLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.accounts = accounts;
        this.dateOfBirth = dateOfBirth;
        this.lastLogin = lastLogin;
    }

    /*
     * Personal Details
     */
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    /*
     * Contact Details
     */
    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    /*
     * Account Details
     */
    public String getPassword() {
        return password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(String accountNumber) {
        accounts.removeIf(account -> account.getAccountNumber().equals(accountNumber));
    }

    /*
     * Date Details
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

}
