package com.aeli.qa.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.EntityPage;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.util.ExcelHandler;


public class EntityPageTest extends TestBase{
	EntityPage entitypage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public EntityPageTest() {
		super();
	}
	
	@Test(priority=9)
	public void validateCreatingEntitiesTest(Method method) throws Exception {
		extentTest = extent.createTest("validateCreatingEntitiesTest", "TC_038: To verify user can add new enitity");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		entitypage =new EntityPage();
		//entitypage.validateCreatingEntities("Demo_Auto","Software","Microsoft excel");
		entitypage.validateCreatingEntities(TestDataInMap.get("ProjectName"),TestDataInMap.get("NameOfEntity"),TestDataInMap.get("ValueOfEntity"));
		extentTest.log(extentTest.getStatus(), "User is able to add entities successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}

}


