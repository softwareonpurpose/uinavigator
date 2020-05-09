package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    public void testExecute_nonexistent() {
        MockView.directNav();
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 3;
        final String locatorValue = "select";
        final WebUiGetElementListByLocatorAttributeOrdinal getBehavior =
                WebUiGetElementListByLocatorAttributeOrdinal.getInstance(UiLocatorType.TAG, locatorValue, attribute, attributeValue, ordinal);
        Integer expected = 0;
        Integer actual = getBehavior.execute().size();
        Assert.assertEquals(actual, expected, "Failed to return an empty list of WebUiElement");
    }
}
