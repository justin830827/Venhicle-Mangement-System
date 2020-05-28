package cars;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class PickDate {
	private static final int slotNum = 22;
	private static final double halfLowerFactor = 0.5;
	private static final double halfHigherFactor = 0.59;
	
	private static int halfLower = (int) Math.ceil(slotNum*halfLowerFactor);
	private static int halfHigher = (int)Math.ceil(slotNum*halfHigherFactor);
	
	private static Date today = new Date(System.currentTimeMillis());
	private static Date searchDate = nextweekDay(today);
	 
	 public static Date nextweekDay(Date date) {
		 Date weekDay = null;
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.add(Calendar.DAY_OF_MONTH, 1);
		 
		 if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			 cal.add(Calendar.DAY_OF_MONTH, 2);
		 }
		 
		 if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			 cal.add(Calendar.DAY_OF_MONTH, 1);
		 }
		 
		 weekDay = new Date(cal.getTime().getTime()); 
		 return weekDay;
	 }
	 
	 public static ArrayList<Date> fiveWeekDays (Date lastDate) {
		 Date temDate1 = lastDate;
		 Date temDate2 = null;
		 ArrayList<Date> addDate = new ArrayList<Date>();
		 
		 for(int i=0; i< 5; i++) {
			  temDate2 = nextweekDay(temDate1);
			  addDate.add(temDate2);
			  temDate1 = temDate2; 
		 }
		 
		 return addDate;
	 }
	 
	 public static ArrayList<AppointmentDate> appDates(int num, String centerID, String type){
		 ArrayList<DateValue> allDates = null;
		 ArrayList<AppointmentDate> aDates = new ArrayList<AppointmentDate>();
		 AppointmentDateDAOImpl b = new AppointmentDateDAOImpl();
		 ArrayList<Date> newDates = null;
		 Date lastDate = null;
		
		 if(type.equals("Repair")) {
			 try {
				allDates = b.getRAvaliableDate(num, searchDate, centerID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 if(type.equals("Maintenance")) {
			 try {
				allDates = b.getMAvaliableDate(centerID, num, searchDate, halfLower, halfHigher);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		
		if(allDates.size() < 2) {
			try {
				lastDate = b.getlastDate(centerID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			newDates = fiveWeekDays(lastDate);
			try {
				b.addDate(newDates,centerID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			allDates.clear();
			if(type.equals("Reapir")) {
				 try {
						allDates = b.getRAvaliableDate(num, searchDate, centerID);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			if(type.equals("Maintenance")) {
			try {
				allDates = b.getMAvaliableDate(centerID,num, searchDate, halfLower, halfHigher);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		}
		
		for(DateValue c:allDates) {
			AppointmentDate tem = new AppointmentDate();
			String bSlot = null;
			String eSlot = null;
			
			try {
				bSlot = b.getSlot(c.getbSlot());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				eSlot = b.getSlot(c.getbSlot()+num);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tem.setDate(c.getDate());
			tem.setbSlot(bSlot);
			tem.seteSlot(eSlot);
			
			aDates.add(tem);
	    }
	     return aDates;
	 }
	 
	 public static boolean makeAppointment(AppointmentDate date, String type, String centerID) {
		 boolean flag = false;
		 int status = -1;
		 AppointmentDateDAOImpl b = new AppointmentDateDAOImpl();
		 int bid = 0;
		 int eid = 0;
		 
		 try {
			bid = b.getid(date.getbSlot());
		} catch (Exception e) {
			e.printStackTrace();
		}
		 try {
			eid = b.getid(date.geteSlot());
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 try {
			status = b.setAppointment(date.getDate(), bid, eid,centerID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 if(status == -1)
		 return flag;
		 else if(status == 1) {
			try {
				flag = b.changeSlot(date.getDate(),bid, eid,centerID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 else if(status == 0) {
			 try {
				flag = b.deleteSlot(date.getDate(), bid, centerID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 else if(status == 2) {
			 try {
				flag = b.subSlot(date.getDate(), bid, eid, centerID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 else if(status == 3) {
			 try {
				flag = b.seperateSlot(date.getDate(), bid,eid, centerID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 
		 if(type.equals("Maintenance")) {
			 try {
				flag = b.addMaintainance(date.getDate(), eid-bid,centerID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 System.out.println(flag);
		 
		 return flag;
	 }
	 
	 public static ArrayList<AppointmentDate> reappDates(int num, Date serviceDate, String centerId, String type){
		 
		 ArrayList<AppointmentDate> rDates = null;
		 Date allowDate = nextweekDay(serviceDate);
		 searchDate = allowDate;
		 
		rDates = appDates(num,centerId,type);
		 
		 return rDates;
	 }
	 
	 public static boolean makeReAppointment(AppointmentDate date, AppointmentDate previousDate, String type, String centerId) {
		 boolean flag = false;
		 int status = -1;
		 AppointmentDateDAOImpl b = new AppointmentDateDAOImpl();
		 
		 int bid = 0;
		 int eid = 0;
		 
		 try {
			bid = b.getid(previousDate.getbSlot());
		} catch (Exception e) {
			e.printStackTrace();
		}
		 try {
			eid = b.getid(previousDate.geteSlot());
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
		 flag = makeAppointment(date,type,centerId);
		 if(!flag) {
			 return flag;
		 }
		
		 try {
			status = b.checkState(previousDate.getDate(), bid, eid, centerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 if(status == 2) {
			 try {
				flag = b.reverseSlot(previousDate.getDate(), bid, eid,centerId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }else if(status == 0){
			 try {
				flag = b.addSlot(centerId,previousDate.getDate(), bid, eid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }else if(status == 1){
			 try {
				flag = b.backSlot(previousDate.getDate(), bid, eid, centerId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }else if(status == 3){
			 try {
				flag = b.integrateSlot(centerId,previousDate.getDate(), bid, eid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 
		 
		 if(type.equals("Maintenance")) {
			 try {
				flag = b.subMaintainance(previousDate.getDate(), eid-bid, centerId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 
		 
		 return flag;
	 }
}
