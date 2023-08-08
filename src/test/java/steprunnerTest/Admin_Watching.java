package steprunnerTest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import classes.admin_watching_res;
import classes.loginpage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Admin_Watching {

	loginpage login;
	admin_watching_res admin_watch;
	   private static final Logger logger = Logger.getLogger(Admin_Watching.class.getName());
	
	public Admin_Watching(admin_watching_res admin_watch) {
		this.login=new loginpage("deyaa","123");
		this.admin_watch=admin_watch;
	}
	
	
	@Given("admin is login to watch")
	public void admin_is_login_to_watch() {
	    
		try {
			login.validateCredentials("deyaa", "123");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@When("i want to Watching reservations")
	public void i_want_to_watching_reservations() {
	    
		try {
			admin_watch.found_house();
			assertEquals(true,admin_watch.is_found_book());
			admin_watch.watching(login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("show")
	public void show() {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(true,admin_watch.is_watch());
	}

	@When("i want to Watching reservations but not found")
	public void i_want_to_watching_reservations_but_not_found() throws SQLException {
	    // Write code here that turns the phrase above into concrete actions
		admin_watch.found_house();
		assertEquals(true,admin_watch.is_found_book());
	}

	@Then("message not found {string}")
	public void message_not_found(String string) {
	    // Write code here that turns the phrase above into concrete actions
		logger.log(Level.INFO,"error "+string);
	}

	@Given("admin is not login to watch")
	public void admin_is_not_login_to_watch() {
	    // Write code here that turns the phrase above into concrete actions
		try {
			login.validateCredentials("deyaa", "7777");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("erorr mas {string}")
	public void erorr_mas(String string) {
	    // Write code here that turns the phrase above into concrete actions
		logger.log(Level.INFO,"error "+string);
	}
	



}
