package com.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class KeywordUtil extends DriverUtil{

	protected KeywordUtil(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
	}

	/**
	 * Locate the element using given locator
	 * 
	 * @param by
	 *            Unique identifier of the element
	 * @return Return WebElement
	 */
	public static WebElement locateElement(By by) {

		WebElement w = null;

		try {

			waitForTheElement(by);

			w = driver.findElement(by);

		} catch (NoSuchElementException e) {

			throw new NoSuchElementException("Element not found with the give locator " + by);
		}
		return w;

	}

	/**
	 * Write the String in the field
	 * 
	 * @param by
	 *            Unique identifier of the element
	 * @param text
	 *            String to be written into the field
	 */
	public static void type(By by, String text) {

		WebElement w = locateElement(by);

		w.clear();
		w.sendKeys(text);
	}

	/**
	 * Wait for presence and visibility of the element
	 * 
	 * @param by
	 *            Unique identifier of the element
	 */
	public static void waitForTheElement(By by) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.presenceOfElementLocated(by));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public static boolean isWebElementPresent(String path) {
		Boolean flag=false;
		if(driver.findElements(By.xpath(path)).size()>0)
		{	
			flag=true;
		}

		return flag;

	}
	
	/**
	 * 
	 * Verify present of the element
	 * 
	 * @param xpath
	 * @return
	 */
	public boolean verifyPresenceOfElementUsingXpath(String xpath) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/**
	 * Click on given element
	 * 
	 * @param by
	 *            Unique identifier of the element
	 */
	public static void click(By by) {

		WebElement w = locateElement(by);

		w.click();
	}

	/**
	 * Validate the current URL with the Expected URL
	 * 
	 * @param expectedURL
	 *            Excepted URL of the current page
	 */
	public void validateCurrentURL(String expectedURL) {

		String actualURL = driver.getCurrentUrl().trim();

		Assert.assertEquals(actualURL, expectedURL.trim());
	}

	/**
	 * scroll for an element
	 * 
	 * @param by
	 *            Unique identifier of the element
	 */
	public void scrollAtAElement(By by) {

		WebElement w = locateElement(by);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", w);
	}

	/**
	 * Pause the thread
	 * 
	 * @param time
	 *            Waiting time
	 */
	public void synch(long time) {

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Verify the message
	 * 
	 * @param by		Unique identifier of the element
	 * @param expectedMessage validation text
	 */
	public void checkMessage(By by, String expectedMessage) {

		String actualText = locateElement(by).getText().trim();

		Assert.assertEquals(actualText, expectedMessage);
	}
	/**
	 * Get value from properties file
	 * @param key
	 * @return String
	 */
	public static String GetValue(String key)
	{
		File file = new File(System.getProperty("user.dir")+"/src/main/resources/ConfigFiles/config.properties");
		
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		try {
			prop.load(fileInput);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		String strbaseURL = prop.getProperty(key);
		return strbaseURL;
	}
	/**
	 * Get value from properties file
	 * @param key
	 * @return integer
	 */
	public static int GetIntValue(String key)
	{
		File file = new File(System.getProperty("user.dir")+"/src/main/resources/ConfigFiles/config.properties");

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		try {
			prop.load(fileInput);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String strbaseURL = prop.getProperty(key);
		return Integer.parseInt(strbaseURL);
	}
	/**
	 * Stop the executing up to given seconds 
	 * @param time
	 * @throws InterruptedException
	 */
public static void executionDelay(long time) throws InterruptedException {
		
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * Verify presence of the element
 * 
 * @param xpath
 * @return
 */
public static boolean isElementPresent(By by) {

	try {
		WebElement w = locateElement(by);
		w.isDisplayed();

		return true;
	} catch (Exception e) {

		return false;
	}

}

/**
 * Verify presence of the element
 * 
 * @param xpath
 * @return
 */
public static boolean hideKeyboard() {

	try {

		driver.hideKeyboard();

		return true;
	} catch (Exception e) {

		return false;
	}

}

/**
 * Verify Not presence of the element
 * 
 * @param xpath
 * @return
 */
public static boolean isElementNotPresent(String xpath) {

	try {

		driver.findElement(By.xpath(xpath));

		return false;
	} catch (Exception e) {

		return true;
	}

}
/**
 * Verify presence of the element Enable
 * 
 * @param xpath
 * @return
 */
public boolean isElementEnable(String xpath) {

	try {

		driver.findElement(By.xpath(xpath)).isEnabled();

		return true;
	} catch (Exception e) {

		return false;
	}

}

/**
 * Generate xpath string
 * 
 * @param xpath
 * @param value
 * @return
 */
public String generateXpathString(String xpath, String value) {

	String replacedString = String.format(xpath, value);

	return replacedString;

}
}
