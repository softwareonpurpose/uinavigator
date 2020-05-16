package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class TestClass {
    protected WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void terminate() {
        if (driver != null) {
            driver.quit();
        }
    }
}
