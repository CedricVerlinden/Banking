package com.cedricverlinden.banking.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import com.cedricverlinden.banking.models.Role;
import com.cedricverlinden.banking.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {

    private Connection connection;

    public Database() {
        try {
            Properties properties = new Properties();
            properties.load(Connection.class.getClassLoader().getResourceAsStream("database.properties"));

            String driver = properties.getProperty("jdbc.driver");
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);

        } catch (IOException e) {
            System.out.println("Failed to load properties file");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load driver");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database");
        }
    }

    public void createUser(User user) {
        String identifier = UUID.randomUUID().toString();
        String dateOfBirth = user.getDateOfBirth().toString();

        String sql = "INSERT INTO users (id, role, firstname, lastname, email, phonenumber, address, password, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, identifier);
            ps.setString(2, Role.CLIENT.toString());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhoneNumber());
            ps.setString(7, user.getAddress());
            ps.setString(8, user.getPassword());
            ps.setString(9, dateOfBirth);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}
