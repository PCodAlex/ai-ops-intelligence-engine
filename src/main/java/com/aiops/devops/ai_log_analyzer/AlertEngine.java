package com.aiops.devops.ai_log_analyzer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AlertEngine {
	public static void generateAlerts(){

        try{
            Connection con = DBConnection.getConnection();

            String query="""
            SELECT system_id,error_code,COUNT(*) as total
            FROM logs
            GROUP BY system_id,error_code
            HAVING COUNT(*) >= 10
            """;

            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

            System.out.println("\n===== SMART ALERT ENGINE =====");

            while(rs.next()){
                int system=rs.getInt("system_id");
                String error=rs.getString("error_code");
                int count=rs.getInt("total");

                System.out.println("🚨 ALERT: System "+system+
                        " has critical error "+error+
                        " repeated "+count+" times");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
