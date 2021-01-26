package io.github.iamsomraj.inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import io.github.iamsomraj.inventory.database.DatabaseUtil;
import io.github.iamsomraj.inventory.model.StockItem;
import io.github.iamsomraj.inventory.model.StockItem.Unit;

public class StockItemDao {
	Connection conn = DatabaseUtil.createConnection();
	private static String tableName = "stock_items".toUpperCase();

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
		StockItemDao.tableName = tableName;
	}

	public void init() {
		dropStockItem();
		createStockItem();
	}

	public void createStockItem() {
		try {
			PreparedStatement statement = conn.prepareStatement(
					"" + "" + "CREATE TABLE " + tableName + "(\r\n" + "    itemNumber INT(10) PRIMARY KEY,\r\n"
							+ "    itemDescription VARCHAR(20),\r\n" + "    itemPrice DOUBLE(10 , 2 ),\r\n"
							+ "    quantity INT(10),\r\n" + "    unit VARCHAR(20)\r\n" + ");" + "");
			statement.executeUpdate();
			System.out.println(tableName + " created!");

		} catch (Exception e) {
			System.out.println("Failed to create " + tableName);
		}
	}

	public void dropStockItem() {
		try {
			PreparedStatement statement = conn.prepareStatement("drop table " + tableName);
			statement.executeUpdate();
			System.out.println(tableName + " dropped!");
		} catch (Exception e) {
			System.out.println("Failed to drop " + tableName);
		}
	}

	public void insertStockItem(StockItem stockItem) {
		try {
			PreparedStatement statement = conn.prepareStatement("insert into " + tableName + " values(?,?,?,?,?)");
			statement.setInt(1, stockItem.getItemNumber());
			statement.setString(2, stockItem.getItemDescription());
			statement.setDouble(3, stockItem.getItemPrice());
			statement.setInt(4, stockItem.getQuantity());
			statement.setString(5, stockItem.getUnit());
			statement.executeUpdate();
			System.out.println(stockItem.getItemDescription() + " is inserted in " + tableName);
		} catch (Exception e) {
			System.out.println("Failed to insert in " + tableName);
		}

	}

	public Unit createUnit(String str) {
		Unit unit = null;
		switch (str.trim()) {
		case "kilograms":
			unit = Unit.KG;
			break;
		case "gallons":
			unit = Unit.GALLON;
			break;
		case "numbers":
			unit = Unit.NO;
			break;
		case "grams":
			unit = Unit.GM;
			break;
		}
		return unit;
	}

	public StockItem getStockItemByItemNumber(int itemNumber) {
		try {
			Statement statement = conn.createStatement();
			String query = "select * from " + tableName + " where itemNumber=" + itemNumber;
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				StockItem stockItem = new StockItem(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4),
						createUnit(rs.getString(5)));
				return stockItem;
			}
		} catch (Exception e) {
			System.out.println("Failed to fetch stockitem with itemNumber " + itemNumber + " in " + tableName);
		}
		return null;
	}

}
