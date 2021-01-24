package io.github.iamsomraj.inventory.system;

import java.util.Arrays;
import java.sql.Date;

public class PurchaseOrder {
	int poNumber;
	Date orderDate;
	Date shipDate;
	OrderItem[] orderItems;

	public PurchaseOrder(OrderItem[] orderItem) {
		super();
		this.orderItems = orderItem;
	}

	boolean isShipped() {
		if (shipDate == null) {
			return false;
		}
		return true;
	}

	double sumItems() {
		double sum = 0d;
		for (int i = 0; i < orderItems.length; i++) {
			OrderItem orderItem = orderItems[i];
			sum = sum + orderItem.getTotal();
		}
		return sum;
	}

	void setShipDate(Date date) {
		this.shipDate = date;
		System.out.println("Purchase order: " + poNumber + " shipped at: " + this.shipDate);
	}

	void create(int poNumber, Date date) {
		this.poNumber = poNumber;
		this.orderDate = date;
		System.out.println("Purchase order: " + poNumber + " created at: " + this.orderDate);
	}

	OrderItem[] getItems() {
		return orderItems;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [order id: " + poNumber + ", " + orderDate + " - " + shipDate + ", items: "
				+ Arrays.toString(orderItems) + "]";
	}

}
