package com.homework2;

import DatabaseQueryServiceBD.DatabaseQueryService;
import ClientGetAndSet.LongestProject;
import ClientGetAndSet.MaxProjectCountClient;
import ClientGetAndSet.MaxSalaryWorker;
import ClientGetAndSet.YoungestEldestWorkers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseQueryService service = null; // Перемещаем объявление вне блока try
        try {
            service = new DatabaseQueryService();

            // Виклик та виведення результатів для findMaxProjectsClient
            System.out.println("Max Projects Count Clients:");
            List<MaxProjectCountClient> maxProjectCountClients = service.findMaxProjectsClient();
            for (MaxProjectCountClient client : maxProjectCountClients) {
                System.out.println(client.getName() + " - " + client.getProjectCount());
            }

            // Виклик та виведення результатів для findLongestProject
            System.out.println("\nLongest Projects:");
            List<LongestProject> longestProjects = service.findLongestProject();
            for (LongestProject project : longestProjects) {
                System.out.println(project.getProjectName() + " - Duration: " + project.getDurationMonths() + " months");
            }

            // Виклик та виведення результатів для findMaxSalaryWorker
            System.out.println("\nWorker with Max Salary:");
            MaxSalaryWorker maxSalaryWorker = service.findMaxSalaryWorker();
            if (maxSalaryWorker != null) {
                System.out.println(maxSalaryWorker.getWorkerName() + " - Salary: $" + maxSalaryWorker.getSalary());
            }

            // Виклик та виведення результатів для findYoungestEldestWorkers
            System.out.println("\nYoungest and Eldest Workers:");
            List<YoungestEldestWorkers> youngestEldestWorkers = service.findYoungestEldestWorkers();
            for (YoungestEldestWorkers worker : youngestEldestWorkers) {
                System.out.println(worker.getWorkerName() + " - Birthday: " + worker.getBirthday());
            }

        } catch (IOException | SQLException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (service != null) {
                    service.closeConnection();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
