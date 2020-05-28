package cars;

import java.sql.Date;

public class Car {
	private String license = null;
	private String brand = null;
	private String model = null;
	private int madeYear = 0;
	private Date purchase = null;
	private int mile = 0;
	private String sType = null;
	private Date sDate = null;
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getMadeYear() {
		return madeYear;
	}
	public void setMadeYear(int madeYear) {
		this.madeYear = madeYear;
	}
	public Date getPurchase() {
		return purchase;
	}
	public void setPurchase(Date purchase) {
		this.purchase = purchase;
	}
	public int getMile() {
		return mile;
	}
	public void setMile(int mile) {
		this.mile = mile;
	}
	public String getsType() {
		return sType;
	}
	public void setsType(String sType) {
		this.sType = sType;
	}
	public Date getsDate() {
		return sDate;
	}
	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	
	
}
