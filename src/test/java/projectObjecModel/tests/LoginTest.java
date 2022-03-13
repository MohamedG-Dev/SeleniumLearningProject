package projectObjecModel.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import projectObjecModel.tests.base.BaseTest;
import projectObjectModel.pages.LaunchPage;
import projectObjectModel.util.Constants;

public class LoginTest extends BaseTest {

	@Test
	public void testLogin() {
		eTest = eReport.createTest("Login Test");
		eTest.log(Status.INFO, "Login Test has started");
		openBrowser(Constants.BROWSER_TYPE);
		LaunchPage launchPage = new LaunchPage(driver, eTest);
		PageFactory.initElements(driver, launchPage);
		boolean loginStatus = launchPage.goToLogin();
		if (loginStatus)
			reportPass("Login Test Passed");
		else
			reportFail("Login Test Failed");

	}

	@AfterMethod
	public void testClosure() {
		if (eReport != null) {
			eReport.flush();
		}
		if (driver != null) {
			driver.quit();
		}
	}
}
