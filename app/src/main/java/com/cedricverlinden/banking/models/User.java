package com.cedricverlinden.banking.models;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.List;

import com.cedricverlinden.banking.utils.Hasher;

public class User {

    private Role role;

    private String firstName;
    private String lastName;

    private String email;
    private String phoneNumber;
    private String address;

    private String password;
    private List<Account> accounts;

    private LocalDate dateOfBirth;

    public User() {
    }

    public User(Role role, String firstName, String lastName, String email, String phoneNumber, String address,
            String password,
            List<Account> accounts, LocalDate dateOfBirth)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;

        byte[] salt = Hasher.generateSalt();
        this.password = Hasher.bytesToBase64(Hasher.hashPassword(password, salt));

        this.accounts = accounts;
        this.dateOfBirth = dateOfBirth;
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

}
