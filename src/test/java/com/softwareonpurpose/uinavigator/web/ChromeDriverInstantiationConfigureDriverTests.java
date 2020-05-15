package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiDriverInstantiation;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ChromeDriverInstantiationConfigureDriverTests extends TestClass {
    @Test
    public void testConfigureDriver() {
        UiDriverInstantiation instantiation = ChromeDriverInstantiation.getInstance();
        WebDriver driver = (WebDriver) instantiation.instantiateDriver();
        instantiation.configureDriver(driver);
        driver.quit();
        Assert.assertTrue(true, "Failed to configure driver without throwing an exception");
    }
}
