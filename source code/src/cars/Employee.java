package cars;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Employee {
	public static String landing() {
		do {
			Menu.print(EMPL_OPTS);
			switch (Menu.input()) {
				case 1: command = "profile"; break;
				case 2: command = "viewCustomerProfile"; break;
				case 3: command = "logout"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	public static String profile() {
		do {
			Menu.print(PF_OPTS);
			switch(Menu.input()) {
				case 1: command = "viewProfile"; break;
				case 2: command = "updateProfile"; break;
				case 3: command = "landing"; break;
				default: command = "default"; break;
			}
		} while(command.equals("default"));
		return command;
	}
	
	// OK
	public static String viewProfile() {
		try {
			// Mod
			String sql = "SELECT * FROM EMPLOYEE WHERE EMP_PHONE = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getUserID());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				sql = "SELECT CENTER_NAME FROM SERVICE_CENTER WHERE CENTER_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, Menu.getCenterID());
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next()) {
					System.out.println(String.join("\r\n",
											       "A. Employee ID: " + rs.getString("EMP_ID"), 
											       "B. Name: " + rs.getString("EMP_NAME"),
											       "C. Address: " + rs.getString("EMP_ADDR"),
											       "D. Email Address: " + rs.getString("EMP_EMAIL"),
											       "E. Phone Number: " + rs.getString("EMP_PHONE"),
											       "F. Service Center: " + rs2.getString("CENTER_NAME"),
											       "G. Role: " + rs.getString("EMP_ROLE"),
											       "H. Start Date: " + rs.getDate("EMP_STARTDATE"),
											       "I. Compensation ($): " + rs.getString("EMP_WAGE"),
											       "J. Compensation Frequency (monthly/hourly): " + rs.getString("EMP_WAGE_FREQ")
									              ));
				}
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		do {
			Menu.print(VIEW_PF_OPTS);
			switch(Menu.input()) {
				case 1: command = "profile"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	public static String updateProfile() {
		Boolean trigger = true;
		do {
			Menu.print(UPDATE_PF_OPTS);			
			switch (Menu.input()) {
				case 1: updateName(); break;
				case 2: updateAddress(); break;
				case 3: updateEmail(); break;
				case 4: updatePhoneNumber(); break;
				case 5: updatePassword(); break;
				case 6: command = "profile"; trigger = false; break;
				default: break;
			}
		} while (trigger);
		return command;
	}
	
	private static void updateName() {		
		try {			
			System.out.print("Please enter name: ");
			
			String sql = "Update EMPLOYEE set EMP_NAME = ? WHERE EMP_PHONE = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getline());
			pstmt.setString(2, Menu.getUserID());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void updateAddress() {
		try {			
			System.out.print("Please enter address: ");
			
			String sql = "Update EMPLOYEE set EMP_ADDR = ? WHERE EMP_PHONE = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getline());
			pstmt.setString(2, Menu.getUserID());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void updateEmail() {
		try {			
			System.out.print("Please enter email address: ");
			
			String sql = "Update EMPLOYEE set EMP_EMAIL = ? WHERE EMP_PHONE = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getline());
			pstmt.setString(2, Menu.getUserID());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void updatePhoneNumber() {
		try {
			System.out.print("Please enter phone number: ");
			
			String sql = "Update EMPLOYEE set EMP_EMAIL = ? WHERE EMP_PHONE = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getline());
			pstmt.setString(2, Menu.getUserID());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void updatePassword() {
		try {
			System.out.print("Please enter password: ");
			
			String sql = "Update LOGIN set USER_PW = ? WHERE USER_ID = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getline());
			pstmt.setString(2, Menu.getUserID());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// OK
	public static String viewCustomerProfile() {		
		try {
			System.out.print("Please enter customer email address: ");
			
			String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getline());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				sql = "SELECT * FROM VEHICLE WHERE CUSTOMER_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, rs.getString("CUSTOMER_ID"));
				ResultSet rs2 = pstmt.executeQuery();
				
				String carList = "";
				while (rs2.next()) {
					carList += String.join(" ",
										   "BRAND: " + rs2.getString("BRAND"),
										   "MODEL: " + rs2.getString("MODEL"),
										   "LICENSE_NUM: " + rs2.getString("LICENSE_NUM"),
										   "MADE_YEAR: " + rs2.getString("MADE_YEAR"),
										   "MILEAGE: " + (rs2.getString("MILEAGE") != null ? rs2.getString("MILEAGE") : "NULL"),
										   "PURCHASE_DATE: " + rs2.getDate("PURCHASE_DATE").toString(),
										   "LAST_SERVICE_DATE: " + (rs2.getDate("LAST_SERVICE_DATE") != null ? rs2.getDate("LAST_SERVICE_DATE").toString() : "NULL"),
										   "LAST_SERVICE_TYPE: " + (rs2.getString("LAST_SERVICE_TYPE") != null ? rs2.getString("LAST_SERVICE_TYPE") : "NULL"),
										   "\r\n"
									      );
				}
				
				System.out.println(String.join("\r\n", 
											   "A. Customer ID: " + rs.getString("CUSTOMER_ID"),
											   "B. Name: " + rs.getString("CUSTOMER_NAME"),
											   "C. Address: " + rs.getString("CUSTOMER_ADDR"),
											   "D. Email Address: " + rs.getString("CUSTOMER_EMAIL"),
											   "E. Phone Number: " + rs.getString("CUSTOMER_PHONENUM"),
											   "F. List of All Cars (and their details):\r\n" + carList
											  ));
			} else {
				System.out.println("This email address does not exist.");
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		do {
			Menu.print(VIEW_CUS_PF_OPTS);
			switch(Menu.input()) {
				case 1: command = "landing"; break;
				default: command = "default"; break;
			}
		} while(command.equals("default"));
		return command;
	}
		
	protected static final String[] PF_OPTS = {
										        "1. View Profile",
										        "2. Update Profile",
										        "3. Go Back"
										      };
	
	protected static final String[] VIEW_PF_OPTS = {
											         "1. Go Back"
											  	   };
	
	protected static final String[] UPDATE_PF_OPTS = {
													   "1. Name",
													   "2. Address",
													   "3. Email Address",
													   "4. Phone Number",
													   "5. Password",
													   "6. Go Back"
												     };
	
	protected static final String[] VIEW_CUS_PF_OPTS = {
													     "1. Go Back"
													   };
	
	private static final String[] EMPL_OPTS = {
											    "1. Profile",
											    "2. View Customer Profile",
											    "3. Logout"
											  };
	
	private static String command = "";
}