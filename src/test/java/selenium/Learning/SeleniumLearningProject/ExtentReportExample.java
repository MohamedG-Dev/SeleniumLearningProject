package selenium.Learning.SeleniumLearningProject;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportExample {

	public static ExtentReports getReport() {
		Date date = new Date();
		String fileNameType = date.toString().replace(" ", "_").replace(":", "_") + ".html";
		String reportsFilePath = "./reports/" + fileNameType;
		ExtentReports extentReport = new ExtentReports(reportsFilePath, true, DisplayOrder.NEWEST_FIRST);
		File reportConfigFile = new File("ReportsConfig.xml");
		extentReport.loadConfig(reportConfigFile);
		extentReport.addSystemInfo("TestNG Version", "7.5").addSystemInfo("WebDriver Version", "4.1.2")
				.addSystemInfo("Environment", "Staging QA").addSystemInfo("Executed By", "Johm Smilga");
		return extentReport;
	}

}