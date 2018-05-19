package testNG;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testPages.InputPage;
import testPages.LoginPage;
import testBase.TestBase;
import testUtilities.Util;

public class InputPageTest extends TestBase {

	Util util;
	TestBase testbase;
	LoginPage loginpage;
	InputPage inputpage;
	String SheetName = "ipsiconfigdata";

	InputPageTest() throws IOException {
		super();
	}

	@BeforeMethod()
	public void setup() throws IOException, InterruptedException {
		initialization();
		loginpage = new LoginPage();
		loginpage.loginconfig();
		Thread.sleep(3000);
		}

	@DataProvider
	public Object[][] getdata() throws IOException {
		Object[][] data = util.getTestData(SheetName);
		return data;
	}

	@Test(dataProvider = "getdata",enabled=false)
	public void ipsiconfigtest(String testing, String customerrefe, String amount, String cardholdername, String cardnumber, String cardexpirydate, String cvc) throws IOException, InterruptedException {
		
		inputpage = new InputPage();
		inputpage.ipsiconfig(testing, customerrefe, amount, cardholdername,cardnumber,cardexpirydate, cvc );
		System.out.println(customerrefe);

	}
	

	@Test(dataProvider = "getdata")
	public void generatetoken(String testing, String customerrefe, String amount, String cardholdername, String cardnumber, String cardexpirydate, String cvc) throws IOException, InterruptedException {
		
		inputpage = new InputPage();
		inputpage.generatetoken(testing, customerrefe, amount, cardholdername,cardnumber,cardexpirydate, cvc );
		System.out.println(customerrefe);
	}
	
	
	@Test(dataProvider = "getdata",enabled=false)
	public void purchasewithtoken(String testing, String customerrefe, String amount, String cardholdername, String cardnumber, String cardexpirydate, String cvc) throws IOException, InterruptedException {
		
		inputpage = new InputPage();
		inputpage.purchasewithtokenn(testing, customerrefe, amount, cardholdername,cardnumber,cardexpirydate, cvc);
		
		System.out.println(customerrefe);
	}
	
	

}
