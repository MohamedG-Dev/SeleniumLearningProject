package learning.Excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String filePath;
	FileInputStream fis = null;
	Workbook workbook = null;
	Sheet sheet = null;
	Row row = null;
	Cell cell = null;
	public FileOutputStream fileOut = null;
	String fileExtension = null;

	public ExcelReader(String filePath) throws IOException {
		this.filePath = filePath;
		fileExtension = filePath.substring(filePath.indexOf(".x"));
		try {
			fis = new FileInputStream(filePath);
			if (fileExtension.equals(".xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if (fileExtension.equals(".xls")) {
				workbook = new HSSFWorkbook(fis);
			}
			sheet = workbook.getSheetAt(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			fis.close();
		}
	}

	// returns the RowCount in the sheet
	public int getRowCount(String sheetName) {
		int sheetIndex = workbook.getSheetIndex(sheetName);
		if (sheetIndex == -1) {
			return 0;
		} else {
			sheet = workbook.getSheetAt(sheetIndex);
			int rowsCount = sheet.getLastRowNum() + 1;
			return rowsCount;
		}
	}

	// returns the Data from the Cell
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";
			int sheetIndex = workbook.getSheetIndex(sheetName);
			if (sheetIndex == -1)
				return "";
			sheet = workbook.getSheetAt(sheetIndex);
			row = sheet.getRow(0);
			int colNum = -1;
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName))
					colNum = i;
			}
			if (colNum == -1)
				return "";
			sheet = workbook.getSheetAt(sheetIndex);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";
			if (cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				if (DateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(DateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
				}
				return cellText;
			} else if (cell.getCellType() == CellType.BLANK) {
				return "";
			} else {
				return String.valueOf(cell.getBooleanCellValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column" + "does not exist in xls";
		}
	}

	// returns the Data from the Cell
	public String getCellData(String sheetName, int rowNum, int colNum) {
		try {
			if (rowNum == -1)
				return "";
			int sheetIndex = workbook.getSheetIndex(sheetName);
			if (sheetIndex == -1)
				return "";
			sheet = workbook.getSheetAt(sheetIndex);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum - 1);
			if (cell == null)
				return "";
			if (cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				return cellText;
			} else if (cell.getCellType() == CellType.BLANK) {
				return "";
			} else {
				return String.valueOf(cell.getBooleanCellValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Row " + rowNum + " or column " + colNum + " does not exist in xls";
		}
	}

	// return true if the data is set successfully else false
	public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
		try {
			if (rowNum <= 0)
				return false;
			int sheetIndex = workbook.getSheetIndex(sheetName);
			int colIndex = -1;
			if (sheetIndex == -1)
				return false;
			sheet = workbook.getSheetAt(sheetIndex);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colIndex = i;
			}
			if (colIndex == -1)
				return false;
			sheet.autoSizeColumn(colIndex);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);
			cell = row.getCell(colIndex);
			if (cell == null)
				cell = row.createCell(colIndex);

			// cell style
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setWrapText(true);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(data);

			fileOut = new FileOutputStream(filePath);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {
		try {
			if (rowNum <= 0)
				return false;
			int sheetIndex = workbook.getSheetIndex(sheetName);
			int colIndex = -1;
			if (sheetIndex == -1)
				return false;
			sheet = workbook.getSheetAt(sheetIndex);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colIndex = i;
			}
			if (colIndex == -1)
				return false;
			sheet.autoSizeColumn(colIndex);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);
			cell = row.getCell(colIndex);
			if (cell == null)
				cell = row.createCell(colIndex);
			cell.setCellValue(data);

			CreationHelper createHelper = workbook.getCreationHelper();

			// cell style for hyperlinks. by default hyperlinks are blue and underlined
			CellStyle cellStyle = workbook.createCellStyle();
			Font hyperLink_Font = workbook.createFont();
			hyperLink_Font.setUnderline(Font.U_SINGLE);
			hyperLink_Font.setColor(IndexedColors.BLUE.getIndex());
			cellStyle.setFont(hyperLink_Font);

			Hyperlink hyperLink = createHelper.createHyperlink(HyperlinkType.FILE);
			hyperLink.setAddress(url);
			cell.setHyperlink(hyperLink);
			cell.setCellStyle(cellStyle);

			fileOut = new FileOutputStream(filePath);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// return true if sheet is created successfully else false
	public boolean addSheet(String sheetName) {
		FileOutputStream fout;
		try {
			workbook.createSheet(sheetName);
			fout = new FileOutputStream(filePath);
			workbook.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if sheet is removed successfully else false if the sheet does
	// not exists
	public boolean removeSheet(String sheetName) {
		int sheetIndex = workbook.getSheetIndex(sheetName);
		if (sheetIndex == -1)
			return false;
		FileOutputStream fout;
		try {
			workbook.removeSheetAt(sheetIndex);
			fout = new FileOutputStream(filePath);
			workbook.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if the column is created successfully
	public boolean addColumn(String sheetName, String colName) {
		try {
			int sheetIndex = workbook.getSheetIndex(colName);
			if (sheetIndex == -1)
				return false;
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			sheet = workbook.getSheetAt(sheetIndex);
			row = sheet.getRow(0);
			if (row == null)
				sheet.createRow(0);
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());
			cell.setCellValue(colName);
			cell.setCellStyle(cellStyle);
			fileOut = new FileOutputStream(filePath);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// removes the column and all it's content
	public boolean removeColumn(String sheetName, int colNum) {
		try {
			if (!isSheetExist(sheetName)) {
				sheet = workbook.getSheet(sheetName);
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
				CreationHelper createHelper = workbook.getCreationHelper();
				cellStyle.setFillPattern(FillPatternType.NO_FILL);
				for (int i = 0; i < getRowCount(sheetName); i++) {
					row = sheet.getRow(i);
					if (row != null) {
						cell = row.getCell(colNum - 1);
						if (cell != null) {
							cell.setCellStyle(cellStyle);
							row.removeCell(cell);
						}
					}
				}
				fileOut = new FileOutputStream(filePath);
				workbook.write(fileOut);
				fileOut.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// find whether sheet exists
	public boolean isSheetExist(String sheetName) {
		int sheetIndex = workbook.getSheetIndex(sheetName);
		if (sheetIndex == -1) {
			sheetIndex = workbook.getSheetIndex(sheetName.toUpperCase());
			if (sheetIndex == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();
	}

	//// String sheetName, String testCaseName,String keyword ,String URL,String
	//// message
	public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, String url,
			String message) {
		if (!isSheetExist(sheetName))
			return false;
		sheet = workbook.getSheet(sheetName);
		for (int i = 0; i < getRowCount(sheetName); i++) {
			if (getCellData(sheetName, 1, i).equals(testCaseName)) {
				setCellData(sheetName, screenShotColName, i, message, url);
				break;
			}
		}
		return true;
	}

	// getCellRowNum
	public int getCellRowNum(String sheetName, String colName, String cellValue) {
		for (int i = 0; i < getRowCount(sheetName); i++) {
			if (getCellData(sheetName, colName, i).equals(cellValue)) {
				return i;
			}
		}
		return -1;
	}
}
