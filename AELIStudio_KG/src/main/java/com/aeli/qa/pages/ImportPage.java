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

public class ImportPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 250);
	ProjectsPage projectspage = new ProjectsPage();

	@FindBy(xpath = "//span[text()='Projects']")
	WebElement projectsTab;
	@FindBy(xpath = "//span[text()='Import']")
	WebElement importTab;
	@FindBy(name = "name")
	WebElement projectName;
	@FindBy(name = "description")
	WebElement description;
	@FindBy(xpath = "//div/input[@type='file']")
	WebElement uploadFile;
	@FindBy(xpath = "//span[text()='Configure']")
	WebElement configureBtn;
	@FindBy(id = "mui-component-select-numCategorizations")
	WebElement noOfCategoriesOption;
	@FindBy(xpath = "//ul/li[@data-value='2']")
	WebElement categoriesOption;
	@FindBy(xpath = "//button/span[text()='Create']")
	WebElement createBtn;
	@FindBy(xpath = "//button/span[text()='Import']")
	WebElement importBtn;
	@FindBy(xpath = "//div[@class='MuiBox-root jss62']/div/div[1]/button/span[@class='MuiIconButton-label']")
	WebElement refreshBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;

	public ImportPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void validateImportProject(String ProjectName, String ProjDesc, String path) throws Exception {
		// click Import Tab
		wait.until(ExpectedConditions.visibilityOf(importTab));
		importTab.click();
		Reporter.log("Import Tab is clicked", true);
		projectName.sendKeys(ProjectName);
		Thread.sleep(3000);
		description.sendKeys(ProjDesc);
		Thread.sleep(3000);
		uploadFile.sendKeys(path);
		Thread.sleep(3000);
		importBtn.click();
		// Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.ImportProject;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Project import not started");
		Reporter.log("Project import strated successfully", true);
		// now comes to verification whether project is imported or not
		wait.until(ExpectedConditions.visibilityOf(projectsTab));
		projectsTab.click();
		Thread.sleep(2000);
		refreshBtn.click();
		WebElement ActualName = driver.findElement(By.xpath("//a[text()='" + ProjectName + "']"));
		String actual_projectName = ActualName.getText();
		String expected_projectName = ProjectName;
		Assert.assertEquals(actual_projectName, expected_projectName, "Project not imported");
		Reporter.log("Project imported successfully and reflected in projects tab");
		Thread.sleep(3000);
		projectspage.validateLogOut();
	}
}
