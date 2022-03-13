package projectObjectModel.util;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsManager {
	public static ExtentReports getInstance() {
		Date date = new Date();
		String fileName = date.toString().replace(":", "_").replace(" ", "_") + ".html";
		String filePath = "./reports/" + fileName;
		ExtentReports eReports = new ExtentReports();
		ExtentSparkReporter eSpark = new ExtentSparkReporter(filePath);
		eReports.attachReporter(eSpark);
		return eReports;
	}
}
