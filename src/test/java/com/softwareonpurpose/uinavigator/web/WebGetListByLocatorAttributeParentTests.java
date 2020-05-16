package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeParentTests extends TestClass {
    @SuppressWarnings("rawtypes")
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, host);
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final WebGetElementListByLocatorAttributeParent getBehavior =
                WebGetElementListByLocatorAttributeParent
                        .getInstance(UiLocatorType.TAG, "select", attribute, attributeValue, getParent, host);
        Class expected = UiElement.class;
        MockView.directNav(host);
        Class actual = getBehavior.execute().iterator().next().getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
