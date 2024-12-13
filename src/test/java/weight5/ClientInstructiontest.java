package weight5;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import ClientInstruction.Client;
import ClientInstruction.ForumPost;
import ClientInstruction.Instructor;
import ClientInstruction.Message;

public class ClientInstructiontest {

	@Given("I am logged in as an instructor")
	public void i_am_logged_in_as_an_instructor() {
	    // تسجيل الدخول كمدرب
	    Instructor instructor = new Instructor("Instructor1", "password");
	    instructor.login();
	    System.out.println("Instructor logged in");
	}

	@Given("I have an enrolled client named {string}")
	public void i_have_an_enrolled_client_named(String clientName) {
	    // بتأكد إنه العميل مسجل
	    Client client = new Client(clientName);
	    client.enroll();
	    System.out.println("Enrolled client: " + clientName);
	}

	@When("I send a message saying {string} to the client")
	public void i_send_a_message_saying_to_the_client(String messageContent) {
	    // بتبعت رسالة للعميل
	    Message message = new Message(messageContent);
	    message.sendToClient();
	    System.out.println("Sending message: " + messageContent);
	}

	@Then("the message should be delivered successfully")
	public void the_message_should_be_delivered_successfully() {
	    Message message = new Message("Sample message"); 
	    message.sendToClient(); 
	    boolean isDelivered = message.isDelivered(); 
	    assertTrue("Message delivered successfully", isDelivered); 
	    }


	@Then("the client should be notified of the new message")
	public void the_client_should_be_notified_of_the_new_message() {
	    // بتتأكد إنه العميل اتنبه للرسالة
	    Client client = new Client("ClientName");
	    boolean isNotified = client.checkNotification();
	    assertTrue("Client notified of new message", isNotified);
	}

	@When("I create a new post titled {string} with content {string}")
	public void i_create_a_new_post_titled_with_content(String postTitle, String postContent) {
	    // بتعمل بوست جديد في المنتدى
	    ForumPost post = new ForumPost(postTitle, postContent);
	    post.create();
	    System.out.println("Creating post titled: " + postTitle + " with content: " + postContent);
	}

	@Then("the post should appear in the forum for all enrolled clients")
	public void the_post_should_appear_in_the_forum_for_all_enrolled_clients() {
	    // بتتأكد إنه البوست ظهر في المنتدى لجميع العملاء
	    boolean isPostVisible = ForumPost.isPostVisible();
	    assertTrue("Post appears in forum", isPostVisible);
	}

}
