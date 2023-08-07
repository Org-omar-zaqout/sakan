package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



import conn.connect;


public class admin_watching_res {
	protected boolean watch;
	protected boolean found_book;
	protected int count_book;
	connect con=new connect();
	 
	loginpage login =new loginpage();
	 private static final Logger logger = Logger.getLogger(admin_watching_res.class.getName());

public void watching(loginpage login) throws SQLException {
	String select_watch="SELECT t.id_apart,t.id_floor, u.username, h.name\r\n"
			+ "FROM `tanents` t\r\n"
			+ "JOIN `users` u ON t.id_user = u.id\r\n"
			+ "JOIN `houses` h ON t.id_house=h.id;";
	
	con.func();
	
	try(Statement stmt = con.getConnection().createStatement()) {
		if(login.isLoggedAdmin()) {
		this.watch=false;
		ResultSet rs=stmt.executeQuery(select_watch);
		
		while(rs.next()){
			
			 String username = rs.getString("username");
			 String name_house = rs.getString("name");
			 int id_floor = rs.getInt("id_floor");
		     int id_apart = rs.getInt("id_apart");
		     String logMsg = String.format("username %s  name %s floor %d apart %d  ",username,name_house,id_floor,id_apart);
	             logger.log(Level.INFO, logMsg);
		        
		        this.watch=true;
		}
		
		
	}
		else {
			logger.log(Level.INFO,"the admin is doesnt login");
		}

	}
	catch (SQLException e) {
		logger.info("An SQL exception occurred"+e);
	}
	
}

public void found_house() throws SQLException {
	con.func();
	String select_book="SELECT COUNT(*) AS num_of_house FROM tanents";
	this.found_book=false;
	try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_book)) {
     

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                this.count_book = resultSet.getInt("num_of_house");
            }
        }
        if(this.count_book>0)
        {
        	this.found_book=true;
        	 String logMessage = String.format("count_tenants = %d ",this.count_book);
             logger.log(Level.INFO, logMessage);
        
        }
        
    } 
catch (SQLException e) {
	logger.info("An SQL exception occurred"+e);
}
}

public boolean is_watch() {
	return this.watch;
}
public boolean is_found_book() {
	return this.found_book;
}

}
