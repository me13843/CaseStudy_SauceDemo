package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepDefinitions",
    plugin = {"pretty", "html:target/SauceDemoAutomationReport.html"},
    tags = "@LaunchURL or @Login or @AddToCart or @Checkout"
)
public class TestRunner extends AbstractTestNGCucumberTests {}
