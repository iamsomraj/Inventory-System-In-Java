package io.github.iamsomraj.inventory.service;

import java.io.FileWriter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class FileService {

	static Logger logger = Logger.getLogger(FileService.class.getName());

	static {
		try {
			logger.addHandler(new FileHandler(FileService.class.getSimpleName() + "-logs.xml", true));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeToFile(String fileName, String fileType, String fileContent) {
		try {
			FileWriter file = new FileWriter("src/io/github/iamsomraj/inventory/invoices/" + fileName + "." + fileType);
			file.write(fileContent);
			file.close();
		} catch (Exception e) {
			System.out.println("File failed to write: " + e.getMessage());
			logger.info("File failed to write: " + e.getMessage());
		}
	}

}
