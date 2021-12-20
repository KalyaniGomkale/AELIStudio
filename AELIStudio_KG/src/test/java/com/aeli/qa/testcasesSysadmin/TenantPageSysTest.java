package com.aeli.qa.testcasesSysadmin;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.pagesSysadmin.TenantPageSys;
import com.aeli.qa.util.ExcelHandler;
import com.aeli.qa.util.TestDataHandler;

public class TenantPageSysTest extends TestBase{
	TenantPageSys tenantpage;
	TestDataHandler testdata=new TestDataHandler();

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public TenantPageSysTest() {
		super();
	}
	//Here we are eliminating hard-coded value and adopting data driven approach
	@Test(priority = 13)
	public void validateCreateNewTenantTest(Method method) throws Exception {
		extentTest = extent.createTest("validateCreateNewTenantTest", "TC_01: To verify user is able to create a new tenant");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname_sysadmin"),method.getName());
		tenantpage =new TenantPageSys();
		//tenantpage.validateCreateNewTenant("TestingAuto1", "ORGTA1","For sanity");
		tenantpage.validateCreateNewTenant(TestDataInMap.get("TenantName"),TestDataInMap.get("OrgCode"),TestDataInMap.get("TenantDesc"));
		extentTest.log(extentTest.getStatus(), "Tenant User created successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname_sysadmin"), "Pass", method.getName());
	}

	@Test(priority = 14)
	public void validateEditTenantTest(Method method) throws Exception {
		extentTest = extent.createTest("validateEditTenantTest", "TC_06: To verify User is able to edit the tenant admin details ");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname_sysadmin"),method.getName());
		tenantpage =new TenantPageSys();
		//tenantpage.validateEditTenant("TestingAuto1","For sanity2");
		tenantpage.validateEditTenant(TestDataInMap.get("TenantName"),TestDataInMap.get("UpdatedDescp"));
		extentTest.log(extentTest.getStatus(), "Tenant User edited successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname_sysadmin"), "Pass", method.getName());
	}
}
