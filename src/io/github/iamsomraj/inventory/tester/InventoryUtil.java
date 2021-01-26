package io.github.iamsomraj.inventory.tester;

import java.util.Arrays;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import io.github.iamsomraj.inventory.database.DatabaseUtil;
import io.github.iamsomraj.inventory.model.Customer;
import io.github.iamsomraj.inventory.model.OrderItem;
import io.github.iamsomraj.inventory.model.PurchaseOrder;
import io.github.iamsomraj.inventory.model.StockItem;
import io.github.iamsomraj.inventory.service.CustomerService;
import io.github.iamsomraj.inventory.service.DatabaseService;
import io.github.iamsomraj.inventory.service.StockItemService;

/**
 * Date 15.1.21 Assignment Create StockItem class
 * 
 * Create PurchaseOrder class
 * 
 * Create OrderItem class
 * 
 * Create Test class that does the following - 
 * 
 * Create 3 customers - Jamie, Bill, Joe
 * 
 * Create 5 items - Milk, Chicken, Egg, Apple and Orange
 * 
 * Create 3 Purchase Orders. 2 for Jamie, 1 for Bill and None for Joe.
 * 
 * PO 1 - 2 gallons Milk, 2 lbs Chicken and 12 eggs.
 * 
 * PO 2 - 5 apples and 10 oranges
 * 
 * PO 3 - 5 Lbs chicken and 10 apples
 * 
 * Get all the orders to be shipped and print labels
 * 
 * Ship first order for Jamie
 * 
 * Ship Bills order
 * 
 * Ship Jamie's order
 * 
 * Print total sales by customer
 * 
 * Print all invoices (include customer, Order date, line items with count and
 * price, sum for the invoice at the bottom)
 * 
 * 
 * @author Somraj Mukherjee
 *
 */
public class InventoryUtil {

	public static void printLines() {
		System.out.println();
		System.out.println();
		System.out.println();
	}

	public static String generateNumber() {
		return String.valueOf((long) Math.floor(Math.random() * 1000000000L + 7000000000L));
	}

	public static void main(String[] args) {

		System.out.println("Somraj's Inventory System:");

		printLines();

		CustomerService customerService = new CustomerService();
		List<Customer> customerList = customerService.getCustomers();

		printLines();

		Customer jamie = customerList.get(0);
		Customer bill = customerList.get(1);
		Customer joe = customerList.get(2);
		Customer somraj = customerList.get(3);

		StockItemService stockItemService = new StockItemService();
		List<StockItem> listOfStockItems = stockItemService.getStockItems();

		printLines();

		StockItem milk = listOfStockItems.get(0);
		StockItem chicken = listOfStockItems.get(1);
		StockItem egg = listOfStockItems.get(2);
		StockItem apple = listOfStockItems.get(3);
		StockItem orange = listOfStockItems.get(4);

		OrderItem milkOrder1 = new OrderItem(12, milk);
		OrderItem chickenOrder1 = new OrderItem(2, chicken);
		OrderItem eggOrder1 = new OrderItem(12, egg);

		OrderItem[] orders1 = new OrderItem[] { milkOrder1, chickenOrder1, eggOrder1 };
		PurchaseOrder purchaseOrder1 = new PurchaseOrder(orders1);

		OrderItem appleOrder2 = new OrderItem(5, apple);
		OrderItem orangeOrder2 = new OrderItem(10, orange);

		OrderItem[] orders2 = new OrderItem[] { appleOrder2, orangeOrder2 };
		PurchaseOrder purchaseOrder2 = new PurchaseOrder(orders2);

		OrderItem chickenOrder3 = new OrderItem(5, chicken);
		OrderItem appleOrder3 = new OrderItem(10, apple);

		OrderItem[] orders3 = new OrderItem[] { chickenOrder3, appleOrder3 };
		PurchaseOrder purchaseOrder3 = new PurchaseOrder(orders3);

		OrderItem egg4 = new OrderItem(10, egg);

		OrderItem[] orders4 = new OrderItem[] { egg4 };
		PurchaseOrder purchaseOrder4 = new PurchaseOrder(orders4);

		printLines();

		purchaseOrder1.create(1, Date.valueOf("2021-1-5"));
		purchaseOrder2.create(2, Date.valueOf("2021-1-7"));
		purchaseOrder3.create(3, Date.valueOf("2021-1-9"));
		purchaseOrder4.create(4, Date.valueOf("2021-1-11"));

		printLines();

		purchaseOrder1.shipOrder(Date.valueOf("2021-1-11"));
		purchaseOrder2.shipOrder(Date.valueOf("2021-1-20"));
		purchaseOrder3.shipOrder(Date.valueOf("2021-1-25"));
		purchaseOrder4.shipOrder(Date.valueOf("2021-1-31"));

		printLines();

		System.out.println("Purchase Orders are displayed: ");
		System.out.println(purchaseOrder1);
		System.out.println(purchaseOrder2);
		System.out.println(purchaseOrder3);
		System.out.println(purchaseOrder4);

		jamie.setPhoneNumbers(generateNumber(), generateNumber(), generateNumber());
		jamie.setPrintingAddress("Rose Gardens", "Asansol", "West Bengal", "700523");
		bill.setPhoneNumbers(generateNumber(), generateNumber(), generateNumber());
		bill.setPrintingAddress("Andheri", "Mumbai", "Maharashtra", "4054541");
		joe.setPhoneNumbers(generateNumber(), generateNumber(), generateNumber());
		joe.setPrintingAddress("Patna", "Patna City", "Bihar", "962554");
		somraj.setPhoneNumbers(generateNumber(), generateNumber(), generateNumber());
		somraj.setPrintingAddress("Sodepur", "Kolkata", "West Bengal", "700110");

		jamie.setPurchaseOrder(new PurchaseOrder[] { purchaseOrder1, purchaseOrder3 });
		bill.setPurchaseOrder(new PurchaseOrder[] { purchaseOrder2 });
		somraj.setPurchaseOrder(new PurchaseOrder[] { purchaseOrder4 });

		printLines();

		System.out.println("Total sales of a customer: ");
		for (Customer cust : new Customer[] { jamie, bill, joe, somraj }) {
			System.out.println(cust.getName().toUpperCase() + ":\t$" + cust.getTotalSales());
		}

		printLines();

		System.out.println("Customers: ");
		for (Customer cust : new Customer[] { jamie, bill, joe, somraj }) {
			System.out.println(cust);
		}

		printLines();

		System.out.println("Invoices: ");
		for (Customer cust : new Customer[] { jamie, bill, joe, somraj }) {
			cust.printInvoice();
		}

		printLines();

		System.out.println("Find orders placed by customer: ");
		int findId = 1;
		System.out.println("Orders of a customer with id " + findId + ": ");
		System.out.println(Arrays.toString(customerService.findOrdersPlacedByCustomer(findId)));

		printLines();

		System.out.println("Find orders placed on a date: ");
		Date findDate = Date.valueOf("2021-1-20");
		System.out.println("Orders shipped on " + findDate + ": ");
		System.out.println(Arrays.toString(customerService.findOrdersToBeShippedOn(findDate)));

		printLines();

		System.out.println("Segregated Orders with Area: Customer Id = { Items }: ");
		System.out.println(customerService.segregateOrderAndCustomerByArea());

		printLines();

		System.out.println("Area wise total bill amount: ");
		System.out.println(customerService.getAreaWiseTotalBill());
		
		printLines();

		customerService.printAllShippedOrdersForLastMonth();

		printLines();

		System.out.println("Total sale done for the last month (" + LocalDate.now().minusMonths(1) + " - " + LocalDate.now() + "): ");
		System.out.println(customerService.totalSaleDoneInLastMonth());
		
		printLines();
		
		customerService.printAllItemToBeShippenInCurrentWeek();
		
		printLines();

		printLines();

		DatabaseService databaseService = new DatabaseService();

		printLines();

		System.out.println("Storing all the customer details in database: ");
		for (Customer cust : new Customer[] { jamie, bill, joe, somraj }) {
			databaseService.insertCustomer(cust);
		}

		printLines();

		System.out.println("Fetching all the customer details from database with id: ");
		for (Customer cust : new Customer[] { jamie, bill, joe, somraj }) {
			databaseService.getCustomerById(cust.getId());
		}

		printLines();

		System.out.println("Storing all the stock items details in database: ");
		for (StockItem stockItem : new StockItem[] { milk, chicken, egg, apple, orange }) {
			databaseService.insertStockItem(stockItem);
		}

		printLines();

		System.out.println("Fetching all the stockitem details from database with item number: ");
		for (StockItem stockItem : new StockItem[] { milk, chicken, egg, apple, orange }) {
			databaseService.getStockItemByItemNumber(stockItem.getItemNumber());
		}

		printLines();

		System.out.println("Storing all the purchase order details in database: ");
		for (Customer cust : new Customer[] { jamie, bill, joe, somraj }) {
			if (cust.getPurchaseOrders() != null) {
				for (PurchaseOrder po : cust.getPurchaseOrders()) {
					databaseService.insertPurchaseOrder(po, cust);
				}
			}
		}

		printLines();

		System.out.println("Fetching all the purchase order details from database: ");
		for (Customer cust : new Customer[] { jamie, bill, joe, somraj }) {
			if (cust.getPurchaseOrders() != null) {
				for (PurchaseOrder po : cust.getPurchaseOrders()) {
					databaseService.getPurchaseOrderByNumber(po.getPoNumber());
				}
			}
		}

		printLines();

		System.out.println("Storing all the order item details in database: ");
		for (Customer cust : new Customer[] { jamie, bill, joe, somraj }) {
			if (cust.getPurchaseOrders() != null) {
				for (PurchaseOrder po : cust.getPurchaseOrders()) {
					if (po.getOrderItems() != null) {
						for (OrderItem orderItem : po.getOrderItems()) {
							databaseService.insertOrderItem(orderItem, po);
						}
					}
				}
			}
		}

		printLines();

		System.out.println("Fetching all the order item details from database: ");
		for (Customer cust : new Customer[] { jamie, bill, joe, somraj }) {
			if (cust.getPurchaseOrders() != null) {
				for (PurchaseOrder po : cust.getPurchaseOrders()) {
					if (po.getOrderItems() != null) {
						for (OrderItem orderItem : po.getOrderItems()) {
							databaseService.getOrderItemByPurchaseOrderAndStockItem(po, orderItem.getStockItem());
						}
					}
				}
			}
		}

		printLines();
		
		DatabaseUtil.closeConnection();
	}

}
