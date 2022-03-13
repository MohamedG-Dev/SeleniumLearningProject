package projectObjectModel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import projectObjectModel.base.BasePage;
import projectObjectModel.util.Constants;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver, ExtentTest eTest) {
		this.driver = driver;
		this.eTest = eTest;
	}

	// WebElements of Login Page
	@FindBy(id = "login_id")
	public WebElement EmailOrMobileField;

	@FindBy(css="[id='nextbtn']>span")
	public WebElement NextButton;

	@FindBy(id = "password")
	public WebElement PasswordField;
	
	@FindBy(css="[id='nextbtn']>span")
	public WebElement SignInButton;

	// Reusable methods
	public boolean clickOnLogin() {
		EmailOrMobileField.sendKeys(Constants.USERNAME);
		eTest.info("username entered into the Email address field on login page");
		NextButton.click();
		eTest.log(Status.INFO, "Next button is clicked to enter the password");
		PasswordField.sendKeys(Constants.PASSWORD);
		eTest.info("passwored is entered into the password field");
		SignInButton.click();
		eTest.info("Sign in button is clicked");
		HomePage homePage=new HomePage(driver,eTest);
		PageFactory.initElements(driver, homePage);
		boolean loginStatus = homePage.verifyDisplayOfHomePage();
		return loginStatus;
		
	}
}
