package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ExtentReportGenerate {
public static ExtentHtmlReporter htmlReporter;
public static ExtentReports extent;
public static ExtentTest test;
public static Properties prop;
WebDriver driver;

@BeforeSuite
public void setUp() {
//prop = TestListener.getPropertiesData();
htmlReporter = new ExtentHtmlReporter("D:\\Extent_new\\demo.html");
extent = new ExtentReports();
extent.attachReporter(htmlReporter);
extent.setSystemInfo("OS", "Window10");
extent.setSystemInfo("Host Name", "windows10");
extent.setSystemInfo("Environment", "hello");
extent.setSystemInfo("User Name", "nivedita");
htmlReporter.config().setChartVisibilityOnOpen(true);
htmlReporter.config().setDocumentTitle("ReportbyExtentReport");
htmlReporter.config().setReportName("My Own Report");
htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
htmlReporter.config().setTheme(Theme.STANDARD);
}

	 
@AfterMethod
public void getResult(ITestResult result) throws Exception{
if (result.getStatus() == ITestResult.FAILURE) {
	System.out.print(result.getName());
test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
ExtentColor.RED));
test.log(Status.FAIL, "Test Case Failed is "+result.getName());
test.log(Status.FAIL, "Test Case Failed is "+result.getThrowable());
//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
                       //We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 
                       String screenshotPath = BaseClass.getScreenshot(driver, result.getName());
//To add it in the extent report 
                       test.log(Status.FAIL, (Markup) test.addScreenCaptureFromPath(screenshotPath));
                       test.fail(result.getThrowable());
}

else if(result.getStatus() == ITestResult.SKIP){
	test.log(Status.SKIP, "Test Case Skipped is "+result.getName());

test.fail(result.getThrowable());


} 



else if (result.getStatus() == ITestResult.SUCCESS) {
test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));

} else {
test.log(Status.SKIP,
MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
test.skip(result.getThrowable());
}
//extent.endTest(test);
}


@AfterSuite
public void tearDown() {
extent.flush();
extent.close();
}
}

