package cars;

import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Menu {
	public static void openScanner(InputStream src) {
		try {
			scanner = new Scanner(src);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void closeScanner() {
		try {
			scanner.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String getline() {
		return scanner.nextLine().trim();
	}
	
	public static void setRole(String r) {
		role = r;
	}
	
	public static String getRole() {
		return role;
	}
	
	public static void setUserID(String u) {
		userID = u;
	}
	
	public static String getUserID() {
		return userID;
	}
	
	public static void setCenterID(String c) {
		centerID = c;
	}
	
	public static String getCenterID() {
		return centerID;
	}
	
	public static int input() {
		do {
			reply = getline();
		} while (!isInteger(reply));
		return Integer.parseInt(reply);
	}
	
	public static void print(String[] opts) {
		for (String opt : opts) {
			System.out.println(opt);
		}
	}
	
	private static boolean isInteger(String reply) {
		return reply != null && reply.length() > 0 &&
		       IntStream.range(0, reply.length()).allMatch(i -> i < MAX_NUM_DIGITS && Character.isDigit(reply.charAt(i)));
	}
	
	private static final int MAX_NUM_DIGITS = 2;
	private static String reply = "";
	private static String role = "Undefined";
	private static String userID = "";
	private static String centerID = "";
	private static Scanner scanner = null;
}