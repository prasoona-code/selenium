package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.BaseClass;
import pages.FacebookLogin;
import pages.Log4j;
import utils.Screenshot;

//
public class FbLoginTester extends BaseClass {
	FacebookLogin fb;
	Screenshot s;
	String ud = System.getProperty("user.dir");
	String f = "\\src\\main\\resources\\testData.xlsx";
	String fbUrl = "https://www.facebook.com/";
	Log4j log = new Log4j(driver);

	@BeforeClass
	public void SetUp() throws FileNotFoundException {
		driver = InvokeDriver();
		fb = new FacebookLogin(driver);
		s = new Screenshot(driver);

	}

	@Test
	public void getFBPage() throws IOException, InterruptedException {

		fb.openFbLogin("facebook");
		Assert.assertEquals(driver.getCurrentUrl().toString(), fbUrl);
		log.info("Facebook LogIn page is opened");
		s.getScreenshot();
	}

	@Test(dataProvider = "getExcelTestData")
	public void testFailingLogin(String username, String pwd) throws IOException, InterruptedException {
		String logInA = getDataProp("loginAttempt"); // login attempt fail partial url
		// String SuccessfulLogin = getDataProp("loginSuccess");
		String loginAttemptUrl = fbUrl + logInA;
		// String loginSuccessUrl = fbUrl + SuccessfulLogin;
		fb.credentials(username, pwd);
		try {
			if (fb.getError().isDisplayed()) {
				Assert.assertTrue(fb.getError().isDisplayed());
				log.info("Error message is displayed");
				s.getScreenshot();
			} else if (fb.getAlertError().isDisplayed()) {
				Assert.assertTrue(fb.getAlertError().isDisplayed());
				log.info("Error Alert for username/Password is displayed");
				s.getScreenshot();
			} else if (fb.getProfile().isDisplayed()) {
				s.getScreenshot();
			} else if (driver.getCurrentUrl().matches(loginAttemptUrl)) {
				if (fb.getTroubleLoginMsg().isDisplayed()) {
					Assert.assertTrue(fb.getTroubleLoginMsg().isDisplayed());
					log.info("------" + fb.getTroubleLoginMsg().getText());
					s.getScreenshot();
				}
			} else
				log.info("No error message is displayed");
		} catch (NoSuchElementException e) {
			log.info("----" + e.getMessage() + "------");
		}
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	@DataProvider
	public Object[][] getExcelTestData() throws FileNotFoundException {
		System.out.println("-------------" + ud + f + "--------------------");
		Object[][] data = utils.ExcelUtils.getTableArray(ud + f, "testdata1");
		return data;
	}

}
