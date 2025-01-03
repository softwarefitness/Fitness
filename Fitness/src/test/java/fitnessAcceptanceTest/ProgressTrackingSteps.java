@Then("my weight should be saved successfully in my profile")
public void my_weight_should_be_saved_successfully_in_my_profile() {
    List<String> milestones = progressTracking.viewMilestones(clientId);
    assertTrue("Milestones should not be empty after saving weight", 
               milestones != null && !milestones.isEmpty());
    assertTrue("Milestones should include the updated weight", 
               milestones.stream().anyMatch(m -> m.contains("Weight: 75.5")));
    System.out.println("Weight saved successfully.");
}

@Then("I should be able to view my updated weight in the progress section")
public void i_should_be_able_to_view_my_updated_weight_in_the_progress_section() {
    List<String> milestones = progressTracking.viewMilestones(clientId);
    assertTrue("Updated weight should be visible in milestones", 
               milestones.stream().anyMatch(m -> m.contains("Weight: 75.5")));
    System.out.println("Updated weight is visible.");
}

@Then("my BMI should be saved successfully in my profile")
public void my_bmi_should_be_saved_successfully_in_my_profile() {
    List<String> milestones = progressTracking.viewMilestones(clientId);
    assertTrue("Milestones should include the updated BMI", 
               milestones.stream().anyMatch(m -> m.contains("BMI: 22.0")));
    System.out.println("BMI saved successfully.");
}

@Then("I should be able to view my updated BMI in the progress section")
public void i_should_be_able_to_view_my_updated_bmi_in_the_progress_section() {
    List<String> milestones = progressTracking.viewMilestones(clientId);
    assertTrue("Updated BMI should be visible in milestones", 
               milestones.stream().anyMatch(m -> m.contains("BMI: 22.0")));
    System.out.println("Updated BMI is visible.");
}

@Then("my attendance should be recorded for the program")
public void my_attendance_should_be_recorded_for_the_program() {
    List<String> attendance = progressTracking.viewAttendance(clientId);
    assertTrue("Attendance should be recorded", 
               attendance.stream().anyMatch(a -> a.contains("Program: program1") && a.contains("Date: 2024-12-19")));
    System.out.println("Attendance recorded successfully.");
}

@Then("my weight should not be saved until a valid value is entered")
public void my_weight_should_not_be_saved_until_a_valid_value_is_entered() {
    List<String> milestones = progressTracking.viewMilestones(clientId);
    assertFalse("Invalid weight should not be saved", 
                milestones.stream().anyMatch(m -> m.contains("Weight: -1")));
    System.out.println("Invalid weight was not saved.");
}

@Then("my BMI should not be saved until a valid value is entered")
public void my_bmi_should_not_be_saved_until_a_valid_value_is_entered() {
    List<String> milestones = progressTracking.viewMilestones(clientId);
    assertFalse("Invalid BMI should not be saved", 
                milestones.stream().anyMatch(m -> m.contains("BMI: -1")));
    System.out.println("Invalid BMI was not saved.");
}

@Then("my weight should no longer be tracked and will be removed from the progress section")
public void my_weight_should_no_longer_be_tracked_and_will_be_removed_from_the_progress_section() {
    List<String> milestones = progressTracking.viewMilestones(clientId);
    assertFalse("Weight should be removed from tracking", 
                milestones.stream().anyMatch(m -> m.contains("Weight")));
    System.out.println("Weight tracking removed successfully.");
}
