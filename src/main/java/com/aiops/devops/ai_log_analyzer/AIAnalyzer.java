package com.aiops.devops.ai_log_analyzer;

import java.sql.*;

public class AIAnalyzer {
	public static void analyzeRisk(){

        try{
            Connection con = DBConnection.getConnection();

            String query = """
            SELECT system_id,error_code,COUNT(*) as total
            FROM logs
            GROUP BY system_id,error_code
            HAVING COUNT(*) >= 3
            """;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){

                int systemId = rs.getInt("system_id");
                String error = rs.getString("error_code");
                int count = rs.getInt("total");

                String risk="LOW";
                int score=count*10;

                if(count>=10){
                    risk="CRITICAL";
                    score=95;
                }
                else if(count>=5){
                    risk="HIGH";
                    score=80;
                }
                else if(count>=3){
                    risk="MEDIUM";
                    score=60;
                }

                System.out.println("\nSYSTEM: "+systemId+
                        " | ERROR: "+error+
                        " | COUNT: "+count+
                        " | RISK: "+risk+
                        " | AI SCORE: "+score);

                String insert="INSERT INTO predictions(system_id,error_code,repeat_count,risk_level) VALUES(?,?,?,?)";
                PreparedStatement ps=con.prepareStatement(insert);
                ps.setInt(1,systemId);
                ps.setString(2,error);
                ps.setInt(3,count);
                ps.setString(4,risk);
                ps.executeUpdate();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
