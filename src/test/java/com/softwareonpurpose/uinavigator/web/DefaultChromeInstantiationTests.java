package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.DriverInstantiation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class DefaultChromeInstantiationTests {
    @Test
    public void testGetHostName() {
        String expected = "chrome";
        String actual = DefaultChromeInstantiation.getInstance().getHostName();
        Assert.assertEquals(actual, expected, "Failed to return expected host name");
    }

    @Test
    public void testInstantiateDriver() {
        Class expected = ChromeDriver.class;
        WebDriver chromeDriver = DefaultChromeInstantiation.getInstance().instantiateDriver();
        Class actual = chromeDriver.getClass();
        chromeDriver.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }

    @Test
    public void testConfigureDriver() {
        DriverInstantiation instantiation = DefaultChromeInstantiation.getInstance();
        WebDriver driver = instantiation.instantiateDriver();
        instantiation.configureDriver(driver);
        driver.quit();
        Assert.assertTrue(true, "Failed to configure driver without throwing an exception");
    }
}
