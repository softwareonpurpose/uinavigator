package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorParentNonExistentTests extends TestClass {
    @Test
    public void testExecute_nonExistent() {
        host = UiHost.getInstance();
        String description = "Non Existent";
        final String locatorValue = "nonexistent";
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorParent getElement =
                WebElementGetByLocatorParent.getInstance(description, UiLocatorType.NAME, locatorValue, getParent, UiDriverGet.getInstance());
        MockView.directNav(host);
        WebElement actual = getElement.execute();
        Assert.assertNull(actual, "Failed to return null for non-existent element");
    }
}
