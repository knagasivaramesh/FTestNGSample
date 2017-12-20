package com.test.pages;


import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.pages.KeywordUtil;
import com.pages.DriverUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class BaseTest {

	protected AppiumDriver<MobileElement> driver;
	/*private Properties config;
	private String testName;*/

	/**
	 * Create AppiumDriver instance
	 * 
	 */
	@BeforeMethod(alwaysRun = true)
	public void launch(){
		if(KeywordUtil.GetValue("platformName").contains("Android")){
		driver= DriverUtil.getAndroidDriver(KeywordUtil.GetValue("browserName"));
		}
		else if(KeywordUtil.GetValue("platformName").contains("iOS")){
		driver= DriverUtil.getIOSDriver(KeywordUtil.GetValue("browserName"));
		}
	}
	/**
	 * Quit AppiumDriver
	 * 
	 */
	@AfterMethod(alwaysRun = true)
	public void Quit(){	
		DriverUtil.tearDown();
		//driver.quit();
	}
	
}
