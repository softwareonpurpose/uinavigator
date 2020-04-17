package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.web.mock.MockView;
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

    @Test
    public void testGetUri() {
        String expected = MockView.directNav().getUri();
        String actual = WebUiHost.getInstance().getUri();
        Assert.assertEquals(actual, expected, "Failed to return expected URI");
    }

    @Test
    public void testGetDriverName() {
        String expected = "ChromeDriver";
        String actual = WebUiHost.getInstance().getDriverName();
        Assert.assertTrue(actual.endsWith(expected), "Failed to return correct driver name");
    }
}

