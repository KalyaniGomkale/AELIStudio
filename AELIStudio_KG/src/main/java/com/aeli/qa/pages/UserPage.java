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

public class UserPage  extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 250);
	ProjectsPage projectspage = new ProjectsPage();

	@FindBy(xpath = "//span[text()='User']")
	WebElement userTab;
	@FindBy(xpath="//span[text()='Create User']")
	WebElement createUserBtn;
	@FindBy(name="username")
	WebElement username;
	@FindBy(name="firstName")
	WebElement firstname;
	@FindBy(name="lastName")
	WebElement lastname;
	@FindBy(name="mobile")
	WebElement mobileno;
	@FindBy(name="emailAddress")
	WebElement emailId;
	@FindBy(xpath="//*[@id='password']")
	WebElement password;
	@FindBy(xpath="//div[@id='select-role']")
	WebElement role;
	@FindBy(xpath="//li[@data-value='Tenant Administrator']")
	WebElement TAuser;
	@FindBy(xpath="//span[text()='Create']")
	WebElement createBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;

	public UserPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validateCreateTA(String UserName, String FirstName, String LastName,String MobileNo,
			String EmailID,String Password )
			throws Exception {
		// click User Tab
		wait.until(ExpectedConditions.visibilityOf(userTab));
		userTab.click();
		Reporter.log("User Tab is clicked", true);
		wait.until(ExpectedConditions.visibilityOf(createUserBtn));
		createUserBtn.click();
		Reporter.log("Create user button is clicked", true);
		Thread.sleep(8000);
		username.sendKeys(UserName);
		Thread.sleep(8000);
		firstname.sendKeys(FirstName);
		Thread.sleep(3000);
		lastname.sendKeys(LastName);
		Thread.sleep(3000);
		mobileno.sendKeys(MobileNo);
		Thread.sleep(3000);
		emailId.sendKeys(EmailID);
		Thread.sleep(3000);
		password.sendKeys(Password);
		Thread.sleep(3000);
		role.click();
		Thread.sleep(6000);
		TAuser.click();
		Reporter.log("Tenant Administrator role is selected",true);
		Thread.sleep(4000);
		createBtn.click();
		Reporter.log("User is created",true);
		// Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.userCreation;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "User not created successfully");
		Reporter.log("User created successfully", true);
		projectspage.validateLogOut();
	}
}

