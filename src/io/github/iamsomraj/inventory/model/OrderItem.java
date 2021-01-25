package io.github.iamsomraj.inventory.model;

public class OrderItem {
	private int numberOfItems;
	private StockItem stockItem;

	public OrderItem(int numberOfItems, StockItem stockItem) {
		super();
		this.numberOfItems = numberOfItems;
		this.setStockItem(stockItem);
		this.getStockItem().quantity = this.getStockItem().quantity - numberOfItems;
		System.out.println("Order Item created for: " + this.numberOfItems + " " + this.getStockItem().unit + " of "
				+ this.getStockItem().getItemDescription());
		System.out.println("In inventory, stocks left: " + this.getStockItem().quantity);
	}

	double getTotal() {
		return getStockItem().itemPrice * numberOfItems;
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
