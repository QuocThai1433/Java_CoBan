package com.example.demo.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    private static final String PASSWORD = "FormosVN@123";
    private static final String USERNAME = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/student_management";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] arg) {
        if (getConnection() != null) {
            System.out.print("Connect Successful");
        } else {
            System.out.println("Connect fail!!");
        }
    }
}
