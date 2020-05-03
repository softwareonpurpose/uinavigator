package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetElementByLocatorOrdinalParentTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_bodyTagParentNull() {
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorOrdinalParent getElement =
                WebGetElementByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<WebGetElementByLocatorOrdinalParent> expected = WebGetElementByLocatorOrdinalParent.class;
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_bodyTag() {
        final String parentLocatorValue = "user_name";
        final WebGetElementByLocator getParent =
                WebGetElementByLocator.getInstance(UiLocatorType.NAME, parentLocatorValue);
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebGetElementByLocatorOrdinalParent getElement =
                WebGetElementByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, locatorValue, ordinal, getParent);
        Class<WebGetElementByLocatorOrdinalParent> expected = WebGetElementByLocatorOrdinalParent.class;
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_labelTagParentNull() {
        final String locatorValue = "label";
        final int ordinal = 2;
        final WebGetElementBehavior getParent = null;
        //noinspection ConstantConditions
        final WebGetElementByLocatorOrdinalParent getElement =
                WebGetElementByLocatorOrdinalParent.getInstance(UiLocatorType.NAME, locatorValue, ordinal, getParent);
        Class<WebGetElementByLocatorOrdinalParent> expected = WebGetElementByLocatorOrdinalParent.class;
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }
}
