package testNG;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testPages.LoginPage;
import testBase.TestBase;

public class LoginTest extends TestBase {

	TestBase testbase;
	LoginPage loginpage;

	LoginTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setup() throws IOException  {
		initialization();
		loginpage = new LoginPage();
	}

	
	@Test
	public void logintest() throws IOException {
		loginpage.loginconfig();
		
		}
	


	@AfterMethod
	public void teardown() {
//		driver.close();
		
	}
	

}
