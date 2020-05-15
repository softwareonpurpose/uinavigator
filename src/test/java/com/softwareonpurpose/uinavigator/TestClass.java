package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class TestClass {
    protected WebDriver driver;
    protected UiHost host;

    @AfterMethod(alwaysRun = true)
    public void terminate() {
        if (host != null) {
            host.quit();
        }
        UiDriverGet.quit();
        if (driver != null) {
            driver.quit();
        }
    }
}
