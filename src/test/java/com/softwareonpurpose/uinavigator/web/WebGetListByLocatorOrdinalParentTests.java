package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorOrdinalParentTests extends TestClass {
    @Test
    public void testConstructor_bodyTagNullParent() {
        UiHost host = UiHost.getInstance();
        Class<WebGetElementListByLocatorOrdinalParent> expected = WebGetElementListByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = WebGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "body", 1, null, host).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_selectTagNullParent() {
        UiHost host = UiHost.getInstance();
        Class<WebGetElementListByLocatorOrdinalParent> expected = WebGetElementListByLocatorOrdinalParent.class;
        //noinspection rawtypes
        Class actual = WebGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, "select", 1, null, host).getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testConstructor_bodyTagPrentInstance() {
        UiHost host = UiHost.getInstance();
        String parentDescription = "Select";
        final String parentLocatorValue = "select";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        Class<WebGetElementListByLocatorOrdinalParent> expected = WebGetElementListByLocatorOrdinalParent.class;
        final String locatorValue = "select";
        final int ordinal = 1;
        final WebGetElementListByLocatorOrdinalParent getElement =
                WebGetElementListByLocatorOrdinalParent.getInstance(UiLocatorType.TAG, locatorValue, ordinal, getParent, host);
        //noinspection rawtypes
        Class actual = getElement.getClass();
        final String message = "Failed to return an instance of WebGetListByLocatorOrdinalParent";
        host.quit();
        Assert.assertEquals(actual, expected, message);
    }
}
