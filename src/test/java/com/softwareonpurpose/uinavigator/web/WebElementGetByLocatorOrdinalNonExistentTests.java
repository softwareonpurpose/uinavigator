package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalNonExistentTests extends TestClass {
    @Test
    public void testExecute_ordinalGreaterThanList() {
        UiHost host = UiHost.getInstance();
        String description = "Label";
        final String locatorValue = "label";
        final int ordinal = 5;
        final WebElementGet getBehavior =
                WebElementGetByLocatorOrdinal.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, host);
        MockView.directNav(host);
        final WebElement actual = getBehavior.execute();
        host.quit();
        Assert.assertNull(actual, "Failed to return null when ordinal is greater than list size");
    }
}
