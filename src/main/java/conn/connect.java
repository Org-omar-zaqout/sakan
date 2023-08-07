package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;


public class connect {
	protected Connection connection;
	 private static final Logger logger = Logger.getLogger(connect.class.getName());
    public void func() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom","root","");
            		
            		logger.info("success conected");
        } catch (ClassNotFoundException e) {
        	logger.info("An SQL exception occurred"+e);
        }
    }

    public void executeStatement(String sqlStatement) {
    	 try (Statement statement = connection.createStatement()) {

                ResultSet resultSet = statement.executeQuery(sqlStatement);

                if (resultSet.next()) {
                    int totalRows = resultSet.getInt("total_rows");
                    logger.info("Total rows in the users table: " + totalRows);
                }
                else {
                    logger.info("Connection is not established. Please call establishConnection() first.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
   

    
    
}