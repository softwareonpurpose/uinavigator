package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeOrdinalParentClassLocatorTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_bodyClass() {
        String parentDescription = "Form";
        final String parentLocatorValue = "form";
        WebGetElementBehavior getParent =
                WebGetElementByLocator.getInstance(parentDescription, UiLocatorType.TAG, parentLocatorValue);
        Class<WebUiElement> expected = WebUiElement.class;
        MockView.directNav();
        final String attribute = "data-test";
        final String attributeValue = "label-element";
        final int ordinal = 1;
        final String locatorValue = "body";
        final WebGetListByLocatorAttributeOrdinalParent getBehavior =
                WebGetListByLocatorAttributeOrdinalParent.getInstance(
                        UiLocatorType.CLASS, locatorValue, attribute, attributeValue, ordinal, getParent);
        //noinspection rawtypes
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
