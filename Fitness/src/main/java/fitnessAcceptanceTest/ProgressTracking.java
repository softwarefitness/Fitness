package fitnessAcceptanceTest;

import java.io.*;
import java.util.*;

public class ProgressTracking {

    // A class to represent the progress milestones for the client
    public static class FitnessMilestone {
        private double weight;
        private double bmi;
        private String date;

        public FitnessMilestone(double weight, double bmi, String date) {
            this.weight = weight;
            this.bmi = bmi;
            this.date = date;
        }

        public double getWeight() {
            return weight;
        }

        public double getBmi() {
            return bmi;
        }

        public String getDate() {
            return date;
        }

        @Override
        public String toString() {
            return "Date: " + date + ", Weight: " + weight + "kg, BMI: " + bmi;
        }
    }

    // A class to represent the achievements and badges for the client
    public static class Achievement {
        private String name;
        private String description;

        public Achievement(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return name + ": " + description;
        }
    }

    // Method to track fitness milestones (weight, BMI, and date)
    public void addMilestone(String clientId, double weight, double bmi, String date) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("milestones.txt", true))) {
            writer.write(clientId + "," + weight + "," + bmi + "," + date);
            writer.newLine();
            System.out.println("Milestone recorded for client " + clientId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to view progress milestones for a client
    public void viewMilestones(String clientId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("milestones.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(clientId)) {
                    System.out.println(new FitnessMilestone(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to track attendance for a client
    public void trackAttendance(String clientId, String programId, boolean attended, String date) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attendance.txt", true))) {
            writer.write(clientId + "," + programId + "," + attended + "," + date);
            writer.newLine();
            System.out.println("Attendance recorded for client " + clientId + " on " + date);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to view attendance for a client
    public void viewAttendance(String clientId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("attendance.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(clientId)) {
                    System.out.println("Program ID: " + data[1] + ", Attended: " + data[2] + ", Date: " + data[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add achievements or badges for clients
    public void addAchievement(String clientId, String achievementName, String description) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("achievements.txt", true))) {
            writer.write(clientId + "," + achievementName + "," + description);
            writer.newLine();
            System.out.println("Achievement '" + achievementName + "' added for client " + clientId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to view achievements or badges for a client
    public void viewAchievements(String clientId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("achievements.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(clientId)) {
                    System.out.println(new Achievement(data[1], data[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to check if the client has earned any badges (e.g., completed a program or attended a number of sessions)
    public void checkForBadges(String clientId) {
        int attendanceCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("attendance.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(clientId) && data[2].equals("true")) {
                    attendanceCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (attendanceCount >= 10) {
            addAchievement(clientId, "Frequent Attendee", "Attended 10 or more sessions.");
        }
    }
}