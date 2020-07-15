package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass {
	public WebDriver driver;
	// storing user directory info in String ud
	private String ud = System.getProperty("user.dir");
	Log4j log = new Log4j(driver);

	// Methods
	// Method 1: To invoke Driver
	public WebDriver InvokeDriver() throws FileNotFoundException {
		// print user directory in console
		System.out.println(ud);
		/*
		 * use getDataProp method to get values of Strings browserName and url from data
		 * properties
		 */
		String browserName = getDataProp("browser");
		String url = getDataProp("url");
		// Invoking chromedriver
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ud + "\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			log.logger.info("Chrome opened");
		}
		// invoking FireFox driver
		else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ud + "\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.logger.info("firefox opened");
		}
		// Invoking Internet Explorer Driver
		else if (browserName.equals("IE")) {
			System.setProperty(" webdriver.ie.driver", ud + "\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.logger.info("Internet Explorer opened");
		}
		// To maximize the window
		driver.manage().window().maximize();
		// to open url
		driver.get(url);
		log.logger.info("Application Launched");
		// Timeouts-Implicit wait time
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;

	}

	// Method 2: To get data properties from data.properties file which will be used for entire automation testing

	public String getDataProp(String key) throws FileNotFoundException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(ud + "\\src\\main\\resources\\data.properties");
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(key);

	}

}
