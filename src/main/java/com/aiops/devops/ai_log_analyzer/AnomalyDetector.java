package com.aiops.devops.ai_log_analyzer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AnomalyDetector {
	public static void detectAnomaly(){

        try{
            Connection con = DBConnection.getConnection();

            String query="""
            SELECT error_code, COUNT(*) as total
            FROM logs
            GROUP BY error_code
            HAVING COUNT(*) > 50
            """;

            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

            System.out.println("\n===== ANOMALY DETECTION =====");

            while(rs.next()){
                String error=rs.getString("error_code");
                int count=rs.getInt("total");

                if(count>200){
                    System.out.println("🚨 CRITICAL ANOMALY: "+error+" repeated "+count+" times");
                }
                else{
                    System.out.println("⚠ High occurrence: "+error+" count="+count);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
