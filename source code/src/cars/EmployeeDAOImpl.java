package cars;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAOImpl implements EmployeeDAO {
	@Override
	public Car getCar(String license) throws Exception {
		Car a = new Car();
		
		String sql = "select BRAND,MODEL from VEHICLE where LICENSE_NUM = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, license);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				 a.setModel(rs.getString("MODEL"));
				 a.setBrand(rs.getString("BRAND"));
			}
			
			pstmt.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return a;
	}

}
