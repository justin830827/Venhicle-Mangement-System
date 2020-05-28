package cars;

public class CARS {	
	public static void main(String[] args) {
		Boolean trigger = true;
		String command = "home";
		
		Database.openDatabase("jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01", "cshen9", "200258143");
		Menu.openScanner(System.in);
		while (trigger) {
			switch (command) {
				case "home": command = Entry.home(); break;
				case "login": command = Entry.login(); break;
				case "signUp": command = Entry.signUp(); break;
				case "landing": command = run(); break;
				case "exit": trigger = false; break;
				default: break;
			}
		}
		
		Database.closeDatabase();
		Menu.closeScanner();
	}
	
	private static String run() {		
		switch(Menu.getRole()) {
			case "Customer": accessCustomer(true, "landing"); break;
			case "Manager": accessManager(true, "landing"); break;
			case "Receptionist": accessReceptionist(true, "landing"); break;
			case "Mechanics": accessMechanics(true, "landing"); break;
			default: break;
		}
		
		return "home";
	}
	
	private static void accessCustomer(Boolean trigger, String command) {
		while (trigger) {
			switch (command) {
				case "landing": command = Customer.landing(); break;
				
				case "profile": command = Customer.profile(); break;
				case "viewProfile": command = Customer.viewProfile(); break;
				case "updateProfile": command = Customer.updateProfile(); break;
				
				case "registerCar": command = Customer.registerCar(); break;
				case "service": command = Customer.service(); break;
				case "invoice": command = Customer.invoice(); break;
				
				case "logout": trigger = false; break;
				default: trigger = false; break;
			}
		}
	}
	
	private static void accessManager(Boolean trigger, String command) {
		while (trigger) {
        	switch (command) {
	        	case "landing": command = Manager.landing(); break;
	        	
	    		case "profile": command = Employee.profile(); break;
	    		case "viewProfile": command = Employee.viewProfile(); break;
	    		case "updateProfile": command = Employee.updateProfile(); break;
	    		case "viewCustomerProfile": command = Employee.viewCustomerProfile(); break;
	    		
	    		case "addNewEmployees": command = Manager.addNewEmployees(); break;
	    		case "payroll": command = Manager.payroll(); break;
	    		case "inventory": command = Manager.inventory(); break;
	    		case "orders": command = Manager.orders(); break;
	    		case "notifications": command = Manager.notifications(); break;
	    		case "newCarModel": command = Manager.newCarModel(); break;
	    		case "carServiceDetails": command = Manager.carServiceDetails(); break;
	    		case "serviceHistory": command = Manager.serviceHistory(); break;
	    		case "invoices": command = Manager.invoices(); break;
        		
	    		case "logout": trigger = false; break;
        		default: trigger = false; break;
        	}
        }
	}
	
	private static void accessReceptionist(Boolean trigger, String command) {
		while (trigger) {
        	switch (command) {
        		case "landing": command = Receptionist.landing(); break;
        		
        		case "profile": command = Employee.profile(); break;
        		case "viewProfile": command = Employee.viewProfile(); break;
        		case "updateProfile": command = Employee.updateProfile(); break;
        		case "viewCustomerProfile": command = Employee.viewCustomerProfile(); break;

        		case "registerCar": command = Receptionist.registerCar(); break;
        		case "serviceHistory": command = Receptionist.serviceHistory(); break;
        		case "scheduleService": command = Receptionist.scheduleService(); break;
        		case "rescheduleService": command = Receptionist.rescheduleServicePage1(); break;
        		case "invoices": command = Receptionist.invocies(); break;
        		case "dailyTaskUpdateInventory": command = Receptionist.dailyTaskUpdateInventory(); break;
        		case "dailyTaskRecordDeliveries": command = Receptionist.dailyTaskRecordDeliveries(); break;			    		
        		
        		case "logout": trigger = false; break;
	    		default: trigger = false; break;
        	}
		}
	}
	
	private static void accessMechanics(Boolean trigger, String command) {
		while (trigger) {
        	switch (command) {
        		case "landing": command = Employee.landing(); break;
        		
        		case "profile": command = Employee.profile(); break;
        		case "viewProfile": command = Employee.viewProfile(); break;
        		case "updateProfile": command = Employee.updateProfile(); break;
        		case "viewCustomerProfile": command = Employee.viewCustomerProfile(); break;			    		
        		
        		case "logout": trigger = false; break;
	    		default: trigger = false; break;
        	}
		}
	}
}