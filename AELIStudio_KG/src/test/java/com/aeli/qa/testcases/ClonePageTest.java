package com.aeli.qa.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.ClonePage;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.util.ExcelHandler;
public class ClonePageTest  extends TestBase{
	ClonePage clonepage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public ClonePageTest() {
		super();
	}
	
	@Test(priority = 4)
	public void validateCloingProjectTest(Method method) throws Exception {
			extentTest = extent.createTest("validateCloingProjectTest", "TC_010: To verify project can be cloned t");
			Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
			clonepage =new ClonePage();
			//clonepage.validateCloningProject("CloneProj3", "Automating cloning trial1";
			clonepage.validateCloningProject(TestDataInMap.get("ProjectName"),TestDataInMap.get("Description"));
			extentTest.log(extentTest.getStatus(), "Project cloned successfully");
			ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}

}
