package io.github.iamsomraj.inventory.system;

import java.util.Arrays;

public class Customer {
	int id;
	String name, homePhone, cellPhone, workPhone, street, city, state, zip;
	PurchaseOrder[] purchaseOrders;

	public Customer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/**
	 * @param homePhone the homePhone to set
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * @return the cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * @param cellPhone the cellPhone to set
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	/**
	 * @return the workPhone
	 */
	public String getWorkPhone() {
		return workPhone;
	}

	/**
	 * @param workPhone the workPhone to set
	 */
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the purchaseOrders
	 */
	public PurchaseOrder[] getPurchaseOrders() {
		return purchaseOrders;
	}

	/**
	 * @param purchaseOrders the purchaseOrders to set
	 */
	public void setPurchaseOrders(PurchaseOrder[] purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	@Override
	public String toString() {
		return "Customer [id: " + id + ", " + name + ", phones: " + homePhone + " / " + cellPhone + " / " + workPhone
				+ ", address: " + street + " " + city + " " + state + " " + zip + ", orders: "
				+ Arrays.toString(purchaseOrders) + "]";
	}

	void printPhoneNumbers() {
		System.out.println(
				"Customer [ homePhone=" + homePhone + ", cellPhone=" + cellPhone + ", workPhone=" + workPhone + "]");
	}

	void printShippingAddress() {
		System.out.println("Customer [street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + "]");
	}

	void setPrintingAddress(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	void setPhoneNumbers(String homePhone, String cellPhone, String workPhone) {
		this.homePhone = homePhone;
		this.cellPhone = cellPhone;
		this.workPhone = workPhone;
	}

	void printCustomerFields() {
		System.out.println(this.toString());
	}

	void print() {
		printCustomerFields();
	}

	void printInvoice() {
		System.out.println("Invoice for " + name.toUpperCase() + ": ");
		if (purchaseOrders != null) {
			for (int i = 0; i < purchaseOrders.length; i++) {
				PurchaseOrder purchaseOrder = purchaseOrders[i];
				System.out.println(
						purchaseOrder.orderDate + " - " + purchaseOrder.shipDate + " $" + purchaseOrder.sumItems());
			}
		}
		System.out.println("Total: $" + getTotalSales());
	}

	double getTotalSales() {
		double sum = 0;
		if (purchaseOrders == null) {
			return 0d;
		}
		for (int i = 0; i < purchaseOrders.length; i++) {
			PurchaseOrder purchaseOrder = purchaseOrders[i];
			sum += purchaseOrder.sumItems();
		}
		return sum;
	}

	PurchaseOrder[] getPurchaseOrder() {
		return purchaseOrders;
	}

	void setPurchaseOrder(PurchaseOrder[] orders) {
		this.purchaseOrders = orders;
	}

}