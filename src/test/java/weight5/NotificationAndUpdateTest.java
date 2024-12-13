package weight5;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.util.List;

import ClientInstruction.Client;
import ClientInstruction.Instructor;
import NotificationAndUpdate.*;


public class NotificationAndUpdateTest {

	@Given("I am logged in as an instructor")
	public void i_am_logged_in_as_an_instructor() {
	    // إضافة منطق تسجيل الدخول هنا
	    Instructor instructor = new Instructor("Instructor1", "password");
	    instructor.login();
	    System.out.println("Instructor logged in");
	}

	@Given("the program {string} has a schedule update")
	public void the_program_has_a_schedule_update(String programTitle) {
	    // إضافة منطق تحديد تحديث البرنامج هنا
	    Program program = new Program(programTitle);
	    program.updateSchedule();
	    System.out.println("Schedule update for program: " + programTitle);
	}

	@When("I notify all enrolled clients about the schedule change with the message {string}")
	public void i_notify_all_enrolled_clients_about_the_schedule_change_with_the_message(String notificationContent) {
	    // إضافة منطق إرسال الإشعار هنا
	    Notification notification = new Notification(notificationContent);
	    notification.sendToAllClients();
	    System.out.println("Notification sent: " + notificationContent);
	}

	@Then("all clients should receive the notification")
	public void all_clients_should_receive_the_notification() {
	    List<Client> enrolledClients = List.of(new Client("Client1"), new Client("Client2"));
	    Notification notification = new Notification("Schedule update!");
	    notification.sendToAllClients();
	    boolean allClientsNotified = notification.areAllClientsNotified(enrolledClients);
	    
	    assertTrue("Clients should receive notification", allClientsNotified);
	}

	@Then("the updated schedule should be visible in their program details")
	public void the_updated_schedule_should_be_visible_in_their_program_details() {
	    Program program = new Program("Sample Program");
	    program.updateSchedule(); 
	    boolean isScheduleVisible = program.isScheduleVisible(); 
	    assertTrue("Updated schedule visible", isScheduleVisible);
	}


	@Given("I am logged in as an instructor")
	public void i_am_logged_in_as_an_instructor_again() {
	    // إضافة منطق تسجيل الدخول هنا
	    Instructor instructor = new Instructor("Instructor1", "password");
	    instructor.login();
	    System.out.println("Instructor logged in");
	}

	@When("I create an announcement for a new program titled {string} or a special offer {string}")
	public void i_create_an_announcement_for_a_new_program_titled_or_a_special_offer(String programTitle, String offerDetails) {
	    Announcement announcement = new Announcement(programTitle, offerDetails);
	    announcement.create();
	    System.out.println("Announcement created for program: " + programTitle + " or special offer: " + offerDetails);
	}


	@Then("all clients should receive the announcement")
	public void all_clients_should_receive_the_announcement() {
	    boolean allClientsNotified = Announcement.areAllClientsNotified();
	    assertTrue("Clients should receive the announcement", allClientsNotified);
	}


	@Then("the details should appear in the notifications section for clients")
	public void the_details_should_appear_in_the_notifications_section_for_clients() {
	    boolean isAnnouncementVisible = Announcement.isAnnouncementVisibleInNotifications();
	    assertTrue("Announcement appears in notifications", isAnnouncementVisible);
	}


}
