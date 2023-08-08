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
	 private static final String SQL_EXCEPTION_MESSAGE = "An SQL exception occurred";         
	

	
	
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
			logger.info(SQL_EXCEPTION_MESSAGE + e);
			}
			
		return flag_found;
		
	}
	String select_house="SELECT `name`, `image`, `location`, `available_services`, `price` FROM `houses` WHERE `id`=?;";
	public void Select_houses(loginpage login) throws SQLException {
	    con.func();

	    int tenantId = getTenantId(login);

	    if (tenantId == -1) {
	        logger.info("Error retrieving tenant ID");
	        return;
	    }

	    if (isStudent(tenant_id)) {
	        selectAndLogApartments(con, login, true);
	    } else {
	        selectAndLogApartments(con, login, false);
	    }
	}

	private int getTenantId(loginpage login) throws SQLException {
	    String select_id = "SELECT `id` FROM `users` WHERE `type_user`='tenant' And `username`=?;";

	    try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_id)) {
	        preparedStatement.setString(1, login.username);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                return resultSet.getInt("id");
	            }
	        }
	    } catch (SQLException e) {
	        logger.info(SQL_EXCEPTION_MESSAGE + e);
	    }

	    return -1;
	}

	private boolean isStudent(int tenant_id) throws SQLException {
	    String is_S = "SELECT `id_user` FROM `student` WHERE `id_user`=?;";

	    try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(is_S)) {
	        preparedStatement.setInt(1, tenant_id);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            return resultSet.next();
	        }
	    } catch (SQLException e) {
	        logger.info(SQL_EXCEPTION_MESSAGE + e);
	    }

	    return false;
	}

	private void selectAndLogApartments(connect con, loginpage login, boolean isForStudent) throws SQLException {
	    String select_apart_query = isForStudent
	            ? "SELECT `id_apartment`, `id_floor`, `id_house` FROM `apartments` WHERE`for_student`='1';"
	            : "SELECT `id_apartment`, `id_floor`, `id_house` FROM `apartments` WHERE`for_student`='0';";

	    try (Statement stmt = con.getConnection().createStatement()) {
	        if (login.isLoggedTenant()) {
	            ResultSet rs = stmt.executeQuery(select_apart_query);

	            while (rs.next()) {
	                int id_apartment = rs.getInt("id_apartment");
	                int id_floor = rs.getInt("id_floor");
	                int id_house = rs.getInt("id_house");

	                try (PreparedStatement preparedStatement = con.getConnection().prepareStatement(select_house)) {
	                    preparedStatement.setInt(1, id_house);
	                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                        if (resultSet.next()) {
	                            String name = resultSet.getString("name");
	                            String image = resultSet.getString("image");
	                            String location = resultSet.getString("location");
	                            String available_services = resultSet.getString("available_services");
	                            int price = resultSet.getInt("price");
	                            String logMsg = String.format("%d , %s , %s ,%d , %d , %s , %s , %d ",id_house,name,image,id_floor,id_apartment,location,available_services,price);
	           	             logger.log(Level.INFO, logMsg);
	                           
	                        }
	                    }
	                } catch (SQLException e) {
	                    logger.info(SQL_EXCEPTION_MESSAGE + e);
	                }
	            }
	        } else {
	            logger.log(Level.INFO, "The Tenant is not logged in");
	        }
	    }
	}

	public boolean isFlag_found() {
		return flag_found;
	}

	public void setFlag_found(boolean flag_found) {
		this.flag_found = flag_found;
	}
	

	
	

	
	
	
}
