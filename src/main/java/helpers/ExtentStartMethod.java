package helpers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentStartMethod {

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	final String FileName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Calendar.getInstance().getTime());

	@Test
	public void startEmuWithServices() {
		File f = new File(System.getProperty("user.home") + "/Documents/ExtentReport");
		if (!f.exists()) {
			f.mkdirs();
		}
		htmlReporter = new ExtentHtmlReporter(new File(
				System.getProperty("user.home") + "/Documents/ExtentReport" + "/extentReport_" + FileName + ".html"));
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setReportName("API backEnd TestCase");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		extent.setSystemInfo("Environment", "QA-Test");
		extent.setSystemInfo("Data used for testing", "Seed Data");
	}
}
