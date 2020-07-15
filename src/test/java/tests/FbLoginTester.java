package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.BaseClass;
import pages.ExcelUtils;
import pages.FacebookLogin;
import pages.Log4j;
import utils.Screenshot;

//
public class FbLoginTester extends BaseClass {
	FacebookLogin fb;
	Screenshot s;
	String ud = System.getProperty("user.dir");
	String f = "\\src\\main\\resources\\testData.xlsx";
	Log4j log = new Log4j(driver);

	@BeforeClass
	public void SetUp() throws FileNotFoundException {
		driver = InvokeDriver();
		fb = new FacebookLogin(driver);
		s = new Screenshot(driver);

	}

	@Test
	public void getFBPage() throws IOException, InterruptedException {
		String fbUrl = "https://www.facebook.com/";
		fb.openFbLogin("facebook");
		Assert.assertEquals(driver.getCurrentUrl().toString(), fbUrl);
		log.info("Facebook LogIn page is opened");
		s.getScreenshot();
	}

	@Test(dataProvider = "getExcelTestData")
	public void testFailingLogin(String username, String pwd) throws IOException, InterruptedException {
		fb.credentials(username, pwd);
		Assert.assertTrue(fb.getError().isDisplayed(), "Error message is displayed");
		log.info("Error message is displayed");
		s.getScreenshot();
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	@DataProvider
	public Object[][] getExcelTestData() throws FileNotFoundException {
		System.out.println("-------------" + ud + f + "--------------------");
		Object[][] data = ExcelUtils.getTableArray(ud + f, "testdata1");
		return data;
	}

}
