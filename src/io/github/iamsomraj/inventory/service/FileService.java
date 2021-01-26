package io.github.iamsomraj.inventory.service;

import java.io.FileWriter;

public class FileService {

	public static void writeToFile(String fileName, String fileType, String fileContent) {
		try {
			FileWriter file = new FileWriter(
					"src/io/github/iamsomraj/inventory/invoices/" + fileName + "." + fileType);
			file.write(fileContent);
			file.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
