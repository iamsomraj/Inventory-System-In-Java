package io.github.iamsomraj.inventory.system;

public class OrderItem {
	int numberOfItems;
	StockItem stockItem;

	public OrderItem(int numberOfItems, StockItem stockItem) {
		super();
		this.numberOfItems = numberOfItems;
		this.stockItem = stockItem;
		this.stockItem.quantity = this.stockItem.quantity - numberOfItems;
		System.out.println("Order Item created for: " + this.numberOfItems + " " + this.stockItem.unit + " of "
				+ this.stockItem.itemDescription);
		System.out.println("In inventory, stocks left: " + this.stockItem.quantity);
	}

	double getTotal() {
		return stockItem.itemPrice * numberOfItems;
	}

	@Override
	public String toString() {
		return "OrderItem [items: " + numberOfItems + ", stock item: " + stockItem + ", total price: " + getTotal()
				+ "]";
	}

}
