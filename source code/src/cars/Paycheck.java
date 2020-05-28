package cars;
import java.sql.Date;

public class Paycheck {
	private Date payDate = null;
	private String period = null;
	private int units = 0;
	private int cEarning = 0;
	private int aEarning = 0;
	
	public Date getPayDate() {
		return payDate;
	}
	
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	public String getPeriod() {
		return period;
	}
	
	public void setPeriod(String period) {
		this.period = period;
	}
	
	public int getUnits() {
		return units;
	}
	
	public void setUnits(int units) {
		this.units = units;
	}
	
	public int getcEarning() {
		return cEarning;
	}
	
	public void setcEarning(int cEarning) {
		this.cEarning = cEarning;
	}
	
	public int getaEarning() {
		return aEarning;
	}
	
	public void setaEarning(int aEarning) {
		this.aEarning = aEarning;
	}
}