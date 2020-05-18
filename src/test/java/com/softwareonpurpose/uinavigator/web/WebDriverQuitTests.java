package com.softwareonpurpose.uinavigator.web;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebDriverQuitTests {
    @Test
    public void execute_nullDriver() {
        try {
            WebDriverQuit driverQuit = new WebDriverQuit(null);
            driverQuit.execute();
        } catch (Exception e) {
            Assert.fail("Failed to quit without throwing an Exception");
        }
    }
}
