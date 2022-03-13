package learning.Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Demo {

	public static void main(String[] args) {
		String filePath = "./src/test/resources/ExcelXLSX.xlsx";
		File file = new File(filePath);
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = filePath.endsWith(".xls") ? new HSSFWorkbook(fis) : new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(2);
			Cell cell = row.getCell(2);
			String cellValue = cell.getStringCellValue();
			System.out.println(cellValue);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
