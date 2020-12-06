package com.cleartrip.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cleartrip.qa.base.TestBase;
import com.cleartrip.qa.pages.HomePage;
import com.cleartrip.qa.pages.LoginPage;
import com.cleartrip.qa.screenshot.ScreenShot;
import com.cleartrip.qa.util.TestNGListener;

@Listeners(TestNGListener.class)
public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	ScreenShot screenshot;
	HomePage homePage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Site for Booking Flights, Hotels, Packages, Trains & Local activities.");
	}

	// valid login test
	@Test(priority = 2)
	public void loginTest() throws InterruptedException {
		String email = prop.getProperty("emailaddress");
		String password = prop.getProperty("password");
		homePage = loginPage.login(email, password);
		Thread.sleep(10000);
		String label = loginPage.verifyCorrectUserName();
		Assert.assertEquals(label, prop.getProperty("emailaddress"));
	}

	// invalid login test
	@Test(priority = 3)
	public void invalidloginTest() throws InterruptedException, IOException {
		homePage = loginPage.login(prop.getProperty("emailid"), prop.getProperty("passwordd"));
		Thread.sleep(5000);
		String msg = loginPage.getErrorMsg();
		Assert.assertEquals(msg, "There were errors in your submission");
		ScreenShot.takeScreenshot(driver, "login test");
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
