package org.example;

import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
        function adminDashboard = new function(); // Instance of AdminDashboard
        Scanner scanner = new Scanner(System.in);
        Printing printing = new Printing();

        boolean running = true;
        while (running) {
            printWelcomeMenu(); // Display main menu
            int choice = getValidIntegerInput(scanner, printing);

            switch (choice) {
                case 1 -> adminLogin(adminDashboard, scanner, printing); // Admin Login
                case 2 -> {
                    printing.printSomething("Exiting system. Goodbye!");
                    running = false; // Exit the application
                }
                default -> printing.printSomething("Invalid choice! Please try again.");
            }
        }
        scanner.close(); // Ensure scanner is closed to avoid resource leaks
    }

    private static void printWelcomeMenu() {
        System.out.println("""
            -------- Welcome to Fitness Management System --------
            |                                                   |
            |                1. Admin Login                    |
            |                2. Exit                           |
            |                                                   |
            -----------------------------------------------------
            Enter your choice:
        """);
    }

    private static int getValidIntegerInput(Scanner scanner, Printing printing) {
        while (!scanner.hasNextInt()) {
            printing.printSomething("Invalid input! Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void adminLogin(function  adminDashboard, Scanner scanner, Printing printing) throws Exception {
        printing.printSomething("Enter Admin ID: ");
        String adminId = scanner.next();
        printing.printSomething("Enter Admin Password: ");
        String adminPassword = scanner.next();

        // Dummy admin credentials for demonstration
        String validAdminId = "loaa";
        String validAdminPassword = "12345";

        // Validate admin credentials
        if (adminId.equals(validAdminId) && adminPassword.equals(validAdminPassword)) {
            printing.printSomething("Login successful! Welcome, Admin.");
            adminDashboard.adminPage(adminId); // Enter Admin Dashboard
        } else {
            printing.printSomething("Invalid Admin credentials. Please try again.");
        }
    }
}
