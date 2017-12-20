package com.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class iOSApplicationFunctions extends KeywordUtil {
	
	//private static AndroidApplicationFunctions homePage = null;
	public static iOSApplicationFunctions Page = null;
	
	/**
	 * HomePage constructor
	 * 
	 * @param driver	  AppiumDriver instance
	 */
	public iOSApplicationFunctions(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	/**
	 * Create the instance for the HomePage class if the instance is not created
	 * 
	 * @param driver		WebDriver driver instance
	 * @return		Return HomePage instance
	 */
	public static iOSApplicationFunctions getInstance(AppiumDriver<MobileElement> driver) {
		if (null == Page) {
			Page = new iOSApplicationFunctions(driver);
		}
		
		return Page;
	}

}
