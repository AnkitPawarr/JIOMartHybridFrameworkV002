package com.JioMart.Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtilities {

	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet Sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	public String path = null;

	public XLUtilities(String path) {
		this.path = path;
	}

	public int getRowCount(String SheetName) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);

		Sheet = workbook.getSheet(SheetName);
		int totalRow = Sheet.getLastRowNum();

		fis.close();
		workbook.close();
		return totalRow;
	}

	public int getColCount(String SheetName, int rowNum) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);

		Sheet = workbook.getSheet(SheetName);
		row = Sheet.getRow(rowNum);
		int totalCol = row.getLastCellNum();

		fis.close();
		workbook.close();
		return totalCol;
	}

	public String readData(String SheetName, int rowNum, int colNum) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);

		Sheet = workbook.getSheet(SheetName);
		row = Sheet.getRow(rowNum);
		cell = row.getCell(colNum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}

		fis.close();
		workbook.close();
		return data;
	}

	public void setCellData(String SheetName, int rowNum, int colNum, String data) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);

		Sheet = workbook.getSheet(SheetName);
		row = Sheet.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(data);

		fos = new FileOutputStream(path);
		workbook.write(fos);
		fis.close();
		workbook.close();
		fos.close();
	}

}
