package selenium.Learning.SeleniumLearningProject;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportExample {

	public static ExtentReports getReport() {
		Date date = new Date();
		String fileNameType = date.toString().replace(" ", "_").replace(":", "_") + ".html";
		String reportsFilePath = "./reports/" + fileNameType;
		ExtentReports extentReport = new ExtentReports();
		ExtentSparkReporter eSpark=new ExtentSparkReporter(reportsFilePath);
		extentReport.attachReporter(eSpark);
		/* The below line of Code works fine with the extent report version "2.41.2"
		 * ExtentReports extentReport = new ExtentReports(reportsFilePath, true, DisplayOrder.NEWEST_FIRST);
		 * File reportConfigFile = new File("ReportsConfig.xml");
		 * extentReport.loadConfig(reportConfigFile);
		 * extentReport.addSystemInfo("TestNG Version",
		 * "7.5").addSystemInfo("WebDriver Version", "4.1.2")
		 * .addSystemInfo("Environment", "Staging QA").addSystemInfo("Executed By",
		 * "Johm Smilga");
		 */
		return extentReport;
	}

}