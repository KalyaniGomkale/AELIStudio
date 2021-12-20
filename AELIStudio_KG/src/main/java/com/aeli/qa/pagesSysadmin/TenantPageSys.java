package com.aeli.qa.pagesSysadmin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class TenantPageSys extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 250);
	ProjectsPage projectspage = new ProjectsPage();
	LoginPage loginpage=new LoginPage();

	@FindBy(xpath = "//span[text()='Tenant']")
	WebElement tenantTab;
	@FindBy(xpath="//span[text()='Create Tenants']")
	WebElement createTenantBtn;
	@FindBy(name="name")
	WebElement tenantName;
	@FindBy(name="orgCode")
	WebElement orgCode;
	@FindBy(name="description")
	WebElement description;
	@FindBy(xpath="//span[text()='Create']")
	WebElement createBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;
	@FindBy(xpath = "//button[@title='user profile']")
	WebElement userProfileBtn;
	@FindBy(xpath = "//li[text()='Logout']")
	WebElement logoutBtn;
	@FindBy(xpath = "//div[2]/button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall']")
	WebElement searchBtn;
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement searchBar;
	@FindBy(xpath = "//table/tbody/tr/td/div/button[1]/span[@class='MuiIconButton-label']")
	WebElement editBtn;
	@FindBy(xpath = "//span[text()='Update']")
	WebElement updateBtn;

	public TenantPageSys() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validateLogOut() throws Exception {
		// click User Profile Tab
		wait.until(ExpectedConditions.visibilityOf(userProfileBtn));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", userProfileBtn);
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", logoutBtn);
		// logoutBtn.click();
		Reporter.log("User logged out");
	}
	public void validateCreateNewTenant(String TenantName, String OrgCode, String TenantDesc)
			throws Exception {
		loginpage.validateLogin(prop.getProperty("username_sysadmin"), prop.getProperty("password_sysadmin"));
		// click New NLU Project Tab
		wait.until(ExpectedConditions.visibilityOf(tenantTab));
		tenantTab.click();
		Reporter.log("Tenant Tab is clicked", true);
		Thread.sleep(3000);
		createTenantBtn.click();
		Thread.sleep(6000);
		tenantName.sendKeys(TenantName);
		Thread.sleep(3000);
		orgCode.sendKeys(OrgCode);
		Thread.sleep(2000);
		description.sendKeys(TenantDesc);
		Thread.sleep(3000);
		createBtn.click();
		Reporter.log("Create Button is clicked", true);
		Thread.sleep(3000);
		// Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.TenantCreation;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Tenant not created successfully");
		Reporter.log("Tenant created successfully", true);
		// now comes to verification whether project is created or not
	/*	wait.until(ExpectedConditions.visibilityOf(projectsTab));
		projectsTab.click();
		WebElement ActualName = driver.findElement(By.xpath("//a[text()='" + ProjectName + "']"));
		String actual_projectName = ActualName.getText();
		String expected_projectName = ProjectName;
		Assert.assertEquals(actual_projectName, expected_projectName, "Project not created");
		Reporter.log("Project creared successfully and reflected in projects tab");
		Thread.sleep(3000);*/
		validateLogOut();
	}

	public void validateEditTenant(String UserName, String UpdatedDescp) throws Exception {
		loginpage.validateLogin(prop.getProperty("username_sysadmin"), prop.getProperty("password_sysadmin"));
		// click User Tab
		wait.until(ExpectedConditions.visibilityOf(tenantTab));
		tenantTab.click();
		Reporter.log("Tenant Tab is clicked", true);
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
		for(int i=0;i<30;i++) {
		description.sendKeys(Keys.BACK_SPACE);
		}
		Thread.sleep(3000);
		description.sendKeys(UpdatedDescp);
		Thread.sleep(3000);
		updateBtn.click();
		Reporter.log("Update button is clicked", true);
		// Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.EditTenantData;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Tenant not updated successfully");
		Reporter.log("Tenant updated successfully", true);
		validateLogOut();
	}

}
