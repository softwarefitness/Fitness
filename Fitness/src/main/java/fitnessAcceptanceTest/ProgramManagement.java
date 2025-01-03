package fitnessAcceptanceTest;

import java.io.*;
import java.util.*;

public class ProgramManagement {

    class Client {
        int id;
        String name;
        int age;
        String fitnessGoals;
        String dietaryPreferences;
        String accountStatus;

        public Client(int id, String name, int age, String fitnessGoals, String dietaryPreferences) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.fitnessGoals = fitnessGoals;
            this.dietaryPreferences = dietaryPreferences;
            this.accountStatus = "Pending";
        }

        public String getName() {
            return name;
        }

        public String getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(String status) {
            this.accountStatus = status;
        }

        public void updateProfile(String newName, int newAge, String newGoals, String newPreferences) {
            this.name = newName;
            this.age = newAge;
            this.fitnessGoals = newGoals;
            this.dietaryPreferences = newPreferences;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Fitness Goals: " + fitnessGoals + ", Dietary Preferences: " + dietaryPreferences;
        }
    }

    class AccountManagementSystem {
        private Map<Integer, Client> clients = new HashMap<>();
        private int clientCounter = 1;

        public int createProfile(String name, int age, String fitnessGoals, String dietaryPreferences) {
            Client newClient = new Client(clientCounter, name, age, fitnessGoals, dietaryPreferences);
            clients.put(clientCounter, newClient);
            return clientCounter++;
        }

        // Duplicating this approve function to allow approve logic for ProgramManagement
        public void approve() throws IOException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Listing all users pending approval:\n");

            // List all pending approvals for clients
            listPendingApprovals("clients.txt", "Client");

            // List all pending approvals for instructors
            listPendingApprovals("instructors.txt", "Instructor");

            System.out.print("\nEnter User ID to approve: ");
            String userId = scanner.next(); // Get user ID

            // Try approving the user in clients file
            boolean updatedInClients = updateUserStatus(
                "clients.txt",
                userId,
                "Active"
            );

            // Try approving the user in instructors file if not found in clients
            if (!updatedInClients) {
                boolean updatedInInstructors = updateUserStatus(
                    "instructors.txt",
                    userId,
                    "Active"
                );
                if (!updatedInInstructors) {
                    System.out.println("User not found in either clients or instructors files.");
                }
            }
        }

        private void listPendingApprovals(String fileName, String userType) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            boolean foundPending = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[4].equals("Pending")) {
                    System.out.println(userType + " ID: " + data[0] + ", Name: " + data[1] + ", Status: " + data[4]);
                    foundPending = true;
                }
            }
            if (!foundPending) {
                System.out.println("No pending " + userType + "s for approval.");
            }
            reader.close();
        }

        private boolean updateUserStatus(String fileName, String userId, String newStatus) throws IOException {
            File inputFile = new File(fileName);
            File tempFile = new File("temp_" + fileName);

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean userFound = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(userId) && data[4].equals("Pending")) {
                    data[4] = newStatus; // Update the status
                    userFound = true;
                }
                writer.write(String.join(",", data));
                writer.newLine();
            }

            reader.close();
            writer.close();

            // Replace the original file with the temporary one
            if (userFound) {
                if (tempFile.renameTo(inputFile)) {
                    System.out.println("User " + userId + " has been approved.");
                    return true;
                } else {
                    System.out.println("Failed to update user status.");
                    return false;
                }
            } else {
                System.out.println("User with ID " + userId + " not found or not in Pending status.");
                return false;
            }
        }
    }

    // Main entry point for the program to simulate approval logic
    public static void main(String[] args) throws IOException {
        ProgramManagement programManagement = new ProgramManagement();
        AccountManagementSystem accountManagement = programManagement.new AccountManagementSystem();

        // Create some sample profiles for testing
        accountManagement.createProfile("John Doe", 25, "Lose Weight", "Vegetarian");
        accountManagement.createProfile("Jane Smith", 30, "Build Muscle", "None");

        // Simulating the approve process
        accountManagement.approve();
    }
}
