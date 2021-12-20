package com.aeli.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aeli.qa.base.TestBase;
import com.aeli.qa.util.Messages;

public class MappingPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 300);
	ProjectsPage projectspage=new ProjectsPage();
	LoginPage loginpage=new LoginPage();

	@FindBy(xpath = "//span[text()='Mapping']")
	WebElement mappingTab;
	@FindBy(xpath = "//span[text()='Add Mapping']")
	WebElement addMappingBtn;
	@FindBy(xpath="//input[starts-with(@id,'mui-')]")
	WebElement finalIntent;
	//@FindBy(xpath="//input[@placeholder='Search intent']")
	@FindBy(xpath="(//div[contains(@class,'MuiBox-root')]//input[@placeholder='Search intent'])[1]")
	WebElement searchIntent;
	@FindBy(xpath="//span[text()='Choose account type']/parent::div/span")
	WebElement source_Intent1;
	@FindBy(xpath="//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-7']//div[@data-simplebar='init']//div[@class='simplebar-content']")
	WebElement destination_intent;
	@FindBy(xpath="//span[text()='Create']")
	WebElement createBtn;
//(//div[contains(@class,'MuiBox-root')]//input[@placeholder='Search intent'])[3] change 1,2,3 for 3 categories
	public MappingPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validateAddMapping(String ProjectName,String FinalIntentName,String IntentToSearch) throws Exception {
		loginpage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		// click Projects Tab and select desired project
		projectspage.goToProject(ProjectName);
		Thread.sleep(10000);
		// Once directed to that project click on Mapping tab
		//wait.until(ExpectedConditions.visibilityOf(mappingTab));
		//JavascriptExecutor ja=(JavascriptExecutor)driver;
		//ja.executeScript("arguments[0].click;", mappingTab);
		mappingTab.click();
		Reporter.log("Mapping Tab is clicked", true);
		Thread.sleep(10000);
		addMappingBtn.click();
		Reporter.log("Add mapping button is clicked", true);
		Thread.sleep(3000);
		finalIntent.sendKeys(FinalIntentName);
		Thread.sleep(3000);
		searchIntent.sendKeys(IntentToSearch);
		Thread.sleep(5000);
	/*	WebElement SearchedIntent=driver.findElement(By.xpath("//div/span[text()='"+IntentToSearch+"']"));
		String IntentName=SearchedIntent.getText();
		String Actual_intent=IntentToSearch;
		String Expected_intent=IntentName;
		Assert.assertEquals(Actual_intent,Expected_intent,"Intent not found in list");
		Reporter.log(IntentName+" found in a list",true);*/
		Actions act=new Actions(driver);
		//act.dragAndDrop(source_Intent1, destination_intent).build().perform();
	    Action dragndrop= act.clickAndHold(source_Intent1).moveToElement(destination_intent).release(destination_intent).build();
		dragndrop.perform();
	    Reporter.log("drag n drop ",true);
		Thread.sleep(5000);
		createBtn.click();
		Reporter.log("Create Btn is clicked",true);
		// now comes to verification and Validate the success message
	/*	wait.until(ExpectedConditions.visibilityOf(alertMessage));
		String actual_successMsg = alertMessage.getText();
		System.out.println("Actual Message: " + actual_successMsg);
		String expected_successMsg = Messages.EntityCreation;
		System.out.println("Expected Message: " + expected_successMsg);
		Assert.assertEquals(actual_successMsg, expected_successMsg, "Entity not created successfully");
		Reporter.log("Entity created successfully", true);*/
		projectspage.validateLogOut();
	}


}
