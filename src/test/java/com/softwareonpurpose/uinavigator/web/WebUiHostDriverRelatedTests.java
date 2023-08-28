package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiHostDriverRelatedTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetUri() {
        String expected = MockView.directNav().getAddress();
        String actual = WebUiHost.getInstance().getAddress();
        Assert.assertEquals(actual, expected, "Failed to return expected URI");
    }

    @Test
    public void testGetDriverName() {
        String expected = "ChromeDriver";
        String actual = WebUiHost.getInstance().getDriverName();
        Assert.assertTrue(actual.endsWith(expected), "Failed to return correct driver name");
    }
}

