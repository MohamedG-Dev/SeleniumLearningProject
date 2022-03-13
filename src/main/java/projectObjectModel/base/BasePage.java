package projectObjectModel.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

public class BasePage {
	public WebDriver driver;
	public ExtentTest eTest;
	
	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed() ? true : false;
	}
}
