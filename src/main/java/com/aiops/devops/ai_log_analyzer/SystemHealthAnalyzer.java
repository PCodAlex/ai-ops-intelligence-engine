package com.aiops.devops.ai_log_analyzer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SystemHealthAnalyzer {
	public static void calculateHealth() {

        try {
            Connection con = DBConnection.getConnection();

            String query = """
            SELECT s.system_id, s.system_name, COUNT(l.log_id) as total_errors
            FROM systems s
            LEFT JOIN logs l ON s.system_id = l.system_id
            GROUP BY s.system_id, s.system_name
            """;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n===== SYSTEM HEALTH REPORT =====");

            while(rs.next()){
                int systemId = rs.getInt("system_id");
                String name = rs.getString("system_name");
                int errors = rs.getInt("total_errors");

                int health = 100 - (errors/50); // AI logic
                if(health < 0) health = 0;

                String status="HEALTHY";
                if(health<70) status="WARNING";
                if(health<40) status="CRITICAL";

                System.out.println("System: "+name+
                        " | Errors: "+errors+
                        " | Health Score: "+health+"/100"+
                        " | Status: "+status);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
