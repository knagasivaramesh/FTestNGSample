package com.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DriverUtil {
	// private WebDriver driver;
	public static AppiumDriverLocalService service = null;
	public static AppiumDriver<MobileElement> driver;
	public static Wait wait;
	private Properties config;
	private static String testName;
	public static String browserVersion;
	public static String webBrowserName;
	public static String appium_ip_address = KeywordUtil.GetValue("appium_ip_address");
	public static String appium_port = KeywordUtil.GetValue("appium_port");
	public static DesiredCapabilities capabilities = new DesiredCapabilities();

	/**
	 * Create AppiumDriver instance
	 * @return 
	 * 
	 */
	public static AppiumDriver<MobileElement> getAndroidDriver(String browserName) {

		if (browserName.equals("N.A.")) {
			androidCapabilities("");
		}

		else if (browserName.equalsIgnoreCase("Chrome")) {
			androidCapabilities("Chrome");
		}

		else if (browserName.equalsIgnoreCase("Browser")) {
			androidCapabilities("Browser");
		}

		else {

			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://" + appium_ip_address + ":" + appium_port + "/wd/hub"),capabilities);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new FluentWait(driver).withTimeout(KeywordUtil.GetIntValue("explicit_timeout"), TimeUnit.SECONDS)
				.pollingEvery(KeywordUtil.GetIntValue("polling_time"), TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		return driver;
	}

	public static AppiumDriver<MobileElement> getIOSDriver(String browserName) {

		try {
			if (browserName.equals("N.A.")) {
				iOSCapabilities("");
			}

			else if (browserName.equals("Safari")) {
				iOSCapabilities("Safari");
			}
			driver = new IOSDriver<>(new URL("http://" + appium_ip_address + ":" + appium_port + "/wd/hub"),
					capabilities);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;

	}

	public static void getBrowserNameAndVersion() {
		org.openqa.selenium.Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		webBrowserName = caps.getBrowserName();
		browserVersion = caps.getVersion();
		System.out.println(webBrowserName);
		System.out.println(browserVersion);
	}

	public static void startAppium(String nodePath, String appiumJSPath) {

		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File(nodePath)).withAppiumJS(new File(appiumJSPath)));

		if (service.isRunning()) {
			service.stop();
			service.start();

		} else {
			service.start();
		}
		try {

			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void androidCapabilities(String BrowserName) {
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, KeywordUtil.GetValue("deviceName"));
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, KeywordUtil.GetValue("platformVersion"));
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, KeywordUtil.GetValue("platformName"));
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, KeywordUtil.GetValue("automationName"));
		capabilities.setCapability(MobileCapabilityType.UDID, KeywordUtil.GetValue("deviceID"));
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, KeywordUtil.GetValue("newCommandTimeout"));

		if (BrowserName.isEmpty()) {
			File app = new File(KeywordUtil.GetValue("apkFilePath"));
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		}

	}

	public static void iOSCapabilities(String BrowserName) {
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserName);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, KeywordUtil.GetValue("ios_device_name"));
		capabilities.setCapability("launchTimeout", KeywordUtil.GetIntValue("launchTimeout"));
		capabilities.setCapability("platformName", KeywordUtil.GetValue("ios_platform_name"));
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, KeywordUtil.GetValue("ios_platform_version"));
		capabilities.setCapability("autoAcceptAlerts", true);
		if (KeywordUtil.GetValue("realDevice").equals("Y")) {
			capabilities.setCapability(MobileCapabilityType.UDID, KeywordUtil.GetValue("ios_device_id"));
		}

		if (BrowserName.contains("Safari")) {
			capabilities.setCapability("safariAllowPopups", false);
			capabilities.setCapability("safariIgnoreFraudWarning", true);
		}

		else {
			File app = new File(KeywordUtil.GetValue("ipaFilePath"));
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		}
	}
	

	/**
	 * Quit AppiumDriver
	 * 
	 */
	public static  void tearDown(){
		getScreenShot();

		// Quit the driver if the driver is not null
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Load the configuration values
	 * 
	 */
	public void loadConfigProperties() {

		String filePath = System.getProperty("user.dir") + "//config.properties";

		// Create property file instance
		config = new Properties();

		// Create File Input Stream instance
		FileInputStream fio;

		try {

			fio = new FileInputStream(filePath);

			// load properties file
			config.load(fio);

		} catch (IOException ioe) {

			ioe.printStackTrace();
		}

	}

	private static void getScreenShot() {
		String destPath = System.getProperty("user.dir") + "//src//test//resources//screenshots//" + testName + ".jpeg";

		TakesScreenshot screenshot = (TakesScreenshot) getDriver();

		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(destPath);

		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static AppiumDriver getDriver() {
		return driver;
	}

	/*public void setDriver(AppiumDriver driver) {
		this.driver = driver;
	}*/
}
