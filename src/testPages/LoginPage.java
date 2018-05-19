package testPages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class LoginPage extends TestBase {

	@FindBy(name = "username")
	WebElement user;

	@FindBy(name = "password")
	WebElement pass;

	@FindBy(id = "m_login_signin_submit")
	WebElement submit;

	public LoginPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	public String getFromattedTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		return sdf.format(now) + ": ";
	}
	/*
	 * public boolean pagetitle() { String pagetitle = driver.getTitle();
	 * System.out.println(pagetitle); boolean flag =
	 * pagetitle.equals("Dashboard | Login Page"); return flag; }
	 */

	public void loginconfig() throws IOException {
		String url = prop.getProperty("URL2");
		driver.get(url);
		System.out.println("For URL2 : " + url);
		String usr = prop.getProperty("username");
		String pas = prop.getProperty("password");
		if (driver instanceof InternetExplorerDriver) {

		} else {
			user.sendKeys(usr);
			pass.sendKeys(pas);
		}
		System.out.println("Logging in");
		submit.click();
		System.out.println("Logged in");
	}

	public InputPage loginviewpage() throws IOException {
		String url = prop.getProperty("URL1");
		driver.get(url);
		System.out.println("For URL1 : " + url);
		String usr = prop.getProperty("username");
		String pas = prop.getProperty("password");
		user.sendKeys(usr);
		pass.sendKeys(pas);
		System.out.println("Logging in");
		submit.click();
		System.out.println("Logged in view");

		return new InputPage();
	}

}
