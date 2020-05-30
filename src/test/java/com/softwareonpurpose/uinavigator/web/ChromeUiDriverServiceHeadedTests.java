package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ChromeUiDriverServiceHeadedTests extends TestClass {
    @Test
    public void testInstantiateDriver_headed() {
        //noinspection rawtypes
        Class expected = RemoteWebDriver.class;
        final ChromeUiDriverService service = ChromeUiDriverService.getInstance(false);
        RemoteWebDriver chromeDriver = service.getDriver();
        //noinspection rawtypes
        Class actual = chromeDriver.getClass();
        chromeDriver.close();
        chromeDriver.quit();
        service.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected WebDriver class");
    }
}
