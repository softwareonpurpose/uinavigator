package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiGetElementByLocatorOrdinalParentTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_bodyTagParentNull() {
        String parentDescription = "Body";
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebUiGetElement getParent = null;
        //noinspection ConstantConditions
        final WebUiGetElementByLocatorOrdinalParent getElement =
                WebUiGetElementByLocatorOrdinalParent
                        .getInstance(parentDescription, UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<WebUiGetElementByLocatorOrdinalParent> expected = WebUiGetElementByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_bodyTag() {
        String parentDescription = "Username";
        final String parentLocatorValue = "user_name";
        final WebUiGetElementByLocator getParent =
                WebUiGetElementByLocator.getInstance(parentDescription, UiLocatorType.NAME, parentLocatorValue);
        String description = "Body";
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebUiGetElementByLocatorOrdinalParent getElement =
                WebUiGetElementByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<WebUiGetElementByLocatorOrdinalParent> expected = WebUiGetElementByLocatorOrdinalParent.class;
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
        final WebUiGetElement getParent = null;
        //noinspection ConstantConditions
        final WebUiGetElementByLocatorOrdinalParent getElement =
                WebUiGetElementByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.NAME, locatorValue, ordinal, getParent);
        Class<WebUiGetElementByLocatorOrdinalParent> expected = WebUiGetElementByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }
}
