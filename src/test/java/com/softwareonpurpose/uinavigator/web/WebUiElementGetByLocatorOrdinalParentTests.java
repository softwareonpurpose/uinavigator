package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementGetByLocatorOrdinalParentTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_bodyTagParentNull() {
        String parentDescription = "Body";
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebUiElementGet getParent = null;
        //noinspection ConstantConditions
        final WebUiElementGetByLocatorOrdinalParent getElement =
                WebUiElementGetByLocatorOrdinalParent
                        .getInstance(parentDescription, UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<WebUiElementGetByLocatorOrdinalParent> expected = WebUiElementGetByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_bodyTag() {
        String parentDescription = "Username";
        final String parentLocatorValue = "user_name";
        final WebUiElementGetByLocator getParent =
                WebUiElementGetByLocator.getInstance(parentDescription, UiLocatorType.NAME, parentLocatorValue);
        String description = "Body";
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebUiElementGetByLocatorOrdinalParent getElement =
                WebUiElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<WebUiElementGetByLocatorOrdinalParent> expected = WebUiElementGetByLocatorOrdinalParent.class;
        Class actual;
        actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_labelTagParentNull() {
        String description = "Label";
        final String locatorValue = "label";
        final int ordinal = 2;
        final WebUiElementGet getParent = null;
        //noinspection ConstantConditions
        final WebUiElementGetByLocatorOrdinalParent getElement =
                WebUiElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.NAME, locatorValue, ordinal, getParent);
        Class<WebUiElementGetByLocatorOrdinalParent> expected = WebUiElementGetByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }
}
