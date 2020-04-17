package com.softwareonpurpose.uinavigator.web;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiHostHostRelatedTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetInstance() {
        Class expected = WebUiHost.class;
        Class actual = WebUiHost.getInstance(DefaultChromeInstantiation.getInstance()).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiHost");
    }
}

