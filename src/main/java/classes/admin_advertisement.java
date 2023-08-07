package classes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import conn.connect;

public class admin_advertisement {
	connect con=new connect();
	 private static final String SQL_EXCEPTION_MESSAGE = "An SQL exception occurred";
	loginpage login =new loginpage();
	 private static final Logger logger = Logger.getLogger(admin_advertisement.class.getName());
	 protected boolean has_find;
	 protected int id;
	 protected int option;
	 protected boolean isAccept=false;
	 protected boolean isReject=false;

	
	public void Select_houses(loginpage login) throws SQLException{
		String select_house="SELECT `id`, `name`, `image`, `location`, `available_services`, `price`, `information` FROM `houses` WHERE `isAccept`=0 And `isReject`=0";
		con.func();
		
		try(Statement stmt = con.getConnection().createStatement()) {
			if(login.isLoggedAdmin()) {
			has_find=false;
			ResultSet rs=stmt.executeQuery(select_house);
			
			while(rs.next()){
				int id_house = rs.getInt("id");
				 String name = rs.getString("name");
				 String image = rs.getString("image");
				 String location = rs.getString("location");
				 String available_services = rs.getString("available_services");
			     int price = rs.getInt("price");
			     String information = rs.getString("information");
			     String logMsg = String.format("%d , %s , %s , %s , %s , %d , %s ",id_house,name,image,location,available_services,price,information);
   	             logger.log(Level.INFO, logMsg);
			      
			        has_find=true;
			}
			
			
		}
			else {
				logger.log(Level.INFO,"the admin is doesnt login");
			}
	
		}
		catch (SQLException e) {
			logger.info(SQL_EXCEPTION_MESSAGE + e);	
		}
		
	}
	
	public void joption(){
		 int option = JOptionPane.showOptionDialog(
	                null,
	                "Do you want to accept or reject?",
	                "Confirmation",
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE,
	                null,
	                new Object[]{"Accept", "Reject"},
	                "Accept");
		 	
	        if (option == JOptionPane.YES_OPTION) {
	            JOptionPane.showMessageDialog(null, "Accepted!");
	            this.option=option;
	            this.isAccept=true;
	        } else if (option == JOptionPane.NO_OPTION) {
	            JOptionPane.showMessageDialog(null, "Rejected!");
	            this.option=option;
	            this.isReject=true;
	        } else {
	            JOptionPane.showMessageDialog(null, "No option selected!");
	            this.option=option;
	        }
	        
	}
	
	public void req_houses(int id) throws SQLException {
		this.id=id;
		joption();
		con.func();
		if(this.option==0) {
			 String updateQuery = "UPDATE `houses` SET `isAccept`='1' WHERE `id`=?";
			 try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(updateQuery)) {
	              
	                preparedStatement.setInt(1, this.id);
	                
	              
	                preparedStatement.executeUpdate();
	            }
	         catch (SQLException e) {
	        	 logger.info(SQL_EXCEPTION_MESSAGE + e);
	        }
		}
		else if(this.option==1) {
			String acc_req="UPDATE `houses` SET `isReject`='1' WHERE `id`=?;";
			 try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(acc_req)) {
	              
	                preparedStatement.setInt(1, this.id);
	                
	              
	                preparedStatement.executeUpdate();
	            }
	         catch (SQLException e) {
	        	 logger.info(SQL_EXCEPTION_MESSAGE + e);
	        }
		}

		
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public boolean isAccept() {
		return isAccept;
	}

	public void setAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}

	public boolean isReject() {
		return isReject;
	}

	public void setReject(boolean isReject) {
		this.isReject = isReject;
	}


	
	
	
	
}
