package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalParentTests {
    @AfterMethod
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testConstructor_bodyTagParentNull() {
        String parentDescription = "Body";
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorOrdinalParent getElement =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(parentDescription, UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<WebElementGetByLocatorOrdinalParent> expected = WebElementGetByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_bodyTag() {
        String parentDescription = "Username";
        final String parentLocatorValue = "user_name";
        final WebElementGetByLocator getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.NAME, parentLocatorValue);
        String description = "Body";
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebElementGetByLocatorOrdinalParent getElement =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<WebElementGetByLocatorOrdinalParent> expected = WebElementGetByLocatorOrdinalParent.class;
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
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorOrdinalParent getElement =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.NAME, locatorValue, ordinal, getParent);
        Class<WebElementGetByLocatorOrdinalParent> expected = WebElementGetByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }
}
