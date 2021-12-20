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

public class ClonePage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 250);
	ProjectsPage projectspage = new ProjectsPage();

	@FindBy(xpath = "//span[text()='Projects']")
	WebElement projectsTab;
	@FindBy(xpath = "//span[text()='Clone']")
	WebElement cloneTab;
	@FindBy(name = "name")
	WebElement projectName;
	@FindBy(name = "description")
	WebElement description;
	@FindBy(id = "mui-component-select-projectId")
	WebElement projectToBeClonedWith;
	@FindBy(xpath = "//li[@data-value='48']")
	WebElement selectProject;
	@FindBy(xpath = "//button/span[text()='Clone']")
	WebElement cloneBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;
	@FindBy(xpath = "//div[@class='MuiBox-root jss62']/div/div[1]/button/span[@class='MuiIconButton-label']")
	WebElement refreshBtn;

	public ClonePage() {
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void validateCloningProject(String ProjectName, String ProjDesc) throws Exception {
		// click Clone Tab
		wait.until(ExpectedConditions.visibilityOf(cloneTab));
		cloneTab.click();
		Reporter.log("Clone Tab is clicked", true);
		projectName.sendKeys(ProjectName);
		Thread.sleep(3000);
		description.sendKeys(ProjDesc);
		Thread.sleep(10000);
		projectToBeClonedWith.click();
		Thread.sleep(5000);
		selectProject.click();
		Reporter.log("Project to be cloned with is selected from dropdown", true);
		cloneBtn.click();
		Reporter.log("Clone button is clicked", true);
		// Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.cloningProject;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Project cloning started successfully");
		Reporter.log("Project cloneing started successfully", true);
		// now comes to verification whether project is created or not
		wait.until(ExpectedConditions.visibilityOf(projectsTab));
		projectsTab.click();
		Thread.sleep(2000);
		refreshBtn.click();
		WebElement ActualName = driver.findElement(By.xpath("//a[text()='" + ProjectName + "']"));
		String actual_projectName = ActualName.getText();
		String expected_projectName = ProjectName;
		Assert.assertEquals(actual_projectName, expected_projectName, "Project not created");
		Reporter.log("Project creared successfully and reflected in projects tab");
		Thread.sleep(3000);
		projectspage.validateLogOut();
	}
	
	public void validateImportWithDuplicateName(){
		wait.until(ExpectedConditions.visibilityOf(cloneTab));
		cloneTab.click();
		Reporter.log("Clone Tab is clicked", true);
		projectName.sendKeys(ProjectName);
		Thread.sleep(3000);
	}

}
