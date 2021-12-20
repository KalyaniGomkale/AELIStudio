package com.aeli.qa.testcases;
import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.pages.ProjectsPage;
import com.aeli.qa.pages.TrainPage;
import com.aeli.qa.util.ExcelHandler;


public class TrainPageTest  extends TestBase{
		TrainPage trainpage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public TrainPageTest() {
		super();
	}
	
	@Test(priority=11)
	public void validateTrainProjectTest(Method method) throws Exception {
		extentTest = extent.createTest("validateTrainProjectTest", "TC_069: To validate user is able to train the model");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		trainpage =new TrainPage();
		//trainpage.validateTrainProject("KGProject");
		trainpage.validateTrainProject(TestDataInMap.get("ProjectName"));
		extentTest.log(extentTest.getStatus(), "User trained model successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
		
	}
}
