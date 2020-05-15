package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalParentExecuteTests extends TestClass {
    @Test
    public void testExecute_bodyTagParentNull() {
        host = UiHost.getInstance();
        String description = "Body";
        final String locatorValue = "body";
        final int ordinal = 1;
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorOrdinalParent getBehavior =
                WebElementGetByLocatorOrdinalParent.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent, UiDriverGet.getInstance());
        Class<RemoteWebElement> expected = RemoteWebElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().getClass();
        final String message = "Failed to return an instance of RemoteWebElement";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testExecute_ordinalNonExistent() {
        host = UiHost.getInstance();
        String parentDescription = "Form";
        WebElementGet getParent = WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, "form", UiDriverGet.getInstance());
        String description = "Label";
        final String locatorValue = "label";
        final int ordinal = 4;
        final WebElementGetByLocatorOrdinalParent getBehavior =
                WebElementGetByLocatorOrdinalParent.getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent, UiDriverGet.getInstance());
        MockView.directNav(host);
        final WebElement actual = getBehavior.execute();
        final String message = "Failed to return null when ordinal > list size";
        Assert.assertNull(actual, message);
    }
}
