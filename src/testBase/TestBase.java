package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBase {
	public static Properties prop;
	public static WebDriver driver;

	public TestBase() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Guest user\\eclipse-workspace\\testing\\src\\main\\config.properties");
		prop.load(fis);

	}

	public static void initialization() {

		String browsername = prop.getProperty("browser");
		System.out.println(browsername);

		if (browsername.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chrome\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browsername.equals("Firefox")) {
			System.setProperty("webdriver.geckodriver.driver",
					"C:\\Selenium\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browsername.equals("IE")) {
		System.setProperty("webdriver.ie.driver",
				"C:\\Selenium\\ie\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

	}
}
