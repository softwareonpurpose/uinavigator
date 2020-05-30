package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ChromeUiDriverServiceHeadlessTests extends TestClass {
    @Test
    public void testInstantiateDriver_headless() {
        //noinspection rawtypes
        Class expected = RemoteWebDriver.class;
        RemoteWebDriver chromeDriver = ChromeUiDriverService.getInstance(true).getDriver();
        //noinspection rawtypes
        Class actual = chromeDriver.getClass();
        chromeDriver.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }
}
