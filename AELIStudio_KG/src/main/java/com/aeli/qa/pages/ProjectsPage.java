package com.aeli.qa.pages;

import java.util.concurrent.TimeUnit;

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

public class ProjectsPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 300);

	@FindBy(xpath = "//span[text()='Projects']")
	WebElement projectsTab;
	@FindBy(xpath = "//button[@title='user profile']")
	WebElement userProfileBtn;
	@FindBy(xpath = "//li[text()='Logout']")
	WebElement logoutBtn;
	@FindBy(xpath = "//div/button[@aria-haspopup='true']")
	WebElement moreAboutProj;
	@FindBy(xpath = "//li[@role='menuitem']")
	WebElement deleteProject;
	@FindBy(xpath = "//button/span[text()='Yes']")
	WebElement yesBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;
	@FindBy(xpath = "//div[2]/button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall']")
	WebElement searchBtn;
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement searchBar;
	@FindBy(xpath = "//td[text()='No project found']")
	WebElement projectResult;
	@FindBy(xpath = "//span[text()='Export Project']")
	WebElement exportProjectBtn;
	@FindBy(xpath = "//div/button[@title='Jobs']")
	WebElement jobsBtn;

	public ProjectsPage() {
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

	public void validateDeleteProject(String ProjectName) throws Exception {
		// click Projects Tab
		wait.until(ExpectedConditions.visibilityOf(projectsTab));
		projectsTab.click();
		Reporter.log("Projects Tab is clicked", true);
		Thread.sleep(3000);
		WebElement ProjectToBeDeletedName = driver.findElement(By.xpath("//a[text()='" + ProjectName + "']"));
		ProjectToBeDeletedName.click();
		// driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		// moreAboutProj.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", moreAboutProj);
		Thread.sleep(5000);
		deleteProject.click();
		Thread.sleep(5000);
		yesBtn.click();
		Reporter.log("Clicked on yes for deletion of project", true);
		// now comes to verification and Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.ProjectDeletion;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Project not deleted successfully");
		Reporter.log("Project deleted successfully", true);
		Thread.sleep(3000);
		validateLogOut();
	}

	public void validateDeletionFromRecords(String ProjectName) throws Exception {
		// click Projects Tab
		wait.until(ExpectedConditions.visibilityOf(projectsTab));
		projectsTab.click();
		Reporter.log("Projects Tab is clicked", true);
		Thread.sleep(3000);
		searchBtn.click();
		Reporter.log("Search button is clicked", true);
		Thread.sleep(3000);
		searchBar.sendKeys(ProjectName);
		Thread.sleep(3000);
		String actual_projectResult = projectResult.getText();
		System.out.println("Record result:" + actual_projectResult);
		String Expected_projectResult = "No project found";
		Assert.assertEquals(actual_projectResult, Expected_projectResult, "Project not deleted successfully");
		Reporter.log("Project deleted suceessfully and doesnt exist in project list", true);
		validateLogOut();
	}

	public void validateDownloadExportProject(String ProjectName) throws Exception {
		// click Projects Tab
		wait.until(ExpectedConditions.visibilityOf(projectsTab));
		projectsTab.click();
		Reporter.log("Projects Tab is clicked", true);
		Thread.sleep(3000);
		searchBtn.click();
		Reporter.log("Search button is clicked", true);
		Thread.sleep(3000);
		searchBar.sendKeys(ProjectName);
		Thread.sleep(3000);
		WebElement ProjectToBeFoundName = driver.findElement(By.xpath("//a[text()='" + ProjectName + "']"));
		String actual_projectResult = ProjectToBeFoundName.getText();
		System.out.println("Record result:" + actual_projectResult);
		String Expected_projectResult = ProjectName;
		Assert.assertEquals(actual_projectResult, Expected_projectResult, "Project not found.");
		Reporter.log("Project found in project list", true);
		Thread.sleep(3000);
		// WebElement ProjectToBeFoundName = driver.findElement(By.xpath("//a[text()='"
		// + ProjectName + "']"));
		ProjectToBeFoundName.click();
		Thread.sleep(5000);
		exportProjectBtn.click();
		Reporter.log("Export project button is clicked", true);
		// now comes to verification and Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.ProjectExport;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Project export not started");
		Reporter.log("Project export started successfully", true);
		Thread.sleep(3000);
		// Now downloading exported project
		jobsBtn.click();
		Reporter.log("Jobs button is clicked", true);
		Thread.sleep(3000);
		WebElement projectDownload = driver.findElement(By.xpath("//div/p[contains(text(),'" + ProjectName
				+ "')]/parent::div/parent::div/div/button/span[text()='Download']"));
		projectDownload.click();
		Reporter.log("Download button is clicked and exported project is downloaded successfully", true);
		Thread.sleep(7000);
		validateLogOut();
	}

	public void goToProject(String ProjectName) throws Exception {
		// click Projects Tab
		wait.until(ExpectedConditions.visibilityOf(projectsTab));
		projectsTab.click();
		Reporter.log("Projects Tab is clicked", true);
		Thread.sleep(3000);
		searchBtn.click();
		Reporter.log("Search button is clicked", true);
		Thread.sleep(3000);
		searchBar.sendKeys(ProjectName);
		Thread.sleep(3000);
		//select desired project
		WebElement ProjectToBeSelect = driver.findElement(By.xpath("//a[text()='" + ProjectName + "']"));
		ProjectToBeSelect.click();
		Thread.sleep(3000);
	}
}
