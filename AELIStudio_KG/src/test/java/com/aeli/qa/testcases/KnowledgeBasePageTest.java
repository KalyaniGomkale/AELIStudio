package com.aeli.qa.testcases;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.Test;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.KnowledgeBasePage;
import com.aeli.qa.pages.NewProjectPage;
import com.aeli.qa.util.ExcelHandler;


public class KnowledgeBasePageTest extends TestBase{
KnowledgeBasePage knowledgebasepage;

	//constructor is used to initialize object of class and super to call superclass objects and access the superclass methods and variables
	public KnowledgeBasePageTest() {
		super();
	}
	
	@Test (priority=12)
	public void validateUploadDocxTest(Method method) throws Exception { 
		extentTest = extent.createTest("validateUploadDocxTest", "TC_107:To verify user should able to upload supported document in upload document for Knowledge Base window.");
		Map<String,String> TestDataInMap=ExcelHandler.getTestDataInMap(prop.getProperty("sheetname"),method.getName());
		knowledgebasepage =new KnowledgeBasePage();
		//knowledgebasepage.validateUploadDocx("KMProject","C:\\Users\\Kalyani\\Desktop\\Office Files\\knowledgebase-template (2).csv");
		knowledgebasepage.validateUploadDocx(TestDataInMap.get("ProjectName"),TestDataInMap.get("FilePath"));
		extentTest.log(extentTest.getStatus(), "Supported docs uploaded for KM Project successfully");
		ExcelHandler.UpdateTestResultsToExcel(prop.getProperty("sheetname"), "Pass", method.getName());
	}

}
