package com.aeli.qa.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.MappingPage;
import com.aeli.qa.pages.UtterancePage;
import com.aeli.qa.util.ExcelHandler;


public class UtterancePageTest extends TestBase{
	UtterancePage utterancepage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public UtterancePageTest() {
		super();
	}
	
	@Test(priority=11)
	public void validateCreatingUtteranceTest(Method method) throws Exception {
		extentTest = extent.createTest("validateCreatingUtteranceTest", "TC_060:To verify user is able to add utterance");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		utterancepage =new UtterancePage();
	//	utterancepage.validateCreatingUtterance("KGProject","Debit rs.20000 for me");
		utterancepage.validateCreatingUtterance(TestDataInMap.get("ProjectName"),TestDataInMap.get("Utterance"));
		extentTest.log(extentTest.getStatus(), "User added utterance successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}

}
