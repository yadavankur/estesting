package testNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class delete {

	public static void main(String[] args) throws IOException {
		
		
/*
		String text = "Post Message <2018-03-25T23:32:04+11:00>:\r\n" + 
				"{\"merchantReference\":\"EC682566-48C1-11E8-BE93-152v4181695PG9\",\"info\":\"iframe content for payment page is loading.\",\"iframeName\":\"0c88bb6885efdf5d93dcaebb53bc4f7fe3b2ae219746f90d52df3e3a508d9709\"}\r\n" + 
				"\r\n" + 
				"Post Message <2018-03-25T23:32:04+11:00>:\r\n" + 
				"{\"merchantReference\":\"EC682566-48C1-11E8-BE93-152v4181695PG9\",\"info\":\"The payment form has rendered.\",\"iframeName\":\"0c88bb6885efdf5d93dcaebb53bc4f7fe3b2ae219746f90d52df3e3a508d9709\"}\r\n" + 
				"\r\n" + 
				"Post Message <2018-03-25T23:32:06+11:00>:\r\n" + 
				"{\"merchantReference\":\"EC682566-48C1-11E8-BE93-152v4181695PG9\",\"info\":\"The payment form has been submitted.\",\"name\":\"AVV\",\"iframeName\":\"0c88bb6885efdf5d93dcaebb53bc4f7fe3b2ae219746f90d52df3e3a508d9709\"}\r\n" + 
				"\r\n" + 
				"Post Message <2018-03-25T23:32:06+11:00>:\r\n" + 
				"{\"merchantReference\":\"EC682566-48C1-11E8-BE93-152v4181695PG9\",\"info\":\"Google recaptcha validated successfully.\",\"name\":\"AVV\",\"iframeName\":\"0c88bb6885efdf5d93dcaebb53bc4f7fe3b2ae219746f90d52df3e3a508d9709\"}\r\n" + 
				"\r\n" + 
				"Post Message <2018-03-25T23:32:06+11:00>:\r\n" + 
				"{\"merchantReference\":\"EC682566-48C1-11E8-BE93-152v4181695PG9\",\"info\":\"The confirmation form has rendered.\",\"name\":\"AVV\",\"iframeName\":\"0c88bb6885efdf5d93dcaebb53bc4f7fe3b2ae219746f90d52df3e3a508d9709\"}\r\n" + 
				"\r\n" + 
				"Post Message <2018-03-25T23:32:06+11:00>:\r\n" + 
				"{\"merchantReference\":\"EC682566-48C1-11E8-BE93-152v4181695PG9\",\"info\":\"The confirmation form has been submitted.\",\"name\":\"AVV\",\"iframeName\":\"0c88bb6885efdf5d93dcaebb53bc4f7fe3b2ae219746f90d52df3e3a508d9709\"}";
		
		int start = text.indexOf("cardToken");
		//517
		String token = text.substring(start+12,start+12+16);
		
		System.out.println(token);*/
		
		System.out.println("Test");
		
		String TESTDATA_SHEET_PATH = "C:\\Users\\Guest user\\eclipse-workspace\\testing\\src\\main\\ipsiTestdata.xlsx";


		Sheet sheet;



			FileInputStream excelFile = new FileInputStream(new File(TESTDATA_SHEET_PATH));
			Workbook book = new XSSFWorkbook(excelFile);
			
			System.out.println(book);

			sheet = book.getSheet("ipsiconfigdata");
			
			System.out.println(sheet);
			
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			// System.out.println(sheet.getLastRowNum() + "--------" +
			// sheet.getRow(0).getLastCellNum());
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					
					System.out.println("data: "+data[i][k]);
				}
			}



	}

}
