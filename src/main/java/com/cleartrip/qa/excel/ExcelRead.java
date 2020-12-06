package com.cleartrip.qa.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	public static ArrayList<Object[]> Location(String fileName) throws IOException {
		File src = new File(fileName);
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		ArrayList<Object[]> myData = new ArrayList<>();
		int rowNum = sheet.getLastRowNum();
		for (int j = 1; j <= rowNum; j++) {
			String place = sheet.getRow(j).getCell(0).toString();
			String checkin = sheet.getRow(j).getCell(1).toString();
			String checkout = sheet.getRow(j).getCell(2).toString();
			// XSSFRow currentRow=sheet.getRow(j);
			Object ob[] = { place, checkin, checkout };
			myData.add(ob);
		}

		wb.close();
		return myData;
	}
}
