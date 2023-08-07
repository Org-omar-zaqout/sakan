package classes;

import java.sql.SQLException;
import java.util.Scanner;
//import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import conn.connect;


public class Main {
	
	static Scanner scan = new Scanner(System.in);
	static final Logger logger = Logger.getLogger(Main.class.getName());
	private static final String NUMBER_NOT_EXIST_MESSAGE = "The number is not exist ";
	 private static final String INVALID_OPTION_MESSAGE = "Invalid option";


	public static void main(String[] args) throws SQLException {
	    Scanner scan = new Scanner(System.in);
	    boolean shouldContinue = true;

	    while (shouldContinue) {
	        logger.info("Enter Username ");
	        String username = scan.next();
	        logger.info("Enter Password ");
	        String password = scan.next();

	        loginpage login = new loginpage(username, password);
	        boolean loggedIn = login.validateCredentials(username, password);

	        if (!loggedIn) {
	            logger.info("Invalid username or password");
	            continue;
	        }

	        if (login.isLoggedAdmin()) {
	            adminFlow(scan, login);
	        } else if (login.isLoggedOwner()) {
	            ownerFlow(scan, login);
	        } else if (login.isLoggedTenant()) {
	            tenantFlow(scan, login);
	        } else {
	            logger.info("Invalid user type");
	        }

	        logger.info("Do you want to continue? (y/n)");
	        String userInput = scan.next();
	        if (userInput.equalsIgnoreCase("n")) {
	            shouldContinue = false;
	        }
	    }
	}

	private static void adminFlow(Scanner scan, loginpage login) throws SQLException {
	    admin_advertisement adminAdv = new admin_advertisement();
	    admin_watching_res adminWatch = new admin_watching_res();
	    logger.info("login AS Admin ");

	    boolean flagAdmin = true;
	    while (flagAdmin) {
	        logger.info("1- Show add requests \n" + "2-  Watching reservations \n 3- Logout Admin \n choise option");
	        int switchValue = scan.nextInt();

	        switch (switchValue) {
	            case 1:
	                Admin_switch1(adminAdv, login);
	                break;
	            case 2:
	                Admin_switch2(adminWatch, login);
	                break;
	            case 3:
	                Admin_switch3(login);
	                flagAdmin = false;
	                break;
	            default:
	            	  logger.info(INVALID_OPTION_MESSAGE);
	                break;
	        }
	    }
	}

	private static void ownerFlow(Scanner scan, loginpage login) throws SQLException {
	    Owner_add_houses ownerAdd = new Owner_add_houses();
	    Dashboard dashboard = new Dashboard();
	    Control_Panel controlPanel = new Control_Panel();
	    logger.info("login AS Owner ");

	    boolean flagOwner = true;
	    while (flagOwner) {
	        logger.info("1- Adding house \n" + "2-  Dashboard \n 3-  Control Panel \n 4- Logout Owner \n choose option");
	        int switchValue = scan.nextInt();

	        switch (switchValue) {
	            case 1:
	                Owner_switch1( login);
	                break;
	            case 2:
	                Owner_switch2( login);
	                break;
	            case 3:
	                Owner_switch3(controlPanel, login);
	                break;
	            case 4:
	                Owner_switch4(login);
	                flagOwner = false;
	                break;
	            default:
	            	  logger.info(INVALID_OPTION_MESSAGE);
	                break;
	        }
	    }
	}

	private static void tenantFlow(Scanner scan, loginpage login) throws SQLException {
	    Tenant_avilable_house tenantAvail = new Tenant_avilable_house();
	   
	    tenant_control_panel tenantCP = new tenant_control_panel();
	    logger.info("login AS Tenant ");

	    boolean flagTenant = true;
	    while (flagTenant) {
	        logger.info("1- Show available housing \n" + "2-  book accommodation \n 3- Adding furniture \n 4-  The presence of a tenant control panel \n 5- Logout Tenant \n choose option");
	        int switchValue = scan.nextInt();

	        switch (switchValue) {
	            case 1:
	                Tenant_switch1(tenantAvail, login);
	                break;
	            case 2:
	                Tenant_switch2( login);
	                break;
	            case 3:
	                Tenant_switch3( login);
	                break;
	            case 4:
	                Tenant_switch4(tenantCP, login);
	                break;
	            case 5:
	                Tenant_switch5(login);
	                flagTenant = false;
	                break;
	            default:
	            	  logger.info(INVALID_OPTION_MESSAGE);
	                break;
	        }
	    }
	}

	
	
	public static void Admin_switch1(admin_advertisement admin_adv,loginpage login) throws SQLException {
			
		admin_adv.Select_houses(login);
			logger.info("Enter the id house ");
			int id_house=scan.nextInt();
			admin_adv.req_houses(id_house);
			
		}
	public static void Admin_switch2(admin_watching_res admin_Watch,loginpage login) throws SQLException {
		
		admin_Watch.watching(login);
		
	}
	public static void Admin_switch3(loginpage login) throws SQLException {
		
		login.logout_admin();
		logger.info("Admin IS Logged out ");
		
	}

	
	public static void Owner_switch1(loginpage login) throws SQLException {
		logger.info("Enter name_House ");
		String name_House=scan.next();
		logger.info("Enter picture_House ");
		String picture_House=scan.next();
		logger.info("Enter location_House ");
		String location_House=scan.next();
		logger.info("Enter available_services_House ");
		String available_services_House=scan.next();
		logger.info("Enter price_House ");
		int price_Housescan=scan.nextInt();
		logger.info("Enter information_contact ");
		String information_contact=scan.next();
		
		
		Owner_add_houses new_houses=new Owner_add_houses(name_House,picture_House,location_House,available_services_House,price_Housescan,information_contact);
		
		new_houses.add(login);
			
		}
	public static void Owner_switch2(loginpage login) throws SQLException {
		
		
		logger.info("Enter id_House ");
		int id_House=scan.nextInt();
		logger.info("Enter num_floor ");
		int num_floor=scan.nextInt();
		logger.info("Enter is_student ");
		int is_student=scan.nextInt();
		logger.info("Enter num_apartment ");
		int num_apartment=scan.nextInt();
		int[] bath = new int[num_apartment];
		int[] bed = new int[num_apartment];
		boolean[] bal = new boolean[num_apartment];
		
		for(int i=0,j=1;i<num_apartment;i++,j++) {
			 String logMessage = String.format("Enter num of bathroom for the apartment (%d) ",j);
             logger.log(Level.INFO, logMessage);		
			 bath[i]=scan.nextInt();
			  String logMsg = String.format("Enter num of bedroom for the apartment  (%d) ",j);
	         logger.log(Level.INFO, logMsg);
			 bed[i]=scan.nextInt();
			 String logMsgg = String.format("Enter true or false of balcony for the apartment  (%d) ",j);
             logger.log(Level.INFO, logMsgg);
			 bal[i]=scan.nextBoolean();
		}
		Dashboard	dash=new Dashboard(id_House,num_floor, num_apartment,is_student, bath, bed, bal);
		dash.add(login);
	}
	public static void Owner_switch3(Control_Panel CP,loginpage login) throws SQLException {
		
		 CP.Select_houses(login);
		 logger.info("Enter id_House ");
			int id_House=scan.nextInt();
		 CP.count_tanents(id_House);
		 CP.num_floor(id_House);
			logger.info("Enter num_floor ");
			int num_floor=scan.nextInt();
		 CP.num_apartment(num_floor);
		 logger.info("Enter num_apartment ");
			int num_apartment=scan.nextInt();
		 CP.num_details(num_apartment);
		
	}
	
public static void Owner_switch4(loginpage login) throws SQLException {
		
		login.logout_owner();
		logger.info("Owner IS Logged out ");
		
	}
//Tenant
public static void Tenant_switch1(Tenant_avilable_house tenant_aval,loginpage login) throws SQLException {
	
	tenant_aval.Select_houses(login);
		
	}
public static void Tenant_switch2(loginpage login) throws SQLException {
	logger.info("Enter id_house ");
	int id_house=scan.nextInt();
	logger.info("Enter id_floor ");
	int id_floor=scan.nextInt();
	logger.info("Enter id_apart ");
	int id_apart=scan.nextInt();
	
	
	book_accommodation Bookaccommodation=new book_accommodation(id_house,id_floor,id_apart);
	Bookaccommodation.insert_book(login);
	
}
public static void Tenant_switch3(loginpage login) throws SQLException {
	
	logger.info("Enter name ");
	String name=scan.next();	
	logger.info("Enter price ");
	int price=scan.nextInt();
	
	tenant_add_furniture tenant_furniture=new tenant_add_furniture(name,price);
	tenant_furniture.add_furniture(login);
	
}
public static void Tenant_switch4(tenant_control_panel tenant_CP,loginpage login) throws SQLException {
	
	tenant_CP.personal_data(login);
	
}
public static void Tenant_switch5(loginpage login) throws SQLException {
	
	login.logout_tenant();
	logger.info("Tenant IS Logged out ");
}

}
