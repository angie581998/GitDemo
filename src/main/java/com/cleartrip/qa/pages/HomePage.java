package com.cleartrip.qa.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cleartrip.qa.base.TestBase;
import com.cleartrip.qa.excel.ExcelWrite;

public class HomePage extends TestBase {
	@FindBy(xpath = "//li[@id='hotelLink']//a[@href='/hotels']")
	WebElement hotelLink;

	@FindBy(xpath = "//input[@id='Tags']")
	WebElement searchWhere;

	@FindBy(xpath = "//ul[@id='ui-id-1']//li//a")
	List<WebElement> elementPlacelist;

	@FindBy(xpath = "//input[@id='CheckInDate']")
	WebElement checkinDate;

	@FindBy(xpath = "//a[@class='ui-state-default ']")
	List<WebElement> elementlistDate;

	@FindBy(xpath = "//span[@class='ui-datepicker-month']")
	WebElement month;

	@FindBy(xpath = "//span[@class='ui-datepicker-year']")
	WebElement year;

	@FindBy(xpath = "//a[@class='nextMonth ']")
	WebElement nextbtn;

	@FindBy(xpath = "//input[@id='CheckOutDate']")
	WebElement checkOutDate;

	@FindBy(xpath = "//select[@id='travellersOnhome']")
	WebElement selectTravellers;

	@FindBy(xpath = "//input[@id='SearchHotelsButton']")
	WebElement searchbtn;

	@FindBy(xpath = "//span[text()='18' and @class='showingCount']")
	WebElement noOfHotels;

	@FindBy(xpath = "//div[@id='homeErrorMessage']")
	WebElement errorMsg;

	@FindBy(xpath = "//input[@id='1_15']//parent::label//child::div")
	WebElement fiveStarBox;

	@FindBy(xpath = "//a[@data-hash='#highlights']")
	List<WebElement> listOfHotels;

	// initiating page factory
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	// clicking on hotel link
	public void hotellinkclick() {
		hotelLink.click();
	}

	/// to fill hotel search field
	public void search(String location, String checkindate, String checkoutdate)
			throws InterruptedException, IOException {
		searchWhere.click();
		searchWhere.sendKeys(location);
		for (int i = 0; i < elementPlacelist.size(); i++) {
			if (elementPlacelist.get(i).getText().startsWith(location)) {
				elementPlacelist.get(i).click();
				break;
			}
		}
		String[] arr = checkindate.split(" ");
		try {
			checkinDate.click();
		} catch (StaleElementReferenceException e) {
		}
		try {
			while (true) {
				String month1 = month.getText();
				System.out.println(month1);
				String year1 = year.getText();
				System.out.println(year1);
				if (month1.equals(arr[1]) && year1.equals(arr[2])) {
					break;
				} else {
					nextbtn.click();
				}
			}
			for (WebElement ele : elementlistDate) {
				ele.getText();
				if (arr[0].equals(ele.getText())) {
					ele.click();
				}

			}
		} catch (StaleElementReferenceException e) {
		}

		try {
			checkOutDate.click();
		} catch (StaleElementReferenceException e) {
		}
		String[] arr1 = checkoutdate.split(" ");
		// checkinDate.click();
		Thread.sleep(5000);
		try {
			while (true) {
				String month1 = month.getText();
				String year1 = year.getText();
				if (month1.equals(arr1[1]) && year1.equals(arr1[2])) {
					break;
				} else {
					nextbtn.click();
				}
			}
			for (WebElement ele : elementlistDate) {
				ele.getText();
				if (arr1[0].equals(ele.getText())) {
					ele.click();
				}
			}
		} catch (StaleElementReferenceException e) {
		}

		Select select = new Select(selectTravellers);
		select.selectByVisibleText("1 room, 1 adult");
	}

	public void searchbtn() throws InterruptedException {
		searchbtn.click();
		Thread.sleep(10000);
	}

	public void hotelSearch() throws InterruptedException, FileNotFoundException {
		fiveStarBox.click();
		Thread.sleep(5000);
		try {
			Object lastHeight = ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(2000);
				Object newHeight = ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
				if (newHeight.equals(lastHeight)) {
					break;
				}
				lastHeight = newHeight;
				List<String> HotelNamesList = new ArrayList<>();
				{
					for (WebElement HNames : listOfHotels) {
						HotelNamesList.add(HNames.getText());
						HNames.getText();
					}
					ExcelWrite.hotelNames(HotelNamesList);
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyNoOfHotels() {
		return noOfHotels.isDisplayed();
	}

	public String getErrorMsg() {
		return errorMsg.getText();
	}
}
