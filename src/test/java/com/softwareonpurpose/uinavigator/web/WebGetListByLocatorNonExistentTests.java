package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorNonExistentTests extends TestClass {
    @Test
    public void testExecute_nonexistent() {
        String description = "Non Existent";
        int expected = 0;
        final String locatorValue = "nonexistent";
        final WebGetElementListByLocator getElement =
                WebGetElementListByLocator.getInstance(description, UiLocatorType.ID, locatorValue, UiDriverGet.getInstance());
        int actual = getElement.execute().size();
        Assert.assertEquals(actual, expected, "Failed to return an empty Collection");
    }
}
