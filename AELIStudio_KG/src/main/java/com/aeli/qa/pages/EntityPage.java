package com.aeli.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.util.Messages;

public class EntityPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 300);
	ProjectsPage projectspage = new ProjectsPage();

	@FindBy(xpath = "//span[text()='Entity']")
	WebElement entityTab;
	@FindBy(xpath = "//span[text()='New Entity']")
	WebElement newEntityBtn;
	@FindBy(xpath = "//input[@name='name']")
	WebElement name;
	@FindBy(xpath="//span/input[@value='1']")
	WebElement Listtype;
	@FindBy(xpath="//input[@class='MuiInputBase-input']")
	WebElement value;
	@FindBy(xpath = "//span[text()='Save']")
	WebElement saveBtn;
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alertMessage;

	public EntityPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validateCreatingEntities(String ProjectName, String NameOfEntity,String ValueOfEntity) throws Exception {
		// click Projects Tab and select desired project
		projectspage.goToProject(ProjectName);
		// Once directed to that project click on entity tab
		wait.until(ExpectedConditions.visibilityOf(entityTab));
		entityTab.click();
		Reporter.log("Entity Tab is clicked", true);
		Thread.sleep(3000);
		newEntityBtn.click();
		Reporter.log("New Entity button is clicked", true);
		Thread.sleep(3000);
		name.sendKeys(NameOfEntity);
		Thread.sleep(3000);
		Listtype.click();
		Reporter.log("List type is selcted",true);
		Thread.sleep(3000);
		value.sendKeys(ValueOfEntity);
		Thread.sleep(3000);
		saveBtn.click();
		Reporter.log("Save button is clicked", true);
		// now comes to verification and Validate the success message
		wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.EntityCreation;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Entity not created successfully");
		Reporter.log("Entity created successfully", true);
		projectspage.validateLogOut();
	}

}
