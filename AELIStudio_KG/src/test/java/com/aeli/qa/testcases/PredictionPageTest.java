package com.aeli.qa.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.pages.PredictionPage;
import com.aeli.qa.util.ExcelHandler;


public class PredictionPageTest extends TestBase{
PredictionPage predictionpage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public PredictionPageTest() {
		super();
	}
	
	@Test(priority=13)
	public void validateFinalIntentTest(Method method) throws Exception {
		extentTest = extent.createTest("validateFinalIntentTest", "TC_087: To validate when user give valid input to predict check whether it should get some output or predictions");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		predictionpage =new PredictionPage();
	//	predictionpage.validateFinalIntent("TestProject2","Please install Teams s/w on my machine","Softwareinstallation");
		predictionpage.validateFinalIntent(TestDataInMap.get("ProjectName"),TestDataInMap.get("UtteranceToTest"),TestDataInMap.get("ExpectedFinalIntent"));
		extentTest.log(extentTest.getStatus(), "NLU project created successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}
}
