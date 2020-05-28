package cars;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Manager extends Employee {
	public static String landing() {
		do {
			Menu.print(MGR_OPTS);
			switch (Menu.input()) {
				case 1: command = "profile"; break;
				case 2: command = "viewCustomerProfile"; break;
				case 3: command = "addNewEmployees"; break;
				case 4: command = "payroll"; break;
				case 5: command = "inventory"; break;
				case 6: command = "orders"; break;
				case 7: command = "notifications"; break;
				case 8: command = "newCarModel"; break;
				case 9: command = "carServiceDetails"; break;
				case 10: command = "serviceHistory"; break;
				case 11: command = "invoices"; break;
				case 12: command = "logout"; break;
				default: command = "defalut"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	// OK
	public static String addNewEmployees() {
		String[] details = {
							 "A. Name: ",
							 "B. Address: ",
							 "C. Email Address: ",
							 "D. Phone Number: ",
							 "E. Role: ",
							 "F. Start Date: ",
							 "G. Compensation ($): "
						   };
		
		ArrayList<String> inputs = new ArrayList<String>();
		for (String detail : details) {
			System.out.print(detail);
			inputs.add(Menu.getline());
		}
		
		do {
			Menu.print(ADD_NEW_EMPLS_OPTS);
			switch (Menu.input()) {
				case 1: command = add(inputs); break;
				case 2: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	// OK
	private static String add(ArrayList<String> inputs) {
		try {
			String sql = "SELECT MAX(EMP_ID) AS MAX_ID FROM EMPLOYEE";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
				int cusID = Integer.parseInt(rs.getString("MAX_ID")) + 1;
				
				sql = "INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_ROLE, EMP_ADDR, EMP_EMAIL, EMP_PHONE, EMP_STARTDATE, EMP_WAGE, EMP_WAGE_FREQ, CENTER_ID) VALUES (?,?,?,?,?,?,?,?,?,?)";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, Integer.toString(cusID));
				pstmt.setString(2, inputs.get(0));
				pstmt.setString(3, inputs.get(4));
				pstmt.setString(4, inputs.get(1));
				pstmt.setString(5, inputs.get(2));
				pstmt.setString(6, inputs.get(3));
				pstmt.setDate(7, java.sql.Date.valueOf(LocalDate.parse(inputs.get(5), formatter)));
				pstmt.setInt(8, Integer.parseInt(inputs.get(6)));
								
				if (inputs.get(4).equals("Manager") || inputs.get(4).equals("Receptionist")) {
					pstmt.setString(9, "Monthly");
				} else if (inputs.get(4).equals("Mechanics")) {
					pstmt.setString(9, "Hourly");
				}
				
				pstmt.setString(10, Menu.getCenterID());
				pstmt.executeUpdate();
				
				sql = "INSERT INTO LOGIN (USER_ID, LOGIN_ROLE, USER_PW) VALUES (?,?,?)";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, inputs.get(3));
				pstmt.setString(2, inputs.get(4));
				pstmt.setString(3, "12345678");
				pstmt.executeUpdate();
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "landing";
	}
	
	
	// OK
	public static String payroll() {
		System.out.print("Enter employee id: ");
		Payroll payroll = getPayroll(Menu.getline());
				
		for (Paycheck paycheck: payroll.getPaycheck()) {
			System.out.println(String.join("\r\n",
										   "A. Paycheck date: " + paycheck.getPayDate(),
										   "B. Pay period: " + paycheck.getPeriod(),
										   "C. Employee ID: " + payroll.getId(),
										   "D. Employee Name: " + payroll.getName(),
										   "E. Compensation ($): " + payroll.getCompensation(),
										   "F. Compensation Frequency (monthly/hourly): " + payroll.getFrequency(),
										   "G. Units (# of hours/days): " + paycheck.getUnits(),
										   "H. Earnings (Current): " + paycheck.getcEarning(),
										   "I. Earnings (Year-to-date): " + paycheck.getaEarning()
										  ));
			System.out.println();
		}
		
		do {
			Menu.print(PAYRll_OPTS);
			switch (Menu.input()) {
				case 1: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	// OK
	private static Payroll getPayroll(String id) {
		Payroll payroll = new Payroll();
		Paycheck temCheck = null;
		ArrayList<Paycheck> temAll = new ArrayList<Paycheck>();
		
		String sql = "select * from EMPLOYEE where EMP_ID = ?";
		PreparedStatement pstmt = null;		
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				payroll.setId(id);
				payroll.setName(rs.getString("EMP_NAME"));
				payroll.setCompensation(rs.getInt("EMP_WAGE"));
				payroll.setFrequency(rs.getString("EMP_WAGE_FREQ"));
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String sql1 = "select * from PAYROLL where EMP_ID = ?";
		PreparedStatement pstmt1 = null;
		try {
			pstmt1 = Database.getConnection().prepareStatement(sql1);
			pstmt1.setString(1, id);
			ResultSet rs1 = pstmt1.executeQuery();
			
			while(rs1.next()) {
				temCheck = new Paycheck();
				temCheck.setPayDate(rs1.getDate("PAYCHECK_DATE"));
				temCheck.setUnits(rs1.getInt("UNITS"));
				temCheck.setPeriod(rs1.getString("PAYTYPE"));
				temCheck.setcEarning(rs1.getInt("CUR_EARN"));
				temCheck.setaEarning(rs1.getInt("SOFAR_EARN"));
				
				temAll.add(temCheck);
			}
			pstmt1.close();
			rs1.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
		payroll.setPaycheck(temAll);
		return payroll;
	}
	
	// OK
	public static String inventory() {
		try {
			String sql = "SELECT PART_ID, MIN_STOCK, MIN_ORDER, CUR_STOCK FROM CENTER_HAS_INVENTORY WHERE CENTER_ID = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getCenterID());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sql = "SELECT PART_NAME, CAR_TYPE_INVEN, PRICE FROM INVENTORY WHERE PART_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, rs.getString("PART_ID"));
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next()) {
					System.out.println(String.join("\r\n",
												   "A. Part ID: " + rs.getString("PART_ID"),
												   "B. Part Name: " + rs2.getString("CAR_TYPE_INVEN") + " " + rs2.getString("PART_NAME"),
												   "C. Quantity: " + rs.getString("CUR_STOCK"),
												   "D. Unit Price: " + rs2.getString("PRICE"),
												   "E. Minimum Quantity Threshold: " + rs.getString("MIN_STOCK"),
												   "F. Minimum Order Threshold: " + rs.getString("MIN_ORDER")
												  ));
					System.out.println();
				}
			}
			
			pstmt.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		do {
			Menu.print(INVT_OPTS);
			switch (Menu.input()) {
				case 1: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	public static String orders() {
		do {
			Menu.print(ORDS_OPTS);
			switch (Menu.input()) {
				case 1: command = orderHistory(); break;
				case 2: command = newOrder(); break;
				case 3: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("orders") || command.equals("default"));
		return command;
	}
	
	// OK
	private static String orderHistory() {
		try {
			String sql = "SELECT * FROM PORDER WHERE PORDER_DESTINATION = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getCenterID());
			ResultSet rs = pstmt.executeQuery();
						
			while (rs.next()) {
				sql = "SELECT PART_NAME, CAR_TYPE_INVEN, PRICE FROM INVENTORY WHERE PART_ID = ?"; 
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, rs.getString("PART_ID"));
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next()) {
					System.out.println(String.join("\r\n", 
												   "A. Order ID: " + rs.getString("PORDER_ID"),
												   "B. Date: " + rs.getDate("PORDER_GEN_DATE"),
												   "C. Part Name: " + rs2.getString("CAR_TYPE_INVEN") + " " + rs2.getString("PART_NAME"),
												   "D. Supplier Name: " + rs.getString("PORDER_ORIGIN"),
												   "E. Purchaser Name: " + rs.getString("PORDER_DESTINATION"),
												   "F. Quantity: " + rs.getString("PORDER_QUAN"),
												   "G. Unit Price: " + rs2.getString("PRICE"),
												   "H. Total Cost: " + rs.getInt("PORDER_QUAN") * rs2.getDouble("PRICE"),
												   "I. Order Status: " + rs.getString("PORDER_STATUS")
												  ));
					System.out.println();
				}
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		do {
			Menu.print(ORD_HIST_OPTS);
			switch (Menu.input()) {
				case 1: command = "orders"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	private static String newOrder() {
		String[] details = {
							 "Part ID: ",
							 "Quantity: "
						   };
		
		ArrayList<String> inputs = new ArrayList<String>();
		for (String detail : details) {
			System.out.print(detail);
			inputs.add(Menu.getline());
		}
		System.out.println();
		
		do {
			Menu.print(NEW_ORD_OPTS);
			switch (Menu.input()) {
				case 1: command = placeOrder(inputs); break;
				case 2: command = "orders" ;break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	private static String placeOrder(ArrayList<String> inputs) {
		try {
			String sql = "SELECT MAX(PORDER_ID) AS MAX_ID FROM PORDER";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String num = Integer.toString(Integer.parseInt(rs.getString("MAX_ID").substring(1)) + 1);
				String orderID =  "O" + String.join("", Collections.nCopies(rs.getString("MAX_ID").substring(1).length() - num.length(), "0")) + num;
				
				sql = "SELECT WINDOWS FROM INVENTORY WHERE PART_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, inputs.get(0));
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next()) {
					Date orderGenDate = new java.sql.Date(new java.util.Date().getTime());
					Date orderExpDate = new java.sql.Date(orderGenDate.getTime() + rs2.getInt("WINDOWS")*24*60*60*1000);					
					Date orderActDate = null;

					sql = "SELECT DISTRIBUTOR_ID FROM INVENTORY WHERE PART_ID = ?";
					pstmt = Database.getConnection().prepareStatement(sql);
					pstmt.setString(1, inputs.get(0));
					ResultSet rs3 = pstmt.executeQuery();
					
					if (rs3.next()) {
						sql = "INSERT INTO PORDER (PORDER_ID, PORDER_GEN_DATE, PORDER_EXP_DATE, PORDER_ACT_DATE, "
							  + "PORDER_QUAN, PORDER_ORIGIN, PORDER_DESTINATION, PORDER_STATUS, PART_ID) VALUES (?,?,?,?,?,?,?,?,?)";
						pstmt = Database.getConnection().prepareStatement(sql);
						pstmt.setString(1, orderID);
						pstmt.setDate(2, orderGenDate);
						pstmt.setDate(3, orderExpDate);
						pstmt.setDate(4, orderActDate);
					    pstmt.setInt(5, Integer.parseInt(inputs.get(1)));
					    pstmt.setString(6, rs3.getString("DISTRIBUTOR_ID"));
					    pstmt.setString(7, Menu.getCenterID());
					    pstmt.setString(8, "pending");
					    pstmt.setString(9, inputs.get(0));
					    pstmt.executeUpdate();
					}
				}
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "orders";
	}
	
	// OK
	public static String notifications() {
		ArrayList<String> orderIDs = new ArrayList<String>();
		
		try {
			String sql = "SELECT NOTIF_ID, NOTIF_DATE, PORDER_ID FROM NOTIF WHERE NOTIF_CENTER = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getCenterID());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sql = "SELECT PORDER_GEN_DATE, PORDER_EXP_DATE, PORDER_ACT_DATE, PORDER_ORIGIN FROM PORDER WHERE PORDER_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, rs.getString("PORDER_ID"));
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next()) {
					orderIDs.add(rs.getString("PORDER_ID"));
					
					System.out.println(String.join("\r\n", 
												   "A. Notification ID: " + rs.getString("NOTIF_ID"),
												   "B. Notification Date/Time: " + rs.getDate("NOTIF_DATE"),
												   "C. Order ID: " + rs.getString("PORDER_ID"),
												   "D. Supplier Name: " + rs2.getString("PORDER_ORIGIN"),
												   "E. Expected Delivery Date: " + rs2.getDate("PORDER_EXP_DATE"),
												   "F. Delayed by (# of days): " + getBusinessDays(rs2.getDate("PORDER_EXP_DATE").toLocalDate(), 
														   										   rs2.getDate("PORDER_ACT_DATE").toLocalDate())
												  ));
					System.out.println();
				}
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		do {
			Menu.print(NOTIFS_OPTS);
			switch (Menu.input()) {
				case 1: command = notificationsDetail(orderIDs); break;
				case 2: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	// OK
	private static int getBusinessDays(LocalDate startIncl, LocalDate endExcl) {
		int businessDays = 0;
		LocalDate date = startIncl;
		
		while (date.isBefore(endExcl)) {	
			DayOfWeek day = date.getDayOfWeek();
			if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
				businessDays++;
			}
			
			date = date.plusDays(1);
		}
		
		return businessDays;
	}
	
	// OK
	private static String notificationsDetail(ArrayList<String> orderIDs) {
		System.out.print("Enter Order ID: ");
		String inputID = Menu.getline();
		
		if (orderIDs.contains(inputID)) {
			try {
				String sql = "SELECT * FROM PORDER WHERE PORDER_ID = ?";
				PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, inputID);
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()) {
					sql = "SELECT PART_NAME, CAR_TYPE_INVEN, PRICE FROM INVENTORY WHERE PART_ID = ?";
					pstmt = Database.getConnection().prepareStatement(sql);
					pstmt.setString(1, rs.getString("PART_ID"));
					ResultSet rs2 = pstmt.executeQuery();
					
					if (rs2.next()) {
						System.out.println(String.join("\r\n",
													   "A. Order ID: " + rs.getString("PORDER_ID"), 
													   "B. Date: " + rs.getDate("PORDER_GEN_DATE"), 
													   "C. Part Name: " + rs2.getString("CAR_TYPE_INVEN") + " " + rs2.getString("PART_NAME"), 
													   "D. Supplier Name: " + rs.getString("PORDER_ORIGIN"), 
													   "E. Purchaser Name: " + rs.getString("PORDER_DESTINATION"), 
													   "F. Quantity: " + rs.getString("PORDER_QUAN"), 
													   "G. Unit Price: " + rs2.getString("PRICE"), 
													   "H. Total Cost: " + rs.getInt("PORDER_QUAN") * rs2.getDouble("PRICE"), 
													   "I. Order Status: " + rs.getString("PORDER_STATUS")
													  ));
					}
				}
				
				pstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No notifications detail for this order id: " + inputID);
		}
		
		System.out.println();
		do {
			Menu.print(NOTIFS_DTL_OPTS);
			switch (Menu.input()) {
				case 1: command = "notifications"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	public static String newCarModel() {
		String[] details = {
							 "A. Make: ",
							 "B. Model: ",
							 "C. Year: ",
							 "D. Service A - a. Miles: ",
							 "D. Service A - b. Months: ",
							 "D. Service A - c. Parts list: ",
							 "E. Service B - a. Miles: ",
							 "E. Service B - b. Months: ",
							 "E. Service B - c. Additional Parts: ",
							 "F. Service C - a. Miles: ",
							 "F. Service C - b. Months: ",
							 "F. Service C - c. Additional Parts: "
						   };
		
		for (String detail : details) {
			System.out.print(detail);
			Menu.getline();
		}
		System.out.println();
		
		do {
			Menu.print(NEW_CAR_MDL_OPTS);
			switch (Menu.input()) {
				case 1: command = addCar(); break;
				case 2: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	private static String addCar() {
		System.out.println("Add a new car model");
		return "landing";
	}
	
	// OK
	public static String carServiceDetails() {
		try {
			String sql = "SELECT * FROM CARTYPE";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sql =  "SELECT BASIC_DETAIL_NAME FROM BASIC_SERVICE WHERE BASIC_SERVICE_ID in "
					   + "(SELECT BASIC_SERVICE_ID FROM MAINT_HAS_BASIC_SERVICE WHERE SERVICE_TYPE = 'Service A' AND CAR_MODEL = ?)";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, rs.getString("MODEL"));
				ResultSet rsA = pstmt.executeQuery();
				
				sql =  "SELECT BASIC_DETAIL_NAME FROM BASIC_SERVICE WHERE BASIC_SERVICE_ID in "
					   + "(SELECT BASIC_SERVICE_ID FROM MAINT_HAS_BASIC_SERVICE WHERE SERVICE_TYPE = 'Service B' AND CAR_MODEL = ?)";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, rs.getString("MODEL"));
				ResultSet rsB = pstmt.executeQuery();
					
				sql =  "SELECT BASIC_DETAIL_NAME FROM BASIC_SERVICE WHERE BASIC_SERVICE_ID in "
					   + "(SELECT BASIC_SERVICE_ID FROM MAINT_HAS_BASIC_SERVICE WHERE SERVICE_TYPE = 'Service C' AND CAR_MODEL = ?)";
				pstmt = Database.getConnection().prepareStatement(sql);
			    pstmt.setString(1, rs.getString("MODEL"));
			    ResultSet rsC = pstmt.executeQuery();
				
			    StringBuilder sb = new StringBuilder();
			    String prefix = "";
			    while (rsA.next()) {
			    	sb.append(prefix);
			    	sb.append(rsA.getString("BASIC_DETAIL_NAME"));
			    	prefix = ", ";
			    }
			    
			    String listA = sb.toString();

			    sb.setLength(0);
			    prefix = "";
			    while (rsB.next()) {
			    	sb.append(prefix);
			    	sb.append(rsB.getString("BASIC_DETAIL_NAME"));
			    	prefix = ", ";
			    }
			    String listB = sb.toString();
			    
			    sb.setLength(0);
			    prefix = "";
			    while (rsC.next()) {
			    	sb.append(prefix);
			    	sb.append(rsC.getString("BASIC_DETAIL_NAME"));
			    	prefix = ", ";
			    }
			    String listC = sb.toString();
			    
				System.out.println(String.join("\r\n",
											   "A. Make: " + rs.getString("BRAND"),
											   "B. Model: " + rs.getString("MODEL"),
											   "C. Year: ",
											   "D. Service A - a. Miles: " + rs.getString("A_MILEAGE_INCREMENT"),
											   "D. Service A - b. List of Basic Services (Service ID): " + listA,
											   "E. Service B - a. Miles: " + rs.getString("B_MILEAGE_INCREMENT"),
											   "E. Service B - b. List of Basic Services (Service ID): Service A + " + listB,
											   "F. Service C - a. Miles: " + rs.getString("C_MILEAGE_INCREMENT"),
											   "F. Service C - b. List of Basic Services (Service ID): Service B + " + listC
											  ));
				System.out.println();
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		do {
			Menu.print(CAR_SVC_DTL_OPTS);
			switch (Menu.input()) {
				case 1: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	// OK
	public static String serviceHistory() {
		try {
			String sql = "SELECT * FROM APPOINTMENT WHERE CUSTOMER_ID in (SELECT CUSTOMER_ID FROM CUSTOMER WHERE CENTER_ID = ?)";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getCenterID());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sql = "SELECT CARSERVICE_ID, STATUS FROM CARSERVICE WHERE APPOINTMENT_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, rs.getString("APPOINTMENT_ID"));
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next()) {
					sql = "SELECT CUSTOMER_NAME FROM CUSTOMER WHERE CUSTOMER_ID = (SELECT CUSTOMER_ID FROM APPOINTMENT WHERE APPOINTMENT_ID = ?)";
					pstmt = Database.getConnection().prepareStatement(sql);
					pstmt.setString(1, rs.getString("APPOINTMENT_ID"));
					ResultSet rs3 = pstmt.executeQuery();
					
					if (rs3.next()) {
						System.out.println(String.join("\r\n",
													   "A. Service ID: " + rs2.getString("CARSERVICE_ID"),
													   "B. Customer Name: " + rs3.getString("CUSTOMER_NAME"), 
													   "C. License Plate: " + rs.getString("LICENSE_NUM"),
													   "D. Service Type: " + rs.getString("SERVICE_TYPE") + " - " + rs.getString("SERVICE_SPECIFY"),
													   "E. Mechanic Name: " + rs.getString("MECHANIC_NAME"),
													   "F. Service Start Date/Time: " + rs.getDate("APPOINTMENT_DATE") + " " + rs.getString("BEGIN_TIME"),
													   "G. Service End Date/Time (expected or actual): " + rs.getDate("APPOINTMENT_DATE") + " " + rs.getString("END_TIME"),
													   "H. Service Status (Pending, Ongoing, or Complete): " + rs2.getString("STATUS")
													  ));
						
						System.out.println();
					}
				}
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		do {
			Menu.print(SVC_HIST_OPTS);
			switch (Menu.input()) {
				case 1: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	public static String invoices() {
		String sql = "SELECT CENTER_ID FROM EMPLOYEE WHERE EMP_PHONE = ?";
		PreparedStatement pstmt;
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getUserID());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				sql = "SELECT CS.CARSERVICE_ID, A.APPOINTMENT_DATE, C.CUSTOMER_NAME, A.BEGIN_TIME, A.END_TIME, A.LICENSE_NUM, A.SERVICE_TYPE, A.MECHANIC_NAME "
						+ "FROM CARSERVICE CS "
						+ "JOIN APPOINTMENT A ON A.APPOINTMENT_ID = CS.APPOINTMENT_ID "
						+ "JOIN CUSTOMER C ON C.CUSTOMER_ID = A.CUSTOMER_ID "
						+ "WHERE C.CENTER_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, rs.getString("CENTER_ID"));
				ResultSet rs2 = pstmt.executeQuery();
				while (rs2.next()) {
						System.out.println(String.join("\r\n",
								   "A. Service ID: " + rs2.getString("CARSERVICE_ID"),
								   "B. Customer Name: " + rs2.getString("CUSTOMER_NAME"),
								   "C. Service Start Date/Time: " + rs2.getString("APPOINTMENT_DATE") + rs2.getString("BEGIN_TIME"),
								   "D. Service End Date/Time: " + rs2.getString("APPOINTMENT_DATE") + rs2.getString("END_TIME"),
								   "E. Licence Plate: " + rs2.getString("LICENSE_NUM"),
								   "F. Service Type: " + rs2.getString("SERVICE_TYPE"),
								   "G. Mechanic Name: " + rs2.getString("MECHANIC_NAME")
								  ));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println();
		
		do {
			Menu.print(INVS_OPTS);
			switch (Menu.input()) {
				case 1: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	private static final String[] MGR_OPTS = {
											   "1. Profile", 
											   "2. View Customer Profile",
											   "3. Add New Employees",
											   "4. Payroll",
											   "5. Inventory",
											   "6. Orders",
											   "7. Notifications",
											   "8. New Car Model",
											   "9. Car Service Details",
											   "10. Service History",
											   "11. Invoices",
											   "12. Logout"
										     };
	
	private static final String[] ADD_NEW_EMPLS_OPTS = {
													     "1. Add",
													     "2. Go Back"
													   };
	
	private static final String[] PAYRll_OPTS = {
												  "1. Go Back"
											    };
	
	private static final String[] INVT_OPTS = {
										        "1. Go Back"
										      };
	
	private static final String[] ORDS_OPTS = {
											    "1. Order History",
											    "2. New Order",
											    "3. Go Back"
										      };
	
	private static final String[] ORD_HIST_OPTS = {
											        "1. Go Back"
											      };
	
	private static final String[] NEW_ORD_OPTS = {
											       "1. Place Order",
											       "2. Go Back"
											     };
	
	private static final String[] NOTIFS_OPTS = {
												  "1. Order ID",
												  "2. Go Back"
											    };
	
	private static final String[] NOTIFS_DTL_OPTS = {
												      "1. Go Back"
												    };
	
	private static final String[] NEW_CAR_MDL_OPTS = {
												       "1. Add Car",
												       "2. Go Back"
												     };
	
	private static final String[] CAR_SVC_DTL_OPTS = {
												       "1. Go Back"
												     };
	
	private static final String[] SVC_HIST_OPTS = {
												    "1. Go Back"
											      };
	
	private static final String[] INVS_OPTS = {
											    "1. Go Back"
										      };
	
	private static String command = "";
}