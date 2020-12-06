package com.cleartrip.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.cleartrip.qa.util.TestUtil;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;

	// instantiating properties file
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					("C:\\Users\\ACER\\eclipse_oct\\ClearTripAutomationTest\\src\\main\\java\\com\\cleartrip\\qa\\config\\config.properties"));
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// initializing the browser
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\ACER\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\ACER\\Downloads\\geckodriver-v0.27.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PageLoad_Timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.ImplicitWait, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

}
