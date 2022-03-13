package projectObjectModel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import projectObjectModel.base.BasePage;
import projectObjectModel.util.Constants;

public class LaunchPage extends BasePage{
	

	public LaunchPage(WebDriver driver, ExtentTest eTest) {
		this.driver = driver;
		this.eTest = eTest;
	}

	// WebElements of Launch Page
	@FindBy(className = "zh-customers")
	public WebElement Customers;

	@FindBy(className = "zh-support")
	public WebElement Support;

	@FindBy(className = "zh-contact")
	public WebElement ContactSales;

	@FindBy(className = "zh-login")
	public WebElement SignIn;

	@FindBy(className = "zh-signup")
	public WebElement SignUp;

	// Reusable methods
	public boolean goToLogin() {
		driver.get(Constants.APP_URL);
		eTest.info("Application URL: " + Constants.APP_URL + " got launched");
		SignIn.click();
		eTest.info("SignIn Option is clicked");
		LoginPage loginPage = new LoginPage(driver, eTest);
		PageFactory.initElements(driver, loginPage);
		boolean loginStatus = loginPage.clickOnLogin();
		return loginStatus;
	}
}
