package cars;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Receptionist extends Employee {
	public static String landing() {
		do {
			Menu.print(RPT_OPTS);
			switch (Menu.input()) {
				case 1: command = "profile"; break;
				case 2: command = "viewCustomerProfile"; break;
				case 3: command = "registerCar"; break;
				case 4: command = "serviceHistory"; break;
				case 5: command = "scheduleService"; break;
				case 6: command = "rescheduleService"; break;
				case 7: command = "invoices"; break;
				case 8: command = "dailyTaskUpdateInventory"; break;
				case 9: command = "dailyTaskRecordDeliveries"; break;
				case 10: command = "logout"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	// OK
	public static String registerCar() {
		String[] details = {
				  			 "A. Customer email address: ",
							 "B. Licence plate: ",
							 "C. Purchase date: ",
							 "D. Make: ",
							 "E. Model: ",
							 "F. Year: ",
							 "G. Current mileage: ",
							 "H. Last Service Type: ",
							 "I. Last Service Date: "
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
				default: command = "defualt"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	// OK
	private static String register(ArrayList<String> inputs) {
		try {
			String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, inputs.get(0));
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
				sql = "INSERT INTO VEHICLE (LICENSE_NUM, PURCHASE_DATE, MILEAGE, MADE_YEAR, LAST_SERVICE_TYPE, LAST_SERVICE_DATE, BRAND, MODEL, CUSTOMER_ID) VALUES (?,?,?,?,?,?,?,?,?)";
				pstmt = Database.getConnection().prepareStatement(sql);
				
				pstmt.setString(1, inputs.get(1));
				pstmt.setDate(2, java.sql.Date.valueOf(LocalDate.parse(inputs.get(2), formatter)));
				pstmt.setInt(3, Integer.parseInt(inputs.get(6)));
				pstmt.setInt(4, Integer.parseInt(inputs.get(5)));
				pstmt.setString(5, inputs.get(7));
				pstmt.setDate(6, java.sql.Date.valueOf(LocalDate.parse(inputs.get(8), formatter)));
				pstmt.setString(7, inputs.get(3));
				pstmt.setString(8, inputs.get(4));
				pstmt.setInt(9, Integer.parseInt(rs.getString("CUSTOMER_ID")));
				pstmt.executeUpdate();
			}
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "landing";
	}
	
	
	// OK
	public static String serviceHistory() {
		System.out.print("Please enter customer email address: ");
		
		try {
			String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_EMAIL = ?";
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getline());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {				
				sql = "SELECT * FROM APPOINTMENT WHERE CUSTOMER_ID = ?";
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setString(1, rs.getString("CUSTOMER_ID"));
				ResultSet rs2 = pstmt.executeQuery();
				
				while (rs2.next()) {
					sql = "SELECT CARSERVICE_ID, STATUS FROM CARSERVICE WHERE APPOINTMENT_ID = ?";
					pstmt = Database.getConnection().prepareStatement(sql);
					pstmt.setString(1, rs2.getString("APPOINTMENT_ID"));
					ResultSet rs3 = pstmt.executeQuery();
					
					if (rs3.next()) {
						System.out.println(String.join("\r\n",
													   "A. Service ID: " + rs3.getString("CARSERVICE_ID"),
											           "B. License Plate: " + rs2.getString("LICENSE_NUM"),
											           "C. Service Type: " + rs2.getString("SERVICE_TYPE") + " - " + rs2.getString("SERVICE_SPECIFY"),
											           "D. Mechanic Name: " + rs2.getString("MECHANIC_NAME"),
											           "E. Service Start Date/Time: " + rs2.getDate("APPOINTMENT_DATE") + " " + rs2.getString("BEGIN_TIME"),
											           "F. Service End Date/Time (expected or actual): " + rs2.getDate("APPOINTMENT_DATE") + " " + rs2.getString("END_TIME"),
											           "G. Service Status (Pending, Ongoing, or Complete)" + rs3.getString("STATUS")
											          ));
						System.out.println();
					}
				}
			} else {
				System.out.println("This email address does not exist.");
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
		} while (command.equals("defalut"));
		return command;
	}
	
	public static String scheduleService() {
		String[] details = {
							 "A. Customer email address: ",
							 "B. Licence plate: ",
							 "C. Current mileage: ",
							 "D. Mechanic Name: "
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
				case 3: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("scheduleService") || command.equals("default"));
		return command;
	}
	
	public static String scheduleMaintenancePage1(ArrayList<String> inputs) {
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
		} while (command.equals("defalut"));
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
	
	public static String scheduleRepairPage1(ArrayList<String> inputs) {
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
		System.out.print("Please enter customer email address: ");

		String[] details = {
							 "A. Customer email address: "
						   };
		
		ArrayList<String> inputs = new ArrayList<String>();
		for (String detail : details) {
			System.out.print(detail);
			inputs.add(Menu.getline());
		}
		
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
				case 1: command = pickAService(inputs); break;
				case 2: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("rescheduleServicePage1") || command.equals("default"));
		return command;
	}
	
	private static String pickAService(ArrayList<String> inputs) {
		String serviceType = "", licenseNum = "", reportType = "";
		Date serviceDate = null;
		PlotNum pNum = new PlotNum();
		
		int i = 0;
		if (serviceType.equals("M")) {
			i = pNum.getReMNum(serviceType, licenseNum);		
		} else if (serviceType.equals("R")) {
			i = pNum.getRNum(licenseNum, reportType);
		}
		
		ArrayList<AppointmentDate> dates = PickDate.reappDates(i, serviceDate, Menu.getCenterID(), serviceType);
		return rescheduleServicePage2(inputs, dates);
	}
	
	private static String rescheduleServicePage2(ArrayList<String> inputs, ArrayList<AppointmentDate> dates) {
		System.out.println("Display service dates, and the mechanic name");
		
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
			Menu.print(RESCHEDULE_SVC_OPTS_P2);
			switch (Menu.input()) {
				case 1: command = rescheduleDate(inputs, dates); break;
				case 2: command = "rescheduleServicePage1"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	private static String rescheduleDate(ArrayList<String> inputs, ArrayList<AppointmentDate> dates) {
		System.out.print("Please choose a reschedule date: ");
		String type = "";
		AppointmentDate previousDate = null;
		
		switch(Menu.getline()) {
			case "1": PickDate.makeReAppointment(dates.get(0), previousDate, type, Menu.getCenterID()); 
					  if (type.equals("M")) { 
						  PlotNumDAOImpl pNDI = new PlotNumDAOImpl();
						  try {
							  pNDI.setReMile(dates.get(0).getDate(), inputs.get(1));
						  } catch (Exception e) {
							  e.printStackTrace();
						  }
					  }
					  break;
			case "2": PickDate.makeReAppointment(dates.get(1), previousDate, type, Menu.getCenterID());
					  if (type.equals("M")) { 		
					 	  PlotNumDAOImpl pNDI1 = new PlotNumDAOImpl();
					 	  try {
					 		  pNDI1.setReMile(dates.get(1).getDate(), inputs.get(1));
					 	  } catch (Exception e) {
					 		  e.printStackTrace();
					 	  }
					  }
					  break;
		 	default:  break;
		}
		
		return "landing";
	}
	
	public static String invocies() {
		System.out.print("Please enter customer email address: ");

		String sql = "SELECT CS.CARSERVICE_ID, A.APPOINTMENT_DATE, A.BEGIN_TIME, A.END_TIME, A.LICENSE_NUM, A.SERVICE_TYPE, A.MECHANIC_NAME "
					+ "FROM CARSERVICE CS "
					+ "JOIN APPOINTMENT A ON A.APPOINTMENT_ID = CS.APPOINTMENT_ID "
					+ "JOIN CUSTOMER C ON C.CUSTOMER_ID = A.CUSTOMER_ID "
					+ "WHERE C.CUSTOMER_EMAIL = ?";
		
		try {
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, Menu.getline());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
			System.out.println(String.join("\r\n", 
										   "A. Service ID: " + rs.getString("CARSERVICE_ID"),
										   "B. Service Start Date/Time; " + rs.getString("APPOINTMENT_DATE") + rs.getString("BEGIN_TIME"),
										   "C. Service End Date/Time: " +  rs.getString("APPOINTMENT_DATE") + rs.getString("END_TIME"),
										   "D. Licence Plate: " + rs.getString("LICENSE_NUM"), 
										   "E. Service Type; " + rs.getString("SERVICE_TYPE"), 
										   "F. Mechanic Name: " + rs.getString("MECHANIC_NAME")
										  ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		do {
			Menu.print(INVS_OPTS);
			switch (Menu.input()) {
				case 1: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	public static String dailyTaskUpdateInventory() {
		try {
			Date today = new java.sql.Date(new java.util.Date().getTime());
			String sql = "SELECT CI.PART_ID AS REQUIRED_PART_ID , (CI.CUR_STOCK-BS.REQUIRED_QUANTITY) AS NEW_QUANTITY "
							+ "FROM APPOINTMENT A "
							+ "JOIN VEHICLE V ON A.LICENSE_NUM = V.LICENSE_NUM "
							+ "RIGHT JOIN CARTYPE_HAS_BASIC_SERVICE CBS ON CBS.MODEL = V.MODEL "
							+ "JOIN BASIC_SERVICE BS ON BS.BASIC_SERVICE_ID = CBS.BASIC_SERVICE_ID "
							+ "JOIN CENTER_HAS_INVENTORY CI ON  CI.PART_ID = BS.PART_ID "
							+ "WHERE A.SERVICE_SPECIFY = CBS.SERVICE_TYPE AND A.APPOINTMENT_DATE = ? AND CI.CENTER_ID = ( SELECT C.CENTER_ID "
							+ "FROM APPOINTMENT A "
							+ "JOIN CUSTOMER C ON C.CUSTOMER_ID = A.CUSTOMER_ID "
							+ "WHERE A.APPOINTMENT_DATE = ?) "
							+ "UPDATE CENTER_HAS_INVENTORY SET CUR_STOCK = NEW_QUANTITY WHERE PART_ID = REQUIRED_PART_ID"; 
		
			PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setDate(1, today);
			pstmt.setDate(2, today);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		}
		
		do {
			Menu.print(DLY_TSK_UPDT_INV_OPTS);
			switch (Menu.input()) {
				case 1: command = "landing"; break;
				default: command = "default"; break;
			}
		} while (command.equals("default"));
		return command;
	}
	
	public static String dailyTaskRecordDeliveries() {
		do {
			System.out.print("Please enter a comma separated list of order ids: ");
			List<String> list = new ArrayList<String>(Arrays.asList(Menu.getline().split("\\s*,\\s*")));

			Menu.print(DLY_TSK_REC_OPTS);
			switch (Menu.input()) {
				case 1: command = updateStatusComplete(list); break;
				case 2: command = updateStatusDelayed(); break;
				default: command = "deault"; break;
			}
		} while (command.equals("dailyTaskRecordDeliveries") || command.equals("default"));
		return command;
	}
	
	private static String updateStatusComplete(List<String> list) {
		Date today = new java.sql.Date(new java.util.Date().getTime());
		String sql = "UPDATE PORDER SET PORDER_STATUS = 'Complete', PORDER_ACT_DATE = ? WHERE PORDER_ID = ?";
		PreparedStatement pstmt = null;
		
		for (String item: list) {
			try {
				pstmt = Database.getConnection().prepareStatement(sql);
				pstmt.setDate(1, today);
				pstmt.setString(2, item);
				if (pstmt.executeUpdate() == 1) {
					System.out.println("OrderID (" + item + "): Update Status Complete - Successful");
				} else {
					System.out.println("OrderID (" + item + "): Update Status Complete - Failed");
				}
				
				pstmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());			
			}
		}
		
		return "landing";
	}
	
	private static String updateStatusDelayed() {
		System.out.println("Update status - Delayed");
		return "landing";
	}
	
	private static final String[] RPT_OPTS = {
											   "1. Profile", 
										       "2. View Customer Profile",
										       "3. Register Car",
										       "4. Service History",
										       "5. Schedule Service",
										       "6. Reschedule Service",
										       "7. Invoices",
										       "8. Daily Task-Update Inventory",
										       "9. Daily Task-Record Deliveries",
										       "10. Logout"
										     };
	
	private static final String[] REGISTER_CAR_OPTS = {
												        "1. Register",
												        "2. Cancel",
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
														   "1. Engine Knock",
														   "2. Car drifts in a particular direction",
														   "3. Battery does not hold charge",
														   "4. Black/unclean exhaust",
														   "5. A/C-Heater not working",
														   "6. Headlamps/Taillamps not working",
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
	
	private static final String[] INVS_OPTS = {
											    "1. Go Back"
											  };
	
	private static final String[] DLY_TSK_UPDT_INV_OPTS = {
											 		        "1. Go Back"
										   		          };
	
	private static final String[] DLY_TSK_REC_OPTS = {
													   "1. Enter Order ID(CSV)",
													   "2. Go Back"
												     };
	
	private static String command = "";
}