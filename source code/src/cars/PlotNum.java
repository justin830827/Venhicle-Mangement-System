package cars;

public class PlotNum {
	private String model = null;
	private String brand = null;
	private int aMile = 0;
	private int bMile = 0;
	private int cMile = 0;
	/*private int aMile = 10;
	private int bMile = 35;
	private int cMile = 85;*/
	//private String thisService = null;
	
	public void getAllMiles() {
		PlotNumDAOImpl a = new PlotNumDAOImpl();
		ServiceMile mile = new ServiceMile();
		
		try {
			mile = a.getMile(brand, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		aMile = mile.getAmile();
		bMile = mile.getBmile();
		cMile = mile.getCmile();
		
	}
	
	public String getMService(String license, int curMile) {
		String service = null;
		PlotNumDAOImpl a = new PlotNumDAOImpl();
		Servicetype type = null;
		int mile = 0;
		String lastService = null;
		
		try {
			type = a.getService(license);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mile = type.getMile();
		lastService = type.getService();
		
		//mile = 84;
		//lastService = "Service C";
		
		if(lastService == null) {
			if(curMile<=Math.ceil((bMile-aMile)/2+aMile))
				service = "Service A";
			else if((curMile>Math.ceil((bMile-aMile)/2+aMile))&&(curMile<=Math.ceil((cMile-bMile)/2+bMile)))
				service = "Service B";
			else if(curMile>Math.ceil((cMile-bMile)/2+bMile))
				service = "Service C";
		}
		else if(lastService.equals("Service A")){
			int i = (curMile-mile)%(cMile);
			int t = curMile-mile;
			if(t<=Math.ceil((bMile-aMile)/2))
				service = "Service B";
			else if((i>Math.ceil((bMile-aMile)/2))&&(i<=Math.ceil((cMile-bMile)/2+(bMile-aMile))))
				service = "Service B";
			else if((i>Math.ceil((cMile-bMile)/2+(bMile-aMile)))&&(i<=Math.ceil((aMile)/2+(cMile-aMile))))
				service = "Service C";
			else if(((i>Math.ceil((aMile)/2+(cMile-aMile)))||i<=Math.ceil((bMile-aMile)/2))&&(t>Math.ceil((bMile-aMile)/2)))
				service = "Service A";
		}
		else if(lastService.equals("Service B")){
			int i = (curMile-mile)%(cMile);
			int t = curMile-mile;
			if(t<=Math.ceil((cMile-bMile)/2))
				service = "Service C";
			else if((i>Math.ceil((cMile-bMile)/2))&&(i<=Math.ceil((aMile)/2+(cMile-bMile))))
				service = "Service C";
			else if((i>Math.ceil((aMile)/2+(cMile-bMile)))&&(i<=Math.ceil((bMile-aMile)/2+(cMile-bMile+aMile))))
				service = "Service A";
			else if(((i>Math.ceil((bMile-aMile)/2+(cMile-bMile+aMile)))||(i<=Math.ceil((cMile-bMile)/2)))&&(t>Math.ceil((cMile-bMile)/2)))
				service = "Service B";
		}
		else if(lastService.equals("Service C")){
			int i = (curMile-mile)%(cMile);
			int t = curMile-mile;
			if(t<=Math.ceil(aMile/2))
				service = "Service A";
			else if((i>Math.ceil((aMile)/2))&&(i<=Math.ceil((bMile-aMile)/2+(aMile))))
				service = "Service A";
			else if((i>Math.ceil((bMile-aMile)/2+(aMile)))&&(i<=Math.ceil((cMile-bMile)/2+(bMile))))
				service = "Service B";
			else if(((i>Math.ceil((cMile-bMile)/2+(bMile)))||(i<=Math.ceil((bMile-aMile)/2)))&&(t>Math.ceil(aMile/2)))
				service = "Service C";
		}
		
		return service;
	}
	
	public NextService getMNum(String license, int curMile) {
		NextService next = new NextService();
		int num = 0;
		double time = 0;
		PlotNumDAOImpl a = new PlotNumDAOImpl();
		EmployeeDAOImpl b = new EmployeeDAOImpl();
		int tempTime1 = 0;
		double tempTime2 = 0;
		String service = null;
		Car c = new Car();
		
		try {
			c = b.getCar(license);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		brand = c.getBrand();
		model = c.getModel();
		
		getAllMiles();
		
		service = getMService(license, curMile);
		
		
		try {
			time = a.getMServiceTime(model, service);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//time = 4;
		
		tempTime1 = (int)time;
		tempTime2 = time - tempTime1;
		if(tempTime2 == 0)
			num = tempTime1*2;
		else if(tempTime2 <= 0.5)
			num = tempTime1*2 + 1;
		else if(tempTime2 > 0.5)
			num = tempTime1*2 + 2; 
		//System.out.println(num);
		next.setPlotNum(num);
		next.setService(service);
		
		return next;
	}
	
	public int getRNum(String license, String service) {
		int num = 0;
		double time = 0;
		int tempTime1 = 0;
		double tempTime2 = 0;
		Car c = new Car();
		PlotNumDAOImpl a = new PlotNumDAOImpl();
		EmployeeDAOImpl b = new EmployeeDAOImpl();
		
		try {
			c = b.getCar(license);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		brand = c.getBrand();
		model = c.getModel();
		
		try {
			time = a.getRServiceTime(model, service);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tempTime1 = (int)time;
		tempTime2 = time - tempTime1;
		if(tempTime2 == 0)
			num = tempTime1*2;
		else if(tempTime2 <= 0.5)
			num = tempTime1*2 + 1;
		else if(tempTime2 > 0.5)
			num = tempTime1*2 + 2; 
		
		return num;
	}
	
	public int getReMNum(String service, String license) {
		int num = 0;
		double time = 0;
		int tempTime1 = 0;
		double tempTime2 = 0;
		Car c = new Car();
		PlotNumDAOImpl a = new PlotNumDAOImpl();
		EmployeeDAOImpl b = new EmployeeDAOImpl();
		
		
		try {
			c = b.getCar(license);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		brand = c.getBrand();
		model = c.getModel();
		
		try {
			time = a.getMServiceTime(model, service);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tempTime1 = (int)time;
		tempTime2 = time - tempTime1;
		if(tempTime2 == 0)
			num = tempTime1*2;
		else if(tempTime2 <= 0.5)
			num = tempTime1*2 + 1;
		else if(tempTime2 > 0.5)
			num = tempTime1*2 + 2; 
		
		
		
		
		return num;
	}
	
	
}
