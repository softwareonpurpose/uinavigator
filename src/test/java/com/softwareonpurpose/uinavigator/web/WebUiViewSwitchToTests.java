package com.softwareonpurpose.uinavigator.web;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiViewSwitchToTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testSwitchTo_iFrame() {
        final MockViewFramed view = MockViewFramed.directNav();
        String expected = "Life Reconciled";
        String actual = view.getSiteTitle();
        Assert.assertEquals(actual, expected, "Failed to successfully switch to iframe");
    }
}
