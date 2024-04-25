package com.cedricverlinden.banking.models;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.List;

import com.cedricverlinden.banking.constants.Role;

public class User {

    private String identifier;
    private Role role;

    private String firstName;
    private String lastName;

    private String email;
    private String phoneNumber;
    private String address;

    private List<Account> accounts;

    private LocalDate dateOfBirth;

    public User() {
    }

    public User(String identifier, Role role, String firstName, String lastName, String email, String phoneNumber,
            String address,
            List<Account> accounts, LocalDate dateOfBirth)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.identifier = identifier;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.accounts = accounts;
        this.dateOfBirth = dateOfBirth;
    }

    /*
     * Identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /*
     * Role Details
     */
    public Role getRole() {
        return role;
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

}
