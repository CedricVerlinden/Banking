package com.cedricverlinden.banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnect {
    public static Connection connect() {
        try {
            Properties properties = new Properties();
            properties.load(MySQLConnect.class.getClassLoader().getResourceAsStream("database.properties"));

            String driver = properties.getProperty("jdbc.driver");
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");

            System.out.println(driver + " " + url + " " + username + " " + password);

            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load properties file");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to load driver");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database");
        }

        return null;
    }
}
