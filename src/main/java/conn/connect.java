package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.Statement;

public class connect {
	protected Connection connection;
	private static final Logger logger = Logger.getLogger(connect.class.getName());
	private String pass="123456";
    public void func() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom","root",pass);
		 Statement statement = connection.createStatement();

            // Execute the SQL query to change the password
            statement.executeUpdate("ALTER USER 'root'@'localhost' IDENTIFIED BY 'new_password';");

            // Close the resources
            statement.close();
            connection.close();
            		
            		
        } catch (ClassNotFoundException e) {
        	logger.info("An SQL exception occurred"+e);        }
    }

    

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
   

    
    
}
