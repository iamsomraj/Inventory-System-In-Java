package io.github.iamsomraj.inventory.service;

import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.github.iamsomraj.inventory.model.Customer;
import io.github.iamsomraj.inventory.model.InsufficientDataException;
import io.github.iamsomraj.inventory.model.OrderItem;
import io.github.iamsomraj.inventory.model.PurchaseOrder;

public class CustomerService {

	private List<Customer> customers = new ArrayList<Customer>();
	private String fileName = "src/io/github/iamsomraj/inventory/customer-info.txt";

	public Customer createCustomer(String str[]) {
		return new Customer(Integer.parseInt(str[0].trim()), str[1].trim());
	}

	public CustomerService() {
		super();
		try {
			FileReader file = new FileReader(fileName);
			int ch;
			String fileContent = "";
			while ((ch = file.read()) != -1) {
				fileContent += (char) ch;
			}
			if (fileContent.isBlank() || fileContent.isEmpty()) {
				file.close();
				throw new InsufficientDataException("Data is insufficient: Customer");
			}
			String persons[] = fileContent.split("\n");
			for (String cust : persons) {
				String values[] = cust.split(",");
				Customer fetchedItem = createCustomer(values);
				this.customers.add(fetchedItem);
			}
			System.out.println("Data fetched succesfully: Customer");
			file.close();
		} catch (Exception e) {
			System.out.println("Data fetch failed: Customer");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @return the customers
	 */
	public List<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers the customers to set
	 */
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public PurchaseOrder[] findOrdersPlacedByCustomer(int customerId) {
		Customer customer = null;
		for (Customer cust : customers) {
			if (cust.getId() == customerId) {
				customer = cust;
				break;
			}
		}
		return customer.getPurchaseOrders();
	}

	public PurchaseOrder[] findOrdersToBeShippedOn(Date findDate) {
		List<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();
		for (Customer cust : customers) {
			PurchaseOrder[] arrayOfOrders = cust.getPurchaseOrders();
			if (arrayOfOrders != null) {
				for (PurchaseOrder ord : arrayOfOrders) {
					if (ord.getShipDate().compareTo(findDate) == 0) {
						orders.add(ord);
					}
				}
			}
		}
		PurchaseOrder[] fetchedOrders = new PurchaseOrder[orders.size()];
		int k = 0;
		for (PurchaseOrder prd : orders) {
			fetchedOrders[k] = prd;
			k++;
		}
		return fetchedOrders;
	}

	public Set<String> getAreas() {
		Set<String> areas = new LinkedHashSet<String>();
		for (Customer cust : customers) {
			areas.add(cust.getState());
		}
		return areas;
	}

	public List<Integer> getCustomersByArea(String area) {
		List<Integer> custList = new ArrayList<Integer>();
		for (Customer cust : customers) {
			if (area.equals(cust.getState())) {
				custList.add(cust.getId());
			}
		}
		return custList;
	}

	public ArrayList<String> getItemsFromPurchaseOrder(PurchaseOrder[] arr) {
		ArrayList<String> listOfItems = new ArrayList<String>();
		if (arr == null) {
			return null;
		}
		for (int i = 0; i < arr.length; i++) {
			PurchaseOrder purchaseOrder = arr[i];
			OrderItem[] orderItems = purchaseOrder.getOrderItems();
			for (int j = 0; j < orderItems.length; j++) {
				OrderItem orderItem = orderItems[j];
				listOfItems.add(orderItem.getStockItem().getItemDescription());
			}
		}
		return listOfItems;
	}

	public LinkedHashMap<String, LinkedHashMap<Integer, ArrayList<String>>> segregateOrderAndCustomerByArea() {
		Set<String> areas = getAreas();
		LinkedHashMap<String, LinkedHashMap<Integer, ArrayList<String>>> result = new LinkedHashMap<String, LinkedHashMap<Integer, ArrayList<String>>>();
		for (String ar : areas) {
			List<Integer> customerList = getCustomersByArea(ar);
			LinkedHashMap<Integer, ArrayList<String>> map = new LinkedHashMap<Integer, ArrayList<String>>();
			for (Integer id : customerList) {
				ArrayList<String> items = getItemsFromPurchaseOrder(findOrdersPlacedByCustomer(id));
				map.put(id, items);
			}
			result.put(ar, map);
		}
		return result;
	}

	public Customer getCustomerById(Integer id) {
		for (Customer cust : customers) {
			if (id == cust.getId()) {
				return cust;
			}
		}
		return null;
	}

	public LinkedHashMap<String, Double> getAreaWiseTotalBill() {
		LinkedHashMap<String, Double> result = new LinkedHashMap<String, Double>();
		LinkedHashMap<String, LinkedHashMap<Integer, ArrayList<String>>> map = segregateOrderAndCustomerByArea();
		for (String area : map.keySet()) {
			double totalBill = 0d;
			LinkedHashMap<Integer, ArrayList<String>> idAndItem = map.get(area);
			for (Integer id : idAndItem.keySet()) {
				Customer cust = getCustomerById(id);
				totalBill += cust.getTotalSales();
			}
			result.put(area, totalBill);
		}
		return result;
	}

}
