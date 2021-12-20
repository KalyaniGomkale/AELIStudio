package com.aeli.qa.pagesSysadmin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.pages.LoginPage;
import com.aeli.qa.pages.ProjectsPage;
import com.aeli.qa.util.Messages;

public class UserPageSys extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 250);
	TenantPageSys tenantpage = new TenantPageSys();
	LoginPage loginpage = new LoginPage();

	@FindBy(xpath = "//span[text()='User']")
	WebElement userTab;
	@FindBy(xpath = "//span[text()='Create User']")
	WebElement createUserBtn;
	@FindBy(name = "username")
	WebElement username;
	@FindBy(name = "firstName")
	WebElement firstname;
	@FindBy(name = "lastName")
	WebElement lastname;
	@FindBy(name = "mobile")
	WebElement mobileno;
	@FindBy(name = "emailAddress")
	WebElement emailId;
	@FindBy(xpath = "//*[@id='password']")
	WebElement password;
	@FindBy(xpath = "//div[@id='select-role']")
	WebElement role;
	@FindBy(xpath = "//li[@data-value='System Administrator']")
	WebElement Sysuser;
	@FindBy(xpath = "//span[text()='Create']")
	WebElement createBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;
	@FindBy(xpath = "//div[2]/button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall']")
	WebElement searchBtn;
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement searchBar;
	@FindBy(xpath = "//table/tbody/tr/td/div/button[1]/span[@class='MuiIconButton-label']")
	WebElement editBtn;
	@FindBy(xpath = "//span[text()='Update']")
	WebElement updateBtn;

	public UserPageSys() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validateCreateSysadmin(String UserName, String FirstName, String LastName, String MobileNo,
			String EmailID, String Password) throws Exception {
		loginpage.validateLogin(prop.getProperty("username_sysadmin"), prop.getProperty("password_sysadmin"));
		// click User Tab
		wait.until(ExpectedConditions.visibilityOf(userTab));
		userTab.click();
		Reporter.log("User Tab is clicked", true);
		wait.until(ExpectedConditions.visibilityOf(createUserBtn));
		createUserBtn.click();
		Reporter.log("Create user button is clicked", true);
		Thread.sleep(8000);
		username.clear();
		username.sendKeys(UserName);
		Thread.sleep(8000);
		firstname.clear();
		firstname.sendKeys(FirstName);
		Thread.sleep(3000);
		lastname.clear();
		lastname.sendKeys(LastName);
		Thread.sleep(3000);
		mobileno.clear();
		mobileno.sendKeys(MobileNo);
		Thread.sleep(3000);
		emailId.clear();
		emailId.sendKeys(EmailID);
		Thread.sleep(3000);
		password.clear();
		password.sendKeys(Password);
		Thread.sleep(3000);
		role.click();
		Thread.sleep(6000);
		Sysuser.click();
		Reporter.log("System Administrator role is selected", true);
		Thread.sleep(4000);
		createBtn.click();
		Reporter.log("Sysadmin is created", true);
		// Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.userCreation;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Sysadmin not created successfully");
		Reporter.log("Sysadmin created successfully", true);
		tenantpage.validateLogOut();
	}

	public void validateEditSysadmin(String UserName, String UpdMobileNo,String ResetPassword) throws Exception {
		loginpage.validateLogin(prop.getProperty("username_sysadmin"), prop.getProperty("password_sysadmin"));
		// click User Tab
		wait.until(ExpectedConditions.visibilityOf(userTab));
		userTab.click();
		Reporter.log("User Tab is clicked", true);
		Thread.sleep(3000);
		searchBtn.click();
		Thread.sleep(3000);
		searchBar.sendKeys(UserName);
		Thread.sleep(3000);
		WebElement SysUserToSearch = driver.findElement(By.xpath("//td[text()='" + UserName + "']"));
		String actual_projectResult = SysUserToSearch.getText();
		System.out.println("Record result:" + actual_projectResult);
		String Expected_projectResult = UserName;
		Assert.assertEquals(actual_projectResult, Expected_projectResult, "Sysadmin not present in table");
		Reporter.log("Syadmin fund in list", true);
		editBtn.click();
		Reporter.log("Edit button is clicked", true);
		Thread.sleep(3000);
		for(int i=0;i<12;i++) {
		mobileno.sendKeys(Keys.BACK_SPACE);
		}
		Thread.sleep(3000);
		mobileno.sendKeys(UpdMobileNo);
		Thread.sleep(3000);
		password.sendKeys(ResetPassword);
		Thread.sleep(3000);
		updateBtn.click();
		Reporter.log("Update button is clicked", true);
		// Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.EditSysadminData;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Sysadmin not updated successfully");
		Reporter.log("Sysadmin updated successfully", true);
		tenantpage.validateLogOut();
	}
}
