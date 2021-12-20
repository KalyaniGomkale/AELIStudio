package com.aeli.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.util.Messages;

public class IntentPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 300);
	ProjectsPage projectspage = new ProjectsPage();

	@FindBy(xpath = "//span[text()='Intent']")
	WebElement intentTab;
	@FindBy(xpath = "//span[text()='New Intent']")
	WebElement newIntentBtn;
	@FindBy(xpath = "//input[@name='name']")
	WebElement name;
	@FindBy(id = "select-category")
	WebElement category;
	@FindBy(xpath = "//ul/li[@tabindex='-1']")
	WebElement firstCategory;
	@FindBy(xpath = "//span[text()='Create']")
	WebElement createBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;

	public IntentPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validateCreatingIntent(String ProjectName, String NameOfIntent) throws Exception {
		// click Projects Tab and select desired project
		projectspage.goToProject(ProjectName);
		// Once directed to that project click on intent tab
		wait.until(ExpectedConditions.visibilityOf(intentTab));
		intentTab.click();
		Reporter.log("Intent Tab is clicked", true);
		Thread.sleep(3000);
		newIntentBtn.click();
		Reporter.log("New Intent button is clicked", true);
		Thread.sleep(3000);
		name.sendKeys(NameOfIntent);
		Thread.sleep(3000);
		category.click();
		Thread.sleep(6000);
		firstCategory.click();
		Reporter.log("First category is selected from the list", true);
		Thread.sleep(3000);
		createBtn.click();
		Reporter.log("create button is clicked", true);
		// now comes to verification and Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.IntentCreation;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Intent not created successfully");
		Reporter.log("Intent created successfully", true);
		projectspage.validateLogOut();
	}

}
