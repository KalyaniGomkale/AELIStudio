package com.aeli.qa.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.pages.ProjectsPage;
import com.aeli.qa.util.ExcelHandler;


public class ProjectsPageTest  extends TestBase{
	ProjectsPage projectspage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public ProjectsPageTest() {
		super();
	}
	//no need to change data 
	@Test(priority=6)
	public void validateDownloadExportProjectTest(Method method) throws Exception {
		extentTest = extent.createTest("validateDownloadExportProjectTest", "TC_027: Verify if user export project then validate he/she can download from notifications tab.");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		projectspage =new ProjectsPage();
		//projectspage.validateDownloadExportProject("KGProject");
		projectspage.validateDownloadExportProject(TestDataInMap.get("ProjectName"));
		extentTest.log(extentTest.getStatus(), "User can download exported project successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
		
	}
	//Need same data for below test cases, run this two at end 
	@Test(priority=19)
	public void validatedeleteProjectTest(Method method) throws Exception {
		extentTest = extent.createTest("validatedeleteProjectTest", "TC_025: To verify user can delete the project");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		projectspage =new ProjectsPage();
		//projectspage.validateDeleteProject("ImportTrial2");
		projectspage.validateDeleteProject(TestDataInMap.get("ProjectName"));
		extentTest.log(extentTest.getStatus(), "Project deleted successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());	
	}
	
	//below test case is dependent on above test case
	@Test(priority=20,dependsOnMethods="validatedeleteProjectTest")
	public void validateDeletionFromRecordsTest(Method method) throws Exception {
		extentTest = extent.createTest("validateDeletionFromRecordsTest", "TC_026: To verify after deleting the project it is present on projects tab");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		projectspage =new ProjectsPage();
		//projectspage.validateDeletionFromRecords("ImportTrial2");
		projectspage.validateDeletionFromRecords(TestDataInMap.get("ProjectName"));
		extentTest.log(extentTest.getStatus(), "project deleted from project list successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
		
		
	}
}
