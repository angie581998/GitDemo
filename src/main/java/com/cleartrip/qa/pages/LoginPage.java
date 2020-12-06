package com.cleartrip.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cleartrip.qa.base.TestBase;
import com.cleartrip.qa.util.TestUtil;

public class LoginPage extends TestBase {
	@FindBy(xpath = "//span[contains(text(),'Your trips')]")
	WebElement yourtrip;

	@FindBy(xpath = "//input[@id='SignIn']")
	WebElement signin;

	@FindBy(xpath = "//input[@id='email']")
	WebElement email;

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	@FindBy(xpath = "//button[@id='signInButton']")
	WebElement signinbtn;

	@FindBy(xpath = "//span[@class='span span19 truncate']")
	WebElement userNameLabel;

	@FindBy(xpath = "//span[contains(text(),'There were errors in your submission')]")
	WebElement errormsg;

	// initialization of page objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// actions
	public String validateLoginPageTitle() {
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PageLoad_Timeout, TimeUnit.SECONDS);
		return driver.getTitle();
	}

	// to verify correct user name
	public String verifyCorrectUserName() {
		return userNameLabel.getText();
	}

	// to get error msg
	public String getErrorMsg() {
		return errormsg.getText();
	}

	// method to login
	public HomePage login(String un, String pwd) throws InterruptedException {
		yourtrip.click();
		signin.click();
		Thread.sleep(1000);
		driver.switchTo().frame("modal_window");
		email.sendKeys(un);
		password.sendKeys(pwd);
		signinbtn.click();
		Thread.sleep(5000);
		return new HomePage();
	}

}
