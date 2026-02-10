package com.aiops.devops.ai_log_analyzer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import java.util.List;


public class DatasetGenerator {
	static String[] errors = {
            "DB_CONN_FAIL","MEMORY_LEAK","CPU_OVERLOAD",
            "DISK_FULL","AUTH_FAIL","API_TIMEOUT",
            "NULL_POINTER","NETWORK_DOWN","SERVICE_STOP"
    };

    public static void generateLogs(int total){

        try{
            Connection con = DBConnection.getConnection();
            Random r = new Random();

            // 🔥 Fetch REAL system IDs from DB
            List<Integer> systemIds = new ArrayList<>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT system_id FROM systems");

            while(rs.next()){
                systemIds.add(rs.getInt("system_id"));
            }

            if(systemIds.isEmpty()){
                System.out.println("No systems found. Insert systems first.");
                return;
            }

            String sql="INSERT INTO logs(system_id,log_level,error_code,message) VALUES(?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);

            for(int i=1;i<=total;i++){

                int systemId = systemIds.get(r.nextInt(systemIds.size()));
                String error = errors[r.nextInt(errors.length)];

                ps.setInt(1,systemId);
                ps.setString(2,"ERROR");
                ps.setString(3,error);
                ps.setString(4,"Auto generated log "+i);

                ps.executeUpdate();
            }

            System.out.println("🔥 "+total+" logs generated successfully");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
