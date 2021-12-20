package com.aeli.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aeli.qa.base.TestBase;

public class PredictionPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 300);
	ProjectsPage projectspage=new ProjectsPage();
	LoginPage loginpage=new LoginPage();

	@FindBy(xpath = "//span[text()='Prediction']")
	WebElement predictionTab;
	@FindBy(xpath = "//span[text()='Stop']")
	WebElement StopBtn;
	@FindBy(xpath = "//span[text()='Start']")
	WebElement StartBtn;
	@FindBy(xpath="//div/h6/span")
	WebElement engineStatus;
	@FindBy(xpath="//input[@placeholder='Enter utterance to test!']")
	WebElement utteranceToTest;
	@FindBy(xpath="//div[@class='MuiInputAdornment-root MuiInputAdornment-positionEnd']/*[@title='Test']")
	WebElement testBtn;
	@FindBy(xpath="//pre[@id='predictionResult']")
	WebElement resultFile; 
	
//(//div[contains(@class,'MuiBox-root')]//input[@placeholder='Search intent'])[3] change 1,2,3 for 3 categories
	public PredictionPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validateFinalIntent(String ProjectName,String UtteranceToTest, String ExpectedFinalIntent) throws Exception {
		loginpage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		// click Projects Tab and select desired project
		projectspage.goToProject(ProjectName);
		Thread.sleep(10000);
		// Once directed to that project click on Prediction tab
		predictionTab.click();
		Reporter.log("Prediction Tab is clicked", true);
		Thread.sleep(3000);
		String statusOfEngine=engineStatus.getText();
		Reporter.log("Status of Engine is :"+statusOfEngine,true);
		if(statusOfEngine.equals("Not Running")) {
			StartBtn.click();
			Reporter.log("Clicked on start button,engine starting",true);
		} else {
			//StopBtn.click();
			Reporter.log("Prediction engine is already in Running state",true);
		}
		Thread.sleep(18000);	
		utteranceToTest.sendKeys(UtteranceToTest);
		Thread.sleep(5000);
		testBtn.click();
		Reporter.log("Test button is clicked",true);
		Thread.sleep(3000);
		String Result=resultFile.getText();
		Reporter.log("Resulted Json File is: "+Result,true);
		//check presence of final intent
		if(Result.contains("final_intent")) {
			Reporter.log("final intent is present",true);
			//find position of it
			int position=Result.indexOf("final_intent");
			Reporter.log("Position of final intent in result file is: "+position,true);
			//save intent value into substring and then validate
			String intentValue=Result.substring(position+14);
			Reporter.log("final_intent in result file is: "+intentValue,true);
			String actual_finalIntent=intentValue.replace("\"","").replace("}","").replace(" ","").replace("\n","");
		//	String actual_finalIntent2=actual_finalIntent1.replace(" }","");
			Reporter.log("Actual final_intent is: "+actual_finalIntent,true);
			String expected_finalIntent=ExpectedFinalIntent;
			Reporter.log("Expected final_intent is: "+expected_finalIntent,true);
			Assert.assertEquals(actual_finalIntent,expected_finalIntent,"Final intent does not match");
			Reporter.log("Final intent match",true);
		} else {
			Reporter.log("final intent is not present",true);
		}
		projectspage.validateLogOut();
	}

}
