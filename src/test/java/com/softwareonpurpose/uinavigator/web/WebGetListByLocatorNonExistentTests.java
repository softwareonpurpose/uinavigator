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
        String description = "Non Existent";
        int expected = 0;
        final String locatorValue = "nonexistent";
        final WebGetListByLocator getElement =
                WebGetListByLocator.getInstance(description, UiLocatorType.ID, locatorValue);
        int actual = getElement.execute().size();
        Assert.assertEquals(actual, expected, "Failed to return an empty Collection");
    }
}
