package com.test.scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.KeywordUtil;
import com.pages.APIHelper;
import com.pages.AndroidApplicationFunctions;
import com.test.pages.BaseTest;

public class TestScripts_002 extends BaseTest {

	@Test
	public void TestTaskTwo() throws InterruptedException {
		// Create Home Page Object
		AndroidApplicationFunctions.getInstance(driver);
		AndroidApplicationFunctions.loginToApplication(KeywordUtil.GetValue("UserName"),KeywordUtil.GetValue("Password"));
		AndroidApplicationFunctions.verifyNewTaskButton();
		Assert.assertTrue(APIHelper.getresponse(),"API Response not found");
		AndroidApplicationFunctions.verifyNewTaskDetails(KeywordUtil.GetValue("Taskactivityname"), KeywordUtil.GetValue("Tasklocation"),
				KeywordUtil.GetValue("Taskstarttime"), KeywordUtil.GetValue("Taskexpcount"));
		AndroidApplicationFunctions.verifyAddTaskButton();
		AndroidApplicationFunctions.verifyDashboardTasklabels();
		AndroidApplicationFunctions.verifyDashboardTaskDetails();
		
	}

}
