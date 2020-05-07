package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorOrdinalParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testConstructor_bodyTagNullParent() {
        Class<WebGetListByLocatorOrdinalParent> expected = WebGetListByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = WebGetListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "body", 1, null).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_selectTagNullParent() {
        Class<WebGetListByLocatorOrdinalParent> expected = WebGetListByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = WebGetListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "select", 1, null).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_bodyTagPrentInstance() {
        String parentDescription = "Select";
        final String parentLocatorValue = "select";
        WebGetElementBehavior getParent =
                WebGetElementByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue);
        Class<WebGetListByLocatorOrdinalParent> expected = WebGetListByLocatorOrdinalParent.class;
        final String locatorValue = "select";
        final int ordinal = 1;
        final WebGetListByLocatorOrdinalParent getElement =
                WebGetListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, locatorValue, ordinal, getParent);
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }
}
