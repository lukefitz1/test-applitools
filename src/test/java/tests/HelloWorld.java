package tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.applitools.eyes.Eyes;
import com.applitools.eyes.RectangleSize;

public class HelloWorld {

	WebDriver driver;
	WebDriverWait wait;
	Eyes eyes;
	By header;
	
	@SuppressWarnings("rawtypes")
	@BeforeClass(alwaysRun = true)
	public void setUpTests() throws MalformedURLException {		
		eyes = new Eyes();
		eyes.setApiKey("95dlJqlySCR2I46tb9J0HBHdlQjAq5JSN66Y4BcmQig110"); //<-- BA gmail account
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.4");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
		
//		cap.setPlatform(Platform.MAC);
//		cap.setBrowserName("chrome");
//		cap.setVersion("50");
		
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
//		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		
		driver = eyes.open(driver, "test", "Header screenshot test - iPhone 6");
//		driver = eyes.open(driver, "Header screenshot test - Chrome", "Header screenshot test - Chrome", new RectangleSize(1280, 750));
//		driver.manage().window().setSize(new Dimension(1280, 750));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	@Test(priority=0)
	public void goToHomePage() {
		driver.get("http://www.signaturehardware.com/");
	}
	
	@Test(priority=1)
	public void waitForHeader() {
		header = By.cssSelector("#header-container > div.container");
		wait = new WebDriverWait(driver, 5);	
		wait.until(ExpectedConditions.visibilityOfElementLocated(header));
	}
	
	@Test(priority=2)
	public void headerScreenshot() {
		//WebElement e = driver.findElement(header);
		eyes.checkRegion(header);
	}
	
	@Test(priority=3)
	public void compareScreenshots() {
		eyes.close();
	}
	
	@AfterClass
	public void tearDown() {
		eyes.abortIfNotClosed();
		driver.quit();
	}
	
}
