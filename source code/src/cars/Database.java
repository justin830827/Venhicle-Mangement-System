package cars;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	public static void openDatabase(String url, String username, String password) {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	public static void closeDatabase() {
		try {
			conn.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static Connection conn = null;
}