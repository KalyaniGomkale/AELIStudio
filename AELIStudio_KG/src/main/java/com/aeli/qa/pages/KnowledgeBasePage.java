package com.aeli.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.util.Messages;

public class KnowledgeBasePage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 300);
	ProjectsPage projectspage=new ProjectsPage();
	LoginPage loginpage=new LoginPage();

	@FindBy(xpath = "//span[text()='Knowledge Base']")
	WebElement knowledgeBaseTab;
	@FindBy(xpath = "//span[text()='Upload']")
	WebElement uploadBtn;
	@FindBy(xpath="//div[@class='MuiDropzoneArea-textContainer']/p")
	WebElement uploadFileBox;
	@FindBy(xpath="//div[contains(@class,'MuiDropzoneArea-root')]/input")
	WebElement insertFile;
	@FindBy(xpath="(//span[text()='Upload'])[2]")
	WebElement uploadFileBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;

	
	
//(//div[contains(@class,'MuiBox-root')]//input[@placeholder='Search intent'])[3] change 1,2,3 for 3 categories
	public KnowledgeBasePage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validateUploadDocx(String ProjectName,String filePath) throws Exception {
		loginpage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		// click Projects Tab and select desired project
		projectspage.goToProject(ProjectName);
		Thread.sleep(10000);
		// Once directed to that project click on Knowledge base tab
		knowledgeBaseTab.click();
		Reporter.log("Knowledge Base Tab is clicked", true);
		Thread.sleep(10000);
		uploadBtn.click();
		Reporter.log("Upload button is clicked", true);
		Thread.sleep(3000);
	    insertFile.sendKeys(filePath);
		Thread.sleep(3000);
		Reporter.log("File is uploaded",true);
		wait.until(ExpectedConditions.elementToBeClickable(uploadFileBtn));
		uploadFileBtn.click();
		Reporter.log("Upload File button is clicked", true);
	//	Thread.sleep(3000);
		// now comes to verification and Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.KnowledgeArticleStart;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Knowledge article upload not started successfully");
		Reporter.log("Knowledge article upload started successfully", true);
		Thread.sleep(3000);
		projectspage.validateLogOut();
	}



}
