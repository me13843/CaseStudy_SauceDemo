package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtility {

    // Method to capture screenshot
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        // Create a timestamped name for the screenshot
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        
        // Take the screenshot and store it in a temporary file
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Define the destination path where screenshots will be saved
        String destinationPath = "screenshots/" + fileName;
        
        try {
            // Save the screenshot to the destination folder
            FileUtils.copyFile(srcFile, new File(destinationPath));
            System.out.println("Screenshot saved at: " + destinationPath);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
        
        // Return the destination path of the screenshot
        return destinationPath;
    }
}

