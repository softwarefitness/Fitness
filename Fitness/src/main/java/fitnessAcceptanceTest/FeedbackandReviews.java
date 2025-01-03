package fitnessAcceptanceTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FeedbackandReviews {

    // Method for clients to submit feedback
    public void submitFeedback(String clientId, String programId, String feedback) {
        // Store the feedback in the feedback.txt file with a status of "Pending"
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("feedback.txt", true))) {
            writer.write(UUID.randomUUID() + "," + clientId + "," + programId + "," + feedback + ",Pending");
            writer.newLine();
            System.out.println("Your feedback has been submitted and is pending approval.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to allow clients to view their feedback and its status
    public void viewMyFeedback(String clientId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("feedback.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(clientId)) {
                    System.out.println("Feedback for program " + data[2] + ": " + data[3] + " (Status: " + data[4] + ")");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method for admin to approve or reject feedback and notify clients
    public void reviewFeedback(String feedbackId, boolean approve) {
        StringBuilder updatedContent = new StringBuilder();
        String clientId = null;
        boolean feedbackFound = false;  // Flag to track if feedback was found
        
        try (BufferedReader reader = new BufferedReader(new FileReader("feedback.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(feedbackId)) {
                    feedbackFound = true;
                    clientId = data[1]; // Capture client ID for notification
                    data[4] = approve ? "Approved" : "Rejected"; // Set feedback status
                    System.out.println("Feedback ID " + feedbackId + " has been " + (approve ? "approved." : "rejected."));
                }
                updatedContent.append(String.join(",", data)).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If feedback is not found, notify the user
        if (!feedbackFound) {
            System.out.println("Feedback ID " + feedbackId + " not found.");
            return;
        }

        // Write updated content back to the feedback file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("feedback.txt"))) {
            writer.write(updatedContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Notify the client if feedback was found
        if (clientId != null) {
            notifyClient(clientId, feedbackId, approve);
        }
    }


    // Method to notify the client when feedback is approved or rejected
    private void notifyClient(String clientId, String feedbackId, boolean approve) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("notifications.txt", true))) {
            writer.write(clientId + ",Feedback ID " + feedbackId + " has been " + (approve ? "approved." : "rejected."));
            writer.newLine();
            System.out.println("Client " + clientId + " has been notified about the feedback review.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method for clients to view their notifications
    public List<String> viewNotifications(String clientId) {
        List<String> notifications = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("notifications.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(clientId)) {
                    notifications.add(data[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notifications;
    }

}
