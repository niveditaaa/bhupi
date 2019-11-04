package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityLibrary {
	
	public static void ExplicitWait(WebDriver driver1, WebElement element, int timeout, String value){
		new WebDriverWait(driver1, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
		}

}
