package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import conn.connect;

public class Owner_add_houses {
	protected String name_House;
	protected String picture_House;
	protected String location_House;
	protected String available_services_House;
	protected int price_House;
	protected String information_contact;
	protected int id_owner;
	protected boolean determine_unit_availability_House;
	protected boolean add;
    private static final Logger logger = Logger.getLogger(Owner_add_houses.class.getName());
	public String getName_House() {
		return name_House;
	}
	public void setName_House(String name_House) {
		this.name_House = name_House;
	}
	public String getPicture_House() {
		return picture_House;
	}
	public void setPicture_House(String picture_House) {
		this.picture_House = picture_House;
	}
	public String getLocation_House() {
		return location_House;
	}
	public void setLocation_House(String location_House) {
		this.location_House = location_House;
	}
	public String getAvailable_services_House() {
		return available_services_House;
	}
	public void setAvailable_services_House(String available_services_House) {
		this.available_services_House = available_services_House;
	}
	public int getPrice_House() {

		return price_House;
	}
	public void setPrice_House(int price_House) {
		this.price_House = price_House;
	}
	public String getInformation_contact() {
		return information_contact;
	}
	public void setInformation_contact(String information_contact) {
		this.information_contact = information_contact;
	}
	public boolean isDetermine_unit_availability_House() {
		return determine_unit_availability_House;
	}
	public void setDetermine_unit_availability_House(boolean determine_unit_availability_House) {
		this.determine_unit_availability_House = determine_unit_availability_House;
	}
	
	
	public Owner_add_houses() {
		this.determine_unit_availability_House = true;
		this.add = false;
	}
    
	
	public Owner_add_houses(String name_House, String picture_House, String location_House,
			String available_services_House, int price_House, String information_contact) {
		
		this.name_House = name_House;
		this.picture_House = picture_House;
		this.location_House = location_House;
		this.available_services_House = available_services_House;
		this.price_House = price_House;
		this.information_contact = information_contact;
		
		
	}
	public boolean add(loginpage loginO) throws SQLException {
		connect con =new connect();
		con.func();
		String select_id="SELECT `id` FROM `users` WHERE `type_user`='owner' And `username`=?;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_id)) {
            preparedStatement.setString(1, loginO.username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.id_owner = resultSet.getInt("id");
                }
            }
        } 
	catch (SQLException e) {
		logger.info("An SQL exception occurred"+e);
    }
		String insert_house="INSERT INTO `houses` ( `name`, `image`, `location`, `available_services`, `price`, `information`,id_user) VALUES (?,?,?,?,?,?,?);";
		
		
		add = false;
		if(loginO.isLoggedOwner())
		{	
			logger.log(Level.INFO,"the owner loggin");
			try(PreparedStatement statement = con.getConnection().prepareStatement(insert_house)) {
				
				statement.setString(1,this.name_House);
				statement.setString(2, this.picture_House);
				statement.setString(3, this.location_House);
				statement.setString(4, this.available_services_House);
				statement.setInt(5, this.price_House);
				statement.setString(6, this.information_contact);
				statement.setInt(7, this.id_owner);
				statement.executeUpdate();
				this.add=true;
			} catch (SQLException e) {
				logger.info("An SQL exception occurred"+e);
			}
		}
		else
		{
			logger.log(Level.INFO,"the owner not loggin");
		}
			return this.add;
			
		}
	public boolean added() {
		
		return this.add;
	}
    
    
    
    
    
}
