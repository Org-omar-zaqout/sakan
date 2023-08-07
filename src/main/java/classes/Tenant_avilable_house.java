package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import conn.connect;

public class Tenant_avilable_house {

	loginpage login =new loginpage();
	connect con=new connect();
	protected int tenant_id;
	protected int ccc;
	private static final Logger logger = Logger.getLogger(Tenant_avilable_house.class.getName());
	protected boolean flag_found=false;
	

	
	
	public boolean is_found() throws SQLException {
		String num_house="SELECT COUNT(*) FROM `apartments` WHERE `avilable`=1;";
		con.func();
		try(Statement stmt = con.getConnection().createStatement();){
		
		ResultSet rs=stmt.executeQuery(num_house);
		if(rs.next()) {
			int rowCount=rs.getInt(1);

			flag_found=rowCount>0;
		}
		
		
		
	
		}
		catch (SQLException e) {
			logger.info("An SQL exception occurred"+e);	
			}
			
		return flag_found;
		
	}
	
	public void Select_houses(loginpage login) throws SQLException{
		String is_S="SELECT `id_user` FROM `student` WHERE `id_user`=?;";
		String select_house="SELECT `name`, `image`, `location`, `available_services`, `price` FROM `houses` WHERE `id`=?;";
		
		String select_apart_avalabel1="SELECT `id_apartment`, `id_floor`, `id_house` FROM `apartments` WHERE`for_student`='0';";

		String select_apart_avalabel="SELECT `id_apartment`, `id_floor`, `id_house` FROM `apartments` WHERE`for_student`='1';";
		con.func();
		
		String select_id="SELECT `id` FROM `users` WHERE `type_user`='tenant' And `username`=?;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_id)) {
            preparedStatement.setString(1, login.username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.tenant_id = resultSet.getInt("id");
                  //  logger.log(Level.INFO,this.tenant_id +"######################");
                }
            }
        } 
	
		
		   try (PreparedStatement preparedStatement3 = con.getConnection().prepareStatement(is_S)) {
	            preparedStatement3.setInt(1,this.tenant_id);

	            try (ResultSet resultSet3 = preparedStatement3.executeQuery()) {
	           //  logger.log(Level.INFO,resultSet3.next() +"ssssssssssss");
	            	//this.ccc = resultSet3.getInt("id_user");
	                if (resultSet3.next()) {
	                //    logger.log(Level.INFO,resultSet3+"ddddddddddddd");

	                	 this.ccc = resultSet3.getInt("id_user");
	                	// logger.log(Level.INFO,this.ccc +"eeeeeeeeeeeeeee");
	    		  
//student
	                	
		try(Statement stmt = con.getConnection().createStatement()) {
			if(login.isLoggedTenant()) {
			
			ResultSet rs=stmt.executeQuery(select_apart_avalabel);
		
			while(rs.next()){
				int id_apartment = rs.getInt("id_apartment");
				int id_floor = rs.getInt("id_floor");
				int id_house = rs.getInt("id_house");
				
				
			   
			       
			        try (PreparedStatement preparedStatement1 = con.getConnection().prepareStatement(select_house)) {
			            preparedStatement1.setInt(1,id_house);

			            try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
			                if (resultSet1.next()) {
			                	String name = resultSet1.getString("name");
			                	 String image = resultSet1.getString("image");
			    				 String location = resultSet1.getString("location");
			    				 String available_services = resultSet1.getString("available_services");
			    			     int price = resultSet1.getInt("price");

			    			     logger.log(Level.INFO,id_house + ", " +name+ ", "+id_floor + ", " + id_apartment +
				                           ", " + location + ", " + available_services+"," + price + ", ");
			                }
			            }
			        } 
				catch (SQLException e) {
					logger.info("An SQL exception occurred"+e);			    }
					}
					
			}
			else {
				logger.log(Level.INFO,"the Tenant is doesnt login");
			}
	
		}

	                }
	                else
	                {
	                	
	                	
	                	try(Statement stmt = con.getConnection().createStatement()) {
	            			if(login.isLoggedTenant()) {
	            			
	            			ResultSet rs=stmt.executeQuery(select_apart_avalabel1);
	            			
	            			while(rs.next()){
	            				int id_apartment = rs.getInt("id_apartment");
	            				int id_floor = rs.getInt("id_floor");
	            				int id_house = rs.getInt("id_house");
	            				
	            				
	            			   
	            			       
	            			        try (PreparedStatement preparedStatement1 = con.getConnection().prepareStatement(select_house)) {
	            			            preparedStatement1.setInt(1,id_house);

	            			            try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
	            			                if (resultSet1.next()) {
	            			                	String name = resultSet1.getString("name");
	            			                	 String image = resultSet1.getString("image");
	            			    				 String location = resultSet1.getString("location");
	            			    				 String available_services = resultSet1.getString("available_services");
	            			    			     int price = resultSet1.getInt("price");

	            			    			     logger.log(Level.INFO,id_house + ", " +name+ ", "+id_floor + ", " + id_apartment +
	            				                           ", " + location + ", " + available_services+"," + price + ", ");
	            			                }
	            			            }
	            			        } 
	            				catch (SQLException e) {
	            					logger.info("An SQL exception occurred"+e);	            			    }
	            					}
	            				}
	            					
	            			
	            			else {
	            				logger.log(Level.INFO,"the Tenant is doesnt login");
	            			}
	            	
	            		}

	            	                }	
	                	
	                	
	                }
	                
		      
	                
	           
	        } 
		catch (SQLException e) {
			logger.info("An SQL exception occurred"+e);	    }
		
	}

	public boolean isFlag_found() {
		return flag_found;
	}

	public void setFlag_found(boolean flag_found) {
		this.flag_found = flag_found;
	}
	

	
	
	
	//control panel
	
	
	
}
