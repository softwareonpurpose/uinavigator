package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.ChromeUiDriverService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class TestClass {
    protected WebDriver driver;
    protected UiHost host;
    private ChromeUiDriverService service;

    @AfterMethod(alwaysRun = true)
    public void terminate() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass(alwaysRun = true)
    public void exit() {
        if (service != null) {
            service.quit();
        }
        if (host != null) {
            host.quit();
        }
    }

    protected UiDriverService getService() {
        if (service == null) {
            service = ChromeUiDriverService.getInstance();
        }
        return service;
    }
}
