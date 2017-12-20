package com.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AndroidApplicationFunctions extends KeywordUtil {
	
	//private static AndroidApplicationFunctions homePage = null;
	public static AndroidApplicationFunctions Page = null;
	
	/**
	 * HomePage constructor
	 * 
	 * @param driver	  AppiumDriver instance
	 */
	public AndroidApplicationFunctions(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	/**
	 * Create the instance for the HomePage class if the instance is not created
	 * 
	 * @param driver		WebDriver driver instance
	 * @return		Return HomePage instance
	 */
	public static AndroidApplicationFunctions getInstance(AppiumDriver<MobileElement> driver) {
		if (null == Page) {
			Page = new AndroidApplicationFunctions(driver);
		}
		
		return Page;
	}
	
	/**
	 * Login to application with the given text and click on login
	 * 
	 * @param username
	 * @param Password
	 * @return	Returns boolean value 'true'
	 */
	public static boolean loginToApplication(String username, String Password) throws InterruptedException {
		//boolean flag=false;
		executionDelay(5000);
		//if(isElementPresent(TaskApp.AndroidConstants.TaskApplication.input_User)){
		type(TaskApp.AndroidConstants.TaskApplication.input_User, username);
		type(TaskApp.AndroidConstants.TaskApplication.input_Pswd, Password);
		verifyLoginButton();
		boolean flag=true;
		//}
	return flag;
	}
	/**
	 * Add new task
	 * 
	 * @return	Returns boolean value 'true'
	 */
	
	public static boolean verifyNewTaskButton() throws InterruptedException
	{
		boolean flag=false;
		if(isElementPresent(TaskApp.AndroidConstants.TaskApplication.btn_NewTask)){
			click(TaskApp.AndroidConstants.TaskApplication.btn_NewTask);
			executionDelay(2000);

			flag=true;
		}
			return flag;
		}
	/**
	 * Click on Add task 
	 *  
	 * @return	Returns boolean value 'true'
	 */
	public static boolean verifyAddTaskButton() throws InterruptedException
	{
		boolean flag=false;
		if(isElementPresent(TaskApp.AndroidConstants.TaskApplication.btn_AddTask)){
		click(TaskApp.AndroidConstants.TaskApplication.btn_AddTask);
			executionDelay(2000);

			flag=true;
		}
			return flag;
		}
	/**
	 * Click on login 
	 *  
	 * @return	Returns boolean value 'true'
	 */
	public static boolean verifyLoginButton() throws InterruptedException
	{
		boolean flag=false;
		if(isElementPresent(TaskApp.AndroidConstants.TaskApplication.btn_Login)){
			click(TaskApp.AndroidConstants.TaskApplication.btn_Login);
			executionDelay(5000);

			flag=true;
		}
			return flag;
		}
	/**
	 * New Task details with the given text
	 * 
	 * @param activityname
	 * @param location
	 * @param starttime
	 * @param expcount
	 * @return	Returns boolean value 'true'
	 */
	public static boolean verifyNewTaskDetails(String activityname, String location, String starttime, String expcount) throws InterruptedException {
		boolean flag=false;
		if(isElementPresent(TaskApp.AndroidConstants.TaskApplication.input_ActivityName)){
		type(TaskApp.AndroidConstants.TaskApplication.input_ActivityName, activityname);
		hideKeyboard();
		type(TaskApp.AndroidConstants.TaskApplication.input_Location, location);
		hideKeyboard();
		type(TaskApp.AndroidConstants.TaskApplication.input_StartTime, starttime);
		hideKeyboard();
		type(TaskApp.AndroidConstants.TaskApplication.input_ExpCount, expcount);
		hideKeyboard();
		flag=true;
		}
	return flag;
	}
	/**
	 * verify dashboard task labels
	 * 
	 */
	public static boolean verifyDashboardTasklabels() throws InterruptedException {
		boolean flag=false;
		if(isElementPresent(TaskApp.AndroidConstants.TaskApplication.label_ActiveTasks)){
		isElementPresent(TaskApp.AndroidConstants.TaskApplication.label_Activity);
		isElementPresent(TaskApp.AndroidConstants.TaskApplication.label_Location);
		isElementPresent(TaskApp.AndroidConstants.TaskApplication.label_StartTime);
		isElementPresent(TaskApp.AndroidConstants.TaskApplication.label_ExpCount);
		flag=true;
		if(isElementPresent(TaskApp.AndroidConstants.TaskApplication.label_CompletedTasks)){
			flag=true;
		}
		}
	return flag;
	}
	
	/**
	 * verify dashboard task details
	 * @return true
	 * @throws InterruptedException
	 */
	public static boolean verifyDashboardTaskDetails() throws InterruptedException {
		boolean flag=false;
		if(isElementPresent(TaskApp.AndroidConstants.TaskApplication.label_ActiveTasks)){
		isElementPresent(TaskApp.AndroidConstants.TaskApplication.ActivityName);
		isElementPresent(TaskApp.AndroidConstants.TaskApplication.Location);
		isElementPresent(TaskApp.AndroidConstants.TaskApplication.StartTime);
		isElementPresent(TaskApp.AndroidConstants.TaskApplication.ExpCount);
		flag=true;
		}
	return flag;
	}

}
