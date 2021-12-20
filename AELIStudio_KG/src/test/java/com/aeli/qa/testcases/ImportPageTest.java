package com.aeli.qa.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.ImportPage;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.util.ExcelHandler;

public class ImportPageTest extends TestBase{
	ImportPage importpage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public ImportPageTest() {
		super();
	}
	
	@Test(priority = 3)
	public void validateImportProjectTest(Method method) throws Exception {
		extentTest = extent.createTest("validateAddNewProjectTest", "TC_06: To verify user can import a project");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		importpage =new ImportPage();
		importpage.validateImportProject(TestDataInMap.get("ProjectName"),TestDataInMap.get("Description"),TestDataInMap.get("FilePath"));
		//importpage.validateImportProject("ImportTri1","Automating trial 1", "C:\\Users\\Kalyani\\Desktop\\Office Files\\_project48_v1.zip");
		extentTest.log(extentTest.getStatus(), "User can import project successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}
	
}
