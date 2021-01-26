package io.github.iamsomraj.inventory.service;

import io.github.iamsomraj.inventory.dao.CustomerDao;
import io.github.iamsomraj.inventory.dao.OrderItemDao;
import io.github.iamsomraj.inventory.dao.PurchaseOrderDao;
import io.github.iamsomraj.inventory.dao.StockItemDao;
import io.github.iamsomraj.inventory.database.DatabaseUtil;
import io.github.iamsomraj.inventory.model.Customer;
import io.github.iamsomraj.inventory.model.OrderItem;
import io.github.iamsomraj.inventory.model.PurchaseOrder;
import io.github.iamsomraj.inventory.model.StockItem;

public class DatabaseService {

	CustomerDao customerDao = new CustomerDao();
	StockItemDao stockItemDao = new StockItemDao();
	PurchaseOrderDao purchaseOrderDao = new PurchaseOrderDao();
	OrderItemDao orderItemDao = new OrderItemDao();

	public DatabaseService() {
		super();
		DatabaseUtil.initialSetup();
		customerDao.createCustomer();
		stockItemDao.createStockItem();
		purchaseOrderDao.createPurchaseOrder();
		orderItemDao.createOrderItem();
	}

	public void insertCustomer(Customer customer) {
		customerDao.insertCustomer(customer);
	}

	public Customer getCustomerById(int id) {
		return customerDao.getCustomerById(id);
	}

	public void insertStockItem(StockItem stockItem) {
		stockItemDao.insertStockItem(stockItem);
	}

	public StockItem getStockItemByItemNumber(int itemNumber) {
		return stockItemDao.getStockItemByItemNumber(itemNumber);
	}

	public void insertPurchaseOrder(PurchaseOrder purchaseOrder, Customer customer) {
		purchaseOrderDao.insertPurchaseOrder(purchaseOrder, customer);
	}

	public PurchaseOrder getPurchaseOrderByNumber(int poNumber) {
		return purchaseOrderDao.getPurchaseOrderByNumber(poNumber);
	}

	public void insertOrderItem(OrderItem orderItem, PurchaseOrder purchaseOrder) {
		orderItemDao.insertOrderItem(orderItem, purchaseOrder);
	}

	public OrderItem getOrderItemByPurchaseOrderAndStockItem(PurchaseOrder purchaseOrder, StockItem stockItem) {
		return orderItemDao.getOrderItemByPurchaseOrderAndStockItem(purchaseOrder, stockItem);
	}

}
