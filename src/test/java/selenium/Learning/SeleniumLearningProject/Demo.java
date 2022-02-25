package selenium.Learning.SeleniumLearningProject;

import java.time.Duration;
import java.util.logging.Logger;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Demo {
	ExtentReports eReport;
	ExtentTest eTest;
	@Test
	public void LoginIn() {
		eReport = ExtentReportExample.getReport();
		eTest = eReport.startTest("Demo Testing started");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	//	WebDriverWait wait=new 
		eTest.log(LogStatus.INFO,"Chrome Browser is launched");
		driver.manage().window().maximize();
		eTest.log(LogStatus.INFO,"Chrome Browser is maximized");
		driver.get("http://omayo.blogspot.com/");
		eTest.log(LogStatus.INFO,"Omayo blogspot is navigated");
		String expectedText = "PracticeAutomationHere";
		String actualText = driver.findElement(By.id("pah")).getText();
		if (actualText.equals(expectedText)) {
			eTest.log(LogStatus.PASS,"Required text is displayed on the application");
		} else {
			eTest.log(LogStatus.FAIL,"Required text is not displayed on the application");
			Assert.fail("Required text is not displayed on the application");
		}
	}
	@AfterMethod
	public void closureTest() {
		eReport.endTest(eTest);
		eReport.flush();
	}
}
