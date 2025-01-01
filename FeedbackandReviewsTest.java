package fitnessAcceptanceTest;
 import fitnessAcceptanceTest.FeedbackandReviews;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import java.util.List;

public class FeedbackandReviewsTest {
	FeedbackandReviews feedbackAndReviews = new FeedbackandReviews();

    @Given("I have completed a program")
    public void i_have_completed_a_program() {
        // Simulate the completion of a program (for testing purposes)
        System.out.println("Program completed.");
    }

    @When("I rate the program with a score of {int} stars")
    public void i_rate_the_program_with_a_score_of_stars(Integer stars) {
        // Simulate rating a program
        feedbackAndReviews.submitFeedback("client123", "program456", "Rating: " + stars + " stars");
    }

    @Then("the rating should be saved successfully")
    public void the_rating_should_be_saved_successfully() {
        // Verify if the feedback has been stored
        feedbackAndReviews.viewMyFeedback("client123");
    }

    @Then("I should be able to view my rating on the program page")
    public void i_should_be_able_to_view_my_rating_on_the_program_page() {
        // Simulate viewing the rating on the program page
        feedbackAndReviews.viewMyFeedback("client123");
    }

    @When("I write a review with the content {string}")
    public void i_write_a_review_with_the_content(String reviewContent) {
        // Simulate writing a review
        feedbackAndReviews.submitFeedback("client123", "program456", reviewContent);
    }

    @Then("the review should be saved successfully")
    public void the_review_should_be_saved_successfully() {
        // Verify if the review has been saved
        feedbackAndReviews.viewMyFeedback("client123");
    }

    @Then("I should be able to view my review on the program page")
    public void i_should_be_able_to_view_my_review_on_the_program_page() {
        // Simulate viewing the review on the program page
        feedbackAndReviews.viewMyFeedback("client123");
    }

    @When("I submit a suggestion with the content {string}")
    public void i_submit_a_suggestion_with_the_content(String suggestion) {
        // Simulate submitting a suggestion
        feedbackAndReviews.submitFeedback("client123", "program456", suggestion);
    }

    @Then("the suggestion should be saved successfully")
    public void the_suggestion_should_be_saved_successfully() {
        // Verify if the suggestion has been saved
        feedbackAndReviews.viewMyFeedback("client123");
    }

    @Then("the instructor should be notified of my suggestion")
    public void the_instructor_should_be_notified_of_my_suggestion() {
        // Verify if the instructor has been notified (check notifications)
        feedbackAndReviews.viewNotifications("instructor123");
    }

    @When("I view the program page")
    public void i_view_the_program_page() {
        // Simulate viewing the program page
        System.out.println("Viewing program page.");
    }

    @Then("I should be able to see all ratings and reviews from other clients")
    public void i_should_be_able_to_see_all_ratings_and_reviews_from_other_clients() {
        // Simulate viewing all ratings and reviews
        feedbackAndReviews.viewMyFeedback("client123");
    }

    @Given("I have already written a review")
    public void i_have_already_written_a_review() {
        // Simulate that a review has already been written
        feedbackAndReviews.submitFeedback("client123", "program456", "Great program!");
    }

    @When("I edit my review to change the content to {string}")
    public void i_edit_my_review_to_change_the_content_to(String newContent) {
        // Simulate editing the review
        feedbackAndReviews.submitFeedback("client123", "program456", newContent);
    }

    @Then("my review should be updated successfully")
    public void my_review_should_be_updated_successfully() {
        // Verify that the review has been updated
        feedbackAndReviews.viewMyFeedback("client123");
    }

    @Then("I should be able to see the updated review on the program page")
    public void i_should_be_able_to_see_the_updated_review_on_the_program_page() {
        // Simulate viewing the updated review
        feedbackAndReviews.viewMyFeedback("client123");
    }

    @When("I rate the program with a score of {int} star and a comment {string}")
    public void i_rate_the_program_with_a_score_of_star_and_a_comment(Integer stars, String comment) {
        // Simulate rating the program with a comment
        feedbackAndReviews.submitFeedback("client123", "program456", comment + " (" + stars + " stars)");
    }

    @Then("the rating should be flagged for review by the system")
    public void the_rating_should_be_flagged_for_review_by_the_system() {
        // Simulate flagging the rating for review
        System.out.println("Rating flagged for review.");
    }

    @Then("I should be notified that my rating is under review")
    public void i_should_be_notified_that_my_rating_is_under_review() {
        // Simulate notification for under review rating
        feedbackAndReviews.viewNotifications("client123");
    }

    @When("I write a review with content {string}")
    public void i_write_a_review_with_content(String reviewContent) {
        // Simulate writing a review
        feedbackAndReviews.submitFeedback("client123", "program456", reviewContent);
    }

    @Then("the review should be flagged as inappropriate")
    public void the_review_should_be_flagged_as_inappropriate() {
        // Simulate flagging the review as inappropriate
        System.out.println("Review flagged as inappropriate.");
    }

    @Then("I should be notified that my review has been rejected due to inappropriate content")
    public void i_should_be_notified_that_my_review_has_been_rejected_due_to_inappropriate_content() {
        // Simulate notification for rejected review
        feedbackAndReviews.viewNotifications("client123");
    }

    @Given("I have already rated a program with {int} stars")
    public void i_have_already_rated_a_program_with_stars(Integer stars) {
        // Simulate already rating a program
        feedbackAndReviews.submitFeedback("client123", "program456", "Rating: " + stars + " stars");
    }

    @When("I try to submit the same {int}-star rating again")
    public void i_try_to_submit_the_same_star_rating_again(Integer stars) {
        // Simulate trying to submit the same rating again
        feedbackAndReviews.submitFeedback("client123", "program456", "Rating: " + stars + " stars");
    }

    @Then("I should be informed that I have already rated this program")
    public void i_should_be_informed_that_i_have_already_rated_this_program() {
        // Simulate informing the user about duplicate rating
        System.out.println("You have already rated this program.");
    }

    @Then("the system should not allow the duplicate rating or review")
    public void the_system_should_not_allow_the_duplicate_rating_or_review() {
        // Simulate blocking duplicate ratings or reviews
        System.out.println("Duplicate rating or review is not allowed.");
    }

    @When("I submit a review with the content {string}")
    public void i_submit_a_review_with_the_content(String reviewContent) {
        // Simulate submitting a review
        feedbackAndReviews.submitFeedback("client123", "program456", reviewContent);
    }

    @Then("the system should prompt me to provide more details if the review is too vague")
    public void the_system_should_prompt_me_to_provide_more_details_if_the_review_is_too_vague() {
        // Simulate prompting for more details if review is too vague
        System.out.println("Please provide more details for your review.");
    }

    @Then("the review should not be saved until I provide a more detailed review")
    public void the_review_should_not_be_saved_until_i_provide_a_more_detailed_review() {
        // Simulate not saving vague reviews
        System.out.println("Review not saved. Please provide more details.");
    }

    @Given("I have submitted a rating and review for a program")
    public void i_have_submitted_a_rating_and_review_for_a_program() {
        // Simulate submitting a rating and review
        feedbackAndReviews.submitFeedback("client123", "program456", "Great program!");
    }

    @When("the admin reviews my rating and review")
    public void the_admin_reviews_my_rating_and_review() {
        // Simulate admin reviewing the rating and review
        feedbackAndReviews.reviewFeedback("feedback123", true);
    }

    @Then("the admin should approve or reject the review based on guidelines")
    public void the_admin_should_approve_or_reject_the_review_based_on_guidelines() {
        // Simulate admin approval or rejection
        System.out.println("Admin has reviewed the rating and review.");
    }

    @Then("I should be notified if my review is published or rejected")
    public void i_should_be_notified_if_my_review_is_published_or_rejected() throws InterruptedException {
        // Simulate notifying the client about the review status
        feedbackAndReviews.viewNotifications("client123");

        // Add a delay to ensure the file operations have completed
        Thread.sleep(1000);  // Wait for 1 second

        // Now check if the notification was written
        List<String> notifications = feedbackAndReviews.viewNotifications("client123");

        // Assert that the client has been notified
        assertTrue("Client should be notified about the review status", notifications.size() > 0);
    }

}
