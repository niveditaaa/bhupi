package AppiumFirstProject.Appium.com;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.CapabilityType;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
public class AndroidSetup {
	


	WebDriver driver;

	@BeforeClass
	public void setUp() throws MalformedURLException{
		
		
		//Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "emulator-5554"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "7.0");
	 
		caps.setCapability("appPackage", "pegasus.driver");
		caps.setCapability("appActivity", "pegasus.driver.mvvm.auth.AuthActivity");
		//com.flipkart.android
		//caps.setCapability("appPackage", "com.culinarystaffing.golive");
	   //caps.setCapability("appActivity", "com.culinarystaffing.golive.MainActivity");
		caps.setCapability("noReset", "false");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("newCommandTimeout", 10000);
		
		try {
			  String baseURL = "http://0.0.0.0:";
			   String minorURL = "/wd/hub";
			   String port = "4723";
			   
			  // driver = new AndroidDriver(new URL(baseURL+port+minorURL), capabilities);
			   driver = new RemoteWebDriver(new URL(baseURL+port+minorURL), caps);	
			   System.out.print("passed!!!!!!");
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
			System.out.print("failed!!!!!!");
		}
		
		
	}
		//Set up desired capabilities and pass the Android app-activity and app-package to Appium
		
		

	@Test
	public void testLoginPage() throws Exception {
		Thread.sleep(5000);
		String title=driver.findElement(By.id("pegasus.driver:id/ivAppLogo")).getText();
		System.out.print(title);
		
		//implicit waits
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//explicit waits
		/*public static void sendKeys(WebDriver driver1, WebElement element, int timeout, String value){
			new WebDriverWait(driver1, timeout).until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(value);
			}
			
			//fluent wait
			   Wait wait = new FluentWait(driver)
 
               .withTimeout(30, SECONDS)
 
               .pollingEvery(5, SECONDS)
 
               .ignoring(NoSuchElementException.class);
			*/
		
		
		
		driver.findElement(By.id("pegasus.driver:id/etCompanyId")).sendKeys("1111");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("pegasus.driver:id/etVehicleId")).sendKeys("11");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("pegasus.driver:id/etPinNo")).sendKeys("1234");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("pegasus.driver:id/btnLogin")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
		
		//assert.assertEquals(actual, expected);
	   //locate the Text on the calculator by using By.name()
	  /* WebElement two=driver.findElement(By.name("2"));
	   two.click();
	   WebElement plus=driver.findElement(By.name("+"));
	   plus.click();
	   WebElement four=driver.findElement(By.name("4"));
	   four.click();
	   WebElement equalTo=driver.findElement(By.name("="));
	   equalTo.click();
	   //locate the edit box of the calculator by using By.tagName()
	   WebElement results=driver.findElement(By.tagName("EditText"));
		//Check the calculated value on the edit box
	assert results.getText().equals("6"):"Actual value is : "+results.getText()+" did not match with expected value: 6";*/
	}

	@AfterClass
	public void teardown(){
		//close the app
		driver.quit();
	}
	}
