package com.sms.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/college_portal";
    private static final String USER = "root";
    private static final String PASSWORD = "Sindhu@2006";   // <-- PUT YOUR REAL PASSWORD

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");

            con = DriverManager.getConnection(URL, USER, PASSWORD);

            if (con != null)
                System.out.println("DATABASE CONNECTED");
            else
                System.out.println("DATABASE NOT CONNECTED");

        } catch (Exception e) {
            System.out.println("CONNECTION FAILED");
            e.printStackTrace();
        }
        return con;
    }

}
