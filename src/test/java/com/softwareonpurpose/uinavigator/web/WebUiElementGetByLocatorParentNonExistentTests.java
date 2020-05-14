package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementGetByLocatorParentNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nonExistent() {
        String description = "Non Existent";
        final String locatorValue = "nonexistent";
        final WebUiElementGet getParent = null;
        //noinspection ConstantConditions
        final WebUiElementGetByLocatorParent getElement =
                WebUiElementGetByLocatorParent.getInstance(description, UiLocatorType.NAME, locatorValue, getParent);
        MockView.directNav();
        WebElement actual = getElement.execute();
        Assert.assertNull(actual, "Failed to return null for non-existent element");
    }
}
