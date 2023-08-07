package conn;

import java.sql.Connection;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;

public class connect {
	protected Connection connection;
	 private static final Logger logger = Logger.getLogger(connect.class.getName());
    public void func() throws SQLException {
    	 Properties properties = new Properties();
    	 try (FileInputStream fis = new FileInputStream("D:\\Software\\untitled4\\src\\main\\java\\conn\\db.properties")) {
    		 Class.forName("com.mysql.cj.jdbc.Driver");
    		 properties.load(fis);
             String url = properties.getProperty("db.url");
             String user = properties.getProperty("db.user");
             String pass = "db.password";

            
             connection = DriverManager.getConnection(url, user, pass);
           
            		logger.info("success conected");
       
    	 } 
    	 catch (IOException | ClassNotFoundException e1) {
			
			logger.info("Error"+e1);
		}
    }

    public void executeStatement(String sqlStatement) {
    	 try (Statement statement = connection.createStatement()) {

                ResultSet resultSet = statement.executeQuery(sqlStatement);

                if (resultSet.next()) {
                    int totalRows = resultSet.getInt("total_rows");
                    String logMessage = String.format("Total rows in the users table: %d ",totalRows);
                    logger.log(Level.INFO, logMessage);
                   
                }
                else {
                    logger.info("Connection is not established. Please call establishConnection() first.");
                }

            } catch (SQLException e) {
            	logger.info("An SQL exception occurred"+e);
            }
        }

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
   

    
    
}