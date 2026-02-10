package com.aiops.devops.ai_log_analyzer;

import java.sql.*;
import java.util.Scanner;

public class LogService {
	static Scanner sc = new Scanner(System.in);

    // 🔹 Insert manual log (DevOps testing)
    public static void insertLog(){

        try{
            Connection con = DBConnection.getConnection();

            System.out.print("Enter System ID (1-5): ");
            int systemId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Log Level (INFO/WARNING/ERROR): ");
            String level = sc.nextLine();

            System.out.print("Enter Error Code: ");
            String error = sc.nextLine();

            System.out.print("Enter Message: ");
            String msg = sc.nextLine();

            String sql="INSERT INTO logs(system_id,log_level,error_code,message) VALUES(?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);

            ps.setInt(1,systemId);
            ps.setString(2,level);
            ps.setString(3,error);
            ps.setString(4,msg);

            ps.executeUpdate();

            System.out.println("✅ Log inserted successfully");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 🔹 View all logs
    public static void viewLogs(){

        try{
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM logs ORDER BY log_id DESC LIMIT 20");

            System.out.println("\n---- Recent Logs ----");

            while(rs.next()){
                System.out.println(
                        rs.getInt("log_id")+" | "+
                        rs.getInt("system_id")+" | "+
                        rs.getString("error_code")+" | "+
                        rs.getString("log_level")+" | "+
                        rs.getString("message")+" | "+
                        rs.getTimestamp("log_time")
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 🔹 Delete log
    public static void deleteLog(){

        try{
            Connection con = DBConnection.getConnection();

            System.out.print("Enter log ID to delete: ");
            int id=sc.nextInt();

            String sql="DELETE FROM logs WHERE log_id=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);

            int rows=ps.executeUpdate();

            if(rows>0)
                System.out.println("🗑 Log deleted");
            else
                System.out.println("Log not found");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
