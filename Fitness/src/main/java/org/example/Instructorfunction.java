package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Instructorfunction {
    // Static reference to Scanner
    private static final Scanner scanner = new Scanner(System.in);

    public static void instructorDashboard(String instructorId) throws IOException {
        Printing.printSomething("Welcome to the Instructor Dashboard, " + instructorId + "!");

        while (true) {
            Printing.printSomething("""
                ---- Instructor Options ----
                | 1. Program Management     |
                | 2. Client Interaction      |
                | 3. Progress Tracking       |
                | 4. Notifications and Updates|
                | 5. Log Out                 |
                ------------------------------
                Enter your choice:
            """);
            int choice = getValidChoice(1, 5);
            switch (choice) {
                case 1 -> managePrograms(instructorId);
                case 2 -> interactWithClients(instructorId);
                case 3 -> trackProgress(instructorId);
                case 4 -> handleNotifications(instructorId);
                case 5 -> {
                    Printing.printSomething("Logging out...");
                    return;
                }
                default -> Printing.printSomething("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleNotifications(String instructorId) throws IOException {
        Printing.printSomething("""
            ---- Notifications ----
            | 1. View Notifications   |
            | 2. Add Notification     |
            | 3. Delete Notification  |
            | 4. Back                 |
            --------------------------
            Enter your choice:
        """);
        int choice = getValidChoice(1, 4);
        switch (choice) {
            case 1 -> viewNotifications();
            case 2 -> addNotification(instructorId);
            case 3 -> deleteNotification();
            case 4 -> Printing.printSomething("Returning to Instructor Dashboard...");
        }
    }

    private static void viewNotifications() {
        Printing.printSomething("Displaying all notifications...");
        // Implement notification retrieval logic here
    }

    private static void addNotification(String instructorId) throws IOException {
        Printing.printSomething("Enter the notification message: ");
        String message = scanner.nextLine();
        String notification = instructorId + ": " + message;
        appendToFile("C:\\path\\to\\notifications.txt", notification);
        Printing.printSomething("Notification added successfully!");
    }

    private static void deleteNotification() throws IOException {
        Printing.printSomething("Enter the notification message to delete: ");
        String message = scanner.nextLine();
        List<String> remainingNotifications = new ArrayList<>();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\path\\to\\notifications.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(message)) {
                    remainingNotifications.add(line);
                } else {
                    found = true;
                }
            }
        }
        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\path\\to\\notifications.txt"))) {
                for (String line : remainingNotifications) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            Printing.printSomething("Notification deleted successfully!");
        } else {
            Printing.printSomething("Notification not found!");
        }
    }

    private static void trackProgress(String instructorId) {
        Printing.printSomething("Tracking client progress...");
        // Implement client progress tracking logic here
    }

    private static void interactWithClients(String instructorId) throws IOException {
        Printing.printSomething("""
            ---- Client Interaction ----
            | 1. View Clients          |
            | 2. Send Message to Client|
            | 3. Back                  |
            ----------------------------
            Enter your choice:
        """);
        int choice = getValidChoice(1, 3);
        switch (choice) {
            case 1 -> viewClients();
            case 2 -> sendMessageToClient(instructorId);
            case 3 -> Printing.printSomething("Returning to Instructor Dashboard...");
        }
    }

    private static void viewClients() {
        Printing.printSomething("Viewing list of clients...");
        // Implement client list retrieval here
    }

    private static void sendMessageToClient(String instructorId) throws IOException {
        Printing.printSomething("Enter the Client ID: ");
        String clientId = scanner.nextLine();
        Printing.printSomething("Enter your message: ");
        String message = scanner.nextLine();
        String messageData = instructorId + " to " + clientId + ": " + message;
        appendToFile("C:\\path\\to\\messages.txt", messageData);
        Printing.printSomething("Message sent to client successfully!");
    }

    private static void managePrograms(String instructorId) throws IOException {
        Printing.printSomething("""
            ---- Program Management ----
            | 1. Create Program          |
            | 2. Update Program          |
            | 3. Delete Program          |
            | 4. Back                    |
            ------------------------------
            Enter your choice:
        """);
        int choice = getValidChoice(1, 4);
        switch (choice) {
            case 1 -> createProgram(instructorId);
            case 2 -> updateProgram(instructorId);
            case 3 -> deleteProgram(instructorId);
            case 4 -> Printing.printSomething("Returning to Instructor Dashboard...");
        }
    }

    private static void createProgram(String instructorId) throws IOException {
        Printing.printSomething("Enter Program Title: ");
        String title = scanner.nextLine();
        Printing.printSomething("Enter Duration (e.g., 4 weeks): ");
        String duration = scanner.nextLine();
        Printing.printSomething("Enter Difficulty Level (e.g., Beginner, Intermediate, Advanced): ");
        String difficulty = scanner.nextLine();
        Printing.printSomething("Enter Goals: ");
        String goals = scanner.nextLine();
        Printing.printSomething("Enter Price (if applicable): ");
        String price = scanner.nextLine();
        Printing.printSomething("Enter Schedule (e.g., Mondays and Wednesdays at 6 PM): ");
        String schedule = scanner.nextLine();
        String programDetails = instructorId + "," + title + "," + duration + "," + difficulty + "," + goals + "," + price + "," + schedule;
        appendToFile("C:\\path\\to\\programs.txt", programDetails);
        Printing.printSomething("Program created successfully!");
    }

    private static void appendToFile(String filePath, String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
        }
    }

    private static int getValidChoice(int min, int max) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                if (choice >= min && choice <= max) {
                    return choice;
                }
                Printing.printSomething("Invalid choice. Please select between " + min + " and " + max + ".");
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Clear invalid input
                Printing.printSomething("Invalid input. Please enter a number.");
            }
        }
    }
}
