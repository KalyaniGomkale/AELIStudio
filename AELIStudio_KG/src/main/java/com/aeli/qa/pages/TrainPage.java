package com.aeli.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class TrainPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 300);
	ProjectsPage projectspage=new ProjectsPage();

	@FindBy(xpath = "//div/span[text()='Train']")
	WebElement trainTab;
	@FindBy(xpath = "//button/span[text()='Train']")
	WebElement trainBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;
	
	public TrainPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validateTrainProject(String ProjectName) throws Exception {
		// click Projects Tab and select desired project
		projectspage.goToProject(ProjectName);
		Thread.sleep(8000);
		// Once directed to that project click on Mapping tab
		//wait.until(ExpectedConditions.visibilityOf(mappingTab));
		//JavascriptExecutor ja=(JavascriptExecutor)driver;
		//ja.executeScript("arguments[0].click;", trainTab);
		trainTab.click();
		Reporter.log("Train Tab is clicked", true);
		Thread.sleep(10000);
		trainBtn.click();
		Reporter.log("Train button is clicked", true);
		Thread.sleep(5000);
		// now comes to verification and Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.ModelBuilding;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Model building not started");
		Reporter.log("Model building started successfully", true);
		projectspage.validateLogOut();
	}


}
