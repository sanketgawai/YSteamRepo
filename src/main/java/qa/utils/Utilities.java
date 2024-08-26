package qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {

	public static Object[][] getTestDataFromexcel(String sheetName) throws 
IOException
	{//***** need 3 dependency ->poi,poi-ooxml,poi-ooxml-schemas
		
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\testdata\\steam.xlsx");
		
			FileInputStream fisExcel = new FileInputStream(excelFile);
			//XSSFWorkbook workbook = new XSSFWorkbook(fisExcel);//bellow line geving error show make it global
			XSSFWorkbook workbook = new XSSFWorkbook(fisExcel);
		
		
		//sheet = workbook.getSheet(sheetName);//getSheet will return obj of excel of sheet
		//****move mouse on sheet create local veriable
		XSSFSheet sheet = workbook.getSheet(sheetName);//***getSheet will return obj of excel of sheet
		//once you get sheet need to find no of rows and columns in excel sheet
		int rows = sheet.getLastRowNum();//it will find no of row
		int columns = sheet.getRow(0).getLastCellNum();// it will find no of column
				
		Object [][] data = new Object[rows][columns];
		
		for(int i =0;i<rows;i++)
		{//****move mouse on row & cell create local veriable
			XSSFRow row = sheet.getRow(i+1);//**** write i+1, if i then we will get error
			
			for(int j=0;j<columns;j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING: //** :
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					//data[i][j] = cell.getNumericCellValue();//**** if we use this line numaric data come with .0000, to avide this follow next line
					//data[i][j] = (int)cell.getNumericCellValue();//now we need to covert into string for that follow next line
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());//Overall, this expression converts the numeric value stored in the Excel cell into a string., cell.getNumericCellValue() gets the numeric value from the cell., (int) converts that numeric value to an integer., Integer.toString(...) converts the integer to its string representation. 
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
		
	}
	
}
