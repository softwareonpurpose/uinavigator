package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalParentClassLocatorTests extends TestClass {
    @Test
    public void testExecute_bodyClass() {
        host = UiHost.getInstance();
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebElementGet getParent =
                WebElementGetByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue, UiDriverGet.getInstance());
        Class<UiElement> expected = UiElement.class;
        MockView.directNav(host);
        final String attribute = "data-test";
        final String attributeValue = "label-element";
        final int ordinal = 1;
        final String locatorValue = "body";
        final WebGetElementListByLocatorAttributeOrdinalParent getBehavior =
                WebGetElementListByLocatorAttributeOrdinalParent.getInstance(
                        UiLocatorType.CLASS, locatorValue, attribute, attributeValue, ordinal, getParent, UiDriverGet.getInstance());
        //noinspection rawtypes
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
