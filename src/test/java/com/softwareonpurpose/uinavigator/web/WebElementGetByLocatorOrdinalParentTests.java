package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorOrdinalParentTests extends TestClass {
    @Test
    public void testConstructor_bodyTagParentNull() {
        host = UiHost.getInstance();
        String parentDescription = "Body";
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebElementGet getParent = null;
        //noinspection ConstantConditions
        final WebElementGetByLocatorOrdinalParent getElement =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(parentDescription, UiLocatorType.TAG, locatorValue, ordinal, getParent, UiDriverGet.getInstance());
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
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.NAME, parentLocatorValue, UiDriverGet.getInstance());
        String description = "Body";
        final String locatorValue = "body";
        final int ordinal = 2;
        final WebElementGetByLocatorOrdinalParent getElement =
                WebElementGetByLocatorOrdinalParent
                        .getInstance(description, UiLocatorType.TAG, locatorValue, ordinal, getParent, UiDriverGet.getInstance());
        Class<WebElementGetByLocatorOrdinalParent> expected = WebElementGetByLocatorOrdinalParent.class;
        //noinspection rawtypes
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
                        .getInstance(description, UiLocatorType.NAME, locatorValue, ordinal, getParent, UiDriverGet.getInstance());
        Class<WebElementGetByLocatorOrdinalParent> expected = WebElementGetByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetElementByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }
}
