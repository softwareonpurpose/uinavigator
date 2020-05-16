package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        Class<UiElement> expected = UiElement.class;
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final WebGetElementListByLocatorAttribute getBehavior =
                WebGetElementListByLocatorAttribute.getInstance(UiLocatorType.TAG, "select", attribute, attributeValue, host);
        //noinspection rawtypes
        Class actual = getBehavior.execute().iterator().next().getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return a list of at least one WebUiElement");
    }
}
