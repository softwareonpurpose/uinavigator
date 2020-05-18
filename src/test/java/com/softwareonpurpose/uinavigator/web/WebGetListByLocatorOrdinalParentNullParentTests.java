package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorOrdinalParentNullParentTests extends TestClass {
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
}
