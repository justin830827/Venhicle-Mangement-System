package cars;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Entry {
	public static String home() {
		do {
			Menu.print(HOME_OPTS);
			switch (Menu.input()) {
				case 1: command = "login"; break;
				case 2: command = "signUp"; break;
				case 3: command = "exit"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	public static String login() {
		String[] details = {
							 "A. User ID: ",
							 "B. Password: "
						   };
		
		ArrayList<String> inputs = new ArrayList<String>();
		for (String detail: details) {
			System.out.print(detail);
			inputs.add(Menu.getline());
		}
		
		do {
			Menu.print(LOGIN_OPTS);
			switch (Menu.input()) {
				case 1: command = signIn(inputs); break;
				case 2: command = "home"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	private static String signIn(ArrayList<String> inputs) {	
		Menu.setRole("Undefined");
		Menu.setUserID("");
		Menu.setCenterID("");
		
		try {
			String sql = "SELECT USER_ID, LOGIN_ROLE FROM LOGIN WHERE USER_ID = ? AND USER_PW = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, inputs.get(0));
			pstmt.setString(2, inputs.get(1));
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {				
				Menu.setRole(rs.getString("LOGIN_ROLE"));
				Menu.setUserID(rs.getString("USER_ID"));
				
				if (Menu.getRole().equals("Customer")) {
					sql = "SELECT CENTER_ID FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?";
					pstmt = Database.getConnection().prepareStatement(sql);
					pstmt.setString(1, Menu.getUserID());
					rs = pstmt.executeQuery();

					if (rs.next()) {
						Menu.setCenterID(rs.getString("CENTER_ID"));
					}					
				}
				
				if (Menu.getRole().equals("Manager")
					|| Menu.getRole().equals("Receptionist") 
					|| Menu.getRole().equals("Mechanics")) {
					
					sql = "SELECT CENTER_ID FROM EMPLOYEE WHERE EMP_PHONE = ?";
					pstmt = Database.getConnection().prepareStatement(sql);
					pstmt.setString(1, Menu.getUserID());
					rs = pstmt.executeQuery();
					
					if (rs.next()) {
						Menu.setCenterID(rs.getString("CENTER_ID"));
					}
				}				
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (Menu.getRole().equals("Undefined")) {
			System.out.println("Login Incorrect");
			command = "login";
		} else {
			command = "landing";
		}
		
		return command;
	}
	
	public static String signUp() {
		String[] details = {
						     "A. Email Address: ",
						     "B. Password: ",
						     "C. Name: ",
						     "D. Address: ",
						     "E. Phone Number: "
						   };
		
		ArrayList<String> inputs = new ArrayList<String>();
		for (String detail: details) {
			System.out.print(detail);
			inputs.add(Menu.getline());
		}
		System.out.println();
		
		do {
			Menu.print(SIGNUP_OPTS);
			switch (Menu.input()) {
				case 1: command = signUpImpl(inputs); break;
				case 2: command = "home"; break;
				default: command = "default"; break;
			}
		} while(command.equals("default"));
		return command;
	}
	
	private static String signUpImpl(ArrayList<String> inputs) {
		try {
			String sql = "SELECT MAX(CUSTOMER_ID) AS MAX_ID FROM CUSTOMER";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {				
				sql = "INSERT INTO CUSTOMER (CUSTOMER_ID, CUSTOMER_EMAIL, CUSTOMER_NAME, CUSTOMER_ADDR, CUSTOMER_PHONENUM, CENTER_ID) VALUES (?,?,?,?,?,?)";
				pstmt = Database.getConnection().prepareStatement(sql);
				int cusID = rs.getInt("MAX_ID") + 1; 
				
				pstmt.setInt(1, cusID);
				pstmt.setString(2, inputs.get(0));
				pstmt.setString(3, inputs.get(2));
				pstmt.setString(4, inputs.get(3));
				pstmt.setString(5, inputs.get(4));
				
				if (inputs.get(3).contains("Raleigh")) {
					pstmt.setString(6, "S0001");
				} else if (inputs.get(3).contains("Charlotte")) {
					pstmt.setString(6, "S0002");
				}
				
				pstmt.executeUpdate();

				sql = "INSERT INTO LOGIN (USER_ID, LOGIN_ROLE, USER_PW) VALUES (?,?,?)";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, inputs.get(0));
				pstmt.setString(2, "Customer");
				pstmt.setString(3, inputs.get(1));
				pstmt.executeUpdate();
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "login";
	}
	
	private static final String[] HOME_OPTS = {
											    "1. Login",
											    "2. Sign Up",
											    "3. Exit"
										      };
	
	private static final String[] LOGIN_OPTS = {
										  		 "1. Sign in",
										  		 "2. Go Back"
											   };
	
	private static final String[] SIGNUP_OPTS = {
									       		  "1. Sign Up",
									       		  "2. Go Back"
									     	    };
	
	private static String command = "";
}