package weight5;

	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.When;
	import io.cucumber.java.en.Then;
	import static org.junit.Assert.*;

import ClientInstruction.Instructor;
import NotificationAndUpdate.Program;

	public class ProgramManagmentTest {
		@Given("I am logged in as an instructor")
		public void i_am_logged_in_as_an_instructor() {
		    // تسجيل الدخول كمدرب
		    Instructor instructor = new Instructor("Instructor1", "password");
		    instructor.login();
		    System.out.println("Instructor logged in");
		}

		@When("I create a new program with the following details:")
		public void i_create_a_new_program_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
		    // إنشاء برنامج جديد بالتفاصيل
		    Program newProgram = new Program(dataTable);
		    newProgram.create();
		    System.out.println("Creating a new program with the details: " + dataTable);
		}

		@Then("The program should be created successfully")
		public void the_program_should_be_created_successfully() {
		    // التأكد من إنشاء البرنامج
		    boolean isCreated = Program.isProgramCreated();
		    assertTrue("Program created successfully", isCreated);
		}

		@Then("The program details should be visible to clients")
		public void the_program_details_should_be_visible_to_clients() {
		    // التأكد من ظهور التفاصيل للعملاء
		    boolean isVisible = Program.areDetailsVisibleToClients();
		    assertTrue("Program details visible", isVisible);
		}

		@When("I create a fitness program titled {string} and attach videos, images, and documents to the program")
		public void i_create_a_fitness_program_and_attach_media(String programTitle) {
		    // إنشاء برنامج مع وسائط مرفقة
		    Program fitnessProgram = new Program(programTitle);
		    fitnessProgram.attachMedia("video, image, document");
		    System.out.println("Creating program titled: " + programTitle + " with media attachments.");
		}

		@Then("the program should be created successfully with all attached media visible to clients")
		public void the_program_should_be_created_with_attachments() {
		    // التأكد من إنشاء البرنامج مع الوسائط وظهوره للعملاء
		    boolean isCreatedWithMedia = Program.isProgramCreatedWithMedia();
		    assertTrue("Program with media created successfully", isCreatedWithMedia);
		}

		@When("I attempt to create a fitness program titled {string} without entering a price")
		public void i_attempt_to_create_a_program_without_price(String programTitle) {
		    // محاولة إنشاء برنامج بدون سعر
		    Program program = new Program(programTitle);
		    program.setPrice(0); // فرض السعر 0
		    System.out.println("Attempting to create program without price: " + programTitle);
		}

		@Then("I should receive an error stating that the price is required")
		public void i_should_receive_an_error_about_price() {
		    // التحقق من وجود خطأ حول السعر
		    boolean isError = Program.isPriceRequiredError();
		    assertTrue("Error: Price is required", isError);
		}

		@Then("the program should not be created")
		public void the_program_should_not_be_created() {
		    // التأكد من عدم إنشاء البرنامج
		    boolean isCreated = Program.isProgramCreated();
		    assertTrue("Program was not created", !isCreated);
		}

		@When("I attempt to create a fitness program titled {string} with the difficulty level {string}")
		public void i_attempt_to_create_a_program_with_difficulty(String programTitle, String difficultyLevel) {
		    // محاولة إنشاء برنامج بمستوى صعوبة غير مدعوم
		    Program program = new Program(programTitle);
		    program.setDifficultyLevel(difficultyLevel);
		    System.out.println("Attempting to create program with difficulty level: " + difficultyLevel);
		}

		@Then("I should receive an error stating that the difficulty level is unsupported")
		public void i_should_receive_an_error_about_difficulty() {
		    // التأكد من وجود خطأ حول مستوى الصعوبة
		    boolean isError = Program.isDifficultyLevelUnsupported();
		    assertTrue("Error: Difficulty level is unsupported", isError);
		}

		@Given("I have an existing fitness program titled {string}")
		public void i_have_an_existing_fitness_program(String programTitle) {
		    // التأكد من وجود برنامج موجود
		    Program existingProgram = Program.getProgramByTitle(programTitle);
		    System.out.println("Existing program: " + existingProgram.getTitle());
		}

		@When("I update the program with the following details:")
		public void i_update_the_program_with_details(io.cucumber.datatable.DataTable dataTable) {
		    // تحديث البرنامج
		    Program existingProgram = Program.getProgramByTitle(dataTable);
		    existingProgram.update(dataTable);
		    System.out.println("Updating program with new details: " + dataTable);
		}

		@Then("The program should be updated successfully")
		public void the_program_should_be_updated_successfully() {
		    // التأكد من تحديث البرنامج
		    boolean isUpdated = Program.isProgramUpdated();
		    assertTrue("Program updated successfully", isUpdated);
		}

		@Then("The updated details should be visible to clients")
		public void the_updated_details_should_be_visible_to_clients() {
		    // التأكد من ظهور التفاصيل المحدثة للعملاء
		    boolean isVisible = Program.areUpdatedDetailsVisibleToClients();
		    assertTrue("Updated details visible to clients", isVisible);
		}

		@When("I delete the fitness program")
		public void i_delete_the_fitness_program() {
		    // حذف البرنامج
		    Program programToDelete = Program.getProgramByTitle("Program Title");
		    programToDelete.delete();
		    System.out.println("Deleting fitness program");
		}

		@Then("The program should be deleted successfully")
		public void the_program_should_be_deleted_successfully() {
		    // التأكد من حذف البرنامج
		    boolean isDeleted = Program.isProgramDeleted();
		    assertTrue("Program deleted successfully", isDeleted);
		}

		@Then("The program should no longer be visible to clients")
		public void the_program_should_no_longer_be_visible_to_clients() {
		    // التأكد من عدم ظهور البرنامج للعملاء بعد الحذف
		    boolean isVisible = Program.isProgramVisible();
		    assertTrue("Program not visible to clients", !isVisible);
		}

	}

