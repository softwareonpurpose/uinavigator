package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ChromeUiDriverTests {
    @Test
    public void testGetHostName() {
        String expected = "chrome";
        String actual = ChromeUiDriver.getInstance().getHostName();
        Assert.assertEquals(actual, expected, "Failed to return expected host name");
    }

    @Test
    public void testInstantiateDriver() {
        Class expected = org.openqa.selenium.chrome.ChromeDriver.class;
        WebDriver chromeDriver = ChromeUiDriver.getInstance().instantiateDriver();
        Class actual = chromeDriver.getClass();
        chromeDriver.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }

    @Test
    public void testConfigureDriver() {
        UiDriver instantiation = ChromeUiDriver.getInstance();
        WebDriver driver = instantiation.instantiateDriver();
        instantiation.configureDriver(driver);
        driver.quit();
        Assert.assertTrue(true, "Failed to configure driver without throwing an exception");
    }
}
