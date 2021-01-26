package io.github.iamsomraj.inventory.model;

public class OrderItem {
	private int numberOfItems;
	private StockItem stockItem;

	/**
	 * @return the numberOfItems
	 */
	public int getNumberOfItems() {
		return numberOfItems;
	}

	/**
	 * @param numberOfItems the numberOfItems to set
	 */
	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	
	public OrderItem() {
		super();
	}

	public OrderItem(int numberOfItems, StockItem stockItem) {
		super();
		this.numberOfItems = numberOfItems;
		this.setStockItem(stockItem);
		this.getStockItem().setQuantity(this.getStockItem().getQuantity() - numberOfItems);
		System.out.println("Order Item created for: " + this.numberOfItems + " " + this.getStockItem().getUnit()
				+ " of " + this.getStockItem().getItemDescription());
		System.out.println("In inventory, stocks left: " + this.getStockItem().getQuantity());
	}

	double getTotal() {
		return getStockItem().getItemPrice() * numberOfItems;
	}

	@Override
	public String toString() {
		return "OrderItem [items: " + numberOfItems + ", stock item: " + getStockItem() + ", total price: " + getTotal()
				+ "]";
	}

	public StockItem getStockItem() {
		return stockItem;
	}

	public void setStockItem(StockItem stockItem) {
		this.stockItem = stockItem;
	}

}
