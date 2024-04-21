package com.cedricverlinden.banking.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    public static Connection connect() {
        try {
            Properties properties = new Properties();
            properties.load(Connection.class.getClassLoader().getResourceAsStream("database.properties"));

            String driver = properties.getProperty("jdbc.driver");
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);

        } catch (IOException e) {
            System.out.println("Failed to load properties file");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load driver");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database");
        }

        return null;
    }
}
