package org.example;

import java.io.*;
import java.util.*;

public class function {

    // Constants for file names
    private static final String USER_FILE = "users.txt";
    private static final String PROGRAM_FILE = "programs.txt";
    private static final String CONTENT_FILE = "content.txt";
    private static final String FEEDBACK_FILE = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\feedback.txt";
    private static final String SUBSCRIPTION_FILE = "subscriptions.txt";

    private final Printing printing = new Printing();
    private final Scanner scanner = new Scanner(System.in);
    
    //signup 
    public void signUpFunction() throws Exception {
        printing.printSomething("Sign Up Page");
        printing.printSomething("1. Sign Up as Client");
        printing.printSomething("2. Sign Up as Instructor");
        printing.printSomething("Enter your choice: ");
        int choice = getValidChoice(1, 2);

        scanner.nextLine(); // Clear buffer
        printing.printSomething("Enter Name: ");
        String name = scanner.nextLine();
        printing.printSomething("Enter Email: ");
        String email = scanner.nextLine();
        printing.printSomething("Enter Phone: ");
        String phone = scanner.nextLine();
        printing.printSomething("Enter Password: ");
        String password = scanner.nextLine();

        // Generate a unique ID
        String id = generateUniqueId();

        // Append the user to the appropriate file
        if (choice == 1) {
            signUpClient(id, name, email, phone, password);
        } else if (choice == 2) {
            signUpInstructor(id, name, email, phone, password);
        }
    }

    private String generateUniqueId() {
        // Generate a random 3-digit unique ID
        return String.format("%03d", new Random().nextInt(900) + 100);
    }

    private void signUpClient(String id, String name, String email, String phone, String password) throws IOException {
        String clientFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\clients.txt";
        String status = "Pending Approval";
        String clientRecord = String.join(",", id, name, email, phone, password, status);

        appendToFile(clientFilePath, clientRecord);
        printing.printSomething("Client signed up successfully! Your ID is: " + id);
        printing.printSomething("An admin will approve your account soon.");
    }

    private void signUpInstructor(String id, String name, String email, String phone, String password) throws IOException {
        String instructorFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\instructors.txt";
        String status = "Pending Approval";
        String instructorRecord = String.join(",", id, name, email, phone, password, status);

        appendToFile(instructorFilePath, instructorRecord);
        printing.printSomething("Instructor signed up successfully! Your ID is: " + id);
        printing.printSomething("An admin will approve your account soon.");
    }

    private void appendToFile(String filePath, String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            printing.printError("Error writing to file: " + e.getMessage());
            throw e;
        }
    }

    
    

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
            | 4. Approve   |
            | 5. Back                |
            -------------------------
            Enter your choice:
        """);

        int choice = getValidChoice(1, 5);
        switch (choice) {
            case 1 -> addUser();
            case 2 -> updateUser();
            case 3 -> deactivateUser();
            case 4 -> approve();
            case 5 -> printing.printSomething("Returning to Admin Dashboard...");
        }
    }
//add user
    private boolean isDuplicateUser(String filePath, String id, String email) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(id) || fields[2].equalsIgnoreCase(email)) { // Check for duplicate ID or email
                    return true;
                }
            }
        }
        return false;
    }

    public void addUser() throws IOException {
        printing.printSomething("Enter user type (1: Client, 2: Instructor): ");
        int userType = getValidChoice(1, 2);

        scanner.nextLine(); // Consume newline
        printing.printSomething("Enter Name: ");
        String name = scanner.nextLine();
        printing.printSomething("Enter Email: ");
        String email = scanner.nextLine();

        // Email validation
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            printing.printSomething("Invalid email format. Please enter a valid email.");
            return;
        }

        printing.printSomething("Enter Phone: ");
        String phone = scanner.nextLine();
        printing.printSomething("Program: ");
        String program = scanner.nextLine();

        // Generate unique 3-digit ID
        String id = String.format("%03d", new Random().nextInt(900) + 100);
        printing.printSomething("User ID assigned: " + id);

        String userTypeString = (userType == 1) ? "Client" : "Instructor";
        String status = "Pending Approval";

        String userRecord = id + "," + name + "," + email + "," + phone + "," + program + "," + userTypeString + "," + status;

        // Determine the target file
        String targetFilePath = (userType == 1)
                ? "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\clients.txt"
                : "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\instructors.txt";

        // Check for duplicates
        if (isDuplicateUser(targetFilePath, id, email)) {
            printing.printSomething("Duplicate user detected. Cannot add user.");
            return;
        }

        // Append to the file
        appendToFile1(targetFilePath, userRecord);
        printing.printSomething(userTypeString + " added successfully!");
    }


//update
    public void updateUser() throws IOException {
        printing.printSomething("Enter User ID to update: ");
        String userId = scanner.next(); // Read the user ID
        scanner.nextLine(); // Clear the newline left in the input buffer

        // Search for the user in both files
        boolean foundInClients = userExistsInFile("C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\clients.txt", userId);
        boolean foundInInstructors = userExistsInFile("C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\instructors.txt", userId);

        if (!foundInClients && !foundInInstructors) {
            printing.printSomething("User with ID " + userId + " not found in clients or instructors.");
            return; // Exit the method if ID is not found
        }

        printing.printSomething("""
            Select field to update:
            1. Name
            2. Email
            3. Phone
            4. Program
        """);
        int field = getValidChoice(1, 4);
        scanner.nextLine(); // Clear buffer

        printing.printSomething("Enter new value: ");
        String newValue = scanner.nextLine(); // Wait for user input

        // Update the user in the relevant file
        if (foundInClients) {
            updateUserInFile("C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\clients.txt", userId, field, newValue);
            printing.printSomething("Client with ID " + userId + " updated successfully!");
        } else if (foundInInstructors) {
            updateUserInFile("C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\instructors.txt", userId, field, newValue);
            printing.printSomething("Instructor with ID " + userId + " updated successfully!");
        }
    }
    private boolean userExistsInFile(String filePath, String userId) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(userId + ",")) { // Check if the line starts with the user ID
                    return true;
                }
            }
        }
        return false; // Return false if the ID is not found
    }

    public boolean updateUserInFile(String filePath, String userId, int field, String newValue) throws IOException {
        File file = new File(filePath);
        List<String> updatedRecords = new ArrayList<>();
        boolean userFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(userId)) { // Check if the ID matches
                    userFound = true;
                    // Update the specified field (fields are 0-indexed, adjust field index accordingly)
                    fields[field] = newValue; // Update the desired field
                    line = String.join(",", fields); // Reconstruct the line
                }
                updatedRecords.add(line); // Add the (possibly updated) line to the records
            }
        }

        if (userFound) {
            // Rewrite the file with updated records
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String record : updatedRecords) {
                    writer.write(record);
                    writer.newLine();
                }
            }
        }

        return userFound;
    }


//delete
    public void deactivateUser() throws IOException {
        printing.printSomething("Enter User ID to deactivate: ");
        String userId = scanner.next();

        // Try to remove the user from the clients file
        boolean removedFromClients = removeUserFromFile("C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\clients.txt", userId);
        if (removedFromClients) {
            printing.printSomething("Client with ID " + userId + " deactivated successfully!");
            return;
        }

        // Try to remove the user from the instructors file
        boolean removedFromInstructors = removeUserFromFile("C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\instructors.txt", userId);
        if (removedFromInstructors) {
            printing.printSomething("Instructor with ID " + userId + " deactivated successfully!");
            return;
        }

        // If not found in either file
        printing.printSomething("User with ID " + userId + " not found in clients or instructors.");
    }
    private boolean removeUserFromFile(String filePath, String userId) throws IOException {
        File file = new File(filePath);
        List<String> updatedRecords = new ArrayList<>();
        boolean userFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the user ID
                if (line.startsWith(userId + ",")) {
                    userFound = true; // Mark user as found and skip adding their record
                    continue;
                }
                updatedRecords.add(line);
            }
        }

        if (userFound) {
            // Rewrite the file with updated records
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String record : updatedRecords) {
                    writer.write(record);
                    writer.newLine();
                }
            }
        }

        return userFound;
    }
//approve
    public void approve() throws IOException {
        printing.printSomething("Listing all users pending approval:\n");

        // List all pending approvals for clients and instructors
        listPendingApprovals("C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\clients.txt", "Client");
        listPendingApprovals("C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\instructors.txt", "Instructor");

        printing.printSomething("\nEnter User ID to approve: ");
        String userId = scanner.next(); // Get user ID

        // Try approving the user in clients file
        boolean updatedInClients = updateUserStatus(
            "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\clients.txt",
            userId,
            "Approved"
        );

        // Try approving the user in instructors file if not found in clients
        boolean updatedInInstructors = false;
        if (!updatedInClients) {
            updatedInInstructors = updateUserStatus(
                "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\instructors.txt",
                userId,
                "Approved"
            );
        }

        // Handle enrollment updates
        if (updatedInClients) {
            printing.printSomething("Client with ID " + userId + " approved successfully!");
            addEnrollmentForClient(userId); // Add the client to enrollments
        } else if (updatedInInstructors) {
            printing.printSomething("Instructor with ID " + userId + " approved successfully!");
            updateInstructorInEnrollments(userId); // Link the instructor to the program in enrollments
        } else {
            printing.printSomething("User with ID " + userId + " not found in clients or instructors.");
        }
    }
    private void addEnrollmentForClient(String clientId) throws IOException {
        String clientsFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\clients.txt";
        String enrollmentsFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\enrollments.txt";

        String program = null;

        // Find the client's program from clients.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(clientsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(clientId)) {
                    program = fields[4]; // Assuming program is stored in the 5th column
                    break;
                }
            }
        }

        if (program == null) {
            printing.printSomething("Failed to find the program for client ID " + clientId);
            return;
        }

        // Find an instructor for the program
        String instructorId = findInstructorForProgram(program);

        // Generate a unique enrollment ID
        String enrollmentId = String.format("%03d", new Random().nextInt(900) + 100);

        // Append the enrollment data to enrollments.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(enrollmentsFilePath, true))) {
            writer.write(enrollmentId + "," + program + "," + clientId + "," + (instructorId != null ? instructorId : "Unassigned"));
            writer.newLine();
        }

        printing.printSomething("Enrollment added for client ID " + clientId + " in program " + program + ".");
    }
    private String findInstructorForProgram(String program) throws IOException {
        String instructorsFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\instructors.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(instructorsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 4 && fields[4].equalsIgnoreCase(program) && "Approved".equalsIgnoreCase(fields[5])) {
                    return fields[0]; // Return instructor ID
                }
            }
        }

        return null; // No instructor found for the program
    }
    public void updateInstructorInEnrollments(String instructorId) throws IOException {
        String enrollmentsFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\enrollments.txt";
        String instructorsFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\instructors.txt";

        String program = null;

        // Find the instructor's program from instructors.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(instructorsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(instructorId)) {
                    program = fields[4]; // Assuming program is stored in the 5th column
                    break;
                }
            }
        }

        if (program == null) {
            printing.printSomething("Failed to find the program for instructor ID " + instructorId);
            return;
        }

        // Update enrollments.txt to assign this instructor to the program
        List<String> updatedRecords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(enrollmentsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[1].equals(program) && "Unassigned".equals(fields[3])) {
                    fields[3] = instructorId; // Assign the instructor ID
                }
                updatedRecords.add(String.join(",", fields));
            }
        }

        // Rewrite the enrollments file with updated records
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(enrollmentsFilePath))) {
            for (String record : updatedRecords) {
                writer.write(record);
                writer.newLine();
            }
        }

        printing.printSomething("Instructor ID " + instructorId + " assigned to program " + program + ".");
    }

    public void listPendingApprovals(String filePath, String userType) throws IOException {
        // Display the file being processed
        printing.printSomething("Reading file: " + filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean hasPending = false;
            
            // Process each line of the file
            while ((line = reader.readLine()) != null) {
                // Split line into fields
                String[] fields = line.split(",");

                // Check if the line has at least 7 fields and the status field (index 6) is "Pending Approval"
                if (fields.length >= 7 && "Pending Approval".equalsIgnoreCase(fields[6].trim())) {
                    if (!hasPending) {
                        printing.printSomething(userType + "s Pending Approval:");
                        hasPending = true;
                    }
                    // Print the details of users with Pending Approval
                    printing.printSomething("ID: " + fields[0] + ", Name: " + fields[1] + ", Email: " + fields[2]);
                }
            }

            // If no pending approvals were found
            if (!hasPending) {
                printing.printSomething("No " + userType + "s pending approval.");
            }
        } catch (IOException e) {
            printing.printError("Error reading file: " + e.getMessage());
        }
    }



    public boolean updateUserStatus(String filePath, String userId, String newStatus) throws IOException {
        File file = new File(filePath);
        List<String> updatedRecords = new ArrayList<>();
        boolean userFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(userId)) { // Check if the user ID matches
                    userFound = true;
                    if (fields.length > 5) {
                        fields[5] = newStatus; // Update the status field (index 5 is assumed for status)
                    }
                    line = String.join(",", fields); // Rebuild the line
                }
                updatedRecords.add(line); // Add (updated or original) line to the updated records
            }
        }

        if (userFound) {
            // Rewrite the file with updated records
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String record : updatedRecords) {
                    writer.write(record);
                    writer.newLine();
                }
            }
        }

        return userFound; // Return true if the user was found and updated
    }

    
    private void handleProgramMonitoring() {
        printing.printSomething("""
            ---- Program Monitoring ----
            | 1. View Statistics                          |
            | 2. Generate Reports on Revenue, Attendance, |
            |    and Client Progress                      |
            | 3. Track Active and Completed Programs      |
            | 4. Back                                     |
            ----------------------------------------------
            Enter your choice:
        """);

        int choice = getValidChoice(1, 4);
        switch (choice) {
            case 1 -> viewProgramStats();
            case 2 -> generateProgramReports();
            case 3 -> trackActiveAndCompletedPrograms();
            case 4 -> printing.printSomething("Returning to Admin Dashboard...");
        }
    }

//program statistics..
    public void viewProgramStats() {
        printing.printSomething("Displaying program statistics...");
        
        // Path to the enrollment file
        String enrollmentsFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\enrollments.txt";
        Map<String, Integer> programEnrollments = new HashMap<>();

        // Read enrollment data and calculate the total enrollments per program
        try (BufferedReader reader = new BufferedReader(new FileReader(enrollmentsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 3) {
                    String programName = fields[1]; // Assuming the program name is in the 2nd column
                    int enrollmentCount = 1; // Count each record as one enrollment (you may adjust based on the data)
                    
                    programEnrollments.put(programName, programEnrollments.getOrDefault(programName, 0) + enrollmentCount);
                }
            }

            // Sort programs by enrollments in descending order
            programEnrollments.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())) // Sorting by value (enrollment count)
                .forEach(entry -> printing.printSomething(entry.getKey() + ": " + entry.getValue() + " enrollments"));

        } catch (IOException e) {
            printing.printError("Error reading enrollment data: " + e.getMessage());
        }
    }

    private void generateProgramReports() {
        printing.printSomething("""
            ---- Generate Reports ----
            | 1. Report for Revenue        |
            | 2. Report for Attendance     |
            | 3. Report for Client Progress|
            | 4. Back                      |
            --------------------------------
            Enter your choice:
        """);

        int choice = getValidChoice(1, 4);
        switch (choice) {
            case 1 -> generateRevenueReport();
            case 2 -> generateAttendanceReport();
            case 3 -> generateClientProgressReport();
            case 4 -> printing.printSomething("Returning to Program Monitoring...");
            default -> printing.printSomething("Invalid choice. Please try again.");
        }
    }

    public void generateRevenueReport() {
        // Paths to the files
        String enrollmentsFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\enrollments.txt";
        String subscriptionsFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\client_plans.txt.txt";

        Map<String, Double> planPrices = new HashMap<>();
        Map<String, Double> programRevenue = new HashMap<>();

        try (BufferedReader subReader = new BufferedReader(new FileReader(subscriptionsFilePath))) {
            String line;

            // Extract subscription plan prices
            while ((line = subReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5) { // Ensure the file has at least 5 fields
                    String planName = fields[0].trim(); // Plan name
                    String priceText = fields[1].trim().split("/")[0]; // Extract numeric price part
                    Double price = Double.parseDouble(priceText); // Parse price
                    planPrices.put(planName, price); // Map plan to its price
                }
            }

            try (BufferedReader enrollReader = new BufferedReader(new FileReader(enrollmentsFilePath))) {
                // Calculate revenue for each program
                while ((line = enrollReader.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields.length >= 3) {
                        String programName = fields[1].trim(); // Program name
                        String planName = fields[2].trim();   // Plan name

                        // Get the price of the plan
                        Double price = planPrices.get(planName);
                        if (price != null) {
                            programRevenue.put(programName, programRevenue.getOrDefault(programName, 0.0) + price);
                        } else {
                            printing.printError("Plan not found for enrollment: " + planName);
                        }
                    }
                }
            }

            // Display the revenue report
            printing.printSomething("Program Revenue Report:");
            if (programRevenue.isEmpty()) {
                printing.printSomething("No revenue data available.");
            } else {
                programRevenue.forEach((program, revenue) ->
                    printing.printSomething(program + ": $" + revenue));
            }

        } catch (IOException | NumberFormatException e) {
            printing.printError("Error generating revenue report: " + e.getMessage());
        }
    }

    public void generateAttendanceReport() {
        printing.printSomething("Generating attendance report...");
        
        String enrollmentsFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\enrollments.txt";
        Map<String, Integer> programAttendance = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(enrollmentsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    String programName = fields[1]; // Program name
                    programAttendance.put(programName, programAttendance.getOrDefault(programName, 0) + 1);
                }
            }

            // Display attendance report
            printing.printSomething("Program Attendance Report:");
            programAttendance.forEach((program, count) -> 
                printing.printSomething(program + ": " + count + " participants"));

        } catch (IOException e) {
            printing.printError("Error generating attendance report: " + e.getMessage());
        }
    }

    public void generateClientProgressReport() {
        printing.printSomething("Generating client progress report...");

        String progressFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\client_progress.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(progressFilePath))) {
            printing.printSomething("Client Progress Report:");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 3) {
                    String clientId = fields[0];
                    String programName = fields[1];
                    String progress = fields[2];
                    printing.printSomething("Client ID: " + clientId + ", Program: " + programName + ", Progress: " + progress);
                }
            }
        } catch (IOException e) {
            printing.printError("Error generating client progress report: " + e.getMessage());
        }
    }

    public void trackActiveAndCompletedPrograms() {
        printing.printSomething("Tracking active and completed programs...");

        String programsFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\fitness_programs.txt"; // Path to programs file
        Map<String, String> programStatus = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(programsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    String programName = fields[0];
                    String status = fields[1]; // Assuming status is stored in the 2nd column (active/completed)
                    programStatus.put(programName, status);
                }
            }

            // Display active and completed programs
            printing.printSomething("Active Programs:");
            programStatus.entrySet().stream()
                .filter(entry -> "Active".equalsIgnoreCase(entry.getValue()))
                .forEach(entry -> printing.printSomething(entry.getKey()));

            printing.printSomething("\nCompleted Programs:");
            programStatus.entrySet().stream()
                .filter(entry -> "Completed".equalsIgnoreCase(entry.getValue()))
                .forEach(entry -> printing.printSomething(entry.getKey()));

        } catch (IOException e) {
            printing.printError("Error reading program status: " + e.getMessage());
        }
    }


    
    
    //content management 
    private void handleContentManagement() throws IOException {
        printing.printSomething("""
            ---- Content Management ----
            | 1. Approve Articles       |
            | 2. Approve Tips/Recipes   |
            | 3. Handle Feedback        |
            | 4. Back                   |
            ----------------------------
            Enter your choice:
        """);

        int choice = getValidChoice(1, 4);
        switch (choice) {
            case 1 -> approveContent();
            case 2 -> approveWellnessContent();
            case 3 -> handleFeedback();
            case 4 -> printing.printSomething("Returning to Admin Dashboard...");
        }
    }
    

	public void approveContent() throws IOException {
        printing.printSomething("Approving articles...");

        // Path to articles file
        String articlesFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\articles.txt";

        // List pending articles from the file
        List<String[]> pendingArticles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(articlesFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5 && "Pending".equalsIgnoreCase(fields[4].trim())) {
                    // Add pending articles to the list
                    pendingArticles.add(fields);
                }
            }
        } catch (IOException e) {
            printing.printError("Error reading articles file: " + e.getMessage());
            return;
        }

        // Check if there are any pending articles
        if (pendingArticles.isEmpty()) {
            printing.printSomething("No articles pending approval.");
            return;
        }

        // Display the pending articles in a table format
        printing.printSomething("\n+--------+-----------------------+---------------------+------------------------------+");
        printing.printSomething("|  ID    | Article Name          | Instructor Name     | Content Link                  |");
        printing.printSomething("+--------+-----------------------+---------------------+------------------------------+");

        for (String[] article : pendingArticles) {
            // Assuming fields: [ID, Article Name, Instructor Name, Content Link, Approval Status]
            String articleId = article[0];
            String articleName = article[1];
            String instructorName = article[2];
            String contentLink = article[3];
            printing.printSomething(String.format("| %-6s | %-21s | %-19s | %-28s |", 
                articleId, articleName, instructorName, contentLink));
        }

        printing.printSomething("+--------+-----------------------+---------------------+------------------------------+");

     
     // Ask for approval
        printing.printSomething("\nEnter article ID to approve or reject (or type 'exit' to go back): ");
        scanner.nextLine(); // Clear the buffer
        String articleIdToApprove = scanner.nextLine().trim();

        if (articleIdToApprove.equalsIgnoreCase("exit")) {
            printing.printSomething("Returning to previous menu...");
            return;
        }


        if (articleIdToApprove.equalsIgnoreCase("exit")) {
            printing.printSomething("Returning to previous menu...");
            return;
        }

        // Process the approval of the article
        boolean articleFound = false;
        List<String[]> updatedArticles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(articlesFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(articleIdToApprove)) {
                    articleFound = true;
                    // Update the approval status
                    fields[4] = "Approved";  // Set status to Approved
                }
                updatedArticles.add(fields);
            }
        } catch (IOException e) {
            printing.printError("Error processing article approval: " + e.getMessage());
            return;
        }

        // Rewrite the file with updated articles
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(articlesFilePath))) {
            for (String[] article : updatedArticles) {
                writer.write(String.join(",", article));
                writer.newLine();
            }
        }

        if (articleFound) {
            printing.printSomething("Article with ID " + articleIdToApprove + " has been approved!");
        } else {
            printing.printSomething("Article ID " + articleIdToApprove + " not found.");
        }
    }

 //Wellness Content

	private void approveWellnessContent() throws IOException {
	    printing.printSomething("Approving wellness articles, tips, or recipes...");

	    // Path to wellness content file
	    String wellnessContentFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\wellnessContent.txt";

	    // List pending wellness content from the file
	    List<String[]> pendingContent = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(wellnessContentFilePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] fields = line.split(",");
	            if (fields.length >= 5 && "Pending".equalsIgnoreCase(fields[4].trim())) {
	                pendingContent.add(fields);
	            }
	        }
	    } catch (IOException e) {
	        printing.printError("Error reading wellness content file: " + e.getMessage());
	        return;
	    }

	    // Check if there is any pending content
	    if (pendingContent.isEmpty()) {
	        printing.printSomething("No wellness content pending approval.");
	        return;
	    }

	    // Display the pending content in a table format
	    printing.printSomething("\n+--------+-----------------------+---------------------+------------------------------+");
	    printing.printSomething("|  ID    | Content Name          | Instructor Name     | Type (Article/Tip/Recipe)    |");
	    printing.printSomething("+--------+-----------------------+---------------------+------------------------------+");

	    for (String[] content : pendingContent) {
	        // Assuming fields: [ID, Content Name, Instructor Name, Type, Approval Status]
	        String contentId = content[0];
	        String contentName = content[1];
	        String instructorName = content[2];
	        String type = content[3];
	        printing.printSomething(String.format("| %-6s | %-21s | %-19s | %-28s |", 
	            contentId, contentName, instructorName, type));
	    }

	    printing.printSomething("+--------+-----------------------+---------------------+------------------------------+");

	    // Clear the buffer before reading content ID
	    scanner.nextLine(); // Ensure previous input is cleared

	    // Ask for content ID to approve or reject
	    printing.printSomething("\nEnter content ID to approve or reject (or type 'exit' to go back): ");
	    String contentIdToProcess = scanner.nextLine().trim();

	    if (contentIdToProcess.equalsIgnoreCase("exit")) {
	        printing.printSomething("Returning to previous menu...");
	        return;
	    }

	    // Ask for approval or rejection
	    printing.printSomething("Enter 'approve' to approve or 'reject' to reject: ");
	    String action = scanner.nextLine().trim().toLowerCase();

	    if (!action.equals("approve") && !action.equals("reject")) {
	        printing.printSomething("Invalid action. Returning to previous menu...");
	        return;
	    }

	    boolean contentFound = false;
	    List<String[]> updatedContent = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(wellnessContentFilePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] fields = line.split(",");
	            if (fields[0].equals(contentIdToProcess)) {
	                contentFound = true;
	                if (action.equals("approve")) {
	                    fields[4] = "Approved"; // Update status to Approved
	                } else {
	                    deleteContent(contentIdToProcess, wellnessContentFilePath); // Delete rejected content
	                    return;
	                }
	            }
	            updatedContent.add(fields);
	        }

	        // Rewrite the file with updated content
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(wellnessContentFilePath))) {
	            for (String[] content : updatedContent) {
	                writer.write(String.join(",", content));
	                writer.newLine();
	            }
	        }

	    } catch (IOException e) {
	        printing.printError("Error processing wellness content: " + e.getMessage());
	        return;
	    }

	    if (contentFound) {
	        printing.printSomething("Content with ID " + contentIdToProcess + " has been " + (action.equals("approve") ? "approved!" : "rejected and deleted!"));
	    } else {
	        printing.printSomething("Content ID " + contentIdToProcess + " not found.");
	    }
	}
	public void deleteContent(String contentId, String filePath) {
	    List<String> updatedContent = new ArrayList<>();
	    boolean contentFound = false;

	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] fields = line.split(",");
	            if (!fields[0].equals(contentId)) {
	                updatedContent.add(line); // Retain non-matching lines
	            } else {
	                contentFound = true; // Mark that the content was found
	            }
	        }
	    } catch (IOException e) {
	        printing.printError("Error reading file: " + e.getMessage());
	        return;
	    }

	    if (!contentFound) {
	        printing.printSomething("Content ID " + contentId + " not found.");
	        return;
	    }

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	        for (String record : updatedContent) {
	            writer.write(record);
	            writer.newLine();
	        }
	        printing.printSomething("Content with ID " + contentId + " has been deleted successfully.");
	    } catch (IOException e) {
	        printing.printError("Error writing to file: " + e.getMessage());
	    }
	}

//feedback 
	private void handleFeedback() throws IOException {
	    while (true) {
	        printing.printSomething("""
	            ---- Feedback Management ----
	            | 1. Review and Respond       |
	            | 2. Back                     |
	            --------------------------------
	            Enter your choice:
	        """);

	        int choice = getValidChoice(1, 2);
	        switch (choice) {
	            case 1 -> reviewAndRespondFeedback();
	            case 2 -> {
	                printing.printSomething("Returning to Content Management...");
	                return;
	            }
	            default -> printing.printSomething("Invalid choice. Try again.");
	        }
	    }
	}

	// Method to review all feedback and respond to a selected one
	public void reviewAndRespondFeedback() throws IOException {
	    List<String[]> feedbackList = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(FEEDBACK_FILE))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] data = line.split(",");
	            feedbackList.add(data);
	        }
	    } catch (IOException e) {
	        printing.printError("Error reading feedback file: " + e.getMessage());
	        return;
	    }

	    if (feedbackList.isEmpty()) {
	        printing.printSomething("No feedback available for review.");
	        return;
	    }

	    printing.printSomething("\n+--------+-----------+-----------+-------------------------+----------+");
	    printing.printSomething("| ID     | Client ID | Program ID | Feedback                | Status   |");
	    printing.printSomething("+--------+-----------+-----------+-------------------------+----------+");

	    for (String[] feedback : feedbackList) {
	        String responseStatus = feedback.length > 5 && feedback[5] != null ? feedback[5] : "No response yet";
	        printing.printSomething(String.format("| %-6s | %-9s | %-9s | %-23s | %-8s |",
	                feedback[0], feedback[1], feedback[2], feedback[3], feedback[4]));
	    }

	    printing.printSomething("+--------+-----------+-----------+-------------------------+----------+");

	    printing.printSomething("Enter the Feedback ID to respond to (or type 'exit' to go back): ");
	    scanner.nextLine(); // Clear buffer
	    String feedbackId = scanner.nextLine();

	    if (feedbackId.equalsIgnoreCase("exit")) {
	        printing.printSomething("Returning to Feedback Management...");
	        return;
	    }

	    StringBuilder updatedContent = new StringBuilder();
	    boolean feedbackFound = false;

	    for (String[] feedback : feedbackList) {
	        if (feedback[0].equals(feedbackId)) {
	            feedbackFound = true;
	            printing.printSomething("Feedback: " + feedback[3]);
	            printing.printSomething("Enter your response: ");
	            String response = scanner.nextLine();

	            if (feedback.length < 6) {
	                feedback = Arrays.copyOf(feedback, 6);
	            }
	            feedback[5] = response; // Add response
	            printing.printSomething("Response added to Feedback ID " + feedbackId + ".");
	        }
	        updatedContent.append(String.join(",", feedback)).append("\n");
	    }

	    if (!feedbackFound) {
	        printing.printSomething("Feedback ID " + feedbackId + " not found.");
	        return;
	    }

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FEEDBACK_FILE))) {
	        writer.write(updatedContent.toString());
	    } catch (IOException e) {
	        printing.printError("Error writing to feedback file: " + e.getMessage());
	    }
	}

	public void removeFeedback() {
		 printing.printSomething("Enter Feedback ID to remove: ");
 	    scanner.nextLine(); // Clear buffer
 	    String feedbackId = scanner.nextLine();

 	    String feedbackFilePath = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\feedback.txt"; // Update the path as necessary
 	    List<String> updatedFeedback = new ArrayList<>();
 	    boolean feedbackFound = false;

 	    try (BufferedReader reader = new BufferedReader(new FileReader(feedbackFilePath))) {
 	        String line;
 	        while ((line = reader.readLine()) != null) {
 	            String[] fields = line.split(",");
 	            if (!fields[0].equals(feedbackId)) {
 	                updatedFeedback.add(line); // Retain non-matching lines
 	            } else {
 	                feedbackFound = true; // Mark that the feedback was found
 	            }
 	        }
 	    } catch (IOException e) {
 	        printing.printError("Error reading feedback file: " + e.getMessage());
 	        return;
 	    }

 	    if (!feedbackFound) {
 	        printing.printSomething("Feedback ID " + feedbackId + " not found.");
 	        return;
 	    }

 	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(feedbackFilePath))) {
 	        for (String record : updatedFeedback) {
 	            writer.write(record);
 	            writer.newLine();
 	        }
 	        printing.printSomething("Feedback with ID " + feedbackId + " has been removed successfully.");
 	    } catch (IOException e) {
 	        printing.printError("Error writing to feedback file: " + e.getMessage());
 	    }
	}


    
//Subscription Management
    private void handleSubscriptionManagement() throws IOException {
        printing.printSomething("""
            ---- Subscription Management ----
            | 1. Manage Client Plans         |
            | 2. Manage Instructor Plans     |
            | 3. Back                        |
            ---------------------------------
            Enter your choice:
        """);

        int choice = getValidChoice(1, 3);
        switch (choice) {
            case 1 -> manageClientPlans();
            case 2 -> manageInstructorPlans();
            case 3 -> printing.printSomething("Returning to Admin Dashboard...");
        }
    }

    private void manageClientPlans() throws IOException {
        printing.printSomething("""
            ---- Client Subscription Plans ----
            | 1. Add Plan                    |
            | 2. Update Plan                 |
            | 3. Deactivate Plan             |
            | 4. View All Plans              |
            | 5. Back                        |
            ----------------------------------
            Enter your choice:
        """);

        int choice = getValidChoice(1, 5);
        String clientPlansFile = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\client_plans.txt.txt";

        switch (choice) {
            case 1 -> addSubscriptionPlan(clientPlansFile, false);
            case 2 -> updateSubscriptionPlan(clientPlansFile, false);
            case 3 -> deactivateSubscriptionPlan(clientPlansFile);
            case 4 -> viewAllSubscriptionPlans(clientPlansFile, false);
            case 5 -> printing.printSomething("Returning to Subscription Management...");
        }
    }

    private void manageInstructorPlans() throws IOException {
        printing.printSomething("""
            ---- Instructor Subscription Plans ----
            | 1. Add Plan                        |
            | 2. Update Plan                     |
            | 3. Deactivate Plan                 |
            | 4. View All Plans                  |
            | 5. Back                            |
            --------------------------------------
            Enter your choice:
        """);

        int choice = getValidChoice(1, 5);
        String instructorPlansFile = "C:\\Users\\Hp Zbook\\git\\Fitnes\\Fitness\\target\\instructor_plans.txt";

        switch (choice) {
            case 1 -> addSubscriptionPlan(instructorPlansFile, true);
            case 2 -> updateSubscriptionPlan(instructorPlansFile, true);
            case 3 -> deactivateSubscriptionPlan(instructorPlansFile);
            case 4 -> viewAllSubscriptionPlans(instructorPlansFile, true);
            case 5 -> printing.printSomething("Returning to Subscription Management...");
        }
    }

    public void addSubscriptionPlan(String filePath, boolean isInstructor) throws IOException {
        scanner.nextLine(); // Clear buffer
        printing.printSomething("Enter Plan Name: ");
        String name = scanner.nextLine();

        if (!isInstructor) {
            // Client-specific fields
            printing.printSomething("Enter Price (e.g., 50/mo): ");
            String price = scanner.nextLine();
            printing.printSomething("Enter Duration (e.g., Monthly, Yearly): ");
            String duration = scanner.nextLine();
            printing.printSomething("Enter Features (comma-separated): ");
            String features = scanner.nextLine();

            String planRecord = name + "," + price + "," + duration + "," + features + ",Active";
            appendToFile1(filePath, planRecord);
        } else {
            // Instructor-specific fields
            printing.printSomething("Enter Commission Rate (e.g., 10%): ");
            String commissionRate = scanner.nextLine();
            printing.printSomething("Enter Duration (e.g., Monthly, Yearly): ");
            String duration = scanner.nextLine();

            String planRecord = name + "," + commissionRate + "," + duration + ",Active";
            appendToFile1(filePath, planRecord);
        }

        printing.printSomething("Subscription plan added successfully!");
    }

    private void appendToFile1(String filePath, String data) throws IOException {
        System.out.println("Writing to file: " + filePath); // Debugging log
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            printing.printError("Error writing to file: " + e.getMessage());
            throw e; // Re-throw to handle upstream
        }
    }

    public void updateSubscriptionPlan(String filePath, boolean append) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        // Read file contents
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder fileContents = new StringBuilder();
        String line;
        boolean updated = false;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0 && parts[0].equals("Basic")) { // Assuming 'Basic' is the plan to update
                line = "Basic,30/mo,Yearly,Premium Features,Active"; // Update the line with new data
                updated = true;
            }
            fileContents.append(line).append("\n");
        }
        reader.close();

        // If the plan was updated, rewrite the file
        if (updated) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContents.toString());
            writer.close();
        } else {
            throw new IOException("Plan not found for update.");
        }
    }


    public void deactivateSubscriptionPlan(String filePath) throws IOException {
        scanner.nextLine(); // Clear buffer
        printing.printSomething("Enter Plan Name to Deactivate: ");
        String planName = scanner.nextLine();

        List<String> updatedPlans = new ArrayList<>();
        boolean planFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equalsIgnoreCase(planName)) {
                    planFound = true;
                    updatedPlans.add(fields[0] + "," + fields[1] + "," + fields[2] + ",Inactive");
                } else {
                    updatedPlans.add(line);
                }
            }
        }

        if (planFound) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String updatedPlan : updatedPlans) {
                    writer.write(updatedPlan);
                    writer.newLine();
                }
            }
            printing.printSomething("Subscription plan deactivated successfully!");
        } else {
            printing.printSomething("Plan not found.");
        }
    }

    public void viewAllSubscriptionPlans(String filePath, boolean isInstructor) throws IOException {
        printing.printSomething("Displaying all subscription plans:");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            if (!isInstructor) {
                // Client-specific table
                printing.printSomething("\n+----------------+----------+----------+-------------------------+----------+");
                printing.printSomething("| Plan Name      | Price    | Duration | Features                | Status   |");
                printing.printSomething("+----------------+----------+----------+-------------------------+----------+");
            } else {
                // Instructor-specific table
                printing.printSomething("\n+----------------+-----------------+----------+----------+");
                printing.printSomething("| Plan Name      | Commission Rate | Duration | Status   |");
                printing.printSomething("+----------------+-----------------+----------+----------+");
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                
                // Validate fields length
                if (isInstructor && fields.length >= 4) {
                    // Instructor-specific output
                    printing.printSomething(String.format("| %-14s | %-15s | %-8s | %-8s |",
                            fields[0], fields[1], fields[2], fields[3]));
                } else if (!isInstructor && fields.length >= 5) {
                    // Client-specific output
                    printing.printSomething(String.format("| %-14s | %-8s | %-8s | %-23s | %-8s |",
                            fields[0], fields[1], fields[2], fields[3], fields[4]));
                } else {
                    printing.printError("Skipping malformed line: " + line);
                }
            }

            if (!isInstructor) {
                printing.printSomething("+----------------+----------+----------+-------------------------+----------+");
            } else {
                printing.printSomething("+----------------+-----------------+----------+----------+");
            }
        } catch (IOException e) {
            printing.printError("Error reading subscription plans file: " + e.getMessage());
        }
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

    //login
	
    private boolean logState; // Track login state

    public void signInFunction() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Printing printing = new Printing();
        
        printing.printSomething("Enter Username: ");
        String username = scanner.nextLine();

        printing.printSomething("Enter Password: ");
        String password = scanner.nextLine();

        // Example credential validation logic
        String adminUsername = "loaa";
        String adminPassword = "12345";

        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            printing.printSomething("Admin login successful! Redirecting to admin settings...");
            setLogstate(true);
            adminPage(username); // Open admin settings
        } else if (validateUser(username, password)) { // Replace with actual user validation logic
            printing.printSomething("User login successful! Welcome, " + username + ".");
            setLogstate(true);
            // Redirect to user dashboard or other functionality
        } else {
            printing.printSomething("Invalid username or password. Please try again.");
            setLogstate(false);
        }
    }

    // Example user validation (replace with actual database or file checks)
    private boolean validateUser(String username, String password) {
        // Dummy data for example purposes
        Map<String, String> users = Map.of(
            "user1", "password1",
            "user2", "password2"
        );
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private void setLogstate(boolean state) {
        this.logState = state;
    }

    public boolean getLogstate() {
        return this.logState;
    }

	

	

	

	

	

    
}
