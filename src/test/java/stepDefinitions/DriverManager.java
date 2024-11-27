package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Scenario;
import utils.ScreenshotUtility;

public class DriverManager {
	    private static WebDriver driver;

	    // Initialize WebDriver only once
	    public static WebDriver getDriver() {
	        if (driver == null) {
	            driver = new ChromeDriver();
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	            driver.manage().window().maximize();
	        }
	        return driver;
	    }

	    // Quit the WebDriver at the end of the tests
	    public static void quitDriver(Scenario scenario) {
	    	
	    	if (scenario.isFailed()) {
	            // Capture screenshot and attach it to the report
	            String screenshotPath = ScreenshotUtility.captureScreenshot(driver, scenario.getName());
	            
	            // Attach the screenshot to the report (if using Cucumber reports or Allure)
	            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	            scenario.attach(screenshot, "image/png", scenario.getName());
	        }
	        if (driver != null) {
	            driver.quit();
	            driver = null;
	        }
	    }
	}


