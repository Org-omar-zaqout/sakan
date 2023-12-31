package classes;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import conn.connect;



public class Control_Panel {
	protected int owner_id;
	connect con=new connect();
	protected int count_tenants;
	protected boolean has_find;
	protected int num_floor;
	protected int num_apart;
	protected int num_bathroom;
	protected int num_bedroom;
	protected int balcony;
	protected int id_user;
	protected String contact_info;
	protected int id;
    private static final String SQL_EXCEPTION_MESSAGE = "An SQL exception occurred";
	 private static final Logger logger = Logger.getLogger(Control_Panel.class.getName());
	 
	 


	public void Select_houses(loginpage login) throws SQLException{
		con.func();
		String select_id="SELECT `id` FROM `users` WHERE `type_user`='owner' And `username`=?;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_id)) {
            preparedStatement.setString(1, login.username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.owner_id = resultSet.getInt("id");
                }
            }
        } 
	catch (SQLException e) {
		logger.info(SQL_EXCEPTION_MESSAGE + e);
    }
		String select_house="SELECT `id`, `name`, `image`, `location`, `available_services`, `price`, `information` FROM `houses` WHERE id_user=? And isAccept=1;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_house)) {
            preparedStatement.setInt(1,this.owner_id);
            if(login.isLoggedOwner()) {
    		has_find=false;
            try (ResultSet rs = preparedStatement.executeQuery()) {
                
                	while(rs.next()){
        				int id_house = rs.getInt("id");
        				 String name = rs.getString("name");
        				 String image = rs.getString("image");
        				 String location = rs.getString("location");
        				 String available_services = rs.getString("available_services");
        			     int price = rs.getInt("price");
        			     String information = rs.getString("information");
        			     String logMessage = String.format("%d %s %s %s %s %d %s", id_house, name, image, location, available_services, price, information);
                         logger.log(Level.INFO, logMessage);
        			       this.has_find=true; 
        			}
                if(has_find==false) {
                	logger.info("doesnt have any house");
                }
               
            }
            }
            else {
				logger.log(Level.INFO,"the Owner is doesnt login");
			}   
        } 
	catch (SQLException e) {
		logger.info(SQL_EXCEPTION_MESSAGE + e);
    }
		
			
		}
	
	public void count_tanents(int house_id) throws SQLException {
		con.func();
		String select_tan="SELECT COUNT(*) AS num_of_tenants FROM tanents WHERE id_house = ?;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_tan)) {
            preparedStatement.setInt(1,house_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.count_tenants = resultSet.getInt("num_of_tenants");
                }
            }
            String logMessage = String.format("count_tenants =  %d",this.count_tenants);
            logger.log(Level.INFO, logMessage);
          
        } 
	catch (SQLException e) {
		logger.info(SQL_EXCEPTION_MESSAGE + e);
		}
	}
	
	public void num_floor(int house_id) throws SQLException {
		con.func();
		String select_floor="SELECT COUNT(*) as floor FROM `floor` WHERE id_house= ?;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_floor)) {
            preparedStatement.setInt(1,house_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.num_floor = resultSet.getInt("floor");
                }
            }
            String logMessage = String.format("num_floor =  %d",this.num_floor);
            logger.log(Level.INFO, logMessage);
          
        } 
	catch (SQLException e) {
		logger.info(SQL_EXCEPTION_MESSAGE + e);
		}
	}
	
	public void num_apartment(int floor_id) throws SQLException {
		con.func();
		String select_apart="SELECT COUNT(*) as apart FROM `apartments` WHERE id_floor= ?;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_apart)) {
            preparedStatement.setInt(1,floor_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.num_apart = resultSet.getInt("apart");
                }
            }
            String logMessage = String.format("num_apartment =  %d",this.num_apart);
            logger.log(Level.INFO, logMessage);
            
        } 
	catch (SQLException e) {
		logger.info(SQL_EXCEPTION_MESSAGE + e);
		}
	}
	
	public void num_details(int apart_id) throws SQLException {
		con.func();
		String select_id="SELECT `id_user`,`id` FROM `tanents` WHERE `id_apart`=?;";
		
		String select_details="SELECT `num_bathroom`, `num_bedroom`, `balcony` FROM `apartments` WHERE`id_apartment`= ?;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_details)) {
            preparedStatement.setInt(1,apart_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.num_bathroom = resultSet.getInt("num_bathroom");
                    this.num_bedroom = resultSet.getInt("num_bedroom");
                    this.balcony = resultSet.getInt("balcony");

                }
            }
            String logMessage = String.format("num_bathroom = %d num_bedroom = %d balcony %d ",this.num_bathroom,this.num_bedroom,this.balcony);
            logger.log(Level.INFO, logMessage);
        } 
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_id)) {
            preparedStatement.setInt(1,apart_id);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                
                	
                	while(resultSet.next()){
                {
                    this.id_user = resultSet.getInt("id_user");
                    this.id = resultSet.getInt("id");

                    String logMessage = String.format("id = %d ",this.id);
                    logger.log(Level.INFO, logMessage);
             
           
       
		String select_name_contact_info="SELECT `contact_info` FROM `users` WHERE`id`=?;";
		try (PreparedStatement preparedStatement2 = con.getConnection().prepareStatement(select_name_contact_info)) {
            preparedStatement2.setInt(1,this.id_user);

            try (ResultSet resultSet2 = preparedStatement2.executeQuery()) {
                if (resultSet2.next()) {
                    this.contact_info = resultSet2.getString("contact_info");
                    String logMessag = String.format("id = %d user = %d contact_info = %s ",this.id,this.id_user,this.contact_info);
                    logger.log(Level.INFO, logMessag);
                  
                }
            }
    
        } 
		}
                }
            }
            }
	catch (SQLException e) {
		logger.info(SQL_EXCEPTION_MESSAGE + e);
		}
		}
	}
	

	


