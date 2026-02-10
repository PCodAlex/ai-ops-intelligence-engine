package com.aiops.devops.ai_log_analyzer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SystemRanking {
	public static void showRanking(){

        try{
            Connection con = DBConnection.getConnection();

            String query="""
            SELECT system_id, COUNT(*) as total
            FROM logs
            GROUP BY system_id
            ORDER BY total DESC
            LIMIT 5
            """;

            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);

            System.out.println("\n===== TOP 5 UNSTABLE SYSTEMS =====");

            while(rs.next()){
                System.out.println("System "+rs.getInt("system_id")+
                        " → Errors: "+rs.getInt("total"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
