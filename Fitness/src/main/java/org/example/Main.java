package org.example;

import java.util.Scanner;

public class Main { // Class name updated to follow Java naming convention
    public static void main(String[] args) throws Exception {
        function dashboard = new function(); // Instance of Function class
        Scanner scanner = new Scanner(System.in);
        Printing printing = new Printing();

        boolean running = true;
        while (running) {
            printWelcomeMenu(); // Display main menu
            int choice = getValidIntegerInput(scanner, printing);

            switch (choice) {
                case 1 -> adminLogin(dashboard, scanner, printing); // Admin Login
                case 2 -> clientLogin(scanner, printing); // Client Login
                case 3 -> instructorLogin(scanner, printing); // Instructor Login
                case 4 -> {
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
            |                2. Client Login                   |
            |                3. Instructor Login               |
            |                4. Exit                           |
            |                                                   |
            -----------------------------------------------------  
            Enter your choice:
        """);
    }

    private static int getValidIntegerInput(Scanner scanner, Printing printing) {
        while (!scanner.hasNextInt()) {
            printing.printSomething("Invalid input! Please enter a valid number.");
            scanner.next(); // Clear the invalid input
        }
        return scanner.nextInt();
    }

    private static void adminLogin(function dashboard, Scanner scanner, Printing printing) throws Exception {
        printing.printSomething("Enter Admin ID: ");
        String adminId = scanner.next();
        printing.printSomething("Enter Admin Password: ");
        String adminPassword = scanner.next();

        String validAdminId = "loaa";
        String validAdminPassword = "12345";

        if (adminId.equals(validAdminId) && adminPassword.equals(validAdminPassword)) {
            printing.printSomething("Login successful! Welcome, Admin.");
            dashboard.adminPage(adminId); // Enter Admin Dashboard
        } else {
            printing.printSomething("Invalid Admin credentials. Please try again.");
            // Optionally add a retry mechanism here
        }
    }

    private static void clientLogin(Scanner scanner, Printing printing) throws Exception {
        printing.printSomething("Enter Client ID: ");
        String clientId = scanner.next();
        printing.printSomething("Enter Client Password: ");
        String clientPassword = scanner.next();

        String validClientId = "client123";
        String validClientPassword = "password";

        if (clientId.equals(validClientId) && clientPassword.equals(validClientPassword)) {
            printing.printSomething("Login successful! Welcome, Client.");
            ClientFunction clientFunction = new ClientFunction();
            clientFunction.clientDashboard(clientId); // Correct method for Client Dashboard
        } else {
            printing.printSomething("Invalid Client credentials. Please try again.");
            // Optionally add a retry mechanism here
        }
    }

    private static void instructorLogin(Scanner scanner, Printing printing) throws Exception {
        printing.printSomething("Enter Instructor ID: ");
        String instructorId = scanner.next();
        printing.printSomething("Enter Instructor Password: ");
        String instructorPassword = scanner.next();

        String validInstructorId = "instructor1";
        String validInstructorPassword = "instructorpass";

        if (instructorId.equals(validInstructorId) && instructorPassword.equals(validInstructorPassword)) {
            printing.printSomething("Login successful! Welcome, Instructor.");
            Instructorfunction instructorFunction = new Instructorfunction();
            instructorFunction.instructorDashboard(instructorId); // Correct method for Instructor Dashboard
        } else {
            printing.printSomething("Invalid Instructor credentials. Please try again.");
            // Optionally add a retry mechanism here
        }
    }
}
