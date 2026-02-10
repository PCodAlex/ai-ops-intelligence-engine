package com.aiops.devops.ai_log_analyzer;

import java.util.Scanner;


public class Main {
	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true){
        	System.out.println("\n=========== AI OPS INTELLIGENCE ENGINE ===========");
            System.out.println("1. Generate 5000 Logs (Dataset)");
            System.out.println("2. Insert Manual Log");
            System.out.println("3. View Recent Logs");
            System.out.println("4. Delete Log");
            System.out.println("5. Analyze AI Risk Prediction");
            System.out.println("6. DevOps Dashboard");
            System.out.println("7. System Health Score");
            System.out.println("8. Anomaly Detection (AI)");
            System.out.println("9. Smart Alert Engine");
            System.out.println("10. System Ranking (Most Unstable)");
            System.out.println("11. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch(ch){

                case 1:
                    DatasetGenerator.generateLogs(5000);
                    break;

                case 2:
                    LogService.insertLog();
                    break;

                case 3:
                    LogService.viewLogs();
                    break;

                case 4:
                    LogService.deleteLog();
                    break;

                case 5:
                    AIAnalyzer.analyzeRisk();
                    break;

                case 6:
                    Dashboard.showDashboard();
                    break;

                case 7:
                    SystemHealthAnalyzer.calculateHealth();
                    break;

                case 8:
                    AnomalyDetector.detectAnomaly();
                    break;

                case 9:
                    AlertEngine.generateAlerts();
                    break;

                case 10:
                    SystemRanking.showRanking();
                    break;

                case 11:
                    System.out.println("Exiting AI Ops System...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
