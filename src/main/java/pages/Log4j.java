package pages;

//import java.util.logging.Logger;
import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Log4j {

	public WebDriver driver;
	// Define the Logger object to create own logs
	Logger logger = Logger.getLogger(Log4j.class.getName());

	public Log4j(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void info(Object o) {
		logger.info(o.toString());
	}

}
