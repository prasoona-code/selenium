package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.BaseClass;

public class Screenshot {

	public WebDriver driver;
	BaseClass b = new BaseClass();
	String ud = System.getProperty("user.dir");

	public Screenshot(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method 3: To take screenshot listeners

	public void takeScreenshot(String result) throws IOException {
		String s = b.getDataProp("Screenshots");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(ud + s + result + "\\Screenshot.png"));
		System.out.println("Took a screenshot");
	}

	public void getScreenshot() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date d = new Date();
		String f = (dateFormat.format(d)).toString();
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(ud + "\\src\\Screenshots\\TestFinishScreenshots\\" + f + ".png"));
		System.out.println("Took a screenshot");
	}

}
