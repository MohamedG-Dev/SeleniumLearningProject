package projectObjecModel.tests.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import projectObjectModel.util.Constants;
import projectObjectModel.util.ExtentReportsManager;

public class BaseTest {
	public ExtentReports eReport = ExtentReportsManager.getInstance();
	public ExtentTest eTest = null;
	public WebDriver driver;

	public void openBrowser(String browserType) {
		switch (browserType.toUpperCase()) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER);
			driver = new ChromeDriver();
			break;
		case "EDGE":
			System.setProperty("webdriver.edge.driver", Constants.EDGE_DRIVER);
			driver = new EdgeDriver();
			break;
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER);
			driver = new FirefoxDriver();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", Constants.INTERNETEXPLORER_DRIVER);
			driver = new InternetExplorerDriver();
			break;
		}
		eTest.info("Successfully launched the " + browserType + " browser");
		driver.manage().window().maximize();
		eTest.log(Status.INFO, "Browser got maximized");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void reportPass(String message) {
		eTest.pass(message);
	}

	public void reportFail(String message) {
		eTest.fail(message);
		takeScreenshot();
		Assert.fail(message);
	}

	public void takeScreenshot() {
		Date date = new Date();
		String screenshotFile = date.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(srcFile, new File("./screenshots/" + screenshotFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		eTest.info("Screenshot ->"
				+ eTest.addScreenCaptureFromPath(System.getProperty("user.dir") + "/screenshots/" + screenshotFile));
	}
}
