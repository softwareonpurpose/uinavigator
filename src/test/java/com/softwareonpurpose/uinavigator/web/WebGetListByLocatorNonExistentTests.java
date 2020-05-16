package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorNonExistentTests extends TestClass {
    @Test
    public void testExecute_nonexistent() {
        UiHost host = UiHost.getInstance();
        String description = "Non Existent";
        int expected = 0;
        final String locatorValue = "nonexistent";
        final WebGetElementListByLocator getElement =
                WebGetElementListByLocator.getInstance(description, UiLocatorType.ID, locatorValue, host);
        int actual = getElement.execute().size();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an empty Collection");
    }
}
