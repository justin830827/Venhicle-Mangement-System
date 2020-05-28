package cars;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Customer {
	public static String landing() {
		do {
			Menu.print(CUS_OPTS);
			switch (Menu.input()) {
				case 1: command = "profile"; break;
				case 2: command = "registerCar"; break;
				case 3: command = "service"; break;
				case 4: command = "invoice"; break;
				case 5: command = "logout"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	public static String profile() {
		do {
			Menu.print(PF_OPTS);
			switch (Menu.input()) {
				case 1: command = "viewProfile"; break;
				case 2: command = "updateProfile"; break;
				case 3: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	public static String viewProfile() {
		try {
			String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getUserID());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				sql = "SELECT * FROM VEHICLE WHERE CUSTOMER_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, rs.getString("CUSTOMER_ID"));
				ResultSet rs2 = pstmt.executeQuery();
				
				String carList = "";
				while (rs2.next()) {
					sql = "SELECT BRAND, MODEL FROM VEHICLE WHERE LICENSE_NUM = ?";
					pstmt = Database.getConnection().prepareStatement(sql);
					pstmt.setString(1, rs2.getString("LICENSE_NUM"));
					ResultSet rs3 = pstmt.executeQuery();
					
					if (rs3.next()) {
						carList += String.join(" ",
											   "BRAND: " + rs3.getString("BRAND"),
											   "MODEL: " + rs3.getString("MODEL"),
											   "LICENSE_NUM: " + rs2.getString("LICENSE_NUM"),
											   "MADE_YEAR: " + rs2.getString("MADE_YEAR"),
											   "MILEAGE: " + (rs2.getString("MILEAGE") != null ? rs2.getString("MILEAGE") : "NULL"),
											   "PURCHASE_DATE: " + rs2.getDate("PURCHASE_DATE").toString(),
											   "LAST_SERVICE_DATE: " + (rs2.getDate("LAST_SERVICE_DATE") != null ? rs2.getDate("LAST_SERVICE_DATE").toString() : "NULL"),
											   "LAST_SERVICE_TYPE: " + (rs2.getString("LAST_SERVICE_TYPE") != null ? rs2.getString("LAST_SERVICE_TYPE") : "NULL"),
											   "\r\n"
										      );
					}
				}
				
				System.out.println(String.join("\r\n", 
											   "A. Customer ID: " + rs.getString("CUSTOMER_ID"),
											   "B. Name: " + rs.getString("CUSTOMER_NAME"),
											   "C. Address: " + rs.getString("CUSTOMER_ADDR"),
											   "D. Email Address: " + rs.getString("CUSTOMER_EMAIL"),
											   "E. Phone Number: " + rs.getString("CUSTOMER_PHONENUM"),
											   "F. List of All Cars (and their details):\r\n" + carList
											  ));
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		do {
			Menu.print(VIEW_PF_OPTS);
			switch (Menu.input()) {
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
				case 3: updatePhoneNumber(); break;
				case 4: updatePassword(); break;
				case 5: command = "profile"; trigger = false; break;
				default: break;
			}
		} while (trigger);
		return command;
	}
	
	private static void updateName() {
		try {
			String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getUserID());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				System.out.print("Please enter name: ");
				
				sql = "Update CUSTOMER set CUSTOMER_NAME = ? WHERE CUSTOMER_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, Menu.getline());
				pstmt.setString(2, rs.getString("CUSTOMER_ID"));
				pstmt.executeUpdate();
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void updateAddress() {		
		try {
			String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getUserID());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				System.out.print("Please enter address: ");
				
				sql = "Update CUSTOMER set CUSTOMER_ADDR = ? WHERE CUSTOMER_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, Menu.getline());
				pstmt.setString(2, rs.getString("CUSTOMER_ID"));
				pstmt.executeUpdate();
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void updatePhoneNumber() {
		try {
			String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getUserID());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				System.out.print("Please enter phone number: ");
				
				sql = "Update CUSTOMER set CUSTOMER_PHONENUM = ? WHERE CUSTOMER_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, Menu.getline());
				pstmt.setString(2, rs.getString("CUSTOMER_ID"));
				pstmt.executeUpdate();
			}
			
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
	
	public static String registerCar() {
		String[] details = {
							 "A. Licence plate: ",
							 "B. Purchase date: ",
							 "C. Make: ",
							 "D. Model: ",
							 "E. Made Year: ",
							 "F. Current mileage: ",
							 "G. Last Service Type: ",
							 "H. Last Service Date: "
						   };
		
		ArrayList<String> inputs = new ArrayList<String>();
		for (String detail : details) {
			System.out.print(detail);
			inputs.add(Menu.getline());
		}
		
		do {
			Menu.print(REGISTER_CAR_OPTS);
			switch (Menu.input()) {
				case 1: command = register(inputs); break;
				case 2: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	private static String register(ArrayList<String> inputs) {
		try {
			String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getUserID());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
				sql = "INSERT INTO VEHICLE (LICENSE_NUM, PURCHASE_DATE, MILEAGE, MADE_YEAR, LAST_SERVICE_TYPE, LAST_SERVICE_DATE, BRAND, MODEL, CUSTOMER_ID) VALUES (?,?,?,?,?,?,?,?,?)";
				pstmt = Database.getConnection().prepareStatement(sql);
				
				pstmt.setString(1, inputs.get(0));
				pstmt.setDate(2, java.sql.Date.valueOf(LocalDate.parse(inputs.get(1), formatter)));
				pstmt.setInt(3, Integer.parseInt(inputs.get(5)));
				pstmt.setInt(4, Integer.parseInt(inputs.get(4)));
				pstmt.setString(5, inputs.get(6));
				pstmt.setDate(6, java.sql.Date.valueOf(LocalDate.parse(inputs.get(7), formatter)));
				pstmt.setString(7, inputs.get(2));
				pstmt.setString(8, inputs.get(3));
				pstmt.setString(9, rs.getString("CUSTOMER_ID"));
				pstmt.executeUpdate();
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "landing";
	}
	
	public static String service() {
		do {
			Menu.print(SVC_OPTS);
			switch (Menu.input()) {
				case 1: command = serviceHistory(); break;
				case 2: command = scheduleService(); break;
				case 3: command = rescheduleServicePage1(); break;
				case 4: command = "landing"; break;
				default: command = "default"; break;
			}
		} while(command.equals("service") || command.equals("default"));
		return command;
	}
	
	private static String serviceHistory() {
		try {
			String sql = "SELECT * FROM APPOINTMENT WHERE CUSTOMER_ID in (SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?)";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getUserID());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sql = "SELECT CARSERVICE_ID, STATUS FROM CARSERVICE WHERE APPOINTMENT_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, rs.getString("APPOINTMENT_ID"));
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next()) {
					System.out.println(String.join("\r\n",
												   "A. Service ID: " + rs2.getString("CARSERVICE_ID"),
										           "B. License Plate: " + rs.getString("LICENSE_NUM"),
										           "C. Service Type: " + rs.getString("SERVICE_TYPE") + " - " + rs.getString("SERVICE_SPECIFY"),
										           "D. Mechanic Name: " + rs.getString("MECHANIC_NAME"),
										           "E. Service Start Date/Time: " + rs.getDate("APPOINTMENT_DATE") + " " + rs.getString("BEGIN_TIME"),
										           "F. Service End Date/Time (expected or actual): " + rs.getDate("APPOINTMENT_DATE") + " " + rs.getString("END_TIME"),
										           "G. Service Status (Pending, Ongoing, or Complete): " + rs2.getString("STATUS")
										          ));
					
					System.out.println();
				}
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		
		do {
			Menu.print(SVC_HIST_OPTS);
			switch (Menu.input()) {
				case 1: command = "service"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	private static String scheduleService() {
		String[] details = {
							 "A. License Plate: ",
							 "B. Current Mileage: ",
							 "C. Mechanic Name: "
						   };
		
		ArrayList<String> inputs = new ArrayList<String>();
		for (String detail : details) {
			System.out.print(detail);
			inputs.add(Menu.getline());
		}
		
		do {
			Menu.print(SCHEDULE_SVC_OPTS);
			switch (Menu.input()) {
				case 1: command = scheduleMaintenancePage1(inputs); break;
				case 2: command = scheduleRepairPage1(inputs); break;
				case 3: command = "service"; break;
				default: command = "default"; break;
			}
		} while (command.equals("scheduleService") || command.equals("default"));
		return command;
	}
	
	private static String scheduleMaintenancePage1(ArrayList<String> inputs) {
		do {
			Menu.print(SCHEDULE_MAINT_OPTS_P1);
			switch (Menu.input()) {
				case 1: command = findServiceDate(inputs); break;
				case 2: command = "scheduleService"; break;
				default: command = "default"; break;
			}
		} while (command.equals("scheduleMaintenancePage1") || command.equals("default"));
		return command;
	}
	
	private static String findServiceDate(ArrayList<String> inputs) {
		PlotNum pNum = new PlotNum();
		NextService ns = pNum.getMNum(inputs.get(1), Integer.parseInt(inputs.get(2)));
		ArrayList<AppointmentDate> dates = PickDate.appDates(ns.getPlotNum(), Menu.getCenterID(), "Maintenance");
		
		switch ((dates != null) ? 1 : 2) {
			case 1: command = scheduleMaintenancePage2(ns, inputs, dates); break;
			case 2: command = "scheduleService"; break;
			default: break;
		}
		return command;
	}
	
	private static String scheduleMaintenancePage2(NextService ns, ArrayList<String> inputs, ArrayList<AppointmentDate> dates) {
		System.out.println("Display service dates and the mechanic name");
		
		for (AppointmentDate date: dates) {
			System.out.print(". Service date: ");
			System.out.println(date.getDate());
			System.out.print(". Service Begin time: ");
			System.out.println(date.getbSlot());
			System.out.print(". Service End time: ");
			System.out.println(date.geteSlot());
		}
		
		System.out.println(inputs.get(3));
		
		do {
			Menu.print(SCHEDULE_MAINT_OPTS_P2);
			switch (Menu.input()) {
				case 1: command = scheduleOnDate(ns, inputs, dates); break;
				case 2: command = "scheduleMaintenancePage1"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	private static String scheduleOnDate(NextService ns, ArrayList<String> inputs, ArrayList<AppointmentDate> dates) {
		System.out.print("Please choose a date: ");
		switch(Menu.getline()) {
			case "1": 
				PickDate.makeAppointment(dates.get(0), "Maintenance", Menu.getCenterID()); 
				PlotNumDAOImpl pNDI = new PlotNumDAOImpl(); 
				try {
					pNDI.setMile(inputs.get(1), ns.getService(), Integer.parseInt(inputs.get(2)), dates.get(0).getDate());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.print(". Service date: ");
				System.out.println(dates.get(0).getDate());
				System.out.print(". Service Begin time: ");
				System.out.println(dates.get(0).getbSlot());
				System.out.print(". Service End time: ");
				System.out.println(dates.get(0).geteSlot());
				
				try {
					String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?";
					PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
					pstmt.setString(1, inputs.get(0));
					ResultSet rs = pstmt.executeQuery();
					
					if (rs.next()) {
						sql = "SELECT MAX(APPOINTMENT_ID) AS MAX_ID FROM APPOINTMENT";
						pstmt = Database.getConnection().prepareStatement(sql);
						ResultSet rs2 = pstmt.executeQuery();
						
						if (rs2.next()) {
							String num = Integer.toString(Integer.parseInt(rs2.getString("MAX_ID").substring(2)) + 1);
							String appointmentID = "SH" + String.join("", Collections.nCopies(rs2.getString("MAX_ID").substring(2).length() - num.length(), "0")) + num;
							
							sql = "INSERT INTO APPOINTMENT (APPOINTMENT_ID,SERVICE_TYPE,SERVICE_SPECIFY,MECHANIC_NAME,"
								  + "APPOINTMENT_DATE,BEGIN_TIME,END_TIME,LICENSE_NUM,CUSTOMER_ID) VALUES (?,?,?,?,?,?,?,?,?)";
							pstmt = Database.getConnection().prepareStatement(sql);
							pstmt.setString(1, appointmentID);
							pstmt.setString(2, "Maintenance");
							pstmt.setString(3, ns.getService());
							pstmt.setString(4, inputs.get(3));
							pstmt.setDate(5, dates.get(0).getDate());
							pstmt.setString(6, dates.get(0).getbSlot());
							pstmt.setString(7, dates.get(0).geteSlot());
							pstmt.setString(8, inputs.get(1));
							pstmt.setString(9, rs.getString("CUSTOMER_ID"));
							pstmt.executeUpdate();
							
							sql = "SELECT MAX(CARSERVICE_ID) AS MAX_ID FROM CARSERVICE";
							pstmt = Database.getConnection().prepareStatement(sql);
							ResultSet rs3 = pstmt.executeQuery();
							
							if (rs3.next()) {
								num = Integer.toString(Integer.parseInt(rs3.getString("MAX_ID").substring(2)) + 1);
								String carserviceID =  "CS" + String.join("", Collections.nCopies(rs3.getString("MAX_ID").substring(2).length() - num.length(), "0")) + num;
								
								sql = "INSERT INTO CARSERVICE (CARSERVICE_ID, STATUS, APPOINTMENT_ID) VALUES (?,?,?)";
								pstmt = Database.getConnection().prepareStatement(sql);
								pstmt.setString(1, carserviceID);
								pstmt.setString(2, "Pending");
								pstmt.setString(3, appointmentID);
								pstmt.executeUpdate();
							}
							
							pstmt.close();
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				break;
				
			case "2": 
				PickDate.makeAppointment(dates.get(1), "Maintenance", Menu.getCenterID()); 
				PlotNumDAOImpl pNDI1 = new PlotNumDAOImpl(); 
				try {
					pNDI1.setMile(inputs.get(1), ns.getService(), Integer.parseInt(inputs.get(2)), dates.get(1).getDate());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.print(". Service date: ");
				System.out.println(dates.get(1).getDate());
				System.out.print(". Service Begin time: ");
				System.out.println(dates.get(1).getbSlot());
				System.out.print(". Service End time: ");
				System.out.println(dates.get(1).geteSlot());

				try {
					String sql = "SELECT CUSTOMER_ID FROM CUSOMTER WHERE CUSTOMER_EMAIL = ?";
					PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
					pstmt.setString(1, inputs.get(0));
					ResultSet rs = pstmt.executeQuery();
					
					if (rs.next()) {
						sql = "SELECT MAX(APPOINTMENT_ID) AS MAX_ID FROM APPOINTMENT";
						pstmt = Database.getConnection().prepareStatement(sql);
						ResultSet rs2 = pstmt.executeQuery();
						
						if (rs2.next()) {
							String num = Integer.toString(Integer.parseInt(rs2.getString("MAX_ID").substring(1)) + 1);
							String appointmentID = "SH" + String.join("", Collections.nCopies(rs2.getString("MAX_ID").substring(2).length() - num.length(), "0")) + num;
							
							sql = "INSERT INTO APPOINTMENT (APPOINTMENT_ID,SERVICE_TYPE,SERVICE_SPECIFY,MECHANIC_NAME,"
								  + "APPOINTMENT_DATE,BEGIN_TIME,END_TIME,LICENSE_NUM,CUSTOMER_ID) VALUES (?,?,?,?,?,?,?,?,?)";
							pstmt = Database.getConnection().prepareStatement(sql);
							pstmt.setString(1, appointmentID);
							pstmt.setString(2, "Maintenance");
							pstmt.setString(3, ns.getService());
							pstmt.setString(4, inputs.get(3));
							pstmt.setDate(5, dates.get(1).getDate());
							pstmt.setString(6, dates.get(1).getbSlot());
							pstmt.setString(7, dates.get(1).geteSlot());
							pstmt.setString(8, inputs.get(1));
							pstmt.setString(9, rs.getString("CUSTOMER_ID"));
							pstmt.executeUpdate();
							
							sql = "SELECT MAX(CARSERVICE_ID) AS MAX_ID FROM CARSERVICE";
							pstmt = Database.getConnection().prepareStatement(sql);
							ResultSet rs3 = pstmt.executeQuery();
							
							if (rs3.next()) {
								num = Integer.toString(Integer.parseInt(rs3.getString("MAX_ID").substring(2)) + 1);
								String carserviceID =  "CS" + String.join("", Collections.nCopies(rs3.getString("MAX_ID").substring(2).length() - num.length(), "0")) + num;
								
								sql = "INSERT INTO CARSERVICE (CARSERVICE_ID, STATUS, APPOINTMENT_ID) VALUES (?,?,?)";
								pstmt = Database.getConnection().prepareStatement(sql);
								pstmt.setString(1, carserviceID);
								pstmt.setString(2, "Pending");
								pstmt.setString(3, appointmentID);
								pstmt.executeUpdate();
							}
							
							pstmt.close();
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				break;
				
			default: break;
		}
		
		return "scheduleService";
	}
	
	private static String scheduleRepairPage1(ArrayList<String> inputs) {
		do {
			Menu.print(SCHEDULE_REP_OPTS_P1);
			switch (Menu.input()) {
				case 1: command = engineKnock(inputs); break;
				case 2: command = carDriftsInAParticularDirection(inputs); break;
				case 3: command = batteryDoesNotHoldCharge(inputs); break;
				case 4: command = blackUncleanExhaust(inputs); break;
				case 5: command = acHeaterNotWorking(inputs); break;
				case 6: command = headlampsOrTaillampsNotWorking(inputs); break;
				case 7: command = checkEngineLight(inputs); break;
				case 8: command = "scheduleService"; break;
				default: command = "default"; break;
			}
		} while (command.equals("scheduleRepairPage1") || command.equals("default"));
		return command;
	}
	
	private static String engineKnock(ArrayList<String> inputs) {
		System.out.println("Create a Engine-Knock report");
		
		PlotNum pNum = new PlotNum();
		int i = pNum.getRNum(inputs.get(1), "Engine knock");
		ArrayList<AppointmentDate> dates = PickDate.appDates(i, Menu.getCenterID(), "Repair");
		
		switch ((dates != null) ? 1 : 2) {
			case 1: command = scheduleRepairPage2("Engine knock", inputs, dates); break;
			case 2: command = "scheduleService"; break;
			default: break;
		}
		return command;
	}
	
	private static String carDriftsInAParticularDirection(ArrayList<String> inputs) {
		System.out.println("Create a Car-Drifts-In-A-Particular-Direction report");
		
		PlotNum pNum = new PlotNum();
		int i = pNum.getRNum(inputs.get(1), "Car drifts in a particular direction");
		ArrayList<AppointmentDate> dates = PickDate.appDates(i, Menu.getCenterID(), "Repair");	
		
		switch ((dates != null) ? 1 : 2) {
			case 1: command = scheduleRepairPage2("Car drifts in a particular direction", inputs, dates); break;
			case 2: command = "scheduleService"; break;
			default: break;
		}
		return command;
	}
	
	private static String batteryDoesNotHoldCharge(ArrayList<String> inputs) {
		System.out.println("Create a Battery-Does-Not-Hold-Charge report");
		
		PlotNum pNum = new PlotNum();
		int i = pNum.getRNum(inputs.get(1), "Battery does not hold charge");
		ArrayList<AppointmentDate> dates = PickDate.appDates(i, Menu.getCenterID(), "Repair");
		
		switch ((dates != null) ? 1 : 2) {
			case 1: command = scheduleRepairPage2("Battery does not hold charge", inputs, dates); break;
			case 2: command = "scheduleService"; break;
			default: break;
		}
		return command;
	}

	private static String blackUncleanExhaust(ArrayList<String> inputs) {
		System.out.println("Create a Black/Unclean-Exhaust report");
		
		PlotNum pNum = new PlotNum();
		int i = pNum.getRNum(inputs.get(1), "Black/unclean exhaust");
		ArrayList<AppointmentDate> dates = PickDate.appDates(i, Menu.getCenterID(), "Repair");
		
		switch ((dates != null) ? 1 : 2) {
			case 1: command = scheduleRepairPage2("Black/unclean exhaust", inputs, dates); break;
			case 2: command = "scheduleService"; break;
			default: break;
		}
		return command;
	}
	
	private static String acHeaterNotWorking(ArrayList<String> inputs) {
		System.out.println("Create a AC-Heater-Not-Working report");
		
		PlotNum pNum = new PlotNum();
		int i = pNum.getRNum(inputs.get(1), "A/C-Heater not working");
		ArrayList<AppointmentDate> dates = PickDate.appDates(i, Menu.getCenterID(), "Repair");
				
		switch ((dates != null) ? 1 : 2) {
			case 1: command = scheduleRepairPage2("A/C-Heater not working", inputs, dates); break;
			case 2: command = "scheduleService"; break;
			default: break;
		}
		return command;
	}
	
	private static String headlampsOrTaillampsNotWorking(ArrayList<String> inputs) {
		System.out.println("Create a Headlamps-Or-Taillamps-Not-Working report");
		
		PlotNum pNum = new PlotNum();
		int i = pNum.getRNum(inputs.get(1), "Headlamps/Tail lamps not working");
		ArrayList<AppointmentDate> dates = PickDate.appDates(i, Menu.getCenterID(), "Repair");
		
		switch ((dates != null) ? 1 : 2) {
			case 1: command = scheduleRepairPage2("Headlamps/Tail lamps not working", inputs, dates); break;
			case 2: command = "scheduleService"; break;
			default: break;
		}
		return command;
	}
	
	private static String checkEngineLight(ArrayList<String> inputs) {
		System.out.println("Create a Check-Engine-Light report");
		
		PlotNum pNum = new PlotNum();
		int i = pNum.getRNum(inputs.get(1), "Check engine light");
		ArrayList<AppointmentDate> dates = PickDate.appDates(i, Menu.getCenterID(), "Repair");
		
		switch ((dates != null) ? 1 : 2) {
			case 1: command = scheduleRepairPage2("Check engine light", inputs, dates); break;
			case 2: command = "scheduleService"; break;
			default: break;
		}
		return command;
	}
	
	private static String scheduleRepairPage2(String detail, ArrayList<String> inputs, ArrayList<AppointmentDate> dates) {
		System.out.println("Display the diagnostic report, service dates, and the mechanic name");
		
		for (AppointmentDate date: dates) {
			System.out.print(". Service date: ");
			System.out.println(date.getDate());
			System.out.print(". Service Begin time: ");
			System.out.println(date.getbSlot());
			System.out.print(". Service End time: ");
			System.out.println(date.geteSlot());
		}
		
		System.out.println(inputs.get(3));
		
		do {
			Menu.print(SCHEDULE_REP_OPTS_P2);
			switch (Menu.input()) {
				case 1: command = repairOnDate(detail, inputs, dates); break;
				case 2: command = "scheduleRepairPage1"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	private static String repairOnDate(String detail, ArrayList<String> inputs, ArrayList<AppointmentDate> dates) {
		System.out.print("Please choose a date: ");
		switch(Menu.getline()) {
			case "1": PickDate.makeAppointment(dates.get(0), "Repair", Menu.getCenterID()); 
					  
					  System.out.print(". Service date: ");
					  System.out.println(dates.get(0).getDate());
					  System.out.print(". Service Begin time: ");
					  System.out.println(dates.get(0).getbSlot());
					  System.out.print(". Service End time: ");
					  System.out.println(dates.get(0).geteSlot());
					
					  try {
				         String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?";
						 PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
						 pstmt.setString(1, inputs.get(0));
						 ResultSet rs = pstmt.executeQuery();
						
						 if (rs.next()) {
							 sql = "SELECT MAX(APPOINTMENT_ID) AS MAX_ID FROM APPOINTMENT";
							 pstmt = Database.getConnection().prepareStatement(sql);
							 ResultSet rs2 = pstmt.executeQuery();
							
							 if (rs2.next()) {
								 String num = Integer.toString(Integer.parseInt(rs2.getString("MAX_ID").substring(2)) + 1);
								 String appointmentID = "SH" + String.join("", Collections.nCopies(rs2.getString("MAX_ID").substring(2).length() - num.length(), "0")) + num;
								
								 sql = "INSERT INTO APPOINTMENT (APPOINTMENT_ID,SERVICE_TYPE,SERVICE_SPECIFY,MECHANIC_NAME,"
									   + "APPOINTMENT_DATE,BEGIN_TIME,END_TIME,LICENSE_NUM,CUSTOMER_ID) VALUES (?,?,?,?,?,?,?,?,?)";
								 pstmt = Database.getConnection().prepareStatement(sql);
								 pstmt.setString(1, appointmentID);
								 pstmt.setString(2, "Maintenance");
								 pstmt.setString(3, detail);
								 pstmt.setString(4, inputs.get(3));
								 pstmt.setDate(5, dates.get(0).getDate());
								 pstmt.setString(6, dates.get(0).getbSlot());
								 pstmt.setString(7, dates.get(0).geteSlot());
								 pstmt.setString(8, inputs.get(1));
								 pstmt.setString(9, rs.getString("CUSTOMER_ID"));
								 pstmt.executeUpdate();
								
								sql = "SELECT MAX(CARSERVICE_ID) AS MAX_ID FROM CARSERVICE";
								pstmt = Database.getConnection().prepareStatement(sql);
								ResultSet rs3 = pstmt.executeQuery();
								
								if (rs3.next()) {
									num = Integer.toString(Integer.parseInt(rs3.getString("MAX_ID").substring(2)) + 1);
									String carserviceID =  "CS" + String.join("", Collections.nCopies(rs3.getString("MAX_ID").substring(2).length() - num.length(), "0")) + num;
									
									sql = "INSERT INTO CARSERVICE (CARSERVICE_ID, STATUS, APPOINTMENT_ID) VALUES (?,?,?)";
									pstmt = Database.getConnection().prepareStatement(sql);
									pstmt.setString(1, carserviceID);
									pstmt.setString(2, "Pending");
									pstmt.setString(3, appointmentID);
									pstmt.executeUpdate();
								}
								
								pstmt.close();
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					  break;
			case "2": PickDate.makeAppointment(dates.get(1), "Repair", Menu.getCenterID()); 
					  break;
			default:  break;
		}
		
		return "scheduleService";
	}
	
	public static String rescheduleServicePage1() {
		do {
			System.out.println(String.join("\r\n",
										   "License Plate",
										   "Service ID",
										   "Service Date",
										   "Service Type (Maintenance/Repair)",
										   "Service Details (Service A/B/C or Problem)")
										  );
			
			System.out.println();
			Menu.print(RESCHEDULE_SVC_OPTS_P1);
			switch(Menu.input()) {
				case 1: command = pickAService(); break;
				case 2: command = "service"; break;
				default: command = "default"; break;
			}
		} while (command.equals("rescheduleServicePage1") || command.equals("default"));
		return command;
	}
	
	private static String pickAService() {
		System.out.println("Pick a service");
		return rescheduleServicePage2();
	}
	
	private static String rescheduleServicePage2() {
		System.out.println("Display service dates and the mechanic name");
		System.out.println();
		
		do {
			Menu.print(RESCHEDULE_SVC_OPTS_P2);
			switch (Menu.input()) {
				case 1: command = rescheduleDate(); break;
				case 2: command = "rescheduleServicePage1"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	private static String rescheduleDate() {
		System.out.println("Reschedule date");
		return "service";
	};
	
	public static String invoice() {
		do {
			String sql = "SELECT CS.CARSERVICE_ID, A.APPOINTMENT_DATE, A.BEGIN_TIME, A.END_TIME, A.LICENSE_NUM, A.SERVICE_TYPE, A.MECHANIC_NAME"
						 + "FROM CARSERVICE CS"
			             + "JOIN APPOINTMENT A ON A.APPOINTMENT_ID = CS.APPOINTMENT_ID"
			             + "JOIN CUSTOMER C ON C.CUSTOMER_ID = A.CUSTOMER_ID"
			             + "WHERE C.CUSTOMER_ID = ?";
			
			PreparedStatement pstmt = null;
			try {
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, Menu.getUserID());
				ResultSet rs = pstmt.executeQuery();
				
				System.out.println(String.join("\r\n", 
						                       "A. Service ID: " + rs.getString("CARSERVICE_ID"),
						                       "B. Service Start Date/Time: " + rs.getString("APPOINTMENT_DATE") + rs.getString("BEGIN_TIME"),
						                       "C. Service End Date/Time: " + rs.getString("APPOINTMENT_DATE") + rs.getString("END_TIME"),
						                       "D. Licence Plate: " + rs.getString("LICENSE_NUM"),
						                       "E. Service Type: " + rs.getString("SERVICE_TYPE"),
						                       "F. Mechanic Name: " + rs.getString("MECHANIC_NAME")
											  ));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			             
			Menu.print(INV_OPTS);
			switch (Menu.input()) {
				case 1: command = viewInvoiceDetails(); break;
				case 2: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("invoice") || command.equals("default"));
		return command;
	};
	
	private static String viewInvoiceDetails() {
		System.out.print("Please enter the service ID: ");
		Menu.getline();
		
		do {
			Menu.print(INV_DTL_OPTS);
			switch (Menu.input()) {
				case 1: command = "invoice"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	};
	
	private static final String[] CUS_OPTS = {
											   "1. Profile",
											   "2. Register Car", 
											   "3. Service", 
											   "4. Invoices",
											   "5. Logout"
											 };
	
	private static final String[] PF_OPTS = {
										      "1. View Profile",
										      "2. Update Profile",
										      "3. Go Back"
										    };
	
	private static final String[] VIEW_PF_OPTS = {
												   "1. Go Back"
											     };
	
	private static final String[] UPDATE_PF_OPTS = {
												     "1. Name",
												     "2. Address",
												     "3. Phone Number",
												     "4. Password",
												     "5. Go Back"
											       };
	
	private static final String[] REGISTER_CAR_OPTS = {
													    "1. Register",
													    "2. Cancel"
													  };
	
	private static final String[] SVC_OPTS = {
											   "1. View Service History",
											   "2. Schedule Service",
											   "3. Reschedule Service",
											   "4. Go Back"
											 };
	
	private static final String[] SVC_HIST_OPTS = {
												    "1. Go Back"
												  };
	
	private static final String[] SCHEDULE_SVC_OPTS = {
													    "1. Schedule Maintenance",
													    "2. Schedule Repair",
													    "3. Go Back"
													  };
	
	private static final String[] SCHEDULE_MAINT_OPTS_P1 = {
														     "1. Find Service Date",
													         "2. Go Back"
														   };
	
	private static final String[] SCHEDULE_MAINT_OPTS_P2 = {
														     "1. Schedule On Date",
														     "2. Go Back"
														   };
	
	private static final String[] SCHEDULE_REP_OPTS_P1 = {
														   "1. Engine knock",
														   "2. Car drifts in a particular direction",
														   "3. Battery does not hold charge",
														   "4. Black/unclean exhaust",
														   "5. A/C-Heater not working",
														   "6. Headlamps/Tail lamps not working",
														   "7. Check engine light",
														   "8. Go Back"
													     };
	
	private static final String[] SCHEDULE_REP_OPTS_P2 = {
														   "1. Repair On Date",
														   "2. Go Back"
													     };

	private static final String[] RESCHEDULE_SVC_OPTS_P1 = {
														     "1. Pick a service",
														     "2. Go Back"
														   };
	
	private static final String[] RESCHEDULE_SVC_OPTS_P2 = {
														     "1. Reschedule Date",
														     "2. Go Back"
												  		   };
	
	private static final String[] INV_OPTS = {
											   "1. View Invoice Details",
											   "2. Go Back"
											 };
	
	private static final String[] INV_DTL_OPTS = {
											   	   "1. Go Back"
											     };
	
	private static String command = "";
}