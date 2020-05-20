package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ChromeDriverInstantiationTests extends TestClass {
    @Test
    public void testGetHostName() {
        String expected = "chrome";
        String actual = ChromeDriverInstantiation.getInstance().getHostName();
        Assert.assertEquals(actual, expected, "Failed to return expected host name");
    }

    @Test
    public void testInstantiateDriver_default() {
        //noinspection rawtypes
        Class expected = RemoteWebDriver.class;
        RemoteWebDriver chromeDriver = ChromeDriverInstantiation.getInstance().instantiateDriver();
        //noinspection rawtypes
        Class actual = chromeDriver.getClass();
        chromeDriver.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }

    @Test
    public void testInstantiateDriver_headed() {
        //noinspection rawtypes
        Class expected = RemoteWebDriver.class;
        RemoteWebDriver chromeDriver = ChromeDriverInstantiation.getInstance(false).instantiateDriver();
        //noinspection rawtypes
        Class actual = chromeDriver.getClass();
        chromeDriver.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }

    @Test
    public void testInstantiateDriver_headless() {
        //noinspection rawtypes
        Class expected = RemoteWebDriver.class;
        RemoteWebDriver chromeDriver = ChromeDriverInstantiation.getInstance(true).instantiateDriver();
        //noinspection rawtypes
        Class actual = chromeDriver.getClass();
        chromeDriver.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }
}
