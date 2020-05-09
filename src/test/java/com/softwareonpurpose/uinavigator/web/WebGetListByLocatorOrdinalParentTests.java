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
        Class<WebUiGetElementListByLocatorOrdinalParent> expected = WebUiGetElementListByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = WebUiGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "body", 1, null).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_selectTagNullParent() {
        Class<WebUiGetElementListByLocatorOrdinalParent> expected = WebUiGetElementListByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = WebUiGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "select", 1, null).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_bodyTagPrentInstance() {
        String parentDescription = "Select";
        final String parentLocatorValue = "select";
        WebUiGetElement getParent =
                WebUiGetElementByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue);
        Class<WebUiGetElementListByLocatorOrdinalParent> expected = WebUiGetElementListByLocatorOrdinalParent.class;
        final String locatorValue = "select";
        final int ordinal = 1;
        final WebUiGetElementListByLocatorOrdinalParent getElement =
                WebUiGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, locatorValue, ordinal, getParent);
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        Assert.assertEquals(actual, expected, message);
    }
}
