package com.aeli.qa.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.pages.UserPage;
import com.aeli.qa.util.ExcelHandler;

public class UserPageTest extends TestBase{
	UserPage userpage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public UserPageTest() {
		super();
	}
	//Here we are eliminating hard-coded value and adopting data driven approach
	@Test(priority = 5)
	public void validateCreateTATest(Method method) throws Exception {
		extentTest = extent.createTest("validateCreateTATest", "TC_012: To verify user can able to create tenant admin user");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		userpage =new UserPage();
		//userpage.validateCreateTA("KalGoms2", "Kalu2","Goms2", "8551960675","kal.abc2@h.com", "Pune@123");
		userpage.validateCreateTA(TestDataInMap.get("UserName"),TestDataInMap.get("FirstName"),TestDataInMap.get("LastName"),TestDataInMap.get("MobileNo"),TestDataInMap.get("EmailID"),TestDataInMap.get("Password"));
		extentTest.log(extentTest.getStatus(), "Tenant User created successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
		
		
	}

}
