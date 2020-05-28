package cars;

import java.sql.Date;

public interface PlotNumDAO {
	public double getMServiceTime(String model, String type)  throws Exception;
	public double getRServiceTime(String model, String type)  throws Exception;
	public Servicetype getService(String license) throws Exception;
	public ServiceMile getMile(String brand, String model) throws Exception;
	public boolean setMile(String license, String service, int mile, Date date) throws Exception;
	public boolean setReMile(Date date, String license) throws Exception;
}
