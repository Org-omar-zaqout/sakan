package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import conn.connect;
public class book_accommodation {

	connect con =new connect();
	
	protected int id_user;
	protected int id_apart;
	protected int id_house;
	protected int id_floor;
	private static final Logger logger = Logger.getLogger(book_accommodation.class.getName());
	protected boolean booking;
	protected boolean found;
	protected int tenant_id;
	protected int count_apart;
	protected int avilable;
	protected String university_majors;
	protected int ages;

	
	public book_accommodation() {
		this.booking=false;
		this.found=true;
	}
	
	public void found_apart() throws SQLException {
		con.func();
		String select_part="SELECT COUNT(*) AS num_of_apart FROM apartments WHERE avilable =1;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_part)) {
         

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.count_apart = resultSet.getInt("num_of_apart");
                }
            }
            if(this.count_apart==0)
            {
            	this.found=false;
            	 String logMessage = String.format("count_tenants = %d ",this.count_apart);
                 logger.log(Level.INFO, logMessage);
      
            }
            
        } 
	catch (SQLException e) {
		logger.info("An SQL exception occurred"+e);    }
	}
	
	public book_accommodation(int id_house,int id_floor,int id_apart) {
		
		this.id_house=id_house;
		this.id_floor=id_floor;
		this.id_apart=id_apart;
	
		
	}
	
	public void insert_book(loginpage login) throws SQLException{
		
		con.func();
		String is_S="SELECT `id_user` FROM `student` WHERE `id_user`=?;";
		String select_id="SELECT `id` FROM `users` WHERE `type_user`='tenant' And `username`=?;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_id)) {
            preparedStatement.setString(1, login.username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.tenant_id = resultSet.getInt("id");
                }
            }
        } 
		
		
	
String insert_house="INSERT INTO `tanents` ( `id_user`, `id_house`, `id_floor`, `id_apart`) VALUES (?,?,?,?);";
		
		
		booking = false;
		if(login.isLoggedTenant())
		{	
			logger.log(Level.INFO,"the tenant loggin");
			try(PreparedStatement statement = con.getConnection().prepareStatement(insert_house)) {
				
				statement.setInt(1,this.tenant_id);
				statement.setInt(2,this.id_house);
				statement.setInt(3,this.id_floor);
				statement.setInt(4,this.id_apart);
				statement.executeUpdate();
				
			} catch (SQLException e) {
				logger.info("An SQL exception occurred"+e);
			}
		}
		else
		{
			logger.log(Level.INFO,"the tenant not loggin");
		}
			
		
		String upd="UPDATE `apartments` SET`avilable`='0' WHERE `id_apartment`=?;";
		String upd2="UPDATE `apartments` SET`avilable`='1' WHERE `id_apartment`=?;";
		String st="SELECT `ages`,`university majors` FROM `student` WHERE `id_user`=?";
		 try (PreparedStatement preparedStatement3 = con.getConnection().prepareStatement(is_S)) {
	            preparedStatement3.setInt(1,this.tenant_id);

	            try (ResultSet resultSet3 = preparedStatement3.executeQuery()) {
	           //
	                if (resultSet3.next()) {
	                	try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(st)) {
	                        preparedStatement.setInt(1,this.tenant_id);

	                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                            if (resultSet.next()) {
	                                this.ages = resultSet.getInt("ages");
	                                this.university_majors = resultSet.getString("university majors");
	                                String logMessage = String.format("ages = %d  university majors %s",this.ages,this.university_majors);
	                                logger.log(Level.INFO, logMessage);
	                                
	                            }
	                        }
	                	}
try(PreparedStatement statement = con.getConnection().prepareStatement(upd2)) {
	        				
	        				statement.setInt(1,this.id_apart);
	        				statement.executeUpdate();
	                	}
	                	
	                }
	                else {
	                	try(PreparedStatement statement = con.getConnection().prepareStatement(upd)) {
	        				
	        				statement.setInt(1,this.id_apart);
	        				statement.executeUpdate();
	                	
	                	}	
	                }
	                }
	                }
		 this.booking=true;
		}
public boolean Booking() {
		
		return this.booking;
	}

public boolean founding() {
	
	return this.found;
}
}
