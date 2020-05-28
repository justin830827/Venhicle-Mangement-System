package cars;
import java.util.ArrayList;

public class Payroll {
	private String id = null;
	private String name = null;
	private int compensation = 0;
	private String frequency = null;
	private ArrayList<Paycheck> paycheck = null;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCompensation() {
		return compensation;
	}
	
	public void setCompensation(int compensation) {
		this.compensation = compensation;
	}
	
	public String getFrequency() {
		return frequency;
	}
	
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public ArrayList<Paycheck> getPaycheck() {
		return paycheck;
	}
	
	public void setPaycheck(ArrayList<Paycheck> paycheck) {
		this.paycheck = paycheck;
	}
}
