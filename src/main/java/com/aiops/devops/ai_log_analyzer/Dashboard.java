package com.aiops.devops.ai_log_analyzer;

import java.sql.*;

public class Dashboard {
	public static void showDashboard(){

        try{
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs1 = st.executeQuery("SELECT COUNT(*) FROM systems");
            rs1.next();
            System.out.println("Total Systems: "+rs1.getInt(1));

            ResultSet rs2 = st.executeQuery("SELECT COUNT(*) FROM logs");
            rs2.next();
            System.out.println("Total Logs: "+rs2.getInt(1));

            ResultSet rs3 = st.executeQuery("SELECT COUNT(*) FROM predictions WHERE risk_level='CRITICAL'");
            rs3.next();
            System.out.println("Critical Alerts: "+rs3.getInt(1));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
