package fitnessAcceptanceTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProgramExploration {
    ProgramExploration programExploration = new ProgramExploration();
    ProgramExploration.Client currentClient = new ProgramExploration.Client("John Doe");
    static class Client {
        String name;

        public Client(String name) {
            this.name = name;
        }

        public void filterPrograms(String difficultyLevel, String focusArea) {
            System.out.println("Filtering programs by difficulty: " + difficultyLevel + " and focus area: " + focusArea);
        }

        public void viewProgramDetails(String programName) {
            System.out.println("Viewing details for program: " + programName);
        }
    }
    

    // تعريف الكائنات بشكل صحيح
    Client currentClient1 = new Client("John Doe");
    @When("I browse the programs and filter by difficulty level {string}")
    public void i_browse_the_programs_and_filter_by_difficulty_level(String difficultyLevel) {
        System.out.println("Filtering programs by difficulty level: " + difficultyLevel);
        currentClient.filterPrograms(difficultyLevel, "");
    }

    @Then("I should see a list of programs with difficulty level {string}")
    public void i_should_see_a_list_of_programs_with_difficulty_level(String difficultyLevel) {
        System.out.println("Programs with difficulty level: " + difficultyLevel);
        // Here you would verify the list of programs shown matches the difficulty level.
    }

    @When("I browse the programs and filter by focus area {string}")
    public void i_browse_the_programs_and_filter_by_focus_area(String focusArea) {
        System.out.println("Filtering programs by focus area: " + focusArea);
        currentClient.filterPrograms("", focusArea);
    }

    @Then("I should see a list of programs with focus area {string}")
    public void i_should_see_a_list_of_programs_with_focus_area(String focusArea) {
        System.out.println("Programs with focus area: " + focusArea);
        // Here you would verify the list of programs shown matches the focus area.
    }

    @When("I browse the programs and filter by difficulty level {string} and focus area {string}")
    public void i_browse_the_programs_and_filter_by_difficulty_level_and_focus_area(String difficultyLevel, String focusArea) {
        System.out.println("Filtering programs by difficulty level: " + difficultyLevel + " and focus area: " + focusArea);
        currentClient.filterPrograms(difficultyLevel, focusArea);
    }
    
    

    @Then("I should see a list of programs with difficulty level {string} and focus area {string}")
    public void i_should_see_a_list_of_programs_with_difficulty_level_and_focus_area(String difficultyLevel, String focusArea) {
        System.out.println("Programs with difficulty level: " + difficultyLevel + " and focus area: " + focusArea);
        // Verify the programs shown match both the difficulty level and focus area.
    }

    @When("I select a program with difficulty level {string} and focus area {string}")
    public void i_select_a_program_with_difficulty_level_and_focus_area(String difficultyLevel, String focusArea) {
        System.out.println("Selecting a program with difficulty level: " + difficultyLevel + " and focus area: " + focusArea);
        // Select a program based on the filters.
    }

    @When("I click on {string}")
    public void i_click_on(String programName) {
        System.out.println("Clicking on program: " + programName);
        currentClient.viewProgramDetails(programName);
    }

    @Then("I should be successfully enrolled in the selected program")
    public void i_should_be_successfully_enrolled_in_the_selected_program() {
        System.out.println("Successfully enrolled in the selected program.");
        // Verify enrollment success.
    }

    @When("I am enrolled in a program with focus area {string}")
    public void i_am_enrolled_in_a_program_with_focus_area(String focusArea) {
        System.out.println("Enrolled in program with focus area: " + focusArea);
        // Simulate enrolling the client in a program.
    }

    @Then("I should be able to view the program's schedule")
    public void i_should_be_able_to_view_the_program_s_schedule() {
        System.out.println("Viewing the program's schedule.");
        // Display the schedule for the program.
    }

    @Then("I should see the start and end times of the program")
    public void i_should_see_the_start_and_end_times_of_the_program() {
        System.out.println("Viewing start and end times of the program.");
        // Display the start and end times.
    }

    @Then("I should see a message saying {string}")
    public void i_should_see_a_message_saying(String message) {
        System.out.println("Message: " + message);
        // Verify the message is displayed.
    }
    

    @Given("the program with difficulty level {string} and focus area {string} is fully booked")
    public void the_program_with_difficulty_level_and_focus_area_is_fully_booked(String difficultyLevel, String focusArea) {
        System.out.println("Program with difficulty level: " + difficultyLevel + " and focus area: " + focusArea + " is fully booked.");
        // Simulate that the program is fully booked.
    }

    @When("I attempt to enroll in the program")
    public void i_attempt_to_enroll_in_the_program() {
        System.out.println("Attempting to enroll in the program.");
        // Attempt to enroll in the program.
    }

    @Given("I am already enrolled in the program with focus area {string}")
    public void i_am_already_enrolled_in_the_program_with_focus_area(String focusArea) {
        System.out.println("Already enrolled in program with focus area: " + focusArea);
        // Simulate that the client is already enrolled.
    }

    @When("I attempt to enroll in the same program again")
    public void i_attempt_to_enroll_in_the_same_program_again() {
        System.out.println("Attempting to enroll in the same program again.");
        // Simulate the attempt to enroll again.
    }

    @Given("I am enrolled in the program with focus area {string}")
    public void i_am_enrolled_in_the_program_with_focus_area(String focusArea) {
        System.out.println("Already enrolled in the program with focus area: " + focusArea);
        // Ensure the client is enrolled in the program.
    }

    @When("I choose to cancel my enrollment")
    public void i_choose_to_cancel_my_enrollment() {
        System.out.println("Choosing to cancel my enrollment.");
        // Simulate the cancellation of enrollment.
    }

    @Then("I should be successfully unenrolled from the program")
    public void i_should_be_successfully_unenrolled_from_the_program() {
        System.out.println("Successfully unenrolled from the program.");
        // Verify the cancellation was successful.
    }

    @Then("I should see a confirmation message saying {string}")
    public void i_should_see_a_confirmation_message_saying(String message) {
        System.out.println("Confirmation message: " + message);
        // Verify the confirmation message.
    }

    @When("I select the program with focus area {string}")
    public void i_select_the_program_with_focus_area(String focusArea) {
        System.out.println("Selecting program with focus area: " + focusArea);
        // Select a program based on focus area.
    }

    @Then("I should see the program's details including its difficulty level, schedule, and description")
    public void i_should_see_the_program_s_details_including_its_difficulty_level_schedule_and_description() {
        System.out.println("Viewing the program's details including difficulty level, schedule, and description.");
        // Display program details.
    }
}
