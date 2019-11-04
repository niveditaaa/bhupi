package tests;


import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import org.testng.annotations.BeforeClass;

import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pages.Drawer;

import pages.LoginPage;



public class BaseClass extends ExtentReportGenerate   {
	AppiumDriver<MobileElement> driver;
	/*public ExtentHtmlReporter htmlextent = null;
	public ExtentReports report = null;
	public ExtentTest log = null;*/
	
	@BeforeClass
	public void setup() throws InterruptedException {
	    test=extent.createTest("setup");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "emulator-5554"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "7.0");
		caps.setCapability("browser_name", "Chrome");
		
		caps.setCapability("appPackage", "pegasus.driver");
		caps.setCapability("appActivity", "pegasus.driver.mvvm.auth.AuthActivity");
		
		caps.setCapability("noReset", "false");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("newCommandTimeout", 10000);
		caps.setCapability("autoGrantPermissions", true);
		caps.setCapability("autoDismissAlerts", true);
	
		try {
			  String baseURL = "http://0.0.0.0:";
			   String minorURL = "/wd/hub";
			   String port = "4723";
			   //driver = new AppiumDriver<MobileElement>(new URL(baseURL+port+minorURL), caps);	
			   driver = new AndroidDriver<MobileElement>(new URL(baseURL+port+minorURL), caps);	
			  //driver = new IOSDriver<MobileElement>(new URL(baseURL+port+minorURL), caps);	
				  
			   System.out.print("passed!!!!!!");
		} 
		
		catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			System.out.print("failed!!!!!!");
		}
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		   driver.findElement(LoginPage.companyId).sendKeys("1111");
		    
		    //Enter vehicle id
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    driver.findElement(LoginPage.vehicleId).sendKeys("11");
			
		    //enter pin number
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(LoginPage.pinNum).sendKeys("1234");
			
			//click on login button
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(LoginPage.LoginBtn).click();
			try {
			//allow the access location popup
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(LoginPage.allowAccessLocation).click();
			}
			catch(Exception e) {
				
			}
			//close the popup
			try {
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Actions action = new Actions(driver);
		    WebElement element=driver.findElement(By.className("android.widget.Button"));
		    element.click();
		    System.out.println(element);
			}
			catch(Exception e) {
				
			}
		
		
		}
	//driver and screenshotName
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		System.out.print("test123333");
	                //below line is just to append the date format with the screenshot name to avoid duplicate names 
	                String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	 TakesScreenshot ts = (TakesScreenshot) driver;
	 File source = ts.getScreenshotAs(OutputType.FILE);
	                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
	 String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
	 File finalDestination = new File(destination);
	 FileUtils.copyFile(source, finalDestination);
	                //Returns the captured file path
	 return destination;
	}

		@Test
		public void VerifyOpenDrawer() {
			//log = report.createTest("VerifyOpenDrawer");
			test=extent.createTest("VerifyOpenDrawer");
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(Drawer.hamburger).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String appname=driver.findElement(Drawer.DrawerTitle).getText();
			System.out.println(appname);
		    AssertJUnit.assertEquals( "Driver 3",appname);
		    //test.log(Status.PASS, "Test Case (failTest) Status is passed");
		}


	@Test
	   public void VerifyHomeTitle() throws Exception {
		
		test=extent.createTest("VerifyHomeTitle");
		Thread.sleep(5000);
		driver.findElement(Drawer.hamburger).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.findElement(By.id("pegasus.driver:id/tvMenuTitle")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    String homeTitle=driver.findElement(By.id("pegasus.driver:id/tvTitle")).getText();
	    System.out.println(homeTitle);
	    AssertJUnit.assertEquals( "Home",homeTitle);
	   // test.log(Status.PASS, "Test Case (failTest) Status is passed");
	
	
	}
		@Test
		public void VerifyProfileTitle() {
			test=extent.createTest("VerifyProfileTitle");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(Drawer.hamburger).click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.xpath("pegasus.driver:id/tvMenuTitle")).click();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			    String profileTitle=driver.findElement(By.id("pegasus.driver:id/tvTitle")).getText();
			    System.out.println(profileTitle);
			    AssertJUnit.assertEquals( "My Profile",profileTitle);
			   // test.log(Status.PASS, "Test Case (failTest) Status is passed");
			
		}
		
		
	/*	public static String capture(WebDriver driver,String screenShotName) throws IOException
		{
		    TakesScreenshot ts = (TakesScreenshot)driver;
		    File source = ts.getScreenshotAs(OutputType.FILE);
		    String dest = System.getProperty("user.dir") +"\\ErrorScreenshots\\"+screenShotName+".png";
		    File destination = new File(dest);
		    FileUtils.copyFile(source, destination);        
		                 
		    return dest;
		}*/
	@AfterClass
	public void teardown() {
		//log out the application
		//click on a hamburger
	/*	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(LogoutPage.hamburger).click();
		
		//click on logout button
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(LogoutPage.LogoutBtn).click();
		
		//click on confirm button
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(LogoutPage.ConfirmBtn).click();*/
		
		//close the app
		//driver.quit();
		
	}
	}
