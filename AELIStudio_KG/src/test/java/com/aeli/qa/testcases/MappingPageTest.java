package com.aeli.qa.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.MappingPage;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.util.ExcelHandler;


public class MappingPageTest  extends TestBase{
MappingPage mappingpage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public MappingPageTest() {
		super();
	}
	
	@Test(priority=10)
	public void validateAddMappingTest(Method method) throws Exception {
		extentTest = extent.createTest("validateAddMappingTest", "TC_047: To verify user is able to add rule using intents fom different category");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		mappingpage =new MappingPage();
		//mappingpage.validateAddMapping("KGProject","ENQ6","Choose account type");
		mappingpage.validateAddMapping(TestDataInMap.get("ProjectName"),TestDataInMap.get("FinalIntentName"),TestDataInMap.get("IntentToSearch1"));
		extentTest.log(extentTest.getStatus(), "User added rule successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}

}
