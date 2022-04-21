package teamListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports createInstance(String path) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);

		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(path);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(path);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Nisha Dhiman");
		extent.setSystemInfo("Organization", "slidetech");
		extent.setSystemInfo("Build no", "W2A-1234");

		return extent;
	}

	

}
