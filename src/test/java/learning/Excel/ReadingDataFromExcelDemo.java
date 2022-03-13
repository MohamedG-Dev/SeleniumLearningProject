package learning.Excel;

import java.io.IOException;

public class ReadingDataFromExcelDemo {

	public static void main(String[] args) {
		try {
			ExcelReader excelReader=new ExcelReader("./src/test/resources/ExcelXLSX.xlsx");
			int rows = excelReader.getRowCount("EmployeeData");
			System.out.println("The number of rows in the sheet are "+rows);
			String data = excelReader.getCellData("EmployeeData", "Occupation", 4);
			System.out.println("The cell value is: "+data);
			data = excelReader.getCellData("EmployeeData", 3, 3);
			System.out.println("The cell value2 is: "+data);
			boolean exists = excelReader.isSheetExist("EmployeeData");
			System.out.println(exists);
			int columnCount = excelReader.getColumnCount("EmployeeData");
			System.out.println("The column count is: "+columnCount);
			int rowNumber = excelReader.getCellRowNum("EmployeeData", "Occupation", "Senior Test Engineer");
			System.out.println("Row number is: "+rowNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
