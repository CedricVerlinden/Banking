package com.cedricverlinden.banking.models;

public class Account {

    private String accountNumber;
    private double balance;
    
    private User user;

    public Account(String accountNumber, User user, double balance) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
