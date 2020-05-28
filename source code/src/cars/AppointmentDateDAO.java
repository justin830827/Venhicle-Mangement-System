package cars;

import java.util.ArrayList;
import java.sql.Date;

public interface AppointmentDateDAO {
	public ArrayList<DateValue> getMAvaliableDate(String centerId,int num, Date beginDate, int lowerHalf, int higherHalf) throws Exception;
	public Date getlastDate(String centerId) throws Exception;
	public boolean addDate(ArrayList<Date> allDates, String centerId) throws Exception;
	public int getLastSlot() throws Exception;
	public String getSlot(int id) throws Exception;
	public int getid(String slot) throws Exception;
	public int setAppointment(Date date, int bid, int eid, String centerId) throws Exception;
	public boolean deleteSlot(Date date, int bid, String centerId) throws Exception;
	public boolean changeSlot(Date date, int bid, int eid, String centerId) throws Exception;
	public boolean addMaintainance(Date date, int num, String centerId) throws Exception;
	public int checkState(Date date, int bid,int eid, String centerId) throws Exception;
	public boolean addSlot(String centerId,Date date, int bid, int eid) throws Exception;
	public boolean reverseSlot(Date date, int bid, int eid, String centerId) throws Exception;
	public boolean subMaintainance(Date date, int num, String centerId) throws Exception;
	public ArrayList<DateValue> getRAvaliableDate(int num, Date beginDate, String centerID) throws Exception;
	public boolean subSlot(Date date, int bid, int eid, String centerId) throws Exception;
	public boolean seperateSlot(Date date, int bid, int eid, String centerId) throws Exception;
	public boolean integrateSlot(String centerId,Date date, int bid, int eid) throws Exception;
	public boolean backSlot(Date date, int bid, int eid, String centerId) throws Exception;
}
