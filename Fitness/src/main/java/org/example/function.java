package org.example;

import java.io.*;
import java.util.*;

public class function {

    // Constants for file names
    private static final String USER_FILE = "users.txt";
    private static final String PROGRAM_FILE = "programs.txt";
    private static final String CONTENT_FILE = "content.txt";
    private static final String FEEDBACK_FILE = "feedback.txt";
    private static final String SUBSCRIPTION_FILE = "subscriptions.txt";

    private final Printing printing = new Printing();
    private final Scanner scanner = new Scanner(System.in);

    public void adminPage(String adminId) throws Exception {
        while (true) {
            printing.printSomething("""
                -------- Admin Dashboard --------
                | 1. User Management           |
                | 2. Program Monitoring         |
                | 3. Content Management         |
                | 4. Subscription Management    |
                | 5. Log Out                    |
                ---------------------------------
                Enter your choice:
            """);

            int choice = getValidChoice(1, 5);
            switch (choice) {
                case 1 -> handleUserManagement();
                case 2 -> handleProgramMonitoring();
                case 3 -> handleContentManagement();
                case 4 -> handleSubscriptionManagement();
                case 5 -> {
                    printing.printSomething("Logging out...");
                    return;
                }
                default -> printing.printSomething("Invalid choice. Try again.");
            }
        }
    }

    private void handleUserManagement() throws IOException {
        printing.printSomething("""
            ---- User Management ----
            | 1. Add User            |
            | 2. Update User         |
            | 3. Deactivate User     |
            | 4. Approve Instructor  |
            | 5. Back                |
            -------------------------
            Enter your choice:
        """);

        int choice = getValidChoice(1, 5);
        switch (choice) {
            case 1 -> addUser();
            case 2 -> updateUser();
            case 3 -> deactivateUser();
            case 4 -> approveInstructor();
            case 5 -> printing.printSomething("Returning to Admin Dashboard...");
        }
    }

    private void addUser() throws IOException {
        printing.printSomething("Enter user type (1: Client, 2: Instructor): ");
        int userType = getValidChoice(1, 2);

        scanner.nextLine(); // Consume newline
        printing.printSomething("Enter Name: ");
        String name = scanner.nextLine();
        printing.printSomething("Enter Email: ");
        String email = scanner.nextLine();
        printing.printSomething("Enter Phone: ");
        String phone = scanner.nextLine();

        String id = UUID.randomUUID().toString(); // Generate a unique ID
        printing.printSomething("User ID assigned: " + id);

        String userTypeString = (userType == 1) ? "Client" : "Instructor";
        String status = (userType == 2) ? "Pending Approval" : "Active";

        String userRecord = id + "," + name + "," + email + "," + phone + "," + userTypeString + "," + status;

        // Determine the file to which the record should be added
        String targetFile = (userType == 1) ? "clients.txt" : "instructors.txt";

        appendToFile(targetFile, userRecord);
        printing.printSomething(userTypeString + " added successfully to " + targetFile + "!");
    }


    private void updateUser() throws IOException {
        printing.printSomething("Enter User ID to update: ");
        String userId = scanner.next();
        scanner.nextLine(); // Consume newline

        printing.printSomething("""
            Select field to update:
            1. Name
            2. Email
            3. Phone
        """);
        int field = getValidChoice(1, 3);

        printing.printSomething("Enter new value: ");
        String newValue = scanner.nextLine();

        updateFile(USER_FILE, userId, field, newValue);
        printing.printSomething("User updated successfully!");
    }

    private void deactivateUser() throws IOException {
        printing.printSomething("Enter User ID to deactivate: ");
        String userId = scanner.next();
        updateFile(USER_FILE, userId, 6, "Deactivated");
        printing.printSomething("User deactivated successfully!");
    }

    private void approveInstructor() throws IOException {
        printing.printSomething("Enter Instructor ID to approve: ");
        String instructorId = scanner.next();
        updateFile(USER_FILE, instructorId, 6, "Approved");
        printing.printSomething("Instructor approved successfully!");
    }

    private void handleProgramMonitoring() {
        printing.printSomething("""
            ---- Program Monitoring ----
            | 1. View Stats            |
            | 2. Generate Reports      |
            | 3. Back                  |
            ---------------------------
            Enter your choice:
        """);

        int choice = getValidChoice(1, 3);
        switch (choice) {
            case 1 -> viewProgramStats();
            case 2 -> generateProgramReports();
            case 3 -> printing.printSomething("Returning to Admin Dashboard...");
        }
    }

    private void viewProgramStats() {
        printing.printSomething("Displaying program statistics...");
        // Implement logic to fetch and display program statistics
    }

    private void generateProgramReports() {
        printing.printSomething("Generating program reports...");
        // Implement logic to generate and display reports
    }

    private void handleContentManagement() throws IOException {
        printing.printSomething("""
            ---- Content Management ----
            | 1. Approve Articles       |
            | 2. Handle Feedback        |
            | 3. Back                   |
            ----------------------------
            Enter your choice:
        """);

        int choice = getValidChoice(1, 3);
        switch (choice) {
            case 1 -> approveContent();
            case 2 -> handleFeedback();
            case 3 -> printing.printSomething("Returning to Admin Dashboard...");
        }
    }

    private void approveContent() throws IOException {
        printing.printSomething("Approving articles...");
        // Implement content approval logic
    }

    private void handleFeedback() throws IOException {
        printing.printSomething("Handling user feedback...");
        // Implement feedback handling logic
    }

    private void handleSubscriptionManagement() throws IOException {
        printing.printSomething("""
            ---- Subscription Management ----
            | 1. Add Plan                   |
            | 2. Update Plan                |
            | 3. Delete Plan                |
            | 4. Back                       |
            ---------------------------------
            Enter your choice:
        """);

        int choice = getValidChoice(1, 4);
        switch (choice) {
            case 1 -> addSubscriptionPlan();
            case 2 -> updateSubscriptionPlan();
            case 3 -> deleteSubscriptionPlan();
            case 4 -> printing.printSomething("Returning to Admin Dashboard...");
        }
    }

    private void addSubscriptionPlan() throws IOException {
        printing.printSomething("Enter Plan Name: ");
        String name = scanner.nextLine();
        printing.printSomething("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        printing.printSomething("Enter Duration (in months): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String planRecord = name + "," + price + "," + duration;
        appendToFile(SUBSCRIPTION_FILE, planRecord);
        printing.printSomething("Subscription plan added successfully!");
    }

    private void updateSubscriptionPlan() throws IOException {
        // Implement logic to update subscription plans
    }

    private void deleteSubscriptionPlan() throws IOException {
        // Implement logic to delete subscription plans
    }

    // Helper Methods
    private int getValidChoice(int min, int max) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    return choice;
                }
                printing.printSomething("Invalid choice. Please select between " + min + " and " + max + ".");
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear invalid input
                printing.printSomething("Invalid input. Please enter a number.");
            }
        }
    }

    private void appendToFile(String fileName, String record) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(record);
            writer.newLine();
        }
    }

    private void updateFile(String fileName, String userId, int field, String newValue) throws IOException {
        List<String> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(userId)) {
                    fields[field - 1] = newValue; // Update specified field
                    line = String.join(",", fields);
                }
                records.add(line);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
        }
    }
}
