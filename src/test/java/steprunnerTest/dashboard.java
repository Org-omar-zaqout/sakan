package steprunnerTest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import classes.Dashboard;
import classes.loginpage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class dashboard {
    private static final Logger logger = Logger.getLogger(dashboard.class.getName());

	loginpage loginO;
	Dashboard dash ;
	
	public dashboard(Dashboard dash ) {
		this.loginO=new loginpage("omar","321");
		this.dash=dash;
	}
	
@Given("The owner is log")
public void the_owner_is_log() {
	try {
		loginO.validateCredentials("omar","321");
	
		
	} catch (SQLException e) {
	
	}
}

@Given("i want to add")
public void i_want_to_add() {
	 // Write code here that turns the phrase above into concrete actions
	int bath[]= {1,1,3,4,2};
	int bed[]= {2,3,2,2,2,2};
	boolean bal[]= {true,true,true,true};
	dash=new Dashboard(89, 2, 2,0, bath, bed, bal);
	
}

@When("adding floor and apartments")
public void adding_floor_and_apartments() throws SQLException {
	dash.add(loginO);
}

@Then("add Done")
public void add_done() {
	assertEquals(true,dash.added());
}

@Given("The owner is not login")
public void the_owner_is_not_login() {
	try {
		loginO.validateCredentials("omar", "311");
		loginO.logout_owner();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Given("i want to adding the floor and apartments")
public void i_want_to_adding_the_floor_and_apartments() {
	int bath[]= {1,1,3,4,2};
	int bed[]= {2,3,2,2,2,2};
	boolean bal[]= {true,true,true,true};
	dash=new Dashboard(88, 2, 1,1, bath, bed, bal);
	
}

@Then("error messages DB {string}")
public void error_messages_db(String string) {
	// Write code here that turns the phrase above into concrete actions
			logger.log(Level.INFO,"error "+string);
}
}