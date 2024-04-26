package com.cedricverlinden.banking.database;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cedricverlinden.banking.constants.Role;
import com.cedricverlinden.banking.models.Account;
import com.cedricverlinden.banking.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {

    private static Database instance;
    private Connection connection;

    public Database() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/banking";
            String username = "root";
            String password = "";

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load driver");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database");
        }
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void createUser(String firstName, String lastName, String email, String phoneNumber, String street,
            String number,
            String password, Date dateOfBirth) {
        String identifier = UUID.randomUUID().toString();

        String sql = "INSERT INTO users (id, role, firstname, lastname, email, phonenumber, address, password, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, identifier);
            ps.setString(2, Role.CLIENT.toString());
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, email);
            ps.setString(6, phoneNumber);
            ps.setString(7, street + " " + number);
            ps.setString(8, password);
            ps.setDate(9, dateOfBirth);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User verifyUser(String userEmail, String userPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userEmail);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return null;
            }

            String password = rs.getString("password");
            if (!userPassword.equals(password)) {
                return null;
            }

            String id = rs.getString("id");
            Role role = Role.valueOf(rs.getString("role"));
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String email = rs.getString("email");
            String phonenumber = rs.getString("phonenumber");
            String address = rs.getString("address");
            LocalDate dateOfBirth = LocalDate.parse(rs.getString("dateOfBirth"));

            List<Account> accounts = new ArrayList<>();

            return new User(id, role, firstname, lastname, email, phonenumber, address, accounts, dateOfBirth);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void getUsers() {
        String sql = "SELECT * FROM users";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(String accountName) {
        String identifier = UUID.randomUUID().toString();

        String sql = "INSERT INTO accounts (id, name) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, identifier);
            ps.setString(2, accountName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Account> getAccounts() {
        String sql = "SELECT * FROM accounts";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Account> accounts = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                Double balance = rs.getDouble("balance");
                accounts.add(new Account(id, name, balance));
            }

            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void createTransaction(String accountName, Double amount, boolean deposit) {
        Double balance = 0.0;
        String sql = "SELECT * FROM accounts WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, accountName);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            if (rs.next()) {
                System.out.println("Has next");
                balance = rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (deposit) {
            balance += amount;
        } else {
            balance -= amount;
        }

        sql = "UPDATE accounts SET balance = ? WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, balance);
            ps.setString(2, accountName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
