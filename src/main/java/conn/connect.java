package conn;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.logging.Logger;



public class connect {
	protected Connection connection;
	private static final Logger logger = Logger.getLogger(connect.class.getName());

    public void func() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom","root","");
            		
            		
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
