package io.github.iamsomraj.inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import io.github.iamsomraj.inventory.database.DatabaseUtil;
import io.github.iamsomraj.inventory.model.OrderItem;
import io.github.iamsomraj.inventory.model.PurchaseOrder;
import io.github.iamsomraj.inventory.model.StockItem;

public class OrderItemDao {
	Connection conn = DatabaseUtil.createConnection();
	private static String tableName = "order_items".toUpperCase();

	public void init() {
		dropOrderItem();
		createOrderItem();
	}

	public void createOrderItem() {
		try {
			PreparedStatement statement = conn.prepareStatement("" + "" + "CREATE TABLE " + tableName + " (\r\n"
					+ "    noOfItems INT(10),\r\n" + "    poNumber INT(10),\r\n" + "    itemNumber INT(10),\r\n"
					+ "    FOREIGN KEY (poNumber)\r\n" + "        REFERENCES " + PurchaseOrderDao.getTableName()
					+ " (poNumber),\r\n" + "    FOREIGN KEY (itemNumber)\r\n" + "        REFERENCES "
					+ StockItemDao.getTableName() + " (itemNumber)\r\n" + ");\r\n" + "" + "");
			statement.executeUpdate();
			System.out.println(tableName + " created!");

		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("Failed to create " + tableName);
		}
	}

	public void dropOrderItem() {
		try {
			PreparedStatement statement = conn.prepareStatement("drop table " + tableName);
			statement.executeUpdate();
			System.out.println(tableName + " dropped!");
		} catch (Exception e) {
//			System.out.println("Failed to drop " + tableName);
		}
	}

	public void insertOrderItem(OrderItem orderItem, PurchaseOrder purchaseOrder) {
		try {
			PreparedStatement statement = conn.prepareStatement("insert into " + tableName + " values(?,?,?)");
			statement.setInt(1, orderItem.getNumberOfItems());
			statement.setInt(2, purchaseOrder.getPoNumber());
			statement.setInt(3, orderItem.getStockItem().getItemNumber());
			statement.executeUpdate();
			System.out.println(
					"Order Item for " + orderItem.getStockItem().getItemDescription() + " is inserted in " + tableName);
		} catch (Exception e) {
			System.out.println("Failed to insert in " + tableName);
		}

	}

	public OrderItem getOrderItemByPurchaseOrderAndStockItem(PurchaseOrder purchaseOrder, StockItem stockItem) {
		try {
			Statement statement = conn.createStatement();
			String query = "select * from " + tableName + " where poNumber=" + purchaseOrder.getPoNumber()
					+ " and itemNumber=" + stockItem.getItemNumber();
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setNumberOfItems(rs.getInt(1));
				System.out.println("Order Item with number of items " + orderItem.getNumberOfItems() + ": ");
				System.out.println("Purchase Order Number: " + rs.getInt(2));
				System.out.println("Stock Item Number: " + rs.getInt(3));
				return orderItem;
			}
		} catch (Exception e) {
			System.out.println("Failed to fetch order item from " + tableName);
		}
		return null;
	}
}
