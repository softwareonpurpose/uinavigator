package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ChromeUiDriverServiceTests extends TestClass {
    @Test
    public void testGetHostName() {
        String expected = "chrome";
        final ChromeUiDriverService driverService = ChromeUiDriverService.getInstance();
        String actual = driverService.getName();
        driverService.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected host name");
    }

    @Test
    public void testGetHostType() {
        String expected = "browser";
        final ChromeUiDriverService driverService = ChromeUiDriverService.getInstance();
        String actual = driverService.getType();
        driverService.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected host type");
    }

    @Test
    public void testInstantiateDriver_default() {
        //noinspection rawtypes
        Class expected = RemoteWebDriver.class;
        RemoteWebDriver chromeDriver = (RemoteWebDriver) getService().getDriver();
        //noinspection rawtypes
        Class actual = chromeDriver.getClass();
        chromeDriver.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }
}
