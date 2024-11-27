package TestRunner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseTest {

    protected WebDriver driver;
    protected String baseUrl = "https://www.saucedemo.com/";

    @BeforeSuite
    public void setUp() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        // Initialize base URL
        System.out.println("Setting up the test suite and initializing the base URL.");
        driver.manage().window().maximize();
        
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Close the browser after the tests
        }
    }
}

