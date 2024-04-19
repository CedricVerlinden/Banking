package com.cedricverlinden.banking;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnect {
    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "");
        } catch (Exception e) {
            return null;
        }
    }
}
