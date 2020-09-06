package com.test.register.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * Utility Using Apache Poi Library to Read from Excel and Return data set to DataProvider*/
public class TestUtil {

	private static Workbook excelWorkBook;
	private static Sheet excelsheet;
	private static String sheetName ="registration";
	private static String path = "RegistrationInput.xlsx";


	public static final Object[][] getCellData() throws Exception{

		File file = new File(path);
		FileInputStream excelFile = new FileInputStream(file);
		excelWorkBook = WorkbookFactory.create(excelFile);
		excelsheet=excelWorkBook.getSheet(sheetName);
        //Create a Two Dimensional Array 
		Object [][] exceldata = new Object [excelsheet.getLastRowNum()][excelsheet.getRow(0).getLastCellNum()];

		System.out.println("Number of Rows " + excelsheet.getLastRowNum());
		System.out.println("Number of Columns " + excelsheet.getRow(0).getLastCellNum());

		for (int i=0; i < excelsheet.getLastRowNum(); i++) {
			for (int k =0; k <excelsheet.getRow(0).getLastCellNum(); k++) {
				exceldata[i][k] = excelsheet.getRow(i+1).getCell(k).toString();
				System.out.println("Data from Excel >> "+ exceldata[i][k] );
			}
		}
		return exceldata;

	}



}
