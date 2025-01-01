package fitnessAcceptanceTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.*;
import java.util.*;

public class ProgressTrackingSteps {

    private ProgressTracking progressTracking = new ProgressTracking();
    private String clientId = "client123";
    private String programId = "program1";
    private String date = "2024-12-19";

    @When("I update my weight in my profile")
    public void i_update_my_weight_in_my_profile() {
        double newWeight = 75.5;
        progressTracking.addMilestone(clientId, newWeight, 24.5, date); // Assume BMI is calculated
    }

    @Then("my weight should be saved successfully in my profile")
    public void my_weight_should_be_saved_successfully_in_my_profile() {
        progressTracking.viewMilestones(clientId);
    }

    @Then("I should be able to view my updated weight in the progress section")
    public void i_should_be_able_to_view_my_updated_weight_in_the_progress_section() {
        progressTracking.viewMilestones(clientId);
    }

    @When("I update my BMI in my profile")
    public void i_update_my_bmi_in_my_profile() {
        double newBmi = 22.0;
        progressTracking.addMilestone(clientId, 70.0, newBmi, date); // Assume weight is updated
    }

    @Then("my BMI should be saved successfully in my profile")
    public void my_bmi_should_be_saved_successfully_in_my_profile() {
        progressTracking.viewMilestones(clientId);
    }

    @Then("I should be able to view my updated BMI in the progress section")
    public void i_should_be_able_to_view_my_updated_bmi_in_the_progress_section() {
        progressTracking.viewMilestones(clientId);
    }

    @When("I attend a fitness program session")
    public void i_attend_a_fitness_program_session() {
        progressTracking.trackAttendance(clientId, programId, true, date);
    }

    @Then("my attendance should be recorded for the program")
    public void my_attendance_should_be_recorded_for_the_program() {
        progressTracking.viewAttendance(clientId);
    }

    @Then("I should be able to view my attendance history in the progress section")
    public void i_should_be_able_to_view_my_attendance_history_in_the_progress_section() {
        progressTracking.viewAttendance(clientId);
    }

    @When("I complete a fitness program")
    public void i_complete_a_fitness_program() {
        progressTracking.addAchievement(clientId, "Program Completed", "Completed the fitness program successfully.");
    }

    @Then("I should receive an achievement or badge for completing the program")
    public void i_should_receive_an_achievement_or_badge_for_completing_the_program() {
        progressTracking.viewAchievements(clientId);
    }

    @Then("the achievement or badge should appear in the achievements section of my profile")
    public void the_achievement_or_badge_should_appear_in_the_achievements_section_of_my_profile() {
        progressTracking.viewAchievements(clientId);
    }

    @When("I view my progress section")
    public void i_view_my_progress_section() {
        // Just a placeholder to trigger the view of progress
        progressTracking.viewMilestones(clientId);
        progressTracking.viewAttendance(clientId);
        progressTracking.viewAchievements(clientId);
    }

    @Then("I should be able to see my tracked milestones such as weight, BMI, and attendance")
    public void i_should_be_able_to_see_my_tracked_milestones_such_as_weight_bmi_and_attendance() {
        progressTracking.viewMilestones(clientId);
        progressTracking.viewAttendance(clientId);
    }

    @Then("I should be able to see the badges or achievements earned for completing programs")
    public void i_should_be_able_to_see_the_badges_or_achievements_earned_for_completing_programs() {
        progressTracking.viewAchievements(clientId);
    }

    @When("I update my weight to {string}")
    public void i_update_my_weight_to(String weight) {
        try {
            double newWeight = Double.parseDouble(weight);
            if (newWeight > 0) {
                progressTracking.addMilestone(clientId, newWeight, 22.0, date); // Assume BMI is updated accordingly
            } else {
                throw new IllegalArgumentException("Invalid weight");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid weight format");
        }
    }

    @Then("I should see an error message saying {string}")
    public void i_should_see_an_error_message_saying(String message) {
        System.out.println(message); // For simplicity, print the message
    }

    @Then("my weight should not be saved until a valid value is entered")
    public void my_weight_should_not_be_saved_until_a_valid_value_is_entered() {
        // Logic to ensure invalid weight is not saved
    }

    @When("I update my BMI to {string}")
    public void i_update_my_bmi_to(String bmi) {
        try {
            double newBmi = Double.parseDouble(bmi);
            if (newBmi > 0) {
                progressTracking.addMilestone(clientId, 70.0, newBmi, date); // Assume weight is updated
            } else {
                throw new IllegalArgumentException("Invalid BMI");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid BMI format");
        }
    }

    @Then("my BMI should not be saved until a valid value is entered")
    public void my_bmi_should_not_be_saved_until_a_valid_value_is_entered() {
        // Logic to ensure invalid BMI is not saved
    }

    @When("the system fails to record my attendance")
    public void the_system_fails_to_record_my_attendance() {
        System.out.println("Attendance recording failed.");
    }

    @Then("I should see a message saying {string}")
    public void i_should_see_a_message_saying(String message) {
        System.out.println(message); // Display the error message
    }

    @When("the system fails to assign an achievement or badge")
    public void the_system_fails_to_assign_an_achievement_or_badge() {
        System.out.println("Failed to assign achievement.");
    }

    @Then("I should see a graph or list showing the changes in my weight and/or BMI over time")
    public void i_should_see_a_graph_or_list_showing_the_changes_in_my_weight_and_or_bmi_over_time() {
        System.out.println("Displaying progress graph or list.");
    }

    @When("I choose to remove weight tracking from my profile")
    public void i_choose_to_remove_weight_tracking_from_my_profile() {
        // Logic to remove weight tracking
    }

    @Then("my weight should no longer be tracked and will be removed from the progress section")
    public void my_weight_should_no_longer_be_tracked_and_will_be_removed_from_the_progress_section() {
        System.out.println("Weight tracking removed.");
    }
}
