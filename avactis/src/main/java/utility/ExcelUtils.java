package utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {
	static FileInputStream inputStream;

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell cell;

	@SuppressWarnings("deprecation")
	public static String getCellValueForGivenRowAndCol(String sheetname, int row, int col) {
		// Create an object of File class to open xlsx file
		String cellValue = null;
		try {
			String Path = Constants.DataFilePath;

			// Create an object of FileInputStream class to read excel file

			inputStream = new FileInputStream(Path);

			// Creating workbook instance that refers to .xls file

			ExcelWBook = new XSSFWorkbook(inputStream);

			ExcelWSheet = ExcelWBook.getSheet(sheetname);

			cell = ExcelWSheet.getRow(row).getCell(col);

			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
			} else {
				cellValue = cell.getStringCellValue();
			}
			System.out.println("cell value is :" + cellValue);

			// Get the address in a variable

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cellValue;
	}

	public static String getCellValueByHeaderName(String SheetName, String ColumnName, int RowIndex)
			throws FileNotFoundException, IOException {
		Map<String, Integer> requiredHeaders = new HashMap<String, Integer>();
		FileInputStream file = new FileInputStream(new File(Constants.DataFilePath));
		Workbook workbook = new XSSFWorkbook(file);
		DataFormatter formatter = new DataFormatter();
		Sheet sheet = workbook.getSheet(SheetName);
		
		for (Cell cell : sheet.getRow(0)) {
			requiredHeaders.put(cell.getStringCellValue(), cell.getColumnIndex());
		}

		Row row = sheet.getRow(RowIndex);
		String CellValue = formatter.formatCellValue(row.getCell(requiredHeaders.get(ColumnName)));
		workbook.close();

		return CellValue;
	}
}
