package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "debug")
public class ChromeDriverInstantiationTests extends TestClass {
    @Test
    public void testGetHostName() {
        String expected = "chrome";
        String actual = ChromeUiDriverService.getInstance().getName();
        Assert.assertEquals(actual, expected, "Failed to return expected host name");
    }

    @Test
    public void testGetHostType() {
        String expected = "browser";
        String actual = ChromeUiDriverService.getInstance().getType();
        Assert.assertEquals(actual, expected, "Failed to return expected host type");
    }

    @Test
    public void testInstantiateDriver_default() {
        //noinspection rawtypes
        Class expected = RemoteWebDriver.class;
        RemoteWebDriver chromeDriver = (RemoteWebDriver) ChromeUiDriverService.getInstance().getDriver();
        //noinspection rawtypes
        Class actual = chromeDriver.getClass();
        chromeDriver.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }

    @Test
    public void testInstantiateDriver_headed() {
        //noinspection rawtypes
        Class expected = RemoteWebDriver.class;
        RemoteWebDriver chromeDriver = (RemoteWebDriver) ChromeUiDriverService.getInstance(false).getDriver();
        //noinspection rawtypes
        Class actual = chromeDriver.getClass();
        chromeDriver.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }

    @Test
    public void testInstantiateDriver_headless() {
        //noinspection rawtypes
        Class expected = RemoteWebDriver.class;
        RemoteWebDriver chromeDriver = (RemoteWebDriver) ChromeUiDriverService.getInstance(true).getDriver();
        //noinspection rawtypes
        Class actual = chromeDriver.getClass();
        chromeDriver.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }
}
