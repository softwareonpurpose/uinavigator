package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        MockView.directNav();
        Class<UiElement> expected = UiElement.class;
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final WebUiGetElementListByLocatorAttribute getBehavior =
                WebUiGetElementListByLocatorAttribute.getInstance(UiLocatorType.TAG, "select", attribute, attributeValue);
        //noinspection rawtypes
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of at least one WebUiElement");
    }
}
