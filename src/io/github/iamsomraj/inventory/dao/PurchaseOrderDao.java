package io.github.iamsomraj.inventory.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import io.github.iamsomraj.inventory.database.DatabaseUtil;
import io.github.iamsomraj.inventory.model.Customer;
import io.github.iamsomraj.inventory.model.PurchaseOrder;

public class PurchaseOrderDao {

	Connection conn = DatabaseUtil.createConnection();
	private static String tableName = "purchase_orders".toUpperCase();

	static Logger logger = Logger.getLogger(PurchaseOrderDao.class.getName());

	static {
		try {
			logger.addHandler(new FileHandler(PurchaseOrderDao.class.getSimpleName() + "-logs.xml", true));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the tableName
	 */
	public static String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public static void setTableName(String tableName) {
		PurchaseOrderDao.tableName = tableName;
	}

	public void init() {
		dropPurchaseOrder();
		createPurchaseOrder();
	}

	public void createPurchaseOrder() {
		try {
			PreparedStatement statement = conn.prepareStatement("" + "\r\n" + "CREATE TABLE " + tableName + " (\r\n"
					+ "    poNumber INT(10) PRIMARY KEY,\r\n" + "    orderDate DATE,\r\n" + "    shipDate DATE,\r\n"
					+ "    customerId INT(10),\r\n" + "    FOREIGN KEY (customerId)\r\n" + "        REFERENCES "
					+ CustomerDao.getTableName() + " (id)\r\n" + ");" + "");
			statement.executeUpdate();
			System.out.println(tableName + " created!");

		} catch (Exception e) {
			logger.info(e.getMessage());
//			System.out.println("Failed to create " + tableName);
		}
	}

	public void dropPurchaseOrder() {
		try {
			PreparedStatement statement = conn.prepareStatement("drop table " + tableName);
			statement.executeUpdate();
			System.out.println(tableName + " dropped!");
		} catch (Exception e) {
			logger.info(e.getMessage());
//			System.out.println("Failed to drop " + tableName);
		}
	}

	public void insertPurchaseOrder(PurchaseOrder purchaseOrder, Customer customer) {
		try {
			PreparedStatement statement = conn.prepareStatement("insert into " + tableName + " values(?,?,?,?)");
			statement.setInt(1, purchaseOrder.getPoNumber());
			statement.setDate(2, purchaseOrder.getOrderDate());
			statement.setDate(3, purchaseOrder.getShipDate());
			statement.setInt(4, customer.getId());
			statement.executeUpdate();
			System.out.println("Purchase Order " + purchaseOrder.getPoNumber() + " is inserted in " + tableName);
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println("Failed to insert in " + tableName);
		}

	}

	public PurchaseOrder getPurchaseOrderByNumber(int poNumber) {
		try {
			Statement statement = conn.createStatement();
			String query = "select * from " + tableName + " where poNumber=" + poNumber;
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				PurchaseOrder purchaseOrder = new PurchaseOrder();
				purchaseOrder.setPoNumber(rs.getInt(1));
				purchaseOrder.setOrderDate(rs.getDate(2));
				purchaseOrder.setShipDate(rs.getDate(3));
				System.out.println("Purchase Order with number " + poNumber + ": ");
				System.out.println(purchaseOrder);
				System.out.println("Order made by customer with id: " + rs.getInt(4));
				return purchaseOrder;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			System.out.println("Failed to fetch purchase order with number " + poNumber + " in " + tableName);
		}
		return null;
	}
}
