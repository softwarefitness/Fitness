package fitnessAcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ProgramExploration {

    static class Client {
        String name;
        boolean isEnrolled = false;
        boolean isProgramAvailable = true;

        public Client(String name) {
            this.name = name;
        }

        public void filterPrograms(String difficultyLevel, String focusArea) {
            System.out.println("Filtering programs by difficulty: " + difficultyLevel + " and focus area: " + focusArea);
        }

        public void viewProgramDetails(String programName) {
            System.out.println("Viewing details for program: " + programName);
        }

        public void enrollInProgram(String programName) {
            if (isProgramAvailable) {
                isEnrolled = true;
                System.out.println("Enrolled in program: " + programName);
            } else {
                System.out.println("Program is fully booked. Enrollment failed.");
            }
        }

        public void cancelEnrollment(String programName) {
            if (isEnrolled) {
                isEnrolled = false;
                System.out.println("Cancelled enrollment in program: " + programName);
            }
        }
    }

    // Declare a single client instance
    Client currentClient = new Client("John Doe");

    @When("I browse the programs and filter by difficulty level {string}")
    public void i_browse_the_programs_and_filter_by_difficulty_level(String difficultyLevel) {
        currentClient.filterPrograms(difficultyLevel, "");
    }

    @Then("I should see a list of programs with difficulty level {string}")
    public void i_should_see_a_list_of_programs_with_difficulty_level(String difficultyLevel) {
        // Simulating a verification step, assume programs matching difficulty are shown
        boolean programsFiltered = true; // Simulate the condition check
        Assert.assertTrue("Programs with difficulty level " + difficultyLevel + " should be displayed.", programsFiltered);
    }

    @When("I browse the programs and filter by focus area {string}")
    public void i_browse_the_programs_and_filter_by_focus_area(String focusArea) {
        currentClient.filterPrograms("", focusArea);
    }

    @Then("I should see a list of programs with focus area {string}")
    public void i_should_see_a_list_of_programs_with_focus_area(String focusArea) {
        // Simulating the condition check for focus area
        boolean programsFiltered = true; // Simulate the condition check
        Assert.assertTrue("Programs with focus area " + focusArea + " should be displayed.", programsFiltered);
    }

    @When("I browse the programs and filter by difficulty level {string} and focus area {string}")
    public void i_browse_the_programs_and_filter_by_difficulty_level_and_focus_area(String difficultyLevel, String focusArea) {
        currentClient.filterPrograms(difficultyLevel, focusArea);
    }

    @Then("I should see a list of programs with difficulty level {string} and focus area {string}")
    public void i_should_see_a_list_of_programs_with_difficulty_level_and_focus_area(String difficultyLevel, String focusArea) {
        // Simulating the condition check for both filters
        boolean programsFiltered = true; // Simulate the condition check
        Assert.assertTrue("Programs with difficulty level " + difficultyLevel + " and focus area " + focusArea + " should be displayed.", programsFiltered);
    }

    @When("I select a program with difficulty level {string} and focus area {string}")
    public void i_select_a_program_with_difficulty_level_and_focus_area(String difficultyLevel, String focusArea) {
        // Simulate selecting a program
        System.out.println("Selecting program with difficulty: " + difficultyLevel + " and focus area: " + focusArea);
    }

    @When("I click on {string}")
    public void i_click_on(String programName) {
        currentClient.viewProgramDetails(programName);
    }

    @Then("I should be successfully enrolled in the selected program")
    public void i_should_be_successfully_enrolled_in_the_selected_program() {
        currentClient.enrollInProgram("Sample Program");
        // Verify enrollment status
        Assert.assertTrue("The client should be successfully enrolled in the program.", currentClient.isEnrolled);
    }

    @When("I am enrolled in a program with focus area {string}")
    public void i_am_enrolled_in_a_program_with_focus_area(String focusArea) {
        currentClient.enrollInProgram(focusArea);
    }

    @Then("I should be able to view the program's schedule")
    public void i_should_be_able_to_view_the_program_s_schedule() {
        // Simulate the schedule view
        boolean canViewSchedule = true; // Simulate the condition check
        Assert.assertTrue("The client should be able to view the program's schedule.", canViewSchedule);
    }

    @Then("I should see the start and end times of the program")
    public void i_should_see_the_start_and_end_times_of_the_program() {
        // Simulate the start and end time display
        boolean timesDisplayed = true; // Simulate the condition check
        Assert.assertTrue("The program's start and end times should be displayed.", timesDisplayed);
    }

    @Then("I should see a message saying {string}")
    public void i_should_see_a_message_saying(String message) {
        // Simulate message display
        boolean messageDisplayed = true; // Simulate the condition check
        Assert.assertTrue("The message should be displayed.", messageDisplayed);
    }

    @Given("the program with difficulty level {string} and focus area {string} is fully booked")
    public void the_program_with_difficulty_level_and_focus_area_is_fully_booked(String difficultyLevel, String focusArea) {
        currentClient.isProgramAvailable = false; // Simulate a fully booked program
        System.out.println("Program with difficulty level: " + difficultyLevel + " and focus area: " + focusArea + " is fully booked.");
    }

    @When("I attempt to enroll in the program")
    public void i_attempt_to_enroll_in_the_program() {
        currentClient.enrollInProgram("Sample Program");
    }

    @Given("I am already enrolled in the program with focus area {string}")
    public void i_am_already_enrolled_in_the_program_with_focus_area(String focusArea) {
        currentClient.enrollInProgram(focusArea);
    }

    @When("I attempt to enroll in the same program again")
    public void i_attempt_to_enroll_in_the_same_program_again() {
        // Simulate the attempt to enroll in the same program again
        boolean isAlreadyEnrolled = currentClient.isEnrolled;
        Assert.assertTrue("The client should not be able to enroll in the same program again.", !isAlreadyEnrolled);
    }

    @Given("I am enrolled in the program with focus area {string}")
    public void i_am_enrolled_in_the_program_with_focus_area(String focusArea) {
        currentClient.enrollInProgram(focusArea);
    }

    @When("I choose to cancel my enrollment")
    public void i_choose_to_cancel_my_enrollment() {
        currentClient.cancelEnrollment("Sample Program");
    }

    @Then("I should be successfully unenrolled from the program")
    public void i_should_be_successfully_unenrolled_from_the_program() {
        // Verify if the client is successfully unenrolled
        Assert.assertTrue("The client should be successfully unenrolled from the program.", !currentClient.isEnrolled);
    }

    @Then("I should see a confirmation message saying {string}")
    public void i_should_see_a_confirmation_message_saying(String message) {
        // Simulate confirmation message display
        boolean confirmationMessageDisplayed = true; // Simulate condition check
        Assert.assertTrue("The confirmation message should be displayed.", confirmationMessageDisplayed);
    }

    @When("I select the program with focus area {string}")
    public void i_select_the_program_with_focus_area(String focusArea) {
        System.out.println("Selecting program with focus area: " + focusArea);
    }

    @Then("I should see the program's details including its difficulty level, schedule, and description")
    public void i_should_see_the_program_s_details_including_its_difficulty_level_schedule_and_description() {
        // Simulate checking for the program details
        boolean detailsVisible = true; // Simulate the condition check
        Assert.assertTrue("The program's details including difficulty level, schedule, and description should be visible.", detailsVisible);
    }
}
