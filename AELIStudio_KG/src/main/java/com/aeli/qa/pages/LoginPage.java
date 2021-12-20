package com.aeli.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aeli.qa.base.TestBase;

public class LoginPage extends TestBase {
	public WebDriverWait wait = new WebDriverWait(driver, 300);
	ProjectsPage projectspage = new ProjectsPage();

	@FindBy(name = "username")
	WebElement username;
	@FindBy(name = "password")
	WebElement password;
	@FindBy(xpath = "//button/span[text()='Login']")
	WebElement loginBtn;
	

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validateLogin(String UserName, String Password) throws Exception {
		// click Projects Tab and select desired project
		username.sendKeys(UserName);
		Thread.sleep(3000);
		password.sendKeys(Password);
		Thread.sleep(3000);
		loginBtn.click();
		Reporter.log("User logged in Successfully",true);
	}
}
