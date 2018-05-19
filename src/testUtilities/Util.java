package testUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import testBase.TestBase;

public class Util extends TestBase {

	public Util() throws IOException {
		super();
	}

	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Guest user\\eclipse-workspace\\testing\\src\\main\\ipsiTestdata.xlsx";

	static Workbook book;
	static Sheet sheet;

	public static long pageLoadTime = 20;
	public static long implicitwait = 10;

	public static Object[][] getTestData(String sheetName) throws IOException {

		FileInputStream excelFile = new FileInputStream(new File(TESTDATA_SHEET_PATH));
		Workbook book = new XSSFWorkbook(excelFile);

		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		
		book.close();

		return data;
	}

	/*public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");

		// FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" +
		// System.currentTimeMillis() + ".png"));

	}*/

}
