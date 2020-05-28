package cars;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class AppointmentDateDAOImpl implements AppointmentDateDAO {
	public ArrayList<DateValue> getMAvaliableDate(String centerId, int num, Date beginDate, int lowerHalf, int higherHalf) throws Exception{
		ArrayList<DateValue> possibleTime = new ArrayList<DateValue>();
		DateValue tempDate = null;
		String sql = "select * from (select * from TIME_AVALIABLE where THE_DATE in (select APP_DATE from DATE_STATUS d where d.APP_DATE>=? AND MAINTENANCE_NUM <? AND MAINTENANCE_NUM+?<=? AND CENTER_ID=?) AND END_SLOT-BEGIN_SLOT>=? AND CENTER_ID=? ORDER BY THE_DATE, BEGIN_SLOT) Temp where ROWNUM < 3";
		PreparedStatement pstmt = null;
			
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setDate(1,beginDate);
			pstmt.setInt(2, lowerHalf);
			pstmt.setInt(3, num);
			pstmt.setInt(4, higherHalf);
			pstmt.setString(5, centerId);
			pstmt.setInt(6, num);
			pstmt.setString(7, centerId);
			ResultSet rs = pstmt.executeQuery();
			
			int a = 0;
			while(rs.next()) {
				tempDate = new DateValue();
				
				if((a == 0) && (rs.getInt("END_SLOT")-rs.getInt("BEGIN_SLOT"))>= 2*num ){
					tempDate.setDate(rs.getDate("THE_DATE"));
					tempDate.setbSlot(rs.getInt("BEGIN_SLOT"));
					possibleTime.add(tempDate);
					tempDate = new DateValue();
					tempDate.setDate(rs.getDate("THE_DATE"));
					tempDate.setbSlot(rs.getInt("BEGIN_SLOT")+num);
					possibleTime.add(tempDate);
					break;
				}
				tempDate.setDate(rs.getDate("THE_DATE"));
				tempDate.setbSlot(rs.getInt("BEGIN_SLOT"));
				
				possibleTime.add(tempDate);
				a++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return possibleTime;
	}

	@Override
	public Date getlastDate(String centerId) throws Exception {
		Date lastDate = null;
		String sql = "select max(APP_DATE) last from Date_Status where CENTER_ID = ?";
		PreparedStatement pstmt = null;
			
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, centerId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				lastDate = rs.getDate("last");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return lastDate;
	}

	@Override
	public boolean addDate(ArrayList<Date> allDates, String centerId) throws Exception {
		boolean flag = true;
		int lastSlot = getLastSlot();
		String sql1 = "insert into DATE_STATUS (APP_DATE, Maintenance_Num,CENTER_ID) values(?,?,?)";
		PreparedStatement pstmt1 = null ;   
		String sql2 = "insert into TIME_AVALIABLE (THE_DATE, Begin_Slot, End_Slot, CENTER_ID) values(?,?,?,?)";
        PreparedStatement pstmt2 = null ;   
     
        try {
            pstmt1 = Database.getConnection().prepareStatement(sql1) ;
            for(Date a:allDates) {
            	pstmt1.setDate(1,a) ;
            	pstmt1.setInt(2,0) ;
            	pstmt1.setString(3, centerId);
            
            	int i = pstmt1.executeUpdate();
            	if(i == 0){
            		flag = false;          
            	}
            }
            pstmt1.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        }

        try { 
            pstmt2 = Database.getConnection().prepareStatement(sql2) ;   
            for(Date a: allDates) {
            	pstmt2.setDate(1,a) ;
            	pstmt2.setInt(2,1) ;
            	pstmt2.setInt(3,lastSlot) ;
            	pstmt2.setString(4, centerId);
            	int j = pstmt2.executeUpdate();
            	if(j == 0){
            		flag = false;          
            	}
            }
            pstmt2.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        return flag;
	}

	@Override
	public int getLastSlot() throws Exception {
		int last = 0;
		String sql = "select max(TIME_ID) last from TIMESLOT";
		PreparedStatement pstmt = null;
			
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				last = rs.getInt("last");
			}
			
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return last;
	}

	@Override
	public String getSlot(int id) throws Exception {
		String slot = null;
		String sql = "select SLOT from TIMESLOT where TIME_ID = ?";
		PreparedStatement pstmt = null;
			
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				slot = rs.getString("slot");
			}
			
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return slot;
	}

	@Override
	public int getid(String slot) throws Exception {
		int id = 0;
		String sql = "select TIME_ID from TIMESLOT where SLOT = ?";
		PreparedStatement pstmt = null;
			
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, slot);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("TIME_ID");
			}
			pstmt.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		} 
		return id;
	}

	@Override
	public int setAppointment(Date date, int bid, int eid, String centerId) throws Exception {
		int flag = -1;
		int oeid = 0;
		int obid = 0;
		
		String sql = "select Begin_Slot, End_Slot from TIME_AVALIABLE where THE_DATE = ? AND Begin_Slot <= ? AND CENTER_ID = ? AND End_Slot>=?";
		PreparedStatement pstmt = null;
			
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setDate(1, date);
			pstmt.setInt(2, bid);
			pstmt.setString(3, centerId);
			pstmt.setInt(4,eid);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				obid = rs.getInt("Begin_Slot");
				oeid = rs.getInt("End_Slot");
				
			}
			pstmt.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		} 
		
		if(obid==0&&oeid==0)
			flag = -1;
		else if(obid==bid&&oeid==eid)
			flag = 0;
		else if(obid==bid&&oeid>eid)
			flag = 1;
		else if(obid<bid&&oeid==eid)
			flag = 2;
		else if(obid<bid&&oeid>eid)
			flag = 3;
		
		return flag;
	}

	@Override
	public boolean deleteSlot(Date date, int bid, String centerId) throws Exception {
		boolean flag = true;
		
		
		String sql = "delete from TIME_AVALIABLE where THE_DATE = ? AND Begin_Slot = ? AND CENTER_ID = ?";
		PreparedStatement pstmt = null;
			
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setDate(1, date);
			pstmt.setInt(2, bid);
			pstmt.setString(3, centerId);
			int i = pstmt.executeUpdate();
			if(i == 0)
				flag = false;
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		} 
		
		return flag;
	}

	@Override
	public boolean changeSlot(Date date, int bid, int eid, String centerId) throws Exception {
		
		boolean flag = true;
		
		String sql = "update TIME_AVALIABLE set Begin_Slot=? where THE_DATE=? and Begin_Slot=? and CENTER_ID=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setInt(1, eid);
			pstmt.setDate(2, date);
			pstmt.setInt(3, bid);
			pstmt.setString(4, centerId);
			
			int i = pstmt.executeUpdate();
			if(i==0) {
				flag = false;
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
		return flag;
	}

	@Override
	public boolean addMaintainance(Date date, int num, String centerId) throws Exception {
		boolean flag = true;
		int onum = 0;
		
		String sql = "update DATE_STATUS set Maintenance_Num=?+? where APP_DATE=? AND CENTER_ID = ?";
		PreparedStatement pstmt = null;
		String sql1 = "select m.Maintenance_Num from DATE_STATUS m where APP_DATE= ? AND CENTER_ID = ?";
		PreparedStatement pstmt1 = null ;   
        
        try {
			pstmt1 = Database.getConnection().prepareStatement(sql1);
			pstmt1.setDate(1, date);
			pstmt1.setString(2, centerId);
			
			
			ResultSet rs = pstmt1.executeQuery();
			
			if(rs.next()) {
				onum = rs.getInt("Maintenance_Num");
				
			}
			
			rs.close();
			pstmt1.close();
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setInt(1, onum);
			pstmt.setInt(2, num);
			pstmt.setDate(3, date);
			pstmt.setString(4, centerId);
			
			int i = pstmt.executeUpdate();
			if(i==0) {
				flag = false;
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return flag;
	}

	@Override
	public int checkState(Date date, int bid, int eid, String centerId) throws Exception {
		int flag = -1;
		int me = 0;
		int ib = 0;
		
		String sql = "select max(End_Slot) me from TIME_AVALIABLE where THE_DATE = ? AND End_Slot <= ? AND CENTER_ID = ?";
		PreparedStatement pstmt = null;
		String sql2 = "select min(Begin_Slot) ib from TIME_AVALIABLE where THE_DATE = ? AND Begin_Slot >= ? AND CENTER_ID = ?";
        PreparedStatement pstmt2 = null ;   
			
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setDate(1, date);
			pstmt.setInt(2, bid);
			pstmt.setString(3, centerId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				me = rs.getInt("me");
				
			}
			pstmt.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			pstmt2 = Database.getConnection().prepareStatement(sql2);
			pstmt2.setDate(1, date);
			pstmt2.setInt(2, eid);
			pstmt2.setString(3, centerId);
			ResultSet rs2 = pstmt2.executeQuery();
			
			if(rs2.next()) {
				ib = rs2.getInt("ib");
				
			}
			pstmt2.close();
			rs2.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(me==0&&ib==0) 
			flag = 0;
		else if(me==0&&ib==eid) 
			flag = 2;
		else if(me==0&&ib>eid) 
			flag = 0;
		else if(me==bid&&ib==0) 
			flag = 1;
		else if(me<bid&&ib==0) 
			flag = 0;
		else if(me==bid&&ib==eid) 
			flag = 3;
		else if(me==bid&&ib>eid)
			flag = 1;
		else if(me<bid&&ib==eid) 
			flag = 2;
		else if(me<bid&&ib>eid) 
			flag = 0;
		
		return flag;
	}

	@Override
	public boolean addSlot(String centerId, Date date, int bid, int eid) throws Exception {
		boolean flag = true;
		
		String sql = "insert into TIME_AVALIABLE(CENTER_ID,THE_DATE,Begin_Slot,End_Slot) values(?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setString(1, centerId);
			pstmt.setDate(2, date);
			pstmt.setInt(3, bid);
			pstmt.setInt(4, eid);
			
			int i = pstmt.executeUpdate();
			if(i==0) {
				flag = false;
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return flag;
	}

	@Override
	public boolean reverseSlot(Date date, int bid, int eid, String centerId) throws Exception {
		boolean flag = true;
		
		String sql = "update TIME_AVALIABLE set Begin_Slot=? where THE_DATE=? and Begin_Slot=? and CENTER_ID = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setInt(1, bid);
			pstmt.setDate(2, date);
			pstmt.setInt(3, eid);
			pstmt.setString(4, centerId);
			
			int i = pstmt.executeUpdate();
			if(i==0) {
				flag = false;
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean subMaintainance(Date date, int num, String centerId) throws Exception {
		boolean flag = true;
		int onum = 0;
		
		String sql = "update DATE_STATUS set Maintenance_Num=?-? where APP_DATE=? AND CENTER_ID = ?";
		PreparedStatement pstmt = null;
		String sql1 = "select m.Maintenance_Num from DATE_STATUS m where APP_DATE= ? AND CENTER_ID = ?";
		PreparedStatement pstmt1 = null ;   
        
        try {
			pstmt1 = Database.getConnection().prepareStatement(sql1);
			pstmt1.setDate(1, date);
			pstmt1.setString(2, centerId);
			
			
			ResultSet rs = pstmt1.executeQuery();
			
			if(rs.next()) {
				onum = rs.getInt("Maintenance_Num");
				
			}
			
			rs.close();
			pstmt1.close();
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setInt(1, onum);
			pstmt.setInt(2, num);
			pstmt.setDate(3, date);
			pstmt.setString(4, centerId);
			
			int i = pstmt.executeUpdate();
			if(i==0) {
				flag = false;
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
		return flag;
	}

	@Override
	public ArrayList<DateValue> getRAvaliableDate(int num, Date beginDate, String centerID) throws Exception {
		ArrayList<DateValue> possibleTime = new ArrayList<DateValue>();
		DateValue tempDate = null;
		
		String sql = "select * from (select * from TIME_AVALIABLE where End_Slot-Begin_Slot>=? AND CENTER_ID=? AND THE_DATE>? ORDER BY THE_DATE, Begin_Slot)Temp where ROWNUM < 3";
		PreparedStatement pstmt = null;
		
			
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, centerID);
			pstmt.setDate(3,beginDate);
			
			ResultSet rs = pstmt.executeQuery();
			 
			int a = 0;
			while(rs.next()) {
				
				tempDate = new DateValue();
				
				if((a == 0) && (rs.getInt("End_Slot")-rs.getInt("Begin_SLot"))>= 2*num ){
					tempDate.setDate(rs.getDate("THE_DATE"));
					tempDate.setbSlot(rs.getInt("Begin_Slot"));
					possibleTime.add(tempDate);
					tempDate = new DateValue();
					tempDate.setDate(rs.getDate("THE_DATE"));
					tempDate.setbSlot(rs.getInt("Begin_Slot")+num);
					possibleTime.add(tempDate);
					break;
				}
				tempDate.setDate(rs.getDate("THE_DATE"));
				tempDate.setbSlot(rs.getInt("Begin_Slot"));
			
				possibleTime.add(tempDate);
				a++;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return possibleTime;
	}

	@Override
	public boolean subSlot(Date date, int bid, int eid, String centerId) throws Exception {
		boolean flag = true;
		
		String sql = "update TIME_AVALIABLE set End_Slot=? where THE_DATE=? and Begin_Slot<=? and CENTER_ID=? and End_Slot=?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setInt(1, bid);
			pstmt.setDate(2, date);
			pstmt.setInt(3, bid);
			pstmt.setString(4, centerId);
			pstmt.setInt(5, eid);
			
			int i = pstmt.executeUpdate();
			if(i==0) {
				flag = false;
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return flag;
	}

	@Override
	public boolean seperateSlot(Date date, int bid, int eid, String centerId) throws Exception {
		boolean flag = true;
		int oeid = 0;
		
		String sql = "update TIME_AVALIABLE set End_Slot=? where THE_DATE=? and Begin_Slot<=? and CENTER_ID=? and End_Slot>=?";
		PreparedStatement pstmt = null;
		String sql1 = "select End_Slot from TIME_AVALIABLE where THE_DATE=? and Begin_Slot<=? and CENTER_ID=? and End_Slot>=?";
		PreparedStatement pstmt1 = null ;   
        String sql2 = "insert into TIME_AVALIABLE (CENTER_ID,THE_DATE, Begin_Slot, End_Slot) values(?,?,?,?)";
		PreparedStatement pstmt2 = null ;   
		
        try {
			pstmt1 = Database.getConnection().prepareStatement(sql1);
			pstmt1.setDate(1, date);
			pstmt1.setInt(2, bid);
			pstmt1.setString(3, centerId);
			pstmt1.setInt(4, eid);
			
			
			ResultSet rs = pstmt1.executeQuery();
			
			if(rs.next()) {
				oeid = rs.getInt("End_Slot");
				
			}
			
			rs.close();
			pstmt1.close();
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setInt(1, bid);
			pstmt.setDate(2, date);
			pstmt.setInt(3, bid);
			pstmt.setString(4, centerId);
			pstmt.setInt(5, eid);
			
			int i = pstmt.executeUpdate();
			if(i==0) {
				flag = false;
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		 try { 
	            pstmt2= Database.getConnection().prepareStatement(sql2) ; 
	            pstmt2.setString(1, centerId);
	            pstmt2.setDate(2,date) ;
	           	pstmt2.setInt(3,eid) ;
	           	pstmt2.setInt(4,oeid) ;
	            int j=pstmt2.executeUpdate();
	           	if(j==0){
	           		flag=false;          
	           	}
	            
	            pstmt2.close();
	        } catch (SQLException e) {
	         e.printStackTrace();
	        }
		
		return flag;
	}

	

	@Override
	public boolean integrateSlot(String centerId, Date date, int bid, int eid) throws Exception {
		boolean flag = true;
		int oeid = 0;
		
		String sql = "update TIME_AVALIABLE set End_Slot=? where THE_DATE=? and End_Slot=? and CENTER_ID = ?";
		PreparedStatement pstmt = null;
		String sql1 = "select End_Slot from TIME_AVALIABLE where THE_DATE=? and Begin_Slot=? and CENTER_ID=?";
		PreparedStatement pstmt1 = null ;   
        String sql2 = "delete from TIME_AVALIABLE where THE_DATE = ? AND Begin_Slot = ? AND CENTER_ID = ?";
		PreparedStatement pstmt2 = null ;   
		
        
        try {
			pstmt1 = Database.getConnection().prepareStatement(sql1);
			pstmt1.setDate(1, date);
			pstmt1.setInt(2, eid);
			pstmt1.setString(3, centerId);
			
			
			ResultSet rs = pstmt1.executeQuery();
			
			if(rs.next()) {
				oeid = rs.getInt("End_Slot");
				
			}
			
			rs.close();
			pstmt1.close();
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setInt(1, oeid);
			pstmt.setDate(2, date);
			pstmt.setInt(3, bid);
			pstmt.setString(4, centerId);
			
			int i = pstmt.executeUpdate();
			if(i==0) {
				flag = false;
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			pstmt2 = Database.getConnection().prepareStatement(sql2);
			pstmt2.setDate(1, date);
			pstmt2.setInt(2, eid);
			pstmt2.setString(3, centerId);
			int i = pstmt2.executeUpdate();
			if(i == 0)
				flag = false;
			pstmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean backSlot(Date date, int bid, int eid, String centerId) throws Exception {
		boolean flag = true;
		
		String sql = "update TIME_AVALIABLE set End_Slot=? where THE_DATE=? and End_Slot=? and CENTER_ID = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = Database.getConnection().prepareStatement(sql);
			pstmt.setInt(1, eid);
			pstmt.setDate(2, date);
			pstmt.setInt(3, bid);
			pstmt.setString(4, centerId);
			
			int i = pstmt.executeUpdate();
			if(i==0) {
				flag = false;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
