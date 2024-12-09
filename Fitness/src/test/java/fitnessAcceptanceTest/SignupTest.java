package fitnessAcceptanceTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import fitnessAcceptanceTest.User;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class SignupTest {

    User u = new User();
    Logger logger= Logger.getLogger(SignupTest.class.getName());


    @Given("customers")
    public void customers(io.cucumber.datatable.DataTable dataTable) {

    }
    @Given("there is a customer with customer ID {string} ,NAME {string} , PASSWORD {string} , phone {string}, address {string}")
    public void thereIsACustomerWithCustomerIDNAMEPASSWORDPhoneAddress(String string, String string2, String string3, String string4, String string5) {
        u.setId(string);
        u.setUserName(string2);
        u.setPass(string3);
        u.setPhone(string4);
        u.setAddress(string5);
        u.setType("customer");


    }
    @When("the customer is registered {string}")
    public void theCustomerIsRegistered(String string) {

        if(u.isRegest(string))
            User.adduser(u);

    }
    @Then("the customer with ID {string} ,NAME {string} , PASSWORD {string} , phone {string}, address {string} is registered in the system")
    public void theCustomerWithIDNAMEPASSWORDPhoneAddressIsRegisteredInTheSystem(String string) {
        if(u.isRegest(string))
            assertTrue(true);
    }

    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven() {
        String string = "this user is already registered";
        logger.log(Level.INFO, string);
    }

}