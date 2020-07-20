package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Screenshot;

// using POM-page Factory design pattern
public class FacebookLogin {

	public WebDriver driver;
	WebElement Element;

	Log4j log = new Log4j(driver);

	@FindBy(xpath = "//input[@class='gLFyf gsfi']")
	WebElement search;

	@FindBy(partialLinkText = "Log In")
	WebElement login;

	@FindBy(id = "email")
	WebElement email;

	@FindBy(id = "pass")
	WebElement password;

	@FindBy(id = "loginbutton")
	WebElement loginBtn;

	@FindBy(xpath = "//div[@id='error_box']/div[1]")
	WebElement error;

	@FindBy(xpath = "//div[@role='alert']")
	WebElement errorAlert;

	@FindBy(xpath = "//h2[@class='uiHeaderTitle']")
	WebElement troubleLogIn;

	@FindBy(xpath = "//ul/li[@id='navItem_100053619095961']/a/div")
	WebElement element;

	// Constructor
	public FacebookLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// This method Opens google page
	// searches for facebook
	// and from search result then opens Facebook login page
	public WebElement openFbLogin(String data) throws InterruptedException, IOException {
		Screenshot s = new Screenshot(driver);

		try {
			search.clear();
			search.sendKeys(data);
			log.info("Facebook word is typed into search box of google");
			search.sendKeys(Keys.ENTER);
			login.click();
			log.info("Facebook login page is opened");
		} catch (Exception e) {
			Thread.sleep(3000);
			log.info("Something is failing");
			log.info(e.getMessage());
			s.getScreenshot();

		}
		return login;
	}

	// This method is used for data driven testing.
	// Login to facebook using credentials saved in testData.xlsx - Excel sheet
	public void credentials(String username, String pwd) throws IOException, InterruptedException {
		Screenshot s = new Screenshot(driver);
		driver.navigate().refresh();

		try {
			log.info("Username and password are extracted from excel sheet testData1");
			email.clear();
			email.sendKeys(username);
			log.info("UserName : " + username);
			password.clear();
			password.sendKeys(pwd);
			log.info("Password : " + pwd);
			loginBtn.click();
			log.info("After clicking login button error message should be displayed");
			Thread.sleep(10000);
		} catch (Exception e) {
			Thread.sleep(10000);
			log.info("Something is failing below is error details");
			log.info(e.getMessage());
			s.getScreenshot();
		}

	}

	public WebElement getError() {
		return error;
	}

	public WebElement getAlertError() {
		return errorAlert;
	}

	public WebElement getTroubleLoginMsg() {
		return troubleLogIn;
	}

	public WebElement getProfile() {
		return element;
	}

}
