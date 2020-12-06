package com.cleartrip.qa.screenshot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cleartrip.qa.base.TestBase;

public class ScreenShot extends TestBase {

	public static void takeScreenshot(WebDriver driver, String FName) throws IOException {

		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("./Screenshots/" + FName + ".png"));
	}

}
