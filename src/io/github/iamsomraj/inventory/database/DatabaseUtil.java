package io.github.iamsomraj.inventory.database;

import java.io.IOException;
import java.sql.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import io.github.iamsomraj.inventory.service.CustomerService;


public class DatabaseUtil {

	private static Connection conn = null;
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String username = "root";
	private static String password = "1234";
	private static String database = "inventory";
	private static String options = "?autoReconnect=true&useSSL=false";

	static Logger logger = Logger.getLogger(DatabaseUtil.class.getName());

	static {
		try {
			logger.addHandler(new FileHandler(DatabaseUtil.class.getSimpleName() + "-logs.xml", true));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection createConnection() {
		if (conn == null) {
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url + database + options, username, password);
				System.out.println("Database connected:");
				System.out.println("Username: " + username);
				System.out.println("Database name: " + database);
				System.out.println();
				return conn;
			} catch (Exception e) {
				logger.info(e.getMessage());
				System.out.println("Database: cannot connect");
			}
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				System.out.println("Database disconnected!");
				conn.close();
			} catch (Exception e) {
				logger.info(e.getMessage());
				System.out.println("Database: cannot close");
			}
		}
	}

	public static void quickTest() {
		try {
			if (conn != null) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from dummy");
				while (rs.next()) {
					System.out.println(rs.getString(1) + "  " + rs.getString(2));
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println("Database: quick test cannot be performed");
		}
	}

	public static void initialSetup() {
		try {
			if (conn != null) {
				PreparedStatement stmt = conn.prepareStatement("drop database " + database);
				stmt.executeUpdate();
				stmt = conn.prepareStatement("create database " + database);
				stmt.executeUpdate();
				stmt = conn.prepareStatement("use " + database);
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println("Database: initial setup cannot be performed");
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