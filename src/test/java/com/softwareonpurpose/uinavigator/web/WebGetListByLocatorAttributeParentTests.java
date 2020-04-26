package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorAttributeParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @SuppressWarnings("rawtypes")
    public void testExecute() {
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "form");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final WebGetListByLocatorAttributeParent getBehavior =
                WebGetListByLocatorAttributeParent.getInstance(UiLocatorType.TAG, "select", attribute, attributeValue, getParent);
        Class expected = WebUiElement.class;
        MockView.directNav();
        Class actual = getBehavior.execute().iterator().next().getClass();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
