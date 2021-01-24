package io.github.iamsomraj.inventory.system;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import io.github.iamsomraj.inventory.system.StockItem.Unit;

public class StockItemService {

	private List<StockItem> stockItems = new ArrayList<StockItem>();
	private String fileName = "src/io/github/iamsomraj/inventory/system/data.txt";

	public StockItem createStockItem(String str[]) {

		Unit unit = null;
		switch (str[4].trim()) {
		case "KG":
			unit = Unit.KG;
			break;
		case "GALLON":
			unit = Unit.GALLON;
			break;
		case "NO":
			unit = Unit.NO;
			break;
		case "GM":
			unit = Unit.GM;
			break;
		}
		StockItem stockItem = new StockItem(Integer.parseInt(str[0]), str[1], Double.parseDouble(str[2]),
				Integer.parseInt(str[3]), unit);
		return stockItem;
	}

	public StockItemService() {
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
				throw new InsufficientDataException("Data is insufficient: Stock Item");
			}
			String items[] = fileContent.split("\n");
			for (String item : items) {
				String values[] = item.split(",");
				StockItem fetchedItem = createStockItem(values);
				this.stockItems.add(fetchedItem);
			}
			System.out.println("Data fetched succesfully: Stock Item");
			file.close();
		} catch (Exception e) {
			System.out.println("Data fetch failed: Stock Item");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @return the stockItems
	 */
	public List<StockItem> getStockItems() {
		return stockItems;
	}

	/**
	 * @param stockItems the stockItems to set
	 */
	public void setStockItems(List<StockItem> stockItems) {
		this.stockItems = stockItems;
	}

}
