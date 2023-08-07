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
    String url = "jdbc:mysql://localhost:3306/sakancom";
    String user = "root";
    String pass = "your_password";

    try (Connection connection = DriverManager.getConnection(url, user, pass);
         Statement statement = connection.createStatement()) {

        
        statement.executeUpdate("ALTER USER 'root'@'localhost' IDENTIFIED BY 'new_password';");
        

    } catch (SQLException e) {
        // Handle SQL exception
        logger.info("An SQL exception occurred: " + e);
    }
}
       catch (ClassNotFoundException e) {
    // Handle class not found exception
    logger.info("Class not found exception occurred: " + e);

}
    }
    

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
   

    
    
}
