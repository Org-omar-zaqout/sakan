package classes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import conn.connect;
public class tenant_control_panel {
	
	connect con =new connect();
	protected int tenant_id;
	protected String contact_infoT;
	protected String usernameT;
	protected String contact_infoO;
	protected String usernameO;
	protected String date_booking;
	protected boolean conf;
	private static final Logger logger = Logger.getLogger(tenant_control_panel.class.getName());
	public tenant_control_panel() {
		this.conf=false;
	}
	
public void personal_data(loginpage login) throws SQLException{
		
		con.func();
		String date="SELECT `date_booking` FROM `tanents` WHERE `id_user`=?;";
		String owner_detels="SELECT `username`,`contact_info`\r\n"
				+ "FROM `tanents`\r\n"
				+ "JOIN `houses` ON `tanents`.`id_house` = `houses`.`id`\r\n"
				+ "JOIN `users` ON `houses`.`id_user` = `users`.`id` WHERE `tanents`.`id_user`=?;";
		String select_id="SELECT `id`,`username` FROM `users` WHERE `type_user`='tenant' And `username`=?;";
		String select_config="SELECT `id_user`,`contact_info`\r\n"
				+ "FROM `tanents`\r\n"
				+ "JOIN `users` ON `tanents`.`id_user` = `users`.`id` WHERE `tanents`.`id_user`=?;";
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_id)) {
            preparedStatement.setString(1, login.username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    this.tenant_id = resultSet.getInt("id");
                    this.usernameT = resultSet.getString("username");
                }
            }
        }
		logger.log(Level.INFO,"name"+""+this.usernameT+"    ");
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_config)) {
            preparedStatement.setInt(1,this.tenant_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	this.conf=true;
                    this.contact_infoT = resultSet.getString("contact_info");
                }
            }
            logger.log(Level.INFO,"contact_info"+""+this.contact_infoT+"    ");
        } 
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(owner_detels)) {
            preparedStatement.setInt(1,this.tenant_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	this.conf=true;
                	this.usernameO = resultSet.getString("username");
                    this.contact_infoO = resultSet.getString("contact_info");
                }
            }
            logger.log(Level.INFO,"contact_info"+""+this.contact_infoO+"    ");
            logger.log(Level.INFO,"username"+""+this.usernameO+"    ");
        }
		try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(date)) {
            preparedStatement.setInt(1,this.tenant_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	this.conf=true;
              
                    this.date_booking = resultSet.getString("date_booking");
                }
            }
            logger.log(Level.INFO,"date_booking"+""+this.date_booking+"    ");
        }
	catch (SQLException e) {
		logger.info("An SQL exception occurred"+e);    }
		
		
		
}

public boolean doneee() {
	
	return this.conf;
}

}
