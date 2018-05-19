package testPages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class InputPage extends TestBase {

	@FindBy(css = "input[value='txnType']")
	WebElement txnType;

	@FindBy(name = "txnType")
	WebElement testingtxnType;

	@FindBy(name = "merchReference")
	WebElement customerreference;

	@FindBy(name = "amount")
	WebElement amount;

	@FindBy(name = "submit")
	WebElement submit;

	@FindBy(xpath = "//i[@class='flaticon-add']")
	WebElement tokenbut;

	@FindBy(name = "cardHolderName")
	WebElement cardholdername;

	@FindBy(xpath = "//input[@name='pan']")
	WebElement pan;

	@FindBy(name = "expiryDate")
	WebElement expdate;

	@FindBy(name = "cvv")
	WebElement cvv;

	@FindBy(name = "pay")
	WebElement pay;

	@FindBy(xpath = "//input[@type='submit' and @value='Submit']")
	WebElement csubmit;

	@FindBy(xpath = "//input[@type='submit' and @value='Confirm']")
	WebElement confirm;

	@FindBy(xpath = "//button[@type='button' and @class='btn btn-danger float-right mr-10 showPostmessage']/i")
	WebElement logcollectionbutton;

	@FindBy(id = "postmessage")
	WebElement postmessage;

	@FindBy(xpath = "//*[@id=\"renderIframe\"]/button[2]")
	WebElement but;

	@FindBy(xpath = "//*[@id=\"renderIframe\"]/button[1]")
	WebElement back;

	@FindBy(xpath = "//input[@data-name='key']")
	WebElement key;

	@FindBy(xpath = "//input[@data-name='value']")
	WebElement value;

	public InputPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);

	}

	public String getFromattedTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		return sdf.format(now) + ": ";
	}

	public void ipsiconfig(String test, String cusref, String amt, String cref, String cnum, String cexp, String cv)
			throws InterruptedException {

		String time = getFromattedTime();
		System.out.println(time + test + "\n============");

		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println(time + "Clicking!" + js.executeScript("return arguments[0]", txnType));
		js.executeScript("arguments[0].click();", txnType);

		Select sel = new Select(testingtxnType);
		System.out.println(time + "Performing " + testingtxnType);
		sel.selectByVisibleText(test);

		customerreference.clear();
		System.out.println(time + "Using Merchant reference - " + cusref);
		customerreference.sendKeys(cusref);

		int val = (int) Double.parseDouble(amt);
		System.out.println(time + "Amount - " + amt + " ; Value - " + val);
		amount.sendKeys(String.valueOf(val));
		System.out.println(time + "Clicking generate HMAC");
		submit.click();

		Thread.sleep(3000);

		driver.switchTo().frame(0);
		String currentFrame = (String) js.executeScript("return self.name");
		System.out.println(time + "Inside frame - " + currentFrame);

		Thread.sleep(2000);

		System.out.println(time + "Inside frame - " + cref);
		cardholdername.sendKeys(cref);

		System.out.println(time + "PAN - " + cnum);
		pan.sendKeys(cnum);

		System.out.println(time + "Expiry Date - " + cexp);
		expdate.sendKeys(cexp);

		int cv1 = (int) Double.parseDouble(cv);
		System.out.println(time + cv1);
		cvv.sendKeys(String.valueOf(cv1));

		System.out.println(time + "Submitting payment");
		pay.click();
		Thread.sleep(3000);

		// confirm.click(); // No need to confirm on
		// config/4afc908b-bea6-4229-88bc-ea1bf645036a
		// Thread.sleep(8000);

		System.out.println(time + "Switching to default frame");
		driver.switchTo().defaultContent();
		Thread.sleep(3000);

		System.out.println(time + "Clicking!" + js.executeScript("return arguments[0]", but));
		js.executeScript("arguments[0].click();", but);
		// logcollectionbutton.click();

		System.out.println(time + "Sleeping");
		Thread.sleep(6000);
		String postmess = postmessage.getAttribute("value");

		System.out.println(time + "--------Post message-/START--------\n" + postmess + "\n--------/END---------");

		int txref = postmess.indexOf("txnReference");
		String sttxref = postmess.substring(txref + 15, txref + 15 + 15);
		System.out.println(time + "Trx Ref : " + sttxref);

	}

	void fillField(String time, String what, WebElement on, String key, Boolean split, Boolean clear, Boolean onlyLog) {

		if(clear) {
			on.clear();
		}
		if(split) {
			System.out.println(time + what + " - " + key);
		} else {
			System.out.println(time + what + " - " + key);
		}
		if(!onlyLog) {
			on.sendKeys(key);
		}
	}

	public void generatetoken(String test, String cusref, String amt, String cref, String cnum, String cexp, String cv)
			throws InterruptedException {

		String time = getFromattedTime();
		System.out.println(time + test + " - generatetoken \n============");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println(time + "Selecting");
		js.executeScript("arguments[0].click();", txnType);

		Select sel = new Select(testingtxnType);
		System.out.println(time + "Performing " + test);
		sel.selectByVisibleText(test);

//		customerreference.clear();
//		System.out.println(time + "Using Merchant reference - " + cusref);
//		customerreference.sendKeys(cusref);
		fillField(time, "Merchant reference", customerreference, cusref, false, true, false);

		int val = (int) Double.parseDouble(amt);
		fillField(time, "Amount", amount, String.valueOf(val), false, false, false);

		System.out.println(time + "Expanding parameters");
		tokenbut.click();


		// "tokenControl.token"
		fillField(time, "Key - tokenControl.token - ", key, "onlyTokenise", false, false, false);
		fillField(time, "Value - true", value, "true", false, false, false);
		

		System.out.println(time + "Clicking generate HMAC");
		submit.click();

		System.out.println(time + "Waiting...");
		Thread.sleep(3000);
		System.out.println(time + "more...");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

		System.out.println(time + "Inside frame ");
		cardholdername.sendKeys(cref);

		fillField(time, "PAN", pan, cnum, false, false, false);
		fillField(time, "Expiry Date", expdate, cexp, false, false, false);

		//int cv1 = (int) Double.parseDouble(cv);
		//System.out.println(time + cv1 + " - nope!");
		fillField(time, "CVV - NOT SENDING!", cvv, String.valueOf((int) Double.parseDouble(cv)), false, false, true);
		// cvv.sendKeys(String.valueOf(cv1));
		System.out.println(time + "csubmit? - nope!");
		// csubmit.click();
		System.out.println(time + "clicking pay!");
		pay.click();

		// confirm.click();
		System.out.println(time + "Switching to default frame");
		driver.switchTo().defaultContent();
		System.out.println(time + "Sleeping");
		Thread.sleep(3000);

		System.out.println(time + "Clicking!" + js.executeScript("return arguments[0]", but));
		js.executeScript("arguments[0].click();", but);

		String postmess = postmessage.getAttribute("value");
		System.out.println(time + "--------Post message-/START--------\n" + postmess + "\n--------/END---------");

		Thread.sleep(5000);
		int txref = postmess.indexOf("cardToken");
		System.out.println("Card token index " + txref);
		String st1 = postmess.substring(txref + 12, txref + 12 + 16);
		System.out.println("The Value of cardToken  is " + st1);

		System.out.println(time + "Going back!");
		System.out.println(time + "Clicking!" + js.executeScript("return arguments[0]", back));
		js.executeScript("arguments[0].click();", back);

		System.out.println(time + "Key - " + key + " ; Card Token");
		
		fillField(time, "Value ", key, "cardToken", false, true, false);
		fillField(time, "Value ", value, st1, false, true, false);

		customerreference.clear();
		cusref += "-T";
		System.out.println(time + "Using Different Merchant reference - " + cusref);
		customerreference.sendKeys(cusref);

		System.out.println(time + "submit? - " + submit);
		submit.click();
		System.out.println(time + "Sleeping...");
		Thread.sleep(3000);

		System.out.println(time + "Waiting...");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

		System.out.println(time + "Reusing older values");
		// cardholdername.sendKeys(cref);

		// pan.sendKeys(cnum);
		// expdate.sendKeys(cexp);

		fillField(time, "CVV - after tokenization", cvv, String.valueOf((int) Double.parseDouble(cv)), false, false, false);

		System.out.println(time + "Paying with token");
		pay.click();
		// csubmit.click();
		// maybe not confirm.click();

		Thread.sleep(3000);

		driver.switchTo().defaultContent();

		js.executeScript("arguments[0].click();", but);

		String postmes = postmessage.getAttribute("value");

		System.out.println(time + "--------Post message-/START--------\n" + postmess + "\n--------/END---------");
		int txref1 = postmes.indexOf("CardToken");
		String st2 = postmess.substring(txref1 + 12, txref1 + 12 + 16);
		System.out.println("The Value of cardToken  is " + st2);

	}

	public void purchasewithtokenn(String test, String cusref, String amt, String cref, String cnum, String cexp,
			String cv) throws InterruptedException {

		String time = getFromattedTime();
		System.out.println(time + test);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", txnType);
		String currentFrame = (String) js.executeScript("return self.name");
		System.out.println("Frame : " + currentFrame);

		Select sel = new Select(testingtxnType);
		sel.selectByVisibleText(test);

		customerreference.clear();
		System.out.println("Customer reference : " + cusref);
		customerreference.sendKeys(cusref);

		int val = (int) Double.parseDouble(amt);
		System.out.println(val);
		amount.sendKeys(String.valueOf(val));

		tokenbut.click();

		key.sendKeys("tokenControl.token");
		value.sendKeys("true");

		submit.click();

		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

		cardholdername.sendKeys(cref);

		pan.sendKeys(cnum);

		expdate.sendKeys(cexp);

		int cv1 = (int) Double.parseDouble(cv);
		System.out.println(cv1);
		cvv.sendKeys(String.valueOf(cv1));

		csubmit.click();

		confirm.click();

		driver.switchTo().defaultContent();
		Thread.sleep(3000);

		js.executeScript("arguments[0].click();", but);

		Thread.sleep(5000);
		String postmess = postmessage.getAttribute("value");

		Thread.sleep(3000);

		System.out.println(postmess);

		int txref = postmess.indexOf("cardToken");
		String st1 = postmess.substring(txref + 12, txref + 12 + 16);
		System.out.println("The Value of cardToken  is " + st1);

		/*
		 * js.executeScript("arguments[0].click();", back);
		 * 
		 * key.clear(); key.sendKeys("cardToken"); value.clear(); value.sendKeys(st1);
		 * 
		 * submit.click(); Thread.sleep(4000);
		 * 
		 * wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		 * 
		 * cardholdername.sendKeys(cref); pan.sendKeys(cnum);
		 * 
		 * System.out.println(cexp); expdate.sendKeys(cexp);
		 * 
		 * int cv2 = (int) Double.parseDouble(cv); System.out.println(cv2);
		 * cvv.sendKeys(String.valueOf(cv2));
		 * 
		 * csubmit.click();
		 * 
		 * confirm.click();
		 * 
		 * Thread.sleep(4000);
		 * 
		 * driver.switchTo().defaultContent();
		 * 
		 * js.executeScript("arguments[0].click();", but);
		 * 
		 * String postmes = postmessage.getAttribute("value");
		 * 
		 * int txref1 = postmes.indexOf("cardToken"); String st2 =
		 * postmess.substring(txref + 12, txref + 12 + 16);
		 * System.out.println("The Value of cardToken  is " + st2);
		 * 
		 */
	}

}
