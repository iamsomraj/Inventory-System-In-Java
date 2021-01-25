package io.github.iamsomraj.inventory.model;

import java.util.Arrays;
import java.sql.Date;

public class PurchaseOrder {
	private int poNumber;
	private Date orderDate;
	private Date shipDate;
	private OrderItem[] orderItems;

	/**
	 * @return the poNumber
	 */
	public int getPoNumber() {
		return poNumber;
	}

	/**
	 * @param poNumber the poNumber to set
	 */
	public void setPoNumber(int poNumber) {
		this.poNumber = poNumber;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public PurchaseOrder(OrderItem[] orderItem) {
		super();
		this.setOrderItems(orderItem);
	}

	boolean isShipped() {
		if (getShipDate() == null) {
			return false;
		}
		return true;
	}

	double sumItems() {
		double sum = 0d;
		for (int i = 0; i < getOrderItems().length; i++) {
			OrderItem orderItem = getOrderItems()[i];
			sum = sum + orderItem.getTotal();
		}
		return sum;
	}

	public void setShipDate(Date date) {
		this.shipDate = date;
		System.out.println("Purchase order: " + poNumber + " shipped at: " + this.shipDate);
	}

	public void create(int poNumber, Date date) {
		this.poNumber = poNumber;
		this.orderDate = date;
		System.out.println("Purchase order: " + poNumber + " created at: " + this.orderDate);
	}

	OrderItem[] getItems() {
		return getOrderItems();
	}

	@Override
	public String toString() {
		return "PurchaseOrder [order id: " + poNumber + ", " + orderDate + " - " + getShipDate() + ", items: "
				+ Arrays.toString(getOrderItems()) + "]";
	}

	public Date getShipDate() {
		return shipDate;
	}

	public OrderItem[] getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(OrderItem[] orderItems) {
		this.orderItems = orderItems;
	}




}
