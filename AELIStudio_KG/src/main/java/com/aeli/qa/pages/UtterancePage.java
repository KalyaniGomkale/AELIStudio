package com.aeli.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.util.Messages;

public class UtterancePage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 250);
	ProjectsPage projectspage = new ProjectsPage();
	LoginPage loginpage=new LoginPage();

	@FindBy(xpath = "//span[text()='Projects']")
	WebElement projectsTab;
	@FindBy(xpath = "//span[text()='Utterance']")
	WebElement utteranceTab;
	@FindBy(xpath="//span[text()='Add Utterance']")
	WebElement addUtterance;
	@FindBy(xpath="//input[@placeholder='Enter New Utterance']")
	WebElement enterUtterance;
	@FindBy(xpath="(//div/input[@placeholder='Enter New Utterance']/../../button/span[@class='MuiIconButton-label'])[1]")
	WebElement doneBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;
	//@FindBy(id="cat-0-select")
	//WebElement
	@FindBy(xpath="//input[@placeholder='Search Entity']")
	WebElement searchEntity;
	@FindBy(xpath="//div[contains(@class,'MuiBox-root')]/p[text()='saving']")
	WebElement selectEntity;
	@FindBy(xpath="//div[text()='Debit rs.10000 for me']")
	WebElement ele;
	public UtterancePage() {
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void validateCreatingUtterance(String ProjectName, String Utterance)
			throws Exception {
		loginpage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		// click Projects Tab and select desired project
		projectspage.goToProject(ProjectName);
		Thread.sleep(10000);
		// Once directed to that project click on Utterance tab
		//wait.until(ExpectedConditions.visibilityOf(utteranceTab));
		//JavascriptExecutor ja=(JavascriptExecutor)driver;
		//ja.executeScript("arguments[0].click;", utteranceTab);
		utteranceTab.click();
		Reporter.log("Utterance Tab is clicked", true);
		Thread.sleep(10000);
		addUtterance.click();
		Reporter.log("Add Utterance button is clicked", true);
		Thread.sleep(3000);
		enterUtterance.sendKeys(Utterance);
		Thread.sleep(3000);
		doneBtn.click();
		// Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.UtteranceCreation;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Utterance not created successfully");
		Reporter.log("Utterance created successfully", true);
	//Now Highlight the word
		
	//	highlighterMethod(driver,ele);
		
		projectspage.validateLogOut();
	}


}
