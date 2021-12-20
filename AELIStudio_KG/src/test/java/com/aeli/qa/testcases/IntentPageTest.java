package com.aeli.qa.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.IntentPage;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.pages.ProjectsPage;
import com.aeli.qa.util.ExcelHandler;

public class IntentPageTest  extends TestBase{
	IntentPage intentpage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public IntentPageTest() {
		super();
	}
	
	@Test(priority=7)
	public void validateCreatingIntentTest(Method method) throws Exception {
		extentTest = extent.createTest("validateCreatingIntentTest", "TC_029: To verify if user is able to create new intent ");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		intentpage =new IntentPage();
		//intentpage.validateCreatingIntent("Demo_Auto","Transfer money");
		intentpage.validateCreatingIntent(TestDataInMap.get("ProjectName"),TestDataInMap.get("Intent"));
		extentTest.log(extentTest.getStatus(), "User created intent successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}

}
