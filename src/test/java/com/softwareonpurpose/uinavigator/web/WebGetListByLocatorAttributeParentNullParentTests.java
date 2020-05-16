package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeParentNullParentTests extends TestClass {
    public void testExecute_nullParent() {
        UiHost host = UiHost.getInstance();
        final String attribute = "data-test";
        final String attributeValue = "view-element";
        final WebGetElementListByLocatorAttributeParent getBehavior =
                WebGetElementListByLocatorAttributeParent.getInstance(UiLocatorType.TAG, "body", attribute, attributeValue, null, host);
        //noinspection rawtypes
        Class expected = UiElement.class;
        MockView.directNav(host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().iterator().next().getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
