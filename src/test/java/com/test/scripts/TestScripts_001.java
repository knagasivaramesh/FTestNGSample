package com.test.scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.KeywordUtil;
import com.pages.APIHelper;
import com.pages.AndroidApplicationFunctions;
import com.test.pages.BaseTest;

public class TestScripts_001 extends BaseTest {

	@Test
	public void TestTaskOne() throws InterruptedException {
		// Create Home Page Object
		AndroidApplicationFunctions.getInstance(driver);
		AndroidApplicationFunctions.loginToApplication(KeywordUtil.GetValue("UserName"),KeywordUtil.GetValue("Password"));
		AndroidApplicationFunctions.verifyDashboardTasklabels();
		AndroidApplicationFunctions.verifyNewTaskButton();
		Assert.assertTrue(APIHelper.getresponse(),"API Response not found");
		
	}

}
