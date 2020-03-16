package constants;

import static org.testng.Assert.assertTrue;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class StartEmu {
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	final String FileName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Calendar.getInstance().getTime());

	
	@Test
	public void startEmuWithServices()
	{
		File f = new File(System.getProperty("user.home")+"/Documents/SGExtentReport");
		System.out.println(f+"---------------------");
		if(!f.exists())
		{f.mkdirs();}
		htmlReporter = new ExtentHtmlReporter(
				new File(System.getProperty("user.home")+"/Documents/SGExtentReport"+"/extentReport_" + FileName + ".html"));
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setReportName("Sport Guru Sanity Test Cases");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		extent.setSystemInfo("Environment", "QA-Test");
		extent.setSystemInfo("Data used for testing", "Seed Data");
	}
}
