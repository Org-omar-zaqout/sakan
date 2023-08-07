package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import conn.connect;
public class Dashboard{

	protected int id_House;	
	protected int num_floor;
	protected int num_apartment;
	protected int is_student;
	protected int num_bathroom[];
	protected int num_bedroom[];
	protected boolean balcony[];
	protected int available;
	protected String name_apart[];
	protected boolean adding;
	int x;
	private static final Logger logger = Logger.getLogger(Owner_add_houses.class.getName());
	private static final String SQL_EXCEPTION_MESSAGE = "An SQL exception occurred";
	

	
	

	public Dashboard() {
		this.adding=false;
		
	}



	public Dashboard(int id_House, int num_floor, int num_apartment,int is_student,
			 int bath[],int bed[],boolean balcony[]) {
		this.is_student=is_student;
		this.id_House = id_House;
		this.num_floor = num_floor;
		this.num_apartment = num_apartment;
		
		this.num_bathroom=bath;
		this.num_bedroom=bed;
		this.balcony=balcony;
		
		
		
		
	}
	
	
	
	/*public boolean add(loginpage loginO) throws SQLException {
		connect con =new connect();
		con.func();
		int num_floor=this.num_floor;
		
		int num_apart=this.num_apartment;
		adding = false;
		
		if(loginO.isLoggedOwner()) {
			
			String st="SELECT `id`FROM `houses` WHERE `isAccept`=1 And `id`=?;";
			try(PreparedStatement statement1 = con.getConnection().prepareStatement(st)) {
				statement1.setInt(1,this.id_House);
                ResultSet resultSet1 = statement1.executeQuery();
               if (resultSet1.next()) {
            	   
		for(int i=1;i<=num_floor;i++) {
			
			String insert_floor="INSERT INTO `floor`(`num_floor`,`id_house`,`num_apart`) VALUES (?,?,?);";

			logger.log(Level.INFO,"the owner loggin");
			try(PreparedStatement statement = con.getConnection().prepareStatement(insert_floor)) {
				statement.setInt(3, this.num_apartment);
				statement.setInt(2, this.id_House);
				statement.setInt(1,i);

				statement.executeUpdate();
				
			} catch (SQLException e) {
				
				logger.info("An SQL exception occurred"+e);			}
			for(int j=0;j<num_apart;j++) {
				
				String insert_apartment="INSERT INTO `apartments`( `id_house`,`id_floor`,`num_bathroom`,`num_bedroom`,`balcony`,`for_student`) VALUES (?,?,?,?,?,?);";
				String max_floor="SELECT MAX(id_floor) FROM `floor`;";
				logger.log(Level.INFO,"the owner loggin");
				try(PreparedStatement statement = con.getConnection().prepareStatement(insert_apartment)) {
	                 ResultSet resultSet = statement.executeQuery(max_floor);
	                if (resultSet.next()) {
	                    x = resultSet.getInt(1);
	   
	                }
					statement.setInt(1, this.id_House);
					statement.setInt(2, x);
					statement.setInt(3, this.num_bathroom[j]);
					statement.setInt(4, this.num_bedroom[j]);
					statement.setBoolean(5, this.balcony[j]);
					statement.setInt(6, this.is_student);

					statement.executeUpdate();
					
					this.adding=true;
					
				} catch (SQLException e) {
					logger.info("An SQL exception occurred"+e);
				}
				
			}
		}
               }
		}
		}
		
		else
		{
			logger.log(Level.INFO,"the owner not loggin");
		}
			return this.adding;
			
		}
	*/
	public boolean add(loginpage loginO) throws SQLException {
	    connect con = new connect();
	    con.func();
	    int num_floor = this.num_floor;
	    int num_apart = this.num_apartment;
	    adding = false;

	    if (loginO.isLoggedOwner()) {
	        String st = "SELECT `id` FROM `houses` WHERE `isAccept` = 1 AND `id` = ?;";
	        try (PreparedStatement statement1 = con.getConnection().prepareStatement(st)) {
	            statement1.setInt(1, this.id_House);
	            ResultSet resultSet1 = statement1.executeQuery();

	            if (resultSet1.next()) {
	                addFloors(con, num_floor, num_apart);
	            }
	        }
	    } else {
	        logger.log(Level.INFO, "the owner not logging");
	    }

	    return this.adding;
	}

	private void addFloors(connect con, int num_floor, int num_apart) {
	    try {
	        for (int i = 1; i <= num_floor; i++) {
	            String insert_floor = "INSERT INTO `floor`(`num_floor`, `id_house`, `num_apart`) VALUES (?, ?, ?);";
	            logger.log(Level.INFO, "the owner logging");
	            try (PreparedStatement statement = con.getConnection().prepareStatement(insert_floor)) {
	                statement.setInt(3, this.num_apartment);
	                statement.setInt(2, this.id_House);
	                statement.setInt(1, i);
	                statement.executeUpdate();
	            } catch (SQLException e) {
	            	logger.info(SQL_EXCEPTION_MESSAGE + e);
	            }

	            addApartments(con, num_apart);
	        }
	    } catch (SQLException e) {
	    	logger.info(SQL_EXCEPTION_MESSAGE + e);
	    }
	}

	private void addApartments(connect con, int num_apart) throws SQLException {
	    for (int j = 0; j < num_apart; j++) {
		    String insert_apartment = "INSERT INTO `apartments`(`id_house`, `id_floor`, `num_bathroom`, `num_bedroom`, `balcony`, `for_student`) VALUES (?, ?, ?, ?, ?, ?);";
		    String max_floor = "SELECT MAX(id_floor) FROM `floor`;";
		    logger.log(Level.INFO, "the owner logging");
		    try (PreparedStatement statement = con.getConnection().prepareStatement(insert_apartment)) {
		        ResultSet resultSet = statement.executeQuery(max_floor);
		        if (resultSet.next()) {
		            x = resultSet.getInt(1);
		        }
		        statement.setInt(1, this.id_House);
		        statement.setInt(2, x);
		        statement.setInt(3, this.num_bathroom[j]);
		        statement.setInt(4, this.num_bedroom[j]);
		        statement.setBoolean(5, this.balcony[j]);
		        statement.setInt(6, this.is_student);
		        statement.executeUpdate();
		        this.adding = true;
		    } catch (SQLException e) {
		    	logger.info(SQL_EXCEPTION_MESSAGE + e);
		    }
		}
	}
	public boolean added() {
		
		return this.adding;
	}
	
}

