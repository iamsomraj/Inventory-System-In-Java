package io.github.iamsomraj.inventory.database;

import java.sql.*;

class DatabaseUtil {

	private static Connection conn = null;
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String username = "root";
	private static String password = "1234";
	private static String database = "inventory";
	private static String options = "?autoReconnect=true&useSSL=false";

	public static Connection createConnection() {
		if (conn == null) {
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url + database + options, username, password);
				System.out.println("Database connected:");
				System.out.println("Username: " + username);
				System.out.println("Database name: " + database);
				return conn;
			} catch (Exception e) {
				System.out.println("Database: cannot connect");
			}
		}
		return null;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				System.out.println("Database disconnected!");
				conn.close();
			} catch (Exception e) {
				System.out.println("Database: cannot close");
			}
		}
	}

	public static void quickTest() {
		try {
			if ( conn != null) {				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from dummy");
				while (rs.next()) {
					System.out.println(rs.getString(1) + "  " + rs.getString(2));
				}
			}
		} catch (Exception e) {
			System.out.println("Database: quick test cannot be performed");
		}
	}

	public static void main(String args[]) {
		try {
//			conn = createConnection();
//			quickTest();
//			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}