package projectObjectModel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

import projectObjectModel.base.BasePage;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver, ExtentTest eTest) {
		this.driver = driver;
		this.eTest = eTest;
	}

	// WebElements of Home Page
	@FindBy(css = "span[class^='_logo-books']")
	public WebElement Calendar;

	@FindBy(css = "span[class^='_logo-chat']")
	public WebElement Cliq;

	@FindBy(css = "span[class^='_logo-mail']")
	public WebElement Mail;

	@FindBy(css = "span[class^='_logo-crm']")
	public WebElement CRM;

	@FindBy(css = "span[class^='_logo-books']")
	public WebElement Books;

	// Reusable method to validate Login
	public boolean verifyDisplayOfHomePage() {
		return isElementDisplayed(CRM);
	}
}
