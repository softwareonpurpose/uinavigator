package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorOrdinalParentTests extends TestClass {
    @Test
    public void testConstructor_bodyTagNullParent() {
        Class<WebGetElementListByLocatorOrdinalParent> expected = WebGetElementListByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = WebGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "body", 1, null, UiDriverGet.getInstance()).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_selectTagNullParent() {
        Class<WebGetElementListByLocatorOrdinalParent> expected = WebGetElementListByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = WebGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "select", 1, null, UiDriverGet.getInstance()).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_bodyTagPrentInstance() {
        String parentDescription = "Select";
        final String parentLocatorValue = "select";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, UiDriverGet.getInstance());
        Class<WebGetElementListByLocatorOrdinalParent> expected = WebGetElementListByLocatorOrdinalParent.class;
        final String locatorValue = "select";
        final int ordinal = 1;
        final WebGetElementListByLocatorOrdinalParent getElement =
                WebGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, locatorValue, ordinal, getParent, UiDriverGet.getInstance());
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }
}
