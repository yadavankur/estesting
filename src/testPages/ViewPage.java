package testPages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class ViewPage extends TestBase {
	
	@FindBy(xpath = "//input[@placeholder='Search...']")
	WebElement search;
	
	@FindBy(xpath ="//div[@name='transaction']//a")
	WebElement viewclick;
	
	public ViewPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	
	public void viewtranscations(String test) throws InterruptedException {
		search.sendKeys(test);
		Thread.sleep(2000);
					
		viewclick.click();
		Thread.sleep(2000);
					
		String merchantref = driver.findElement(By.xpath("//td[@data-datastore='merchantreference']")).getText();
		System.out.println(merchantref);
		
		Assert.assertEquals(merchantref, test);
		System.out.println("MATCH DONE >>>>>> TEST CASE IS PASSED");
		
		String metadata = driver.findElement(By.xpath("//*[@data-datastore='metadata']")).getText();
		System.out.println(metadata);
		
		int index= metadata.indexOf("browser_version");
		String result = metadata.substring(index+18, index+18+4);
		System.out.println(result);	
	}
	

}
