package org.example.json;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class read_sheet {
	
	public String[][]read_data() throws InvalidFormatException, IOException {

		File file = new File("C:\\Users\\A7med\\IdeaProjects\\demo\\demo1\\ApiDemo\\src\\test\\files\\DDT.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet("Sheet2");
		int number_of_rows = sheet.getPhysicalNumberOfRows();
		int number_of_columns = sheet.getRow(0).getLastCellNum();
		String[][] array = new String[number_of_rows - 1][number_of_columns];
		for (int i = 1; i < number_of_rows; i++) {
			for (int a = 0; a < number_of_columns; a++) {
				XSSFRow row = sheet.getRow(i);
				array[i - 1][a] = row.getCell(a).toString();

			}
		}
		return array;
	}

}
