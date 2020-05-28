package cars;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlotNumDAOImpl implements PlotNumDAO{

	@Override
	public double getMServiceTime(String model, String type) throws Exception {
		double num = 0;
		
		String sql = "select COST_TIME from M_VALUES where MODEL = ? AND DETAIL=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, model);
			pstmt.setString(2, type);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getDouble("COST_TIME");
			}
			
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public double getRServiceTime(String model, String type) throws Exception {
		double num = 0;
		
		String sql = "select COST_TIME from R_VALUES where MODEL = ? AND DETAIL=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, model);
			pstmt.setString(2, type);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getDouble("COST_TIME");
			}
			
			pstmt.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public Servicetype getService(String license) throws Exception {
		Servicetype lastType = new Servicetype();
		
		String sql = "select MILEAGE, LAST_SERVICE_TYPE from VEHICLE where LICENSE_NUM = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, license);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				 lastType.setMile(rs.getInt("MILEAGE"));
				 lastType.setService(rs.getString("LAST_SERVICE_TYPE"));
			}
			
			pstmt.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lastType;
	}

	@Override
	public ServiceMile getMile(String brand, String model) throws Exception {
		ServiceMile mile = new ServiceMile();
		int a = 0;
		int b = 0;
		int c = 0;
		
		
		String sql = "select A_MILEAGE_INCREMENT, B_MILEAGE_INCREMENT, C_MILEAGE_INCREMENT from CARTYPE where BRAND = ? AND MODEL=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, brand);
			pstmt.setString(2, model);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				 a = rs.getInt("A_MILEAGE_INCREMENT");
				 b = rs.getInt("B_MILEAGE_INCREMENT");
				 c = rs.getInt("C_MILEAGE_INCREMENT");
			}
			
			pstmt.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		mile.setAmile(a);
		mile.setBmile(a+b);
		mile.setCmile(a+b+c);
		
		return mile;
	}

	@Override
	public boolean setMile(String license, String service, int mile, Date date) throws Exception {
		boolean flag=true;
		String sql = "update VEHICLE set MILEAGE=?, LAST_SERVICE_TYPE=?, LAST_SERVICE_DATE=? where LICENSE_NUM=?";
        PreparedStatement pstmt = null ;   
        try {
            pstmt=Database.getConnection().prepareStatement(sql) ;
            pstmt.setInt(1,mile);
            pstmt.setString(2,service);
            pstmt.setDate(3, date);
            pstmt.setString(4, license);
            int i= pstmt.executeUpdate();
            if(i==0){
                flag=false;
            }
            pstmt.close();

        } catch (SQLException e) {
         e.printStackTrace();
        } 
		return flag;

	}

	@Override
	public boolean setReMile(Date date, String license) throws Exception {
		boolean flag=true;
		String sql = "update VEHICLE set LAST_SERVICE_DATE=? where LICENSE_NUM=?";
        PreparedStatement pstmt = null ;   
        try {
            pstmt=Database.getConnection().prepareStatement(sql) ;
            pstmt.setDate(1, date);
            pstmt.setString(2, license);
            int i= pstmt.executeUpdate();
            if(i==0){
                flag=false;
            }
            pstmt.close();

        } catch (SQLException e) {
         e.printStackTrace();
        }
        
		return flag;
	}
	
}
