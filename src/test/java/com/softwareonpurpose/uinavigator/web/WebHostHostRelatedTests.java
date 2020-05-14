package com.softwareonpurpose.uinavigator.web;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebHostHostRelatedTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testGetInstance() {
        Class expected = WebHost.class;
        Class actual = WebHost.getInstance(ChromeDriverInstantiation.getInstance()).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiHost");
    }
}

