package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverGet;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebDriverQuitTests {
    @Test
    public void execute_nullDriver() {
        try {
            UiDriverGet getDriver = UiDriverGet.getInstance();
            WebDriverQuit driverQuit = new WebDriverQuit(getDriver);
            driverQuit.execute();
        } catch (Exception e) {
            Assert.fail("Failed to quit without throwing an Exception");
        }
    }
}
