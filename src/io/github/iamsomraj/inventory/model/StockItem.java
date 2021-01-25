package io.github.iamsomraj.inventory.model;

public class StockItem {
	private int itemNumber;
	private String itemDescription;
	private double itemPrice;
	private int quantity;
	private String unit;

	/**
	 * @return the itemNumber
	 */
	public int getItemNumber() {
		return itemNumber;
	}

	/**
	 * @param itemNumber the itemNumber to set
	 */
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	/**
	 * @return the itemPrice
	 */
	public double getItemPrice() {
		return itemPrice;
	}

	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public enum Unit {
		KG, GALLON, NO, GM;
	}

	public StockItem(int itemNumber, String itemDescription, double itemPrice, int quantity, Unit unit) {
		super();
		this.itemNumber = itemNumber;
		this.setItemDescription(itemDescription);
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		if (unit == Unit.KG) {
			this.unit = "kilograms";
		} else if (unit == Unit.GALLON) {
			this.unit = "gallons";
		} else if (unit == Unit.GM) {
			this.unit = "grams";
		} else if (unit == Unit.NO) {
			this.unit = "numbers";
		}
		System.out.println(this.getItemDescription() + "\twith id: " + this.itemNumber + " is available at $"
				+ this.itemPrice + " for " + this.quantity + " (" + this.unit + ")");
	}

	@Override
	public String toString() {
		return "StockItem [id: " + itemNumber + ", desc: " + getItemDescription() + ", price: " + itemPrice + ", qty: "
				+ quantity + " " + unit + "]";
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

}
