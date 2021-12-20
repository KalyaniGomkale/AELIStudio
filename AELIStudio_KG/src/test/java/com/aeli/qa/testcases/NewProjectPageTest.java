package com.aeli.qa.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;




import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.util.ExcelHandler;
import com.aeli.qa.util.TestDataHandler;



public class NewProjectPageTest extends TestBase{
	NewProjectPage newprojectpage;
	TestDataHandler testdata=new TestDataHandler();

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public NewProjectPageTest() {
		super();
	}
	//Here we are eliminating hard-coded value and adopting data driven approach
	@Test(priority = 1)
	public void validateAddNewProjectTest(Method method) throws Exception {
		extentTest = extent.createTest("validateAddNewProjectTest", "TC_01: It will create new NLU  Project");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		newprojectpage =new NewProjectPage();
		newprojectpage.validateAddNewProject("SimpleProject","Automation Trial1","2","Transfter");
		//newprojectpage.validateAddNewProject(TestDataInMap.get("ProjectName"),TestDataInMap.get("Description"),TestDataInMap.get("NoOfCategories"),TestDataInMap.get("CategoriesName"));
		extentTest.log(extentTest.getStatus(), "NLU project created successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}
	

	@Test(priority = 2)
	public void validateAddNewKMProjectTest(Method method) throws Exception {
	extentTest = extent.createTest("validateAddNewKMProjectTest", "TC_100: It will create new KM Project");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		newprojectpage =new NewProjectPage();
		newprojectpage.validateAddNewKMProject("Dem41_KM","Automation Trial1");
		//newprojectpage.validateAddNewKMProject(TestDataInMap.get("ProjectName"),TestDataInMap.get("Description"));
		extentTest.log(extentTest.getStatus(), "KM project created successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}
	
}
