package steprunnerTest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import classes.book_accommodation;
import classes.loginpage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Book_accommodation {
	private static final Logger logger = Logger.getLogger(Book_accommodation.class.getName());
	loginpage login;
	book_accommodation book;
	
	
	public Book_accommodation(book_accommodation book) {
		this.login=new loginpage("ali","123456");
		this.book=book;
	}
	
	@Given("the tenant is loginnn")
	public void the_tenant_is_loginnn() {
	    // Write code here that turns the phrase above into concrete actions
		try {
			login.validateCredentials("ali", "123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Given("i want to  book accommodation")
	public void i_want_to_book_accommodation() {
	    // Write code here that turns the phrase above into concrete actions
		 book= new book_accommodation(89,57,71);
	}

	@When("booking accommodation")
	public void booking_accommodation() throws SQLException {
	    // Write code here that turns the phrase above into concrete actions
book.insert_book(login);	
book.found_apart();
}

	@Then("booking done")
	public void booking_done() {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(true,book.Booking());
		assertEquals(false,book.founding());

	}

	@When("not found avilabel")
	public void not_found_avilabel() {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(false,book.Booking());
	}
	@Then("error messages not book not found {string}")
	public void error_messages_not_book_not_found(String string) {
	    // Write code here that turns the phrase above into concrete actions
		logger.log(Level.INFO,"error "+string);
	}
	
	@Given("the tenant is not loginnn")
	public void the_tenant_is_not_loginnn() {
	    // Write code here that turns the phrase above into concrete actions
		try {
			login.validateCredentials("ali", "1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Then("error messages not book ont login {string}")
	public void error_messages_not_book_ont_login(String string) {
	    // Write code here that turns the phrase above into concrete actions
		logger.log(Level.INFO,"error "+string);
	}
}
