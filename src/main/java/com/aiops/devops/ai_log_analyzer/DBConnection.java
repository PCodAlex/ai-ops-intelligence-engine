package com.aiops.devops.ai_log_analyzer;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/ai_log_analyzer";
    private static final String user = "postgres";
    private static final String password = "Fkp37Si@n";

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
            return null;
        }
    }
}
