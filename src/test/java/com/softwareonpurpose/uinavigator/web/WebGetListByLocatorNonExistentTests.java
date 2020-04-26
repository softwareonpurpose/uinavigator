package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nonexistent() {
        int expected = 0;
        int actual = WebGetListByLocator.getInstance(UiLocatorType.ID, "nonexistent").execute().size();
        Assert.assertEquals(actual, expected, "Failed to return an empty Collection");
    }
}
