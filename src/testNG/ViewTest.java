package testNG;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testPages.LoginPage;
import testPages.ViewPage;
import testBase.TestBase;
import testUtilities.Util;

public class ViewTest extends TestBase {
	LoginPage loginpage;
	ViewPage viewpage;
	Util util;
	String SheetName="ipsiconfigdata";
	

	public ViewTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setup() throws IOException {
		initialization();
		loginpage = new LoginPage();
		loginpage.loginviewpage();

	}
	
	@DataProvider
	public Object[][] getdata() throws IOException {
	Object[][] data = util.getTestData(SheetName);
	return data;
	}

	@Test(dataProvider ="getdata")
	public void viewtranscations(String testing, String customerrefe, String amount, String cardholdername, String cardnumber, String cardexpirydate, String cvc) throws InterruptedException, IOException {
				
	//	driver.findElement(By.xpath("//*[@id='m_dashboard_daterangepicker']/a")).click();
	//	Thread.sleep(2000);
//		driver.findElement(By.xpath("//*[contains(text(),'Yesterday')]")).click();
		viewpage = new ViewPage();
		viewpage.viewtranscations(customerrefe);
		
	}
	
	@AfterMethod()
	public void teardown() {
//	driver.close();	
			}
	

}


