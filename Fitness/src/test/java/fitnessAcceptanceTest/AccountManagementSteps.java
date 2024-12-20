package fitnessAcceptanceTest;
import fitnessAcceptanceTest.ProgramManagement;
import fitnessAcceptanceTest.ProgramManagement.AccountManagementSystem;
import fitnessAcceptanceTest.ProgramManagement.Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fitnessAcceptanceTest.ProgramManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountManagementSteps {
	 AccountManagementSystem accountSystem = new AccountManagementSystem();
	    Client currentClient;
	    AdminInterface admin = new AdminInterface(); // Assuming AdminInterface exists in your code

	    
	    @Given("I am logged in as a Client")
	    public void i_am_logged_in_as_a_client() {
	        try {
	            currentClient = new Client(1, "John Doe", 25, "Weight Loss", "Vegetarian");
	            System.out.println("Client logged in: " + currentClient.getName());
	        } catch (Exception e) {
	            System.out.println("Error during login: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

	@When("I create a profile with my personal details including age and fitness goals")
	public void i_create_a_profile_with_my_personal_details_including_age_and_fitness_goals() {
		 int clientId = accountSystem.createProfile("John Doe", 25, "Weight Loss", "Vegetarian");
	        currentClient = accountSystem.getClientById(clientId);
	    throw new io.cucumber.java.PendingException();
	}

	@Then("my personal details should be saved successfully in my profile")
	public void my_personal_details_should_be_saved_successfully_in_my_profile() {
		  assert currentClient.getName().equals("John Doe");
	        assert currentClient.age == 25;
	        assert currentClient.fitnessGoals.equals("Weight Loss");
	        assert currentClient.dietaryPreferences.equals("Vegetarian");
	    throw new io.cucumber.java.PendingException();
	}

	@When("I update my personal details such as age and fitness goals")
	public void i_update_my_personal_details_such_as_age_and_fitness_goals() {
		  currentClient.updateProfile("John Doe", 26, "Muscle Building", "Gluten-Free");
	    throw new io.cucumber.java.PendingException();
	}

	@Then("my updated personal details should be saved successfully in my profile")
	public void my_updated_personal_details_should_be_saved_successfully_in_my_profile() {
		assert currentClient.age == 26;
        assert currentClient.fitnessGoals.equals("Muscle Building");
        assert currentClient.dietaryPreferences.equals("Gluten-Free");
	    throw new io.cucumber.java.PendingException();
	}

	@Then("my dietary preferences should be saved successfully in my profile")
	public void my_dietary_preferences_should_be_saved_successfully_in_my_profile1() {
	    // تحقق من أن تفضيلات النظام الغذائي تم تحديثها بنجاح
	    assert currentClient.dietaryPreferences.equals("Gluten-Free");
	}


	@Then("my dietary preferences should be saved successfully in my profile")
	public void my_dietary_preferences_should_be_saved_successfully_in_my_profile() {
		 assert currentClient.dietaryPreferences.equals("Gluten-Free");
	    throw new io.cucumber.java.PendingException();
	}
	@When("I edit my dietary preferences or restrictions \\(e.g., add lactose intolerance\\)")
	public void i_edit_my_dietary_preferences_or_restrictions_e_g_add_lactose_intolerance() {
	    // تحديث تفضيلات النظام الغذائي للعميل
	    currentClient.updateProfile(currentClient.getName(), currentClient.age, currentClient.fitnessGoals, "Lactose Intolerant");
	}


	@Then("my edited dietary preferences should be updated successfully in my profile")
	public void my_edited_dietary_preferences_should_be_updated_successfully_in_my_profile() {
		 assert currentClient.dietaryPreferences.equals("Lactose Intolerant");
	    throw new io.cucumber.java.PendingException();
	}

	
	@When("I view my profile")
	public void i_view_my_profile() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I should be able to see my personal details, fitness goals, and dietary preferences")
	public void i_should_be_able_to_see_my_personal_details_fitness_goals_and_dietary_preferences() {
		 assert currentClient.getName().equals("John Doe");
	        assert currentClient.fitnessGoals.equals("Muscle Building");
	        assert currentClient.dietaryPreferences.equals("Lactose Intolerant");
	    throw new io.cucumber.java.PendingException();
	}

	@When("I attempt to create a profile with invalid details such as age = {string}")
	public void i_attempt_to_create_a_profile_with_invalid_details_such_as_age(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I should see an error message indicating invalid input")
	public void i_should_see_an_error_message_indicating_invalid_input() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("my profile should not be saved")
	public void my_profile_should_not_be_saved() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I attempt to create a profile without providing my age")
	public void i_attempt_to_create_a_profile_without_providing_my_age() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I should see an error message indicating that age is required")
	public void i_should_see_an_error_message_indicating_that_age_is_required() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I choose to delete my profile")
	public void i_choose_to_delete_my_profile() {
	    ProgramManagement programManagement = new ProgramManagement();  // تأكد من استخدام الكائن الصحيح
	    programManagement.deactivateAccount(String.valueOf(currentClient.id));  // استدعاء الدالة من الكائن الصحيح
	}


	public void deactivateAccount(String clientId) {
	    File inputFile = new File("clients.txt");
	    File tempFile = new File("temp_clients.txt");

	    if (!inputFile.exists()) {
	        System.out.println("The file clients.txt does not exist.");
	        return;
	    }

	    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
	        String line;
	        boolean clientFound = false;  // لتتبع إذا كان العميل موجودًا

	        while ((line = reader.readLine()) != null) {
	            String[] data = line.split(",");
	            if (data[0].equals(clientId)) {
	                data[4] = "Inactive";  // تعطيل الحساب
	                clientFound = true;
	                System.out.println("Deactivating account for client: " + data[1]);
	                sendNotification(data[1], "Your account has been deactivated.");
	            }
	            writer.write(String.join(",", data));
	            writer.newLine();
	        }

	        if (!clientFound) {
	            System.out.println("Client with ID " + clientId + " not found.");
	            return;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    boolean renamed = tempFile.renameTo(inputFile);
	    if (!renamed) {
	        System.out.println("Failed to rename the temporary file.");
	    } else {
	        System.out.println("File renamed successfully.");
	    }
	}

	private void sendNotification(String clientName, String message) {
	    // منطق إرسال الإشعار
	    System.out.println("Notification to " + clientName + ": " + message);
	}


	@Then("my profile should be removed from the system")
	public void my_profile_should_be_removed_from_the_system() {
		assert currentClient.getAccountStatus().equals("Inactive");
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I should no longer be able to access my account")
	public void i_should_no_longer_be_able_to_access_my_account() {
		 currentClient.setAccountStatus("Inactive");
	        assert currentClient.getAccountStatus().equals("Inactive");
	        System.out.println("Account deactivated: " + currentClient.getAccountStatus());
	    }

	    // Custom exception to handle unauthorized access attempts
	    class UnauthorizedAccessException extends Exception {
	        public UnauthorizedAccessException(String message) {
	            super(message);
	       
	 
	    throw new io.cucumber.java.PendingException();
	}
}

	    @When("I attempt to access another client's profile")
	    public void i_attempt_to_access_another_client_s_profile() {
	        // إنشاء كائن من AccountManagementSystem
	        AccountManagementSystem accountSystem = new AccountManagementSystem();

	        // إضافة عميل آخر إلى النظام
	        Client otherClient = new Client(2, "Jane Doe", 30, "Muscle Gain", "Vegan");
	        accountSystem.createProfile("Jane Doe", 30, "Muscle Gain", "Vegan");  // تأكد من إضافة العميل للنظام

	        // محاولة الوصول إلى ملف تعريف عميل آخر
	        try {
	            Client currentClient = accountSystem.getClientById(1);  // استخدام معرّف العميل الحالي
	            if (currentClient.id != otherClient.id) {
	                throw new UnauthorizedAccessException("Insufficient permissions to view or edit this profile.");
	            }
	        } catch (UnauthorizedAccessException e) {
	            System.out.println(e.getMessage());
	        }

	        throw new io.cucumber.java.PendingException();
	    }

	@Then("I should receive an error message indicating insufficient permissions")
	public void i_should_receive_an_error_message_indicating_insufficient_permissions() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("I should not be able to view or edit their profile")
	public void i_should_not_be_able_to_view_or_edit_their_profile() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}	
