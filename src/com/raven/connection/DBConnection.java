package com.raven.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/penjadwalan_db";
            String user = "root";
            String pass = ""; // ganti sesuai password MySQL kamu

            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
