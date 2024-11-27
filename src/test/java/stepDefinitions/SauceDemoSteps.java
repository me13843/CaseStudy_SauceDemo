package stepDefinitions;

import java.sql.Driver;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import TestRunner.BaseTest;
import stepDefinitions.*;
import utils.ScreenshotUtility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemoSteps extends BaseTest{
	
	public WebDriver driver = DriverManager.getDriver();
	
    @Given("I navigate to the URL {string}")
    public void i_navigate_to_the_url(String url) {
    	
        driver.get(url);
        ScreenshotUtility.captureScreenshot(driver, "NavigateToURL");
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
    	
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        ScreenshotUtility.captureScreenshot(driver, "PageTitleCaptureScreenshot");
    }

    @Given("I am on the SauceDemo login page")
    public void i_am_on_the_saucedemo_login_page() {
    	
    	driver.get("https://www.saucedemo.com/");
    	ScreenshotUtility.captureScreenshot(driver, "LoginPage");
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
    	
    	driver.findElement(By.id("user-name")).sendKeys(username);
    	driver.findElement(By.id("password")).sendKeys(password);
    	ScreenshotUtility.captureScreenshot(driver, "UserNamePswdDetailsScreen");
    }

    @And("I click the login button")
    public void i_click_the_login_button() {
    	driver.findElement(By.id("login-button")).click();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());
        
    }
    
    @Given("I am logged in to the portal")
    public void i_am_logged_in_to_the_portal() {
        // Launch the browser and navigate to the login page
    	
    	driver.get("https://www.saucedemo.com/");
        
        // Enter valid credentials and log in
    	driver.findElement(By.id("user-name")).sendKeys("standard_user");
    	driver.findElement(By.id("password")).sendKeys("secret_sauce");
    	driver.findElement(By.id("login-button")).click();
        
        // Verify successful login
        boolean isLoginSuccessful = driver.findElement(By.className("title")).isDisplayed();
        Assert.assertTrue(isLoginSuccessful, "Login failed! Unable to find the products page title.");
        ScreenshotUtility.captureScreenshot(driver, "AfterLoginPage");
    }

    
    @When("I add the items {string} and {string} to the cart")
    public void i_add_the_items_to_the_cart(String item1, String item2) {
    	
    	driver.findElement(By.xpath("//div[text()='" + item1 + "']/ancestor::div[@class='inventory_item']//button")).click();
    	driver.findElement(By.xpath("//div[text()='" + item2 + "']/ancestor::div[@class='inventory_item']//button")).click();
    	ScreenshotUtility.captureScreenshot(driver, "AddItemsToCart");
    }

    @Then("the cart should display {int} items")
    public void the_cart_should_display_items(Integer itemCount) {
    	
        String actualCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(actualCount, itemCount.toString());
        ScreenshotUtility.captureScreenshot(driver, "ShoppingCartPage");
    }
    
    
    
    @Given("I have items in my cart")
    public void i_have_items_in_my_cart() {
    	
    	    	
        // Add the steps to simulate adding items to the cart
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Add "Sauce Labs Backpack" to the cart
        WebElement backpackButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button")));
        backpackButton.click();
        
        // Add "Sauce Labs Bike Light" to the cart
        WebElement bikeLightButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[text()='Sauce Labs Bike Light']/ancestor::div[@class='inventory_item']//button")));
        bikeLightButton.click();
    }

    
    @When("I click on the checkout button")
    public void i_click_on_the_checkout_button() {
    	
       	driver.findElement(By.id("shopping_cart_container")).click();
    	driver.findElement(By.id("checkout")).click();
    	ScreenshotUtility.captureScreenshot(driver, "CheckOutPage");
    }

    @And("I provide the details:")
    public void i_provide_the_details(io.cucumber.datatable.DataTable dataTable) {
    	
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        driver.findElement(By.id("first-name")).sendKeys(data.get("First Name"));
        driver.findElement(By.id("last-name")).sendKeys(data.get("Last Name"));
        driver.findElement(By.id("postal-code")).sendKeys(data.get("Zip Code"));
        ScreenshotUtility.captureScreenshot(driver, "EnterDetailsPage");
    }

    @And("I click on the continue button")
    public void i_click_on_the_continue_button() {
    	
    	driver.findElement(By.id("continue")).click();
    }
    
    @And("I click on the finish button")
    public void i_click_on_the_finish_button() {
    	
    	driver.findElement(By.id("finish")).click();
    	ScreenshotUtility.captureScreenshot(driver, "FinalPage");
    }

    @Then("the order confirmation message should be {string}")
    public void the_order_confirmation_message_should_be(String expectedMessage) {
    	
    	String actualMessage = driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(actualMessage, expectedMessage);
        ScreenshotUtility.captureScreenshot(driver, "ConfirmationPage");
        driver.quit();
    }
}

