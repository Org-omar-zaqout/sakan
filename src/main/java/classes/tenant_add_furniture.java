package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import conn.connect;

public class tenant_add_furniture{
	protected String name;
	protected int id_tenants;
	protected int price;
	private static final Logger logger = Logger.getLogger(tenant_add_furniture.class.getName());
	protected boolean add_f;
	
	
	public tenant_add_furniture() {
		this.add_f=false;
	}
	
	public tenant_add_furniture(String name,int price) {
		
		this.name=name;
		this.price=price;
		
	}
	
	public boolean add_furniture(loginpage login) throws SQLException {
		connect con =new connect();
		con.func();
		String namee=login.username;
		String insert_furniture="INSERT INTO `furniture` ( `name`,`price`,`id_tenants`) VALUES (?,?,?);";
		Statement stmt = con.getConnection().prepareStatement(insert_furniture);
		
		String select_id="SELECT `id` FROM `users` WHERE `type_user`='tenant' And `username`=?;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_id)) {
            preparedStatement.setString(1, namee);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.id_tenants = resultSet.getInt("id");
                }
            }
        } 
	catch (SQLException e) {
		logger.info("An SQL exception occurred"+e);
		}
		
	

		
	
		
	
		if(login.isLoggedTenant())
		{	
			logger.log(Level.INFO,"the tenant loggin");
			try(PreparedStatement statement = con.getConnection().prepareStatement(insert_furniture)) {
				
				statement.setString(1,this.name);
				statement.setInt(2, this.price);
				statement.setInt(3, this.id_tenants);
				
				statement.executeUpdate();
				this.add_f=true;
				logger.log(Level.INFO,"the tenant is loggin");
			} catch (SQLException e) {
				logger.info("An SQL exception occurred"+e);
			}
		}
		else
		{
			logger.log(Level.INFO,"the tenant not loggin");
		}
			return add_f;
			
		}
	
public boolean added_furniture() {
		
		return this.add_f;
	}
	
}