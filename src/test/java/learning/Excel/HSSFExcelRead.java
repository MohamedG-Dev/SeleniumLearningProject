package learning.Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class HSSFExcelRead {

	public static void main(String[] args) {
		File file=new File("./src/test/resources/ExcelXLS.xls");
		try {
			FileInputStream fis=new FileInputStream(file);
			HSSFWorkbook workbook=new HSSFWorkbook(fis);
			HSSFSheet sheet=workbook.getSheetAt(0);
			HSSFRow row=sheet.getRow(3);
			HSSFCell cell=row.getCell(2);
			String cellValue=cell.getStringCellValue();
			System.out.println(cellValue);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
