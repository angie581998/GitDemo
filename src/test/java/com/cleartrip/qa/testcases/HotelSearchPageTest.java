package com.cleartrip.qa.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.cleartrip.qa.util.TestNGListener;
import com.cleartrip.qa.base.TestBase;
import com.cleartrip.qa.excel.ExcelRead;
import com.cleartrip.qa.pages.HomePage;
import com.cleartrip.qa.pages.LoginPage;

@Listeners(TestNGListener.class)
public class HotelSearchPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;

	public HotelSearchPageTest() {
		super();
	}
	/*
	 * @BeforeTest public static void startTest() { report= new
	 * ExtentReports(System.getProperty("user.dir")+"/Extentreports/sngie.html");
	 * report.startTest("HotelSearchPageTest"); }
	 */

	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization(); // initialization of browser
		homePage = new HomePage();
		// homePage=loginPage.login(prop.getProperty("emailaddress"),prop.getProperty("password")
		// );
	}

	@DataProvider
	public Iterator<Object[]> getData() throws IOException {
		ArrayList<Object[]> testData = null;
		testData = ExcelRead.Location(
				"C:\\Users\\ACER\\eclipse_oct\\ClearTripAutomationTest\\src\\main\\java\\com\\cleartrip\\qa\\testdata\\clearTrip_TestData.xlsx");

		// catch(UnknownHostException e) {}
		return testData.iterator();
	}

	@Test(priority = 1, dataProvider = "getData")
	public void searchTest(String location, String checkindate, String checkoutdate)
			throws InterruptedException, IOException {

		homePage.hotellinkclick();
		homePage.search(location, checkindate, checkoutdate);
		homePage.searchbtn();
		Thread.sleep(3000);
		homePage.hotelSearch();
		boolean numbers = homePage.verifyNoOfHotels();
		Assert.assertTrue(numbers);
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	public void invalidSearchTest() throws InterruptedException, IOException {
		homePage.hotellinkclick();
		homePage.searchbtn();
		Thread.sleep(2000);
		// ScreenShot.takeScreenshot(driver,"hotelsearch");
		String msg = homePage.getErrorMsg();
		Assert.assertEquals(msg, "Sorry, but we can't continue until you fix everything that's marked in RED");

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	/*
	 * @AfterTest public void hotellist() { hotelSearchPage.fiveStarHotel(); }
	 */
}
