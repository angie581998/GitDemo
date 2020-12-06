package com.cleartrip.qa.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {
	public static void hotelNames(List<String> list) throws IOException {
		File file = new File(
				"C:\\Users\\ACER\\eclipse_oct\\ClearTripAutomationTest\\src\\main\\java\\com\\cleartrip\\qa\\testdata\\clearTrip_TestData.xlsx");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheetAt(1);

		XSSFRow row1 = sheet.createRow(0);
		row1.createCell(0).setCellValue("Hospital Name");

		int i1 = 2;
		for (int j = 0; j < list.size(); j++) {
			XSSFRow row2 = sheet.createRow(i1++);
			row2.createCell(0).setCellValue(list.get(j));
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(
					"C:\\Users\\ACER\\eclipse_oct\\ClearTripAutomationTest\\src\\main\\java\\com\\cleartrip\\qa\\testdata\\clearTrip_TestData.xlsx"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook.write(fos);
			fos.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
