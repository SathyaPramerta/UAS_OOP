package uas_oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private static Connection mysqlconfig;
    
    public static Connection getKoneksi() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_rakitpc?useSSL=false"; 
            String user = "root"; 
            String pass = ""; 
            
            mysqlconfig = DriverManager.getConnection(url, user, pass);
            return mysqlconfig;
        } catch (SQLException e) {
            System.out.println("Koneksi Database Gagal: " + e.getMessage());
            return null;
        }
    }
}