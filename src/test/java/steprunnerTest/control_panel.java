package steprunnerTest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;



import classes.Control_Panel;

import classes.loginpage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class control_panel {
	

	loginpage login;
	Control_Panel control;
	
	
	public control_panel(Control_Panel control) {
		this.login=new loginpage("omar","321");
		this.control=new Control_Panel();
	}
	
	@Given("login using owner")
	public void login_using_owner() {
	    // Write code here that turns the phrase above into concrete actions
		try {
			login.validateCredentials("omar", "321");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("select houses")
	public void select_houses() throws SQLException {
	    // Write code here that turns the phrase above into concrete actions
		control.Select_houses(login);
	}

	@Then("complete view")
	public void complete_view() throws SQLException {
	    // Write code here that turns the phrase above into concrete actions
		control.count_tanents(89);
		control.num_floor(88);
		control.num_apartment(50);
		control.num_details(71);
	}

	@Given("doesnt login using owner")
	public void doesnt_login_using_owner() {
	    // Write code here that turns the phrase above into concrete actions
		try {
			login.validateCredentials("omar", "3333");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("failed login owner")
	public void failed_login_owner() {
	    // Write code here that turns the phrase above into concrete actions
assertEquals(false,login.isLoggedOwner());
	}
}
