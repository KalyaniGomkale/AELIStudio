package com.aeli.qa.testcasesSysadmin;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pagesSysadmin.TenantPageSys;
import com.aeli.qa.pagesSysadmin.UserPageSys;
import com.aeli.qa.util.ExcelHandler;

public class UserPageSysTest extends TestBase{
	UserPageSys userpage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public UserPageSysTest() {
		super();
	}
	//Here we are eliminating hard-coded value and adopting data driven approach
	@Test(priority = 15)
	public void validateCreateSysadminTest(Method method) throws Exception {
		extentTest = extent.createTest("validateCreateSysadminTest", "TC_03: To verify User is able to create a new sysadmin");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname_sysadmin"),method.getName());
		userpage =new UserPageSys();
		//userpage.validateCreateSysadmin("SysUser2", "Sys","User2", "8551960676","kal.abc3@h.com", "Pune@123");
		userpage.validateCreateSysadmin(TestDataInMap.get("UserName"),TestDataInMap.get("FirstName"),TestDataInMap.get("LastName"),TestDataInMap.get("MobileNo"),TestDataInMap.get("EmailID"),TestDataInMap.get("Password"));
		extentTest.log(extentTest.getStatus(), "Sysadmin created successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname_sysadmin"), "Pass", method.getName());
	}
	@Test(priority = 16)
	public void validateEditSysadminTest(Method method) throws Exception {
		extentTest = extent.createTest("validateEditSysadminTest", "TC_04: To verify User is able to edit the sysadmin details");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname_sysadmin"),method.getName());
		userpage =new UserPageSys();
		//userpage.validateEditSysadmin("SysUser2", "8668765767","Pune@1234");
		userpage.validateEditSysadmin(TestDataInMap.get("UserName"),TestDataInMap.get("UpdMobileNo"),TestDataInMap.get("ResetPassword"));
		extentTest.log(extentTest.getStatus(), "System admin edited successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname_sysadmin"), "Pass", method.getName());
	}
}


