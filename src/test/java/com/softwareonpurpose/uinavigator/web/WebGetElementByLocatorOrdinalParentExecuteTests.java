package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorOrdinalParentExecuteTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_bodyTagParentNull() {
        String description = "Body";
        final String locatorValue = "body";
        final int ordinal = 1;
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorOrdinalParent getBehavior =
                WebGetElementByLocatorOrdinalParent.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav();
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of RemoteWebElement";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_ordinalNonExistent() {
        String parentDescription = "Form";
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(parentDescription, UiLocatorType.TAG, "form");
        String description = "Label";
        final String locatorValue = "label";
        final int ordinal = 4;
        final WebGetElementByLocatorOrdinalParent getBehavior =
                WebGetElementByLocatorOrdinalParent.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent);
        MockView.directNav();
        final WebElement actual = getBehavior.execute();
        final String message = "Failed to return null when ordinal > list size";
        Assert.assertNull(actual, message);
    }
}
