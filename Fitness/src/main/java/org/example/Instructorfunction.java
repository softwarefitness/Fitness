package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Instructorfunction {
    private final Printing printing = new Printing();
    private final Scanner scanner = new Scanner(System.in);

    public void instructorDashboard(String instructorId) throws IOException {
        printing.printSomething("Welcome to the Instructor Dashboard, " + instructorId + "!");
        while (true) {
            printing.printSomething("""
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
                    printing.printSomething("Logging out...");
                    return;
                }
                default -> printing.printSomething("Invalid choice. Please try again.");
            }
        }
    }

    private void handleNotifications(String instructorId) throws IOException {
        printing.printSomething("""
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
            case 4 -> printing.printSomething("Returning to Instructor Dashboard...");
        }
    }

    private void viewNotifications() {
        // Display the list of notifications (just a basic example here)
        printing.printSomething("Displaying all notifications...");
        // Implement notification retrieval logic here
    }

    private void addNotification(String instructorId) throws IOException {
        printing.printSomething("Enter the notification message: ");
        String message = scanner.nextLine();
        String notification = instructorId + ": " + message;
        appendToFile("C:\\path\\to\\notifications.txt", notification);
        printing.printSomething("Notification added successfully!");
    }

    private void deleteNotification() throws IOException {
        printing.printSomething("Enter the notification message to delete: ");
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
            printing.printSomething("Notification deleted successfully!");
        } else {
            printing.printSomething("Notification not found!");
        }
    }

    private void trackProgress(String instructorId) {
        printing.printSomething("Tracking client progress...");
        // Implement client progress tracking logic here
    }

    private void interactWithClients(String instructorId) throws IOException {
        printing.printSomething("""
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
            case 3 -> printing.printSomething("Returning to Instructor Dashboard...");
        }
    }

    private void viewClients() {
        printing.printSomething("Viewing list of clients...");
        // Implement client list retrieval here
    }

    private void sendMessageToClient(String instructorId) throws IOException {
        printing.printSomething("Enter the Client ID: ");
        String clientId = scanner.nextLine();
        printing.printSomething("Enter your message: ");
        String message = scanner.nextLine();
        String messageData = instructorId + " to " + clientId + ": " + message;
        appendToFile("C:\\path\\to\\messages.txt", messageData);
        printing.printSomething("Message sent to client successfully!");
    }

    private void managePrograms(String instructorId) throws IOException {
        printing.printSomething("""
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
            case 4 -> printing.printSomething("Returning to Instructor Dashboard...");
        }
    }

    private void createProgram(String instructorId) throws IOException {
        printing.printSomething("Enter Program Title: ");
        String title = scanner.nextLine();
        printing.printSomething("Enter Duration (e.g., 4 weeks): ");
        String duration = scanner.nextLine();
        printing.printSomething("Enter Difficulty Level (e.g., Beginner, Intermediate, Advanced): ");
        String difficulty = scanner.nextLine();
        printing.printSomething("Enter Goals: ");
        String goals = scanner.nextLine();
        printing.printSomething("Enter Price (if applicable): ");
        String price = scanner.nextLine();
        printing.printSomething("Enter Schedule (e.g., Mondays and Wednesdays at 6 PM): ");
        String schedule = scanner.nextLine();

        String programDetails = instructorId + "," + title + "," + duration + "," + difficulty + "," + goals + "," + price + "," + schedule;
        appendToFile("C:\\path\\to\\programs.txt", programDetails);
        printing.printSomething("Program created successfully!");
    }

    private void updateProgram(String instructorId) throws IOException {
        printing.printSomething("Enter the title of the program to update: ");
        String programTitle = scanner.nextLine();

        List<String> updatedPrograms = new ArrayList<>();
        boolean programFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\path\\to\\programs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[1].equalsIgnoreCase(programTitle) && fields[0].equals(instructorId)) {
                    programFound = true;
                    printing.printSomething("Enter new details for the program:");
                    printing.printSomething("Enter New Duration: ");
                    fields[2] = scanner.nextLine();
                    printing.printSomething("Enter New Difficulty Level: ");
                    fields[3] = scanner.nextLine();
                    printing.printSomething("Enter New Goals: ");
                    fields[4] = scanner.nextLine();
                    printing.printSomething("Enter New Price: ");
                    fields[5] = scanner.nextLine();
                    printing.printSomething("Enter New Schedule: ");
                    fields[6] = scanner.nextLine();
                    line = String.join(",", fields);
                }
                updatedPrograms.add(line);
            }
        }

        if (programFound) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\path\\to\\programs.txt"))) {
                for (String updatedLine : updatedPrograms) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
            printing.printSomething("Program updated successfully!");
        } else {
            printing.printSomething("Program not found!");
        }
    }

    private void deleteProgram(String instructorId) throws IOException {
        printing.printSomething("Enter the title of the program to delete: ");
        String programTitle = scanner.nextLine();

        List<String> remainingPrograms = new ArrayList<>();
        boolean programFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\path\\to\\programs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[1].equalsIgnoreCase(programTitle) && fields[0].equals(instructorId)) {
                    programFound = true;
                    continue; // Skip adding this line to remaining programs
                }
                remainingPrograms.add(line);
            }
        }

        if (programFound) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\path\\to\\programs.txt"))) {
                for (String remainingLine : remainingPrograms) {
                    writer.write(remainingLine);
                    writer.newLine();
                }
            }
            printing.printSomething("Program deleted successfully!");
        } else {
            printing.printSomething("Program not found!");
        }
    }

    private void appendToFile(String filePath, String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
        }
    }

    private int getValidChoice(int min, int max) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
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
}
