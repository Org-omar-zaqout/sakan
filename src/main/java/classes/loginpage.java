package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import conn.connect;

import java.util.logging.Logger;
public class loginpage {
	
	
	protected boolean flag_login_admin;
	protected boolean flag_login_owner;
	protected boolean flag_login_tenant;
	protected boolean flag_login;
	protected String username;
	protected String password;
	String type_admin="admin";
	String type_owner="owner";
	String type_tenant="tenant";
	  private static final Logger logger = Logger.getLogger(Owner_add_houses.class.getName());
	

	public loginpage(String username,String password) {
	
//		
//		this.flag_login_admin = false;
//		this.flag_login_owner = false;
//		this.flag_login_tenant = false;
		this.username = username;
		this.password=password;
		
	}
	
	public boolean validateCredentials(String username, String password) throws SQLException {
		connect con=new connect();
		con.func();
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? ";
        
        try (
             PreparedStatement statement = con.getConnection().prepareStatement(sql)) {
        	 statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
               String type =resultSet.getString("type_user");
             
                this.flag_login_admin=false;
                this.flag_login_owner=false;
           		this.flag_login_tenant=false;
           		this.flag_login=false;
                if(type.equals(type_admin)) {
                	this.flag_login_admin=true;
                	this.flag_login=true;
                	//return this.flag_login_admin;
                }
                else if(type.equals(type_owner)) {
                	this.flag_login_owner=true;
                	this.flag_login=true;
                	//return this.flag_login_owner;
                }
                else {
                	
                	this.flag_login_tenant=true;
                	this.flag_login=true;
                	//return this.flag_login_tenant;
                }
            }
            

        } catch (SQLException e) {
          System.out.print(e);
        }
		return flag_login;

        // The credentials are invalid
        
    }
	
	
	
	@Override
	public String toString() {
		return "loginpage [flag_login=" + flag_login + "]";
	}

	public loginpage() {
		
		this.flag_login_admin = false;
		this.flag_login_owner = false;
		this.flag_login_tenant = false;
		this.username = null;
        this.password = null;
	}
	public boolean isLogged() {
		
		return this.flag_login;
	}
	public boolean logout() {
		this.flag_login = false;
		
		return (this.flag_login);
	}
	
	public boolean isLoggedAdmin() {
		
		return this.flag_login_admin;
	}
	public boolean isLoggedOwner() {
		return this.flag_login_owner;
	}
	public boolean isLoggedTenant() {
		return this.flag_login_tenant;
	}
	public boolean logout_admin() {
		this.flag_login_admin = false;
		
		return (this.flag_login_admin);
	}
	public boolean logout_owner(){
		this.flag_login_owner= false;
		
		return (this.flag_login_owner);
	}
	public boolean logout_tenant(){
		this.flag_login_tenant = false;

		return (this.flag_login_tenant);
	}
}
