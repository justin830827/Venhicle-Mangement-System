package cars;

import java.sql.Date;

public class AppointmentDate {
	private Date date = null;
	private String bSlot = null;
	private String eSlot = null;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getbSlot() {
		return bSlot;
	}
	public void setbSlot(String bSlot) {
		this.bSlot = bSlot;
	}
	public String geteSlot() {
		return eSlot;
	}
	public void seteSlot(String eSlot) {
		this.eSlot = eSlot;
	}
	
	
}
