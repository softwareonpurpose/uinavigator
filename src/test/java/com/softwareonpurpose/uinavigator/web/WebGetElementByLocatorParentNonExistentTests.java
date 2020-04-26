package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorParentNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nonExistent() {
        final String locatorValue = "nonexistent";
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorParent getElement =
                WebGetElementByLocatorParent.getInstance(UiLocatorType.NAME, locatorValue, getParent);
        MockView.directNav();
        WebElement actual = getElement.execute();
        Assert.assertNull(actual, "Failed to return null for non-existent element");
    }
}
