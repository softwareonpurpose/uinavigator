package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverInstantiation;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ChromeDriverInstantiationTests {
    @Test
    public void testGetHostName() {
        String expected = "chrome";
        String actual = ChromeDriverInstantiation.getInstance().getHostName();
        Assert.assertEquals(actual, expected, "Failed to return expected host name");
    }

    @Test
    public void testInstantiateDriver() {
        //noinspection rawtypes
        Class expected = org.openqa.selenium.chrome.ChromeDriver.class;
        WebDriver chromeDriver = (WebDriver) ChromeDriverInstantiation.getInstance().instantiateDriver();
        //noinspection rawtypes
        Class actual = chromeDriver.getClass();
        chromeDriver.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }

    @Test
    public void testConfigureDriver() {
        UiDriverInstantiation instantiation = ChromeDriverInstantiation.getInstance();
        WebDriver driver = (WebDriver) instantiation.instantiateDriver();
        instantiation.configureDriver(driver);
        driver.quit();
        Assert.assertTrue(true, "Failed to configure driver without throwing an exception");
    }
}
