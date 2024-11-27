package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    // The DataProvider method supplies the username and password for login
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            { "standard_user", "secret_sauce" }, // Set 1 of credentials
            { "locked_out_user", "secret_sauce" }, // Set 2 (if you want to test other user types)
            { "problem_user", "secret_sauce" }  // Set 3 (example)
        };
    }
}
