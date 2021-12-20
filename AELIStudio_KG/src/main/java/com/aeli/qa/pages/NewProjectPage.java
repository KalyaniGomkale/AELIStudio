package com.aeli.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.util.Messages;

public class NewProjectPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 250);
	ProjectsPage projectspage = new ProjectsPage();
	LoginPage loginpage=new LoginPage();

	@FindBy(xpath = "//span[text()='Projects']")
	WebElement projectsTab;
	@FindBy(xpath = "//span[text()='New Project']")
	WebElement newProjectTab;
	@FindBy(name = "name")
	WebElement projectName;
	@FindBy(id = "type_id")
	WebElement projectType;
	@FindBy(xpath = "//ul/li[@data-value='1']")
	WebElement NLUProject;
	@FindBy(xpath = "//ul/li[@data-value='2']")
	WebElement KMProject;
	@FindBy(name = "description")
	WebElement description;
	@FindBy(xpath = "//span[text()='Configure']")
	WebElement configureBtn;
	@FindBy(id = "mui-component-select-numCategorizations")
	WebElement noOfCategoriesOption;
	@FindBy(xpath = "//ul/li[@data-value='2']")
	WebElement categoriesOption;
	@FindBy(xpath = "//button/span[text()='Create']")
	WebElement createBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;

	public NewProjectPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void validateAddNewProject(String ProjectName, String ProjDesc, String attempts, String CatName)
			throws Exception {
		loginpage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		// click New NLU Project Tab
		wait.until(ExpectedConditions.visibilityOf(newProjectTab));
		newProjectTab.click();
		Reporter.log("New Project Tab is clicked", true);
		projectName.sendKeys(ProjectName);
		Thread.sleep(3000);
		projectType.click();
		Thread.sleep(2000);
		NLUProject.click();
		Thread.sleep(3000);
		description.sendKeys(ProjDesc);
		Thread.sleep(3000);
		configureBtn.click();
		Reporter.log("Configure Button is clicked", true);
		Thread.sleep(3000);
		noOfCategoriesOption.click();
		Reporter.log("No. of categories selected", true);
		Thread.sleep(3000);
		int attemptsNo = Integer.parseInt(attempts);
		WebElement NoOfCategories = driver.findElement(By.xpath("//ul/li[@data-value='" + attemptsNo + "']"));
		NoOfCategories.click();
		for (int i = 0; i < attemptsNo; i++) {
			WebElement categoryNames = driver.findElement(By.name("C" + i + "_name"));
			categoryNames.sendKeys(CatName + i);
			String nameOfCategory = CatName + i;
			Reporter.log("Name of category is" + nameOfCategory, true);
		}
		Thread.sleep(3000);
		createBtn.click();
		// Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.ProjectCreation;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Project not created successfully");
		Reporter.log("Project created successfully", true);
		// now comes to verification whether project is created or not
		wait.until(ExpectedConditions.visibilityOf(projectsTab));
		projectsTab.click();
		WebElement ActualName = driver.findElement(By.xpath("//a[text()='" + ProjectName + "']"));
		String actual_projectName = ActualName.getText();
		String expected_projectName = ProjectName;
		Assert.assertEquals(actual_projectName, expected_projectName, "Project not created");
		Reporter.log("Project creared successfully and reflected in projects tab");
		Thread.sleep(3000);
		projectspage.validateLogOut();
	}

	public void validateAddNewKMProject(String ProjectName, String ProjDesc) throws Exception {
		loginpage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		// click New KM Project Tab
		wait.until(ExpectedConditions.visibilityOf(newProjectTab));
		newProjectTab.click();
		Reporter.log("New Project Tab is clicked", true);
		projectName.sendKeys(ProjectName);
		Thread.sleep(5000);
		projectType.click();
		Thread.sleep(2000);
		KMProject.click();
		Thread.sleep(3000);
		description.sendKeys(ProjDesc);
		Thread.sleep(5000);
		createBtn.click();
		// Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.ProjectCreation;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Project not created successfully");
		Reporter.log("Project created successfully", true);
		// now comes to verification whether project is created or not
		wait.until(ExpectedConditions.visibilityOf(projectsTab));
		projectsTab.click();
		WebElement ActualName = driver.findElement(By.xpath("//a[text()='" + ProjectName + "']"));
		String actual_projectName = ActualName.getText();
		String expected_projectName = ProjectName;
		Assert.assertEquals(actual_projectName, expected_projectName, "Project not created");
		Reporter.log("Project creared successfully and reflected in projects tab");
		Thread.sleep(5000);
		projectspage.validateLogOut();
	}
}
